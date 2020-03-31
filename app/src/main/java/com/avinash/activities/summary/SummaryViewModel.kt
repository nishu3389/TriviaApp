package com.avinash.activities.summary

import android.view.View
import com.avinash.base.BaseViewModel
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.avinash.activities.summary.SummaryNavigator
import com.avinash.db.entity.Game
import com.avinash.repository.GameRepository


public class SummaryViewModel() : BaseViewModel<SummaryNavigator>()  {

    private var repository: GameRepository? = null

    internal var observableModel = ObservableField<Game>()
    private val insertLiveData: MutableLiveData<Boolean>? = null

    fun provideRepository(repository: GameRepository){
        this.repository = repository
    }

    fun setObservableModel(observableModel: Game) {
        this.observableModel.set(observableModel)
    }

    fun getObservableModel(): ObservableField<Game> {
        return observableModel
    }

    fun onFinishClicked(view: View) {
        navigator.onFinishClicked()
    }

    fun insert(game: Game) : LiveData<Long> {
        return repository!!.insertTask(game)
    }

}
