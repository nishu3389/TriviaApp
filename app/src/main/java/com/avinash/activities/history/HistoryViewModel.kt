package com.avinash.activities.history

import com.avinash.base.BaseViewModel
import androidx.lifecycle.LiveData
import com.avinash.activities.history.HistoryNavigator
import com.avinash.db.entity.Game
import com.avinash.repository.GameRepository

class HistoryViewModel : BaseViewModel<HistoryNavigator>() {

    var repository : GameRepository? = null

    fun provideRepository(repository: GameRepository){
        this.repository = repository
    }

//    private var allNotes: LiveData<List<Game>> = repository!!.tasks

    fun getAllNotes(): LiveData<List<Game>> {
        return repository!!.tasks
    }

}
