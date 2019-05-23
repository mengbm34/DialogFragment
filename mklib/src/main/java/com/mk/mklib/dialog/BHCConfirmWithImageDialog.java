package com.mk.mklib.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.mk.mklib.R;


/**
 * created by mbm on 2019/5/6
 */
public class BHCConfirmWithImageDialog extends BHCBaseDialogFragment {


    @Override

    protected View setView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bhc_dialog_confirm_with_image, container, false);
        Button btn = view.findViewById(R.id.btn_confirm);
        if (getArguments() != null) {
            btn.setText(getArguments().getString("btnText"));
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mDialogConfirmListener != null) {
                        dismiss();
                    }
                }
            });
        }

        return view;
    }


    private static BHCConfirmWithImageDialog getInstance(Builder builder) {
        BHCConfirmWithImageDialog dialog = new BHCConfirmWithImageDialog();
        Bundle bundle = getArgumentBundle(builder);
        bundle.putString("btnText", builder.btnText);
        dialog.setArguments(bundle);
        return dialog;
    }

    public static Builder newConfirmBuilder() {
        return new Builder();
    }

    public static class Builder extends BHCBaseDialogFragment.Builder<Builder, BHCConfirmWithImageDialog> {

        private String btnText;

        public Builder setBtnText(String btnText) {
            this.btnText = btnText;
            return this;
        }


        @Override
        public BHCConfirmWithImageDialog build() {
            return BHCConfirmWithImageDialog.getInstance(this);
        }
    }
}
