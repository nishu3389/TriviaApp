package com.avinash.activities.name

import com.avinash.base.BaseNavigator

interface NameNavigator : BaseNavigator<Any, Any> {
    fun onNextClicked()
    fun onHistoryClicked()
}