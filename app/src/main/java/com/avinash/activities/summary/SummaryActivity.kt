package com.avinash.activities.summary

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.avinash.BR
import com.avinash.R
import com.avinash.activities.name.NameActivity
import com.avinash.base.BaseActivity
import com.avinash.databinding.ActivitySummaryBinding
import com.avinash.db.entity.Game
import com.avinash.repository.GameRepository

class SummaryActivity : BaseActivity<ActivitySummaryBinding, SummaryViewModel>(), SummaryNavigator {

    private lateinit var context: Context
    private var mViewBinding: ActivitySummaryBinding? = null
    private var mViewModel: SummaryViewModel? = null

    /*
   * init layout
   * */
    override fun getLayoutId(): Int {
        return R.layout.activity_summary
    }

    /*
   * Init viewmodel
   * */
    override fun getViewModel(): SummaryViewModel {
        mViewModel = ViewModelProviders.of(this).get(SummaryViewModel::class.java)
        return mViewModel as SummaryViewModel
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
    * Initial work - get binding, init context, get data from previous activity, set data to viewmodel, init Repository
    * */
    private fun initWork() {
        context = this
        mViewBinding = getmViewDataBinding()
        val model = intent.getSerializableExtra(getString(R.string.model))
        mViewModel?.setObservableModel(model as Game)
        mViewModel?.provideRepository(GameRepository(this))
    }


    /*
    * Method to check validation and navigate to next screen
    * */
    override fun onFinishClicked() {
        val game = mViewModel?.observableModel?.get() as Game
        game.createdAt = System.currentTimeMillis()
        saveGameData(game)
    }

    /*
    * Save the data to local database
    * */
    private fun saveGameData(game : Game) {
        mViewModel?.insert(game)?.observe(this, Observer {
            startActivity(Intent(context, NameActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
        })
    }


    override fun handleError(throwable: Throwable, tag: Int, msg: String) {

    }


}
