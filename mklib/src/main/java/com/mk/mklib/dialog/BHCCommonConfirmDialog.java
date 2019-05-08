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
    private static final String PARAM_TITLE = "title";
    private static final String PARAM_MESSAGE = "message";

    private static final String ISVISIBLE_LEFT_TEXT = "is_visible_left_text";

    @Override

    protected View setView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bhc_dialog_common_confirm, container, false);

        TextView titleTv = view.findViewById(R.id.title);
        TextView messageTv = view.findViewById(R.id.message);

        if (!TextUtils.isEmpty(getArguments().getString(PARAM_TITLE))) {
            titleTv.setVisibility(View.VISIBLE);
            titleTv.setText(getArguments().getString(PARAM_TITLE));
        }
        if (!TextUtils.isEmpty(getArguments().getString(PARAM_MESSAGE))) {
            messageTv.setText(getArguments().getString(PARAM_MESSAGE));
        }
        setBottomButton(view);

        return view;
    }

    private void setBottomButton(View view) {
        Button cancelBtn = view.findViewById(R.id.cancel_btn);
        final Button confirmBtn = view.findViewById(R.id.confirm_btn);
        if (getArguments() != null) {
            //判断是否需要隐藏其中一个按钮
            if (getArguments().getBoolean(ISVISIBLE_LEFT_TEXT)) {
                cancelBtn.setVisibility(View.GONE);
            } else {
                cancelBtn.setVisibility(View.VISIBLE);
                cancelBtn.setText(getArguments().getString(LEFT_TEXT));
                cancelBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (mDialogDismissListener != null) {
                            dismiss();
                        }
                    }
                });
            }
            confirmBtn.setText(getArguments().getString(RIGHT_TEXT));
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
        bundle.putString(PARAM_TITLE, builder.mTitle);
        bundle.putString(PARAM_MESSAGE, builder.mMessage);
        bundle.putBoolean(ISVISIBLE_LEFT_TEXT, builder.isVisibleLeftText);
        dialog.setArguments(bundle);
        return dialog;
    }

    public static Builder newConfirmBuilder() {
        return new Builder();
    }

    public static class Builder extends BHCBaseDialogFragment.Builder<Builder, BHCCommonConfirmDialog> {
        private String mTitle = "";
        private String mMessage;
        private String leftText;
        private String rightText;
        private Boolean isVisibleLeftText = false;

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

        public Builder setIsVisibleLeftText(Boolean isVisibleLeftText) {
            this.isVisibleLeftText = isVisibleLeftText;
            return this;
        }

        @Override
        public BHCCommonConfirmDialog build() {
            return BHCCommonConfirmDialog.getInstance(this);
        }
    }
}
