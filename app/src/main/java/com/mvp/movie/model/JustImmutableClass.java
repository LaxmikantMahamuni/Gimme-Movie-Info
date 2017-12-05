package com.mvp.movie.model;

/**
 * Created by hardik on 29/11/17.
 */

public class JustImmutableClass {
    final private int i;
    private String s ;

    public JustImmutableClass(int i) {
        this.i = i;
    }


    public int getI() {
        return i;
    }

}
