package com.mvp.movie;

import android.content.Context;

import com.mvp.movie.model.ClassUnderTest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import java.util.List;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * https://blog.mindorks.com/falling-in-love-with-android-testing-dd11ffa6ac3e
 * mockito doc = https://static.javadoc.io/org.mockito/mockito-core/2.11.0/org/mockito/Mockito.html
 * Created by hardik on 08/11/17.
 */
@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class MockitoTest {
    @Mock
    Context context;

    List<String> mockedList = mock(List.class);


    private String appName;

    @Before
    public void initialise(){
        mockedList.add("one");
        mockedList.add("two");
        when(context.getString(R.string.app_name)).thenReturn("AppName");
        when(mockedList.size()).thenReturn(2);
    }

    @Test
    public void testAppName(){
        ClassUnderTest classUnderTest = new ClassUnderTest(context);
        appName = classUnderTest.getAppName();
        assertThat(appName,is("AppName"));
        assertThat(mockedList.size(),is(2));
    }
}
