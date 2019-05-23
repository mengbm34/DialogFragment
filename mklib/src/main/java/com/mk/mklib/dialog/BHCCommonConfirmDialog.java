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
 * 确认/取消弹框
 */
public class BHCCommonConfirmDialog extends BHCBaseDialogFragment {

    private static final String TITLE = "title";
    private static final String MESSAGE = "message";
    private static final String IS_SHOW_LEFT_BTN = "is_show_left_btn";

    private static final String CONFIRM_BTN_TEXT = "confirm_btn_text";
    private static final String CANCEL_BTN_TEXT = "cancle_btn_text";

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

            confirmBtn.setText(getArguments().getString(CONFIRM_BTN_TEXT));
            cancelBtn.setText(getArguments().getString(CANCEL_BTN_TEXT));
            //判断是否需要隐藏其中一个按钮
            if (!getArguments().getBoolean(IS_SHOW_LEFT_BTN)) {
                cancelBtn.setVisibility(View.GONE);
            } else {
                cancelBtn.setVisibility(View.VISIBLE);
                cancelBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dismiss();
                    }
                });
            }

            confirmBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mDialogConfirmListener != null) {
                        mDialogConfirmListener.result("");
                        dismiss();
                    }
                }
            });
        }

    }


    private static BHCCommonConfirmDialog getInstance(Builder builder) {
        BHCCommonConfirmDialog dialog = new BHCCommonConfirmDialog();
        Bundle bundle = getArgumentBundle(builder);
        bundle.putString(CANCEL_BTN_TEXT, builder.cancelBtnText);
        bundle.putString(CONFIRM_BTN_TEXT, builder.confirmBtnText);
        bundle.putString(TITLE, builder.mTitle);
        bundle.putString(MESSAGE, builder.mMessage);
        bundle.putBoolean(IS_SHOW_LEFT_BTN, builder.isShowLeftBtn);
        dialog.setArguments(bundle);
        return dialog;
    }

    public static Builder newConfirmBuilder() {
        return new Builder();
    }

    public static class Builder extends BHCBaseDialogFragment.Builder<Builder, BHCCommonConfirmDialog> {
        private String mTitle = "";
        private String mMessage;
        private String cancelBtnText = "取消";
        private String confirmBtnText = "确定";
        private Boolean isShowLeftBtn = true;

        public <T> Builder setTitle(T title) {
            this.mTitle = (String) title;
            return this;
        }

        public <T> Builder setMessage(T message) {
            mMessage = (String) message;
            return this;
        }

        public <T> Builder setCancelBtnText(T cancelBtnText) {
            this.cancelBtnText = (String) cancelBtnText;
            return this;
        }

        public <T> Builder setConfirmBtnText(T confirmBtnText) {
            this.confirmBtnText = (String) confirmBtnText;
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
