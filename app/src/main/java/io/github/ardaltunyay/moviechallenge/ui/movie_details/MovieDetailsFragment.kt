package io.github.ardaltunyay.moviechallenge.ui.movie_details

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import io.github.ardaltunyay.moviechallenge.R
import io.github.ardaltunyay.moviechallenge.core.base.BaseFragment
import io.github.ardaltunyay.moviechallenge.databinding.FragmentMovieDetailsBinding
import io.github.ardaltunyay.moviechallenge.ui.movie_details.adapter.GenresAdapter
import io.github.ardaltunyay.moviechallenge.ui.movie_details.model.MovieDetailsActionState
import io.github.ardaltunyay.moviechallenge.ui.movie_details.model.MovieDetailsClickActions
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieDetailsFragment :
    BaseFragment<MovieDetailsViewModel, FragmentMovieDetailsBinding>(R.layout.fragment_movie_details) {

    private val args: MovieDetailsFragmentArgs by navArgs()

    override val viewModel: MovieDetailsViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        viewModel.fetchMovieDetails(args.movieId)

        handleClickActions()
        handleActionState()

    }

    private fun handleActionState() {
        viewModel.actionState.observe(viewLifecycleOwner) { actionState ->
            when (actionState) {
                is MovieDetailsActionState.SetMovieState -> {
                    setGenreAdapter(actionState.movieDetails.genres)
                }
            }
        }
    }

    private fun handleClickActions() {
        viewModel.clickActions.observe(viewLifecycleOwner) { clickActions ->
            when (clickActions) {
                is MovieDetailsClickActions.OnBackClicked -> {
                    findNavController().popBackStack()
                }
            }
        }
    }

    private fun setGenreAdapter(genres: List<String>) {
        with(binding.rvGenres) {
            adapter = GenresAdapter(genres = genres)
        }
    }

}