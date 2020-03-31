package com.avinash.activities.firstquestion

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.RadioGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.avinash.BR
import com.avinash.R
import com.avinash.activities.secondquestion.SecondQuestionActivity
import com.avinash.base.BaseActivity
import com.avinash.databinding.ActivityFirstQuestionBinding
import com.avinash.db.entity.Game

class FirstQuestionActivity : BaseActivity<ActivityFirstQuestionBinding, FirstQuestionViewModel>(),
    FirstQuestionNavigator {

    private lateinit var context: Context;
    private var mViewBinding: ActivityFirstQuestionBinding? = null
    private var mViewModel: FirstQuestionViewModel? = null

    override fun getBindingVariable(): Int {
        return BR.viewmodel
    }

    /*
    * init layout
    * */
    override fun getLayoutId(): Int {
        return R.layout.activity_first_question
    }

    /*
    * Init viewmodel
    * */
    override fun getViewModel(): FirstQuestionViewModel {
        mViewModel = ViewModelProviders.of(this).get(FirstQuestionViewModel::class.java);
        return mViewModel as FirstQuestionViewModel;
    }

    override fun getNavigator(): Any {
        return this
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initWork()

    }

    /*
    * Initial work - get binding, init context, get data from previous activity, set answer listener
    * */
    private fun initWork() {
        mViewBinding = getmViewDataBinding()
        context = this
        val model = intent.getSerializableExtra(getString(R.string.model)) as Game
        mViewModel?.setObservableModel(model)
        setRadioGroupListener(mViewBinding?.radioGroup)
    }

    /*
    * Method to check validation and send data to next screen
    * */
    override fun onNextClicked() {
        if (TextUtils.isEmpty(mViewModel?.getObservableModel()?.get()?.answerOne)) {
            Toast.makeText(context, getString(R.string.please_select_answer_first), Toast.LENGTH_SHORT).show()
            return
        }

        startActivity(Intent(context,SecondQuestionActivity::class.java).putExtra(getString(R.string.model),viewModel.observableModel.get()))

    }

    override fun handleError(throwable: Throwable, tag: Int, msg: String) {

    }

    /*
    * method to listen click event of selected answer
    * */
    fun setRadioGroupListener(radioGroup: RadioGroup?) {
        radioGroup?.setOnCheckedChangeListener { radioGroup, id ->
            run {
                when (id) {
                    R.id.radio_one -> viewModel.observableModel.get()?.answerOne =
                        mViewBinding?.radioOne?.text.toString()
                    R.id.radio_two -> viewModel.observableModel.get()?.answerOne =
                        mViewBinding?.radioTwo?.text.toString()
                    R.id.radio_three -> viewModel.observableModel.get()?.answerOne =
                        mViewBinding?.radioThree?.text.toString()
                    R.id.radio_four -> viewModel.observableModel.get()?.answerOne =
                        mViewBinding?.radioFour?.text.toString()
                }
            }
        }
    }

}
