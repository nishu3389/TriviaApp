package com.avinash.activities.history

import androidx.lifecycle.Observer
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.avinash.BR
import com.avinash.R
import com.avinash.adapter.GameAdapter
import com.avinash.base.BaseActivity
import com.avinash.databinding.ActivityHistoryBinding
import com.avinash.db.entity.Game
import com.avinash.repository.GameRepository

class HistoryActivity : BaseActivity<ActivityHistoryBinding, HistoryViewModel>(), HistoryNavigator {

    private var adapter: GameAdapter? = null
    private var mViewBinding: ActivityHistoryBinding? = null
    private var mViewModel: HistoryViewModel? = null

    override fun handleError(throwable: Throwable, tag: Int, msg: String) {

    }

    override fun getBindingVariable(): Int {
        return BR.viewmodel
    }

    /*
    * init layout
    * */
    override fun getLayoutId(): Int {
        return R.layout.activity_history
    }

    /*
      * Init viewmodel
      * */
    override fun getViewModel(): HistoryViewModel {
        mViewModel = ViewModelProviders.of(this).get(HistoryViewModel::class.java)
        return mViewModel as HistoryViewModel
    }

    override fun getNavigator(): Any {
        return this
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initWork()
    }

    /*
   * Initial work - get binding, init context, init Repository, setup recyclerview
   * */
    private fun initWork() {
        mViewBinding = getmViewDataBinding()
        mViewModel?.provideRepository(GameRepository(this))
        setupRecyclerView()
        showGameList()
    }

    /*
    * Mehtod to fetch and show the game list
    * */
    private fun showGameList() {
        mViewModel?.getAllNotes()?.observe(this,
            Observer<List<Game>> { list ->
                list?.let {
                    adapter?.setGames(it)
                }
            })
    }

    /*
    * setup the recyclerview and adapter
    * */
    private fun setupRecyclerView() {
        mViewBinding?.recyclerView?.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        mViewBinding?.recyclerView?.setHasFixedSize(true)
        adapter = GameAdapter()
        mViewBinding?.recyclerView?.adapter = adapter
    }


}