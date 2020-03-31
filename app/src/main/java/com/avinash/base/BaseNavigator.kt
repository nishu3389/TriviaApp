package com.avinash.base

/**
 * The interface Base navigator.
 *
 * @param <A> the type parameter
 * @param <V> the type parameter
</V></A> */
interface BaseNavigator<A, V> {

    /**
     * Handle error.
     *
     * @param throwable the throwable
     * @param tag       the tag
     * @param msg       the msg
     */
    fun handleError(throwable: Throwable, tag: Int, msg: String)
}
