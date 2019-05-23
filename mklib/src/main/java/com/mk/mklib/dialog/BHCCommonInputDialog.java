package com.mk.mklib.dialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.mk.mklib.R;

/**
 * created by mbm on 2019/5/6
 * 带输入框的弹框
 */
public class BHCCommonInputDialog extends BHCBaseDialogFragment {

    public static Builder newInputBuilder() {
        return new Builder();
    }

    private static BHCCommonInputDialog getInstance(Builder builder) {
        BHCCommonInputDialog dialog = new BHCCommonInputDialog();
        Bundle bundle = getArgumentBundle(builder);
        bundle.putString("hint", builder.mHint);
        bundle.putString("hint1", builder.mHint1);
        bundle.putString("title", builder.title);
        bundle.putString("subTitle", builder.subTitle);
        bundle.putBoolean("isShow", builder.isShowLastEdit);
        bundle.putBoolean("isShowSubTitle", builder.isShowSubTitle);
        dialog.setArguments(bundle);
        return dialog;
    }

    public static class Builder extends BHCBaseDialogFragment.Builder<Builder, BHCCommonInputDialog> {

        private String mHint;
        private String mHint1;
        private String title;
        private String subTitle;
        private Boolean isShowLastEdit = false;
        private Boolean isShowSubTitle = false;

        public <T> Builder setHint(T hint) {
            mHint = (String) hint;
            return this;
        }

        public <T>Builder setHint1(T hint1) {
            mHint1 = (String) hint1;
            return this;
        }

        public <T>Builder setTitle(T title) {
            this.title = (String) title;
            return this;
        }

        public <T> Builder setSubTitle(T subTitle) {
            this.subTitle = (String) subTitle;
            return this;
        }

        public Builder setIsShowLastEdit(Boolean isShowLastEdit) {
            this.isShowLastEdit = isShowLastEdit;
            return this;
        }

        public Builder setIsShowSubTitle(Boolean isShowSubTitle) {
            this.isShowSubTitle = isShowSubTitle;
            return this;
        }


        @Override
        public BHCCommonInputDialog build() {
            return BHCCommonInputDialog.getInstance(this);
        }
    }

    @Override
    protected View setView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bhc_dialog_common_input, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        TextView title = view.findViewById(R.id.tv_title);
        TextView sub_title = view.findViewById(R.id.tv_subtitle);
        final EditText et_1 = view.findViewById(R.id.et_1);
        final EditText et_2 = view.findViewById(R.id.et_2);
        Button cancelBtn = view.findViewById(R.id.cancel_btn);
        Button confirmBtn = view.findViewById(R.id.confirm_btn);


        if (getArguments() != null) {
            if (!TextUtils.isEmpty(getArguments().getString("title"))) {
                title.setText(getArguments().getString("title"));
            }
            et_1.setHint(getArguments().getString("hint"));
            et_2.setHint(getArguments().getString("hint1"));

            if (getArguments().getBoolean("isShowSubTitle")) {
                sub_title.setVisibility(View.VISIBLE);
                sub_title.setText(getArguments().getString("subTitle"));
            }
            if (getArguments().getBoolean("isShow")) {
                et_2.setVisibility(View.VISIBLE);
            }

            cancelBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dismiss();
                }
            });
            confirmBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mDialogConfirmListener != null) {
                        if (getArguments().getBoolean("isShow")) {
                            mDialogConfirmListener.result(et_1.getText().toString().trim() + et_2.getText().toString().trim());
                        } else {
                            mDialogConfirmListener.result(et_1.getText().toString().trim());
                        }

                        dismiss();
                    }
                }
            });
        }
    }
}

