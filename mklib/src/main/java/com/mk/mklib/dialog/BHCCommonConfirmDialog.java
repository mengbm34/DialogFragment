package com.mk.mklib.dialog;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.mk.mklib.R;


/**
 * created by mbm on 2019/5/6
 */
public class BHCCommonConfirmDialog extends BHCBaseDialogFragment {

    private static final String LEFT_TEXT = "left_text";
    private static final String RIGHT_TEXT = "right_text";
    private static final String TITLE = "title";
    private static final String MESSAGE = "message";

    private static final String ISSHOW_LEFT_BTN = "is_show_left_btn";

    @Override

    protected View setView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bhc_dialog_common_confirm, container, false);

        initView(view);

        return view;
    }

    private void initView(View view) {
        TextView titleTv = view.findViewById(R.id.title);
        TextView messageTv = view.findViewById(R.id.message);
        Button cancelBtn = view.findViewById(R.id.cancel_btn);
        final Button confirmBtn = view.findViewById(R.id.confirm_btn);

        if (getArguments() != null) {
            if (!TextUtils.isEmpty(getArguments().getString(TITLE))) {
                titleTv.setVisibility(View.VISIBLE);
                titleTv.setText(getArguments().getString(TITLE));
            }
            if (!TextUtils.isEmpty(getArguments().getString(MESSAGE))) {
                messageTv.setText(getArguments().getString(MESSAGE));
            }

            confirmBtn.setText(getArguments().getString(RIGHT_TEXT));
            cancelBtn.setText(getArguments().getString(LEFT_TEXT));
            //判断是否需要隐藏其中一个按钮
            if (!getArguments().getBoolean(ISSHOW_LEFT_BTN)) {
                cancelBtn.setVisibility(View.GONE);
            } else {
                cancelBtn.setVisibility(View.VISIBLE);
                cancelBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (mDialogDismissListener != null) {
                            dismiss();
                        }
                    }
                });
            }

            confirmBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mDialogResultListener != null) {
                        mDialogResultListener.result(confirmBtn.getText().toString());
                        dismiss();
                    }
                }
            });
        }

    }

    private static BHCCommonConfirmDialog getInstance(Builder builder) {
        BHCCommonConfirmDialog dialog = new BHCCommonConfirmDialog();
        Bundle bundle = getArgumentBundle(builder);
        bundle.putString(LEFT_TEXT, builder.leftText);
        bundle.putString(RIGHT_TEXT, builder.rightText);
        bundle.putString(TITLE, builder.mTitle);
        bundle.putString(MESSAGE, builder.mMessage);
        bundle.putBoolean(ISSHOW_LEFT_BTN, builder.isShowLeftBtn);
        dialog.setArguments(bundle);
        return dialog;
    }

    public static Builder newConfirmBuilder() {
        return new Builder();
    }

    public static class Builder extends BHCBaseDialogFragment.Builder<Builder, BHCCommonConfirmDialog> {
        private String mTitle = "";
        private String mMessage;
        private String leftText = "取消";
        private String rightText = "确定";
        private Boolean isShowLeftBtn = true;

        public Builder setTitle(String title) {
            this.mTitle = title;
            return this;
        }

        public Builder setMessage(String message) {
            mMessage = message;
            return this;
        }

        public Builder setLeftText(String leftText) {
            this.leftText = leftText;
            return this;
        }

        public Builder setRightText(String rightText) {
            this.rightText = rightText;
            return this;
        }

        public Builder setIsShowLeftBtn(Boolean isShowLeftBtn) {
            this.isShowLeftBtn = isShowLeftBtn;
            return this;
        }

        @Override
        public BHCCommonConfirmDialog build() {
            return BHCCommonConfirmDialog.getInstance(this);
        }
    }
}
