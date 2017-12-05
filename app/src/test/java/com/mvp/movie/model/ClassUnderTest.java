package com.mvp.movie.model;

import android.content.Context;

import com.mvp.movie.R;

/**
 * Created by hardik on 08/11/17.
 */

public class ClassUnderTest {
    public ClassUnderTest(Context context) {
        this.context = context;
    }

    public String getAppName() {
        return context.getString(R.string.app_name);
    }

    Context context;

}
