package com.ipast.mvp;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;

import butterknife.ButterKnife;
import butterknife.Unbinder;


public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements IBaseView {

    protected P mPresenter;
    private Unbinder unbinder;
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (getContentLayout() <= 0) {
            return super.onCreateView(inflater, container, savedInstanceState);
        }
        View view = inflater.inflate(getContentLayout(), container, false);
        unbinder = ButterKnife.bind(this, view);
        createPresenter();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view, savedInstanceState);
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
        } catch (IllegalAccessException | InvocationTargetException | java.lang.InstantiationException e) {
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
    public void onDestroyView() {
        super.onDestroyView();
        detach();
    }

    //??????????????????id
    protected abstract int getContentLayout();

    //?????????????????????????????????
    protected abstract void initView(View view, Bundle savedInstanceState);

    //?????????????????????????????????
    protected abstract void initData(Bundle savedInstanceState);

    //???????????????????????????????????????
    protected abstract void initEvent();
}
