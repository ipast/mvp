package com.ipast.mvp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public abstract class LazyFragment<P extends BasePresenter> extends BaseFragment<P> {
    private boolean isCanLoadData;
    private final String TAG = getClass().getSimpleName();

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isCanLoadData = true;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && isCanLoadData) {
            lazyLoad();
            // Log.d(TAG, "lazyLoad()");
        }
    }

    //当前Fragment可见时加载
    protected abstract void lazyLoad();

    @Override
    public void onResume() {
        super.onResume();
        if (getUserVisibleHint()) {
            lazyLoad();
            // Log.d(TAG, "lazyLoad()");
        }
    }

    @Override
    public void onDestroyView() {
        isCanLoadData = false;
        super.onDestroyView();
    }
}
