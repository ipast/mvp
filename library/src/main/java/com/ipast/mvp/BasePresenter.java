package com.ipast.mvp;

import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class BasePresenter<M extends BaseModel, V extends IBaseView> {
    private final String TAG = getClass().getSimpleName();
    /**
     * 使用弱引用，防止内存泄漏
     */
    private WeakReference<V> mView;
    protected M mModel;

    public void attachView(V view) {
        this.mView = new WeakReference<>(view);
        registerModel();
    }

    private void registerModel() {
        try {
            ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
            if (type != null) {
                Class<M> clazz = (Class<M>) type.getActualTypeArguments()[0];
                Constructor c = clazz.getDeclaredConstructors()[0];
                c.setAccessible(true);
                mModel = (M) c.newInstance();
            }
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public V getView() {
        return isViewAttached() ? mView.get() : null;
    }

    private boolean isViewAttached() {
        return mView != null && mView.get() != null;
    }

    void detachView() {
        if (mView != null) {
            mView.clear();
            mView = null;
        }
    }

}
