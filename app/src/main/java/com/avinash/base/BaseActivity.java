package com.avinash.base;


import android.os.Bundle;
import android.text.TextUtils;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.Observer;

import com.avinash.base.BaseViewModel;
import com.avinash.db.entity.Game;
import com.avinash.repository.GameRepository;

import java.util.List;

/**
 * The type Base activity.
 *
 * @param <T> the type parameter
 * @param <V> the type parameter
 */
public abstract class BaseActivity<T extends ViewDataBinding,  V extends BaseViewModel> extends AppCompatActivity {

    private T mViewDataBinding;
    private V mViewModel;

    /**
     * Override for set binding variable
     *
     * @return variable id
     */
    public abstract int getBindingVariable();

    /**
     * Gets layout id.
     *
     * @return layout resource id
     */
    public abstract
    @LayoutRes
    int getLayoutId();

    /**
     * Override for set view model
     *
     * @return view model instance
     */
    public abstract V getViewModel();

    /**
     * Gets navigator.
     *
     * @return the navigator
     */
    public abstract  Object  getNavigator();

    /**
     * Gets view data binding.
     *
     * @return the view data binding
     */
    public T getmViewDataBinding() {
        return mViewDataBinding;
    }

    /**
     * Sets view data binding.
     *
     * @param mViewDataBinding the m view data binding
     */
    public void setmViewDataBinding(T mViewDataBinding) {
        this.mViewDataBinding = mViewDataBinding;
    }

    /**
     * Gets view model.
     *
     * @return the view model
     */
    public V getmViewModel() {
        return mViewModel;
    }

    /**
     * Sets view model.
     *
     * @param mViewModel the m view model
     */
    public void setmViewModel(V mViewModel) {
        this.mViewModel = mViewModel;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        performDataBinding();

        GameRepository gameRepository2 = new GameRepository(this);
        gameRepository2.getTasks().observe(this, new Observer<List<Game>>() {
            @Override
            public void onChanged(List<Game> game2s) {

            }
        });
    }




    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
    private void performDataBinding() {
        mViewDataBinding = DataBindingUtil.setContentView(this, getLayoutId());
        mViewModel = getViewModel();
        mViewDataBinding.setVariable(getBindingVariable(), mViewModel);
        mViewDataBinding.executePendingBindings();
        mViewModel.setNavigator(getNavigator());
    }

    public Boolean isEmpty(String str){
        return TextUtils.isEmpty(str);
    }

}
