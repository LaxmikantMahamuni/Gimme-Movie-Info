package com.mvp.movie.presentor;

import com.mvp.movie.ConnectionManager;
import com.mvp.movie.Connectivity;
import com.mvp.movie.R;
import com.mvp.movie.view.MovieView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by hardik on 05/12/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class MoviePresenterImplTest {
    private MoviePresenter moviePresenter;

    @Mock
    private MovieView movieView;
    @Mock
    private Connectivity connectivity;

    @Before
    public void setup() throws Exception {
        moviePresenter = new MoviePresenterImpl(movieView,connectivity);
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


}