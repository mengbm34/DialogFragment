package com.mk.mklib.dialog;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

import com.mk.mklib.R;

/**
 * created by mbm on 2019/5/6
 * 带webview的弹框
 */
public class BHCWithWebViewDialog extends BHCBaseDialogFragment {

    private static final String CONFIRM_BTN_TEXT = "confirm_btn_text";
    private static final String CANCEL_BTN_TEXT = "cancle_btn_text";
    private static final String TITLE = "title";
    private static final String URL = "url";

    @Override

    protected View setView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bhc_dialog_with_webview, container, false);
        initView(view);

        return view;
    }

    private void initView(View view) {
        TextView titleTv = view.findViewById(R.id.title);
        WebView webView = view.findViewById(R.id.web);
        Button cancelBtn = view.findViewById(R.id.cancel_btn);
        final Button confirmBtn = view.findViewById(R.id.confirm_btn);
        if (getArguments() != null) {
            if (!TextUtils.isEmpty(getArguments().getString(TITLE))) {
                titleTv.setText(getArguments().getString(TITLE));
            }
            cancelBtn.setText(getArguments().getString(CANCEL_BTN_TEXT));
            load(webView, getArguments().getString(URL));
        }
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();

            }
        });
        confirmBtn.setText(getArguments().getString(CONFIRM_BTN_TEXT));
        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mDialogConfirmListener != null) {
                    mDialogConfirmListener.result(confirmBtn.getText().toString());
                    dismiss();
                }
            }
        });
    }

    @SuppressLint({"JavascriptInterface", "SetJavaScriptEnabled"})
    private void load(WebView webView, String loadUrl) {
        WebSettings webSettings = webView.getSettings();
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); //设置缓存
        webSettings.setJavaScriptEnabled(true);//设置能够解析Javascript
        webSettings.setDomStorageEnabled(true);//设置适应Html5 //重点是这个设置
        webView.setVerticalScrollBarEnabled(false);//垂直滚动条不显示
        webView.loadUrl(loadUrl);
    }


    private static BHCWithWebViewDialog getInstance(Builder builder) {
        BHCWithWebViewDialog dialog = new BHCWithWebViewDialog();
        Bundle bundle = getArgumentBundle(builder);
        bundle.putString(CANCEL_BTN_TEXT, builder.cancelBtnText);
        bundle.putString(CONFIRM_BTN_TEXT, builder.confirmBtnText);
        bundle.putString(TITLE, builder.mTitle);
        bundle.putString(URL, builder.url);
        dialog.setArguments(bundle);
        return dialog;
    }

    public static Builder newWithWebViewDialog() {
        return new Builder();
    }

    public static class Builder extends BHCBaseDialogFragment.Builder<Builder, BHCWithWebViewDialog> {
        private String mTitle = "";
        private String url;
        private String cancelBtnText = "";
        private String confirmBtnText = "";

        public <T> Builder setTitle(T title) {
            this.mTitle = (String) title;
            return this;
        }

        public <T> Builder setUrl(T url) {
            this.url = (String) url;
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

        @Override
        public BHCWithWebViewDialog build() {
            return BHCWithWebViewDialog.getInstance(this);
        }

    }
}
