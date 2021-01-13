package io.github.ardaltunyay.moviechallenge.ui.search_movies

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.navigation.fragment.findNavController
import io.github.ardaltunyay.moviechallenge.R
import io.github.ardaltunyay.moviechallenge.core.base.BaseFragment
import io.github.ardaltunyay.moviechallenge.core.listeners.ItemClickListener
import io.github.ardaltunyay.moviechallenge.databinding.FragmentSearchMoviesBinding
import io.github.ardaltunyay.moviechallenge.ui.search_movies.adapter.SearchMoviesAdapter
import io.github.ardaltunyay.moviechallenge.ui.search_movies.adapter.divider.MovieItemDecoration
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchMoviesFragment :
    BaseFragment<SearchMoviesViewModel, FragmentSearchMoviesBinding>(R.layout.fragment_search_movies) {

    override val viewModel: SearchMoviesViewModel by viewModel()

    private val searchMoviesAdapter: SearchMoviesAdapter by lazy(LazyThreadSafetyMode.NONE) {
        SearchMoviesAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        setSearchMoviesAdapter()

        //TODO create two way binding
        binding.svMovie.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    val oldQueryText = viewModel.queryText.value
                    if (oldQueryText != it) {
                        viewModel.queryText.value = it
                    }
                }
                return false
            }

        })


        viewModel.searchMoviesCombination.observe(viewLifecycleOwner) { pagedList ->
            searchMoviesAdapter.submitList(pagedList)
        }


    }

    private fun setSearchMoviesAdapter() {
        with(binding.rvMovies) {
            adapter = searchMoviesAdapter
            addItemDecoration(
                MovieItemDecoration(
                    spacing = 30,
                    spanCount = 3
                )
            )
        }
        searchMoviesAdapter.setClickListener(ItemClickListener { movie ->
            findNavController().navigate(
                SearchMoviesFragmentDirections.actionSearchMoviesFragmentToMovieDetailsFragment(
                    movieId = movie.movieId
                )
            )
        })
    }

}