package com.mvp.movie.presentor;

import com.mvp.movie.util.Connectivity;
import com.mvp.movie.R;
import com.mvp.movie.adapter.model.MovieModel;
import com.mvp.movie.model.Movie;
import com.mvp.movie.model.Result;
import com.mvp.movie.intercator.MovieInteractor;
import com.mvp.movie.view.MovieView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by hardik on 05/12/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class MoviePresenterImplTest {
    private MoviePresenter moviePresenter;
    private Movie movie;
    private final int index = 1;

    @Mock
    private MovieView movieView;
    @Mock
    private Connectivity connectivity;
    @Mock
    MovieInteractor movieInteractor; //IRepo

    private final String moviename = "Whiplash";

    @Before
    public void setup() throws Exception {
        moviePresenter = new MoviePresenterImpl(movieView, connectivity, movieInteractor);
        movie = createMockMovie();
    }

    @Test
    public void shouldShowErrorMessageWhenNameIsEmpty() throws Exception {
        when(movieView.getMovieName()).thenReturn("");
        moviePresenter.onSubmitClicked();
        verify(movieView).emptyMovieName(R.string.error_empty_movie_name);
    }

    @Test
    public void shouldShowNoInternetError() throws Exception {
        when(movieView.getMovieName()).thenReturn(moviename);
        when(connectivity.isConnected()).thenReturn(false);
        moviePresenter.onSubmitClicked();
        verify(movieView).internetRequired(R.string.error_no_internet);
    }

    @Test
    public void shouldShowSuccess() throws Exception {
        when(movieView.getMovieName()).thenReturn(moviename);
        when(connectivity.isConnected()).thenReturn(true);

        //Repo method for movie info accepts a movie name and a callback; callback parameter returns the result to callee,
        //Hence we have to mock the callback parameter

        //We want to stub a void method with generic
        doAnswer(new Answer() {
            //It allows to configure mock's answer.
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                //We modify the callback parameter here as we want it to return success,
                //index defines position of the callback variable
                ((MovieInteractor.OnMovieResultListener) invocation.getArgument(index)).onMovieRetrieveSuccess(movie);
                return null;
            }
        }).when(movieInteractor).
                getMovieInfo(any(String.class), any(MovieInteractor.OnMovieResultListener.class));

        //Mocking the OnMovieResultListener callback object
        MovieInteractor.OnMovieResultListener onMovieResultListener = Mockito.mock(MovieInteractor.OnMovieResultListener.class);
        movieInteractor.getMovieInfo(movieView.getMovieName(), onMovieResultListener);

        //verify if the result was successful, we expect it to be
        verify(onMovieResultListener).onMovieRetrieveSuccess(any(Movie.class));

        //Perform onclick and check if test runs success
        moviePresenter.onSubmitClicked();
        verify(movieView).onSuccess(new ArrayList<MovieModel>());
    }

    @Test
    public void shouldShowError() throws Exception {
        final int index = 1;
        when(movieView.getMovieName()).thenReturn(moviename);
        when(connectivity.isConnected()).thenReturn(true);

        doAnswer(new Answer() {
            //It allows to configure mock's answer.
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Throwable throwable = new Throwable("Error occurred while retrieving result");
                ((MovieInteractor.OnMovieResultListener) invocation.getArgument(index)).onMovieRetrieveError(throwable);
                return null;
            }
        }).when(movieInteractor).
                getMovieInfo(any(String.class), any(MovieInteractor.OnMovieResultListener.class));
        MovieInteractor.OnMovieResultListener onMovieResultListener = Mockito.mock(MovieInteractor.OnMovieResultListener.class);
        movieInteractor.getMovieInfo(movieView.getMovieName(), onMovieResultListener);
        //verify if the result was failure, we expect it to be
        verify(onMovieResultListener).onMovieRetrieveError(any(Throwable.class));

        moviePresenter.onSubmitClicked();
        verify(movieView).onFailed(R.string.error_failed_to_get_movie_result);
    }

    @Test
    public void onActivityKilled() {
        assertNotNull(movieView);
        moviePresenter.onDestroy();
        assertNull(movieView);
    }

    private Movie createMockMovie() {
        List<Result> list = new ArrayList<Result>();
        return movie = new Movie(1, 1, 1, list, 1);
    }
}