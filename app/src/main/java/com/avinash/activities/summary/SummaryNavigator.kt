package com.avinash.activities.summary

import com.avinash.base.BaseNavigator


interface SummaryNavigator : BaseNavigator<Any, Any> {
    fun onFinishClicked()
}