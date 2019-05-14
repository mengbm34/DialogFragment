package com.mk.mklib.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mk.mklib.R;


/**
 * created by mbm on 2019/5/6
 * 加载弹框
 */
public class BHCCommonLoadingDialog extends BHCBaseDialogFragment {


    @Override
    protected View setView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.bhc_dialog_loading, container, false);
    }


    private static BHCCommonLoadingDialog getInstance(Builder builder) {
        BHCCommonLoadingDialog dialog = new BHCCommonLoadingDialog();
        Bundle bundle = getArgumentBundle(builder);
        dialog.setArguments(bundle);
        return dialog;
    }

    public static Builder newBHCCommonLoadingDialog() {
        return new Builder();
    }

    public static class Builder extends BHCBaseDialogFragment.Builder<Builder, BHCCommonLoadingDialog> {

        @Override
        public BHCCommonLoadingDialog build() {
            return BHCCommonLoadingDialog.getInstance(this);
        }
    }
}
