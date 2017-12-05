package com.mvp.movie.presentor;

import com.mvp.movie.ConnectionManager;
import com.mvp.movie.Connectivity;
import com.mvp.movie.R;
import com.mvp.movie.model.Movie;
import com.mvp.movie.presentor.intercator.MovieInteractor;
import com.mvp.movie.view.MovieView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.stubbing.answers.CallsRealMethods;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by hardik on 05/12/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class MoviePresenterImplTest {
    private MoviePresenter moviePresenter;
    private Movie movie;

    @Mock
    private MovieView movieView;
    @Mock
    private Connectivity connectivity;
    @Mock
    MovieInteractor movieIntercator;

    @Before
    public void setup() throws Exception {
        moviePresenter = new MoviePresenterImpl(movieView, connectivity, movieIntercator);
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
        when(movieView.getMovieName()).thenReturn("golmaal");
        when(connectivity.isConnected()).thenReturn(false);
        moviePresenter.onSubmitClicked();
        verify(movieView).internetRequired(R.string.error_no_internet);
    }

    @Test
    public void shouldShowSuccess() throws Exception {
        when(movieView.getMovieName()).thenReturn("golmaal");
        when(connectivity.isConnected()).thenReturn(true);

        MovieInteractor.OnMovieResultListener onMovieResultListener = Mockito.mock(MovieInteractor.OnMovieResultListener.class);
        //when(movieIntercator.getMovieInfo(movieView.getMovieName());)
        moviePresenter.onSubmitClicked();
        //How to mock movie result here?

    }

    private Movie createMockMovie() {
        Movie movie = new Movie();

        return movie;
    }

    class ResultListener implements MovieInteractor.OnMovieResultListener {

        @Override
        public void onMovieRetrieveError(Throwable e) {

        }

        @Override
        public void onMovieRetrieveSuccess(Movie movie) {

        }
    }
}