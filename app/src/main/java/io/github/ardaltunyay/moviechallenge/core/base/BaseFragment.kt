package io.github.ardaltunyay.moviechallenge.core.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import io.github.ardaltunyay.moviechallenge.R
import io.github.ardaltunyay.moviechallenge.core.extensions.snackbar

abstract class BaseFragment<VM : BaseViewModel, DB : ViewDataBinding>(@LayoutRes private val layoutId: Int) :
    Fragment() {

    protected lateinit var binding: DB

    abstract val viewModel: VM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(layoutInflater, layoutId, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeBaseStatus()
    }

    private fun observeBaseStatus() {
        viewModel.errorState.observe(viewLifecycleOwner) { status ->
            val message = status.exception.message ?: getString(R.string.error_general_message)
            snackbar(binding.root, message, Snackbar.LENGTH_LONG)
        }
    }

}