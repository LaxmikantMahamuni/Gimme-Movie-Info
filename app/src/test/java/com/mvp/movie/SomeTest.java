package com.mvp.movie;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Created by hardik on 08/11/17.
 */

public class SomeTest {

    @Test
    public void just_a_test_of_addition() {
        int sum = 1 + 1;
        assertThat(sum, is(2));
    }
}
