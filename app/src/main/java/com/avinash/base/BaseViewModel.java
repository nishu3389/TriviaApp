package com.avinash.base;


import androidx.lifecycle.ViewModel;

import java.lang.ref.WeakReference;

/**
 * The type Base view model.
 *
 * @param <Any> the type parameter
 */
public abstract class BaseViewModel<Any> extends ViewModel {

    private WeakReference<Any> mNavigator;

    /**
     * Instantiates a new Base view model.
     */
    public BaseViewModel(){

    }

    /**
     * Gets navigator.
     *
     * @return the navigator
     */
    public Any getNavigator() {
        return mNavigator.get();
    }

    /**
     * Sets navigator.
     *
     * @param navigator the navigator
     */
    public void setNavigator(Any navigator) {
        this.mNavigator = new WeakReference<>(navigator);
    }

}
