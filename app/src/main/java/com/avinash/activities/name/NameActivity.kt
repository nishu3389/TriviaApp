package com.avinash.activities.name

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.avinash.BR
import com.avinash.R
import com.avinash.activities.history.HistoryActivity
import com.avinash.activities.firstquestion.FirstQuestionActivity
import com.avinash.base.BaseActivity
import com.avinash.databinding.ActivityNameBinding
import com.avinash.db.entity.Game

class NameActivity : BaseActivity<ActivityNameBinding, NameViewModel>(), NameNavigator {

    override fun getBindingVariable(): Int {
        return BR.viewmodel
    }

    private lateinit var context : Context;
    private var mViewBinding: ActivityNameBinding? = null
    private var mViewModel: NameViewModel? = null

    /*
    * init layout
    * */
    override fun getLayoutId(): Int {
        return R.layout.activity_name
    }

    /*
    * init viewmodel
    * */
    override fun getViewModel(): NameViewModel {
        mViewModel = ViewModelProviders.of(this).get(NameViewModel::class.java);
        return mViewModel as NameViewModel;
    }

    /*
    * set navigator
    * */
    override fun getNavigator(): Any {
        return this
    }

    /*
    * Check validation and send data to next page
    * */
    override fun onNextClicked() {
        if(TextUtils.isEmpty(mViewModel?.getObservableModel()?.get()?.name)){
            Toast.makeText(context,"Please enter your name",Toast.LENGTH_SHORT).show()
            return
        }

        startActivity(Intent(context,FirstQuestionActivity::class.java).putExtra(getString(R.string.model),viewModel.observableModel.get()))
    }

   /*
   * Method will be invoked when user click on history button - Navigate to history page
   * */
    override fun onHistoryClicked() {
        startActivity(Intent(context,
            HistoryActivity::class.java))
    }


    override fun handleError(throwable: Throwable, tag: Int, msg: String) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initWork()
    }

    /*
    * Initial work
    * */
    private fun initWork() {
        context = this
        mViewBinding = getmViewDataBinding()
        mViewModel?.setObservableModel(Game())
    }

}
