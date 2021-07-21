package com.ipast.mvp.ui.presenter;

import com.ipast.mvp.BasePresenter;
import com.ipast.mvp.interfaces.MainCallback;
import com.ipast.mvp.ui.contract.MainContract;
import com.ipast.mvp.ui.model.MainModel;

/**
 * @author gang.cheng
 * @description :
 * @date :2021/7/21
 */
public class MainPresenter extends BasePresenter<MainModel, MainContract.IMainView> implements MainContract.IMainPresenter {

    @Override
    public void test() {
        mModel.onTest(new MainCallback() {
            @Override
            public void onSucceed() {
                getView().onSucceed();
            }

            @Override
            public void onFailed() {

            }
        });

    }
}
