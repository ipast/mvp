package com.ipast.mvp.ui.contract;

import com.ipast.mvp.BaseModel;
import com.ipast.mvp.IBaseView;
import com.ipast.mvp.interfaces.MainCallback;

/**
 * @author gang.cheng
 * @description :
 * @date :2021/7/21
 */
public interface MainContract {
    interface IMainView extends IBaseView {
        void onSucceed();
    }

    interface IMainModel {
        void onTest(MainCallback callback);
    }

    interface IMainPresenter {
        void test();
    }
}
