package com.mk.mklib.dialog;


import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.mk.mklib.R;
import com.mk.mklib.utils.DensityUtils;

import java.util.Objects;

import static android.view.Gravity.CENTER;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

/**
 * created by mbm on 2019/5/6
 * dialogfragment基类
 */
public abstract class BHCBaseDialogFragment extends DialogFragment {
    private int mWidth = WRAP_CONTENT;
    private int mHeight = WRAP_CONTENT;
    private int mGravity = CENTER;
    private int mOffsetX = 0;
    private int mOffsetY = 0;
    private String mTag = "";
    private int mAnimation = R.style.DialogBaseAnimation;
    private boolean mCanceledOnTouchOutside = false;
    protected DialogConfirmListener mDialogConfirmListener;
    protected DialogCancelListener mDialogCancelListener;


    protected static Bundle getArgumentBundle(Builder b) {
        Bundle bundle = new Bundle();
        bundle.putInt("mWidth", b.mWidth);
        bundle.putInt("mHeight", b.mHeight);
        bundle.putInt("mGravity", b.mGravity);
        bundle.putInt("mOffsetX", b.mOffsetX);
        bundle.putInt("mOffsetY", b.mOffsetY);
        bundle.putInt("mAnimation", b.mAnimation);
        bundle.putBoolean("mCanceledOnTouchOutside", b.mCanceledOnTouchOutside);
        bundle.putString("mTag", b.mTag);
        return bundle;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mWidth = getArguments().getInt("mWidth");
            mHeight = getArguments().getInt("mHeight");
            mOffsetX = getArguments().getInt("mOffsetX");
            mOffsetY = getArguments().getInt("mOffsetY");
            mAnimation = getArguments().getInt("mAnimation");
            mGravity = getArguments().getInt("mGravity");
            mCanceledOnTouchOutside = getArguments().getBoolean("mCanceledOnTouchOutside");
            mTag = getArguments().getString("mTag");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        setStyle();
        return setView(inflater, container, savedInstanceState);
    }


    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
//        if (mDialogCancelListener != null) {
//            mDialogCancelListener.dismiss();
//        }
    }

    /**
     * 显示dialogFragment
     *
     * @param fragmentManager
     */
    public void show(FragmentManager fragmentManager) {
        show(fragmentManager, mTag);
    }

    /**
     * 设置统一样式
     */
    private void setStyle() {
        Window window = getDialog().getWindow();//获取window
        getDialog().requestWindowFeature(STYLE_NO_TITLE);//无标题
        Objects.requireNonNull(getDialog().getWindow()).setBackgroundDrawable(
                new ColorDrawable(Color.TRANSPARENT));//透明背景
        getDialog().setCanceledOnTouchOutside(mCanceledOnTouchOutside);
        //设置宽高
        assert window != null;
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = mWidth;
        lp.height = mHeight;
        //设置对齐方式
        lp.gravity = mGravity;
        //设置偏移量
        lp.x = DensityUtils.dip2px(getDialog().getContext(), mOffsetX);
        lp.y = DensityUtils.dip2px(getDialog().getContext(), mOffsetY);
        //设置动画
        window.setWindowAnimations(mAnimation);
        window.setAttributes(lp);
    }


    //由子类来实现布局
    protected abstract View setView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    public BHCBaseDialogFragment setDialogConfirmListener(DialogConfirmListener dialogConfirmListener) {
        this.mDialogConfirmListener = dialogConfirmListener;
        return this;
    }

    public BHCBaseDialogFragment setDialogCancelListener(DialogCancelListener dialogCancelListener) {
        this.mDialogCancelListener = dialogCancelListener;
        return this;
    }


    public static abstract class Builder<T extends Builder, D extends BHCBaseDialogFragment> {
        private int mWidth = WRAP_CONTENT;
        private int mHeight = WRAP_CONTENT;
        private int mGravity = CENTER;
        private int mOffsetX = 0;
        private int mOffsetY = 0;
        private int mAnimation = R.style.DialogBaseAnimation;
        private boolean mCanceledOnTouchOutside = false;
        private String mTag = "";

        public T setSize(int mWidth, int mHeight) {
            this.mWidth = mWidth;
            this.mHeight = mHeight;
            return (T) this;
        }

        public T setGravity(int mGravity) {
            this.mGravity = mGravity;
            return (T) this;
        }

        public T setOffsetX(int mOffsetX) {
            this.mOffsetX = mOffsetX;
            return (T) this;
        }

        public T setOffsetY(int mOffsetY) {
            this.mOffsetY = mOffsetY;
            return (T) this;
        }

        public T setAnimation(int mAnimation) {
            this.mAnimation = mAnimation;
            return (T) this;
        }

        public T setCanceledOnTouchOutside(boolean mCanceledOnTouchOutside) {
            this.mCanceledOnTouchOutside = mCanceledOnTouchOutside;
            return (T) this;
        }

        public T setTag(String mTag) {
            this.mTag = mTag;
            return (T) this;
        }

        public abstract D build();

    }


    /**
     * 确认/取消 回调
     *
     * @param <T>
     */
    public interface DialogConfirmListener<T> {
        void result(T result);
    }

    /**
     * 关闭dialog回调
     */
    public interface DialogCancelListener {
        void dismiss();
    }

}