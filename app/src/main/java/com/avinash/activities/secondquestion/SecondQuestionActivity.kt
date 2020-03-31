package com.avinash.activities.secondquestion

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.avinash.BR
import com.avinash.R
import com.avinash.activities.summary.SummaryActivity
import com.avinash.base.BaseActivity
import com.avinash.databinding.ActivitySecondQuestionBinding
import com.avinash.db.entity.Game

class SecondQuestionActivity : BaseActivity<ActivitySecondQuestionBinding, SecondQuestionViewModel>(),
    SecondQuestionNavigator {

    private lateinit var context: Context
    private var mViewBinding: ActivitySecondQuestionBinding? = null
    private var mViewModel: SecondQuestionViewModel? = null

    /*
    * init layout
    * */
    override fun getLayoutId(): Int {
        return R.layout.activity_second_question
    }

    /*
   * Init viewmodel
   * */
    override fun getViewModel(): SecondQuestionViewModel {
        mViewModel = ViewModelProviders.of(this).get(SecondQuestionViewModel::class.java)
        return mViewModel as SecondQuestionViewModel
    }

    override fun getNavigator(): Any {
        return this
    }

    override fun getBindingVariable(): Int {
        return BR.viewmodel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initWork()
        }

    /*
    * Initial work - get binding, init context, get data from previous activity, set data to viewmodel
    * */
    private fun initWork() {
        context = this
        mViewBinding = getmViewDataBinding()
        val model = intent.getSerializableExtra(getString(R.string.model)) as Game
        mViewModel?.setObservableModel(model)
    }


    /*
    * method to validate answer
    * */
    private fun isValid(): Boolean {
        if (!mViewBinding?.checkboxOne?.isChecked!! &&
            !mViewBinding?.checkboxTwo?.isChecked!! &&
            !mViewBinding?.checkboxThree?.isChecked!! &&
            !mViewBinding?.checkboxFour?.isChecked!!)
        {
            Toast.makeText(context, getString(R.string.please_select_answer_second), Toast.LENGTH_SHORT).show()
            return false
        } else
            return true
    }

    /*
    * Method to check validation and navigate to next screen
    * */
    override fun onNextClicked() {
        if (isValid()) {
            mViewModel?.observableModel?.get()?.answerTwo = getAnswer()
            startActivity(
                Intent(
                    context,
                    SummaryActivity::class.java
                ).putExtra(getString(R.string.model), mViewModel?.observableModel?.get())
            )
        }
    }

    /*
    * method to get selected answers
    * */
    private fun getAnswer(): String {
        var builder = StringBuilder("")
        if (mViewBinding?.checkboxOne?.isChecked!!)
            builder.append(mViewBinding?.checkboxOne?.text.toString())
        if (mViewBinding?.checkboxTwo?.isChecked!!)
            builder.append(", " + mViewBinding?.checkboxTwo?.text.toString())
        if (mViewBinding?.checkboxThree?.isChecked!!)
            builder.append(", " + mViewBinding?.checkboxThree?.text.toString())
        if (mViewBinding?.checkboxFour?.isChecked!!)
            builder.append(", " + mViewBinding?.checkboxFour?.text.toString())

        var toString = builder.toString()
        if(toString.trim().startsWith(","))
            toString = toString.replaceFirst(",","").trim()

        return toString
    }

    override fun handleError(throwable: Throwable, tag: Int, msg: String) {

    }


}
