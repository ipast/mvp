package com.ipast.mvp;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;

import butterknife.ButterKnife;
import butterknife.Unbinder;


public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements IBaseView {

    protected P mPresenter;
    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        if (getContentLayout() != 0) {
            setContentView(getContentLayout());
            unbinder = ButterKnife.bind(this);
        }
        createPresenter();
        initView(savedInstanceState);
        initData(savedInstanceState);
        initEvent();
    }

    private void createPresenter() {
        try {
            ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
            if (type != null) {
                Class<P> clazz = (Class<P>) type.getActualTypeArguments()[0];
                Constructor c = clazz.getDeclaredConstructors()[0];
                c.setAccessible(true);
                mPresenter = (P) c.newInstance();
                mPresenter.attachView(this);
            }
        } catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
            e.printStackTrace();
        }
    }

    private void detach() {
        if (mPresenter != null) {
            mPresenter.detachView();
            mPresenter = null;
        }
        unbinder.unbind();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        detach();
    }

    //返回页面布局id
    @LayoutRes
    protected abstract int getContentLayout();

    //做视图相关的初始化工作
    protected abstract void initView(Bundle savedInstanceState);

    //做数据相关的初始化工作
    protected abstract void initData(Bundle savedInstanceState);

    //做监听事件相关的初始化工作
    protected abstract void initEvent();

}
