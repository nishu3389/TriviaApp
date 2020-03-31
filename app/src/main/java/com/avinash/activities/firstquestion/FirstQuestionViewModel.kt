package com.avinash.activities.firstquestion

import android.view.View
import com.avinash.base.BaseViewModel
import androidx.databinding.ObservableField
import com.avinash.activities.firstquestion.FirstQuestionNavigator
import com.avinash.db.entity.Game


class FirstQuestionViewModel : BaseViewModel<FirstQuestionNavigator>()  {

    internal var observableModel = ObservableField<Game>()
//    private val profileLiveData: MutableLiveData<BaseResponse<ModelEditProfile>>? = null
//    private val uploadFileLiveData: MutableLiveData<BaseResponse<UploadFile>>? = null
//    private val statesLiveData: MutableLiveData<BaseResponse<List<StateModel>>>? = null

    fun setObservableModel(observableModel: Game) {
        this.observableModel.set(observableModel)
    }

    fun getObservableModel(): ObservableField<Game> {
        return observableModel
    }

    fun onNextClicked(view: View) {
        navigator.onNextClicked()
    }

}
