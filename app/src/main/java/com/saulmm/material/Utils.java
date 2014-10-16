package com.saulmm.material;

import android.transition.CircularPropagation;
import android.transition.Explode;
import android.view.Window;

/**
 * Created by saulmm on 16/10/14.
 */
public class Utils {

    public static void configureWindowEnterExitTransition (Window w) {

        Explode ex = new Explode();

        CircularPropagation c = new CircularPropagation();
        c.setPropagationSpeed(2f);

        ex.setPropagation(c);

        w.setExitTransition(ex);
        w.setEnterTransition(ex);
    }
}
