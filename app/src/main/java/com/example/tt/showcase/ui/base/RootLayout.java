package com.example.tt.showcase.ui.base;

import android.support.annotation.LayoutRes;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author tturcic
 *         \date 30.8.2017.
 *
 *         Annotate base activities and base fragments with this annotation to set their layout.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface RootLayout {

    @LayoutRes int value();

}
