package com.ipast.mvp.ui.view;
import android.os.Bundle;
import com.ipast.mvp.BaseActivity;
import com.ipast.mvp.R;
import com.ipast.mvp.ui.contract.MainContract;
import com.ipast.mvp.ui.presenter.MainPresenter;
import com.ipast.utils.toast.ToastUtil;

import butterknife.OnClick;

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.IMainView {

    @Override
    protected int getContentLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    protected void initEvent() {

    }
    @OnClick(R.id.btn_test)
    public void onTest(){
        mPresenter.test();
    }
    @Override
    public void onSucceed() {
        ToastUtil.showLong(this, "test successful!");
    }
}