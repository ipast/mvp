package com.ipast.mvp.ui.model;

import com.ipast.mvp.BaseModel;
import com.ipast.mvp.interfaces.MainCallback;
import com.ipast.mvp.ui.contract.MainContract;

/**
 * @author gang.cheng
 * @description :
 * @date :2021/7/21
 */
public class MainModel extends BaseModel implements MainContract.IMainModel {

    @Override
    public void onTest(MainCallback callback) {
        callback.onSucceed();
    }
}
