package com.mk.mklib.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.itheima.wheelpicker.WheelPicker;
import com.itheima.wheelpicker.widgets.WheelDatePicker;
import com.itheima.wheelpicker.widgets.WheelDayPicker;
import com.itheima.wheelpicker.widgets.WheelMonthPicker;
import com.itheima.wheelpicker.widgets.WheelYearPicker;
import com.mk.mklib.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * created by mbm on 2019/5/7
 * 年月日滚动选择器,自定义String数据选择器
 */
public class BHCDateDialog extends BHCBaseDialogFragment {
    private String type;
    private List<String> data;

    @Override
    protected View setView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.bhc_dialog_date, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        final WheelDatePicker wheelDatePicker = view.findViewById(R.id.wheelDatePicker);
        final WheelYearPicker wheelYearPicker = view.findViewById(R.id.wheelYearPicker);
        final WheelMonthPicker wheelMonthPicker = view.findViewById(R.id.wheelMonthPicker);
        final WheelDayPicker wheelDayPicker = view.findViewById(R.id.wheelDayPicker);
        final WheelPicker wheelPicker = view.findViewById(R.id.wheelPicker);
        Button btn_cancel = view.findViewById(R.id.cancel_btn);
        Button btn_confirm = view.findViewById(R.id.confirm_btn);

        if (getArguments() != null) {
            type = getArguments().getString("type");
            switch (type) {
                case "date":
                    wheelDatePicker.setAtmospheric(true);
                    wheelDatePicker.setCurtain(true);
                    wheelDatePicker.setCurved(true);
                    wheelDatePicker.setCyclic(false);
                    wheelDatePicker.setItemSpace(50);
                    wheelDatePicker.setSelectedItemTextColor(getDialog().getContext().getResources().getColor(R.color.blue));
                    wheelDatePicker.setItemTextColor(getDialog().getContext().getResources().getColor(R.color.black));
                    break;
                case "year":
                    wheelDatePicker.setVisibility(View.GONE);
                    wheelYearPicker.setVisibility(View.VISIBLE);
                    wheelYearPicker.setYearStart(getArguments().getInt("yearStart"));
                    wheelYearPicker.setYearEnd(getArguments().getInt("yearEnd"));
                    wheelYearPicker.setSelectedYear(getArguments().getInt("selectedYear"));
                    break;
                case "month":
                    wheelDatePicker.setVisibility(View.GONE);
                    wheelMonthPicker.setVisibility(View.VISIBLE);
                    wheelMonthPicker.setSelectedMonth(getArguments().getInt("selectedMonth"));
                    break;
                case "day":
                    wheelDatePicker.setVisibility(View.GONE);
                    wheelDayPicker.setVisibility(View.VISIBLE);
                    wheelDayPicker.setMonth(getArguments().getInt("month"));
                    wheelDayPicker.setSelectedDay(getArguments().getInt("selectedDay"));
                    break;
                case "common":
                    wheelDatePicker.setVisibility(View.GONE);
                    wheelPicker.setVisibility(View.VISIBLE);
                    data = Objects.requireNonNull(getArguments().getStringArrayList("data"));
                    wheelPicker.setData(data);
                    break;
            }


        }


        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDialogConfirmListener != null) {
                    switch (type) {
                        case "date":
                            mDialogConfirmListener.result(new SimpleDateFormat("yyyy-MM-dd")
                                    .format(wheelDatePicker.getCurrentDate()));
                            break;
                        case "year":
                            mDialogConfirmListener.result(wheelYearPicker.getCurrentYear());
                            break;
                        case "month":
                            mDialogConfirmListener.result(wheelMonthPicker.getCurrentMonth());
                            break;
                        case "day":
                            mDialogConfirmListener.result(wheelDayPicker.getCurrentDay());
                            break;
                        case "common":
                            mDialogConfirmListener.result(data.get(wheelPicker.getCurrentItemPosition()));
                            break;
                    }

                    dismiss();
                }
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDialogCancelListener != null) {
                    dismiss();
                }
            }
        });

    }

    private static BHCDateDialog getInstance(Builder builder) {
        BHCDateDialog dialog = new BHCDateDialog();
        Bundle bundle = getArgumentBundle(builder);
        bundle.putInt("yearStart", builder.yearStart);
        bundle.putInt("yearEnd", builder.yearEnd);
        bundle.putInt("selectedYear", builder.selectedYear);
        bundle.putInt("selectedMonth", builder.selectedMonth);
        bundle.putInt("selectedDay", builder.selectedDay);
        bundle.putInt("month", builder.month);
        bundle.putString("type", builder.type);
        bundle.putStringArrayList("data", (ArrayList<String>) builder.data);
        dialog.setArguments(bundle);
        return dialog;
    }

    public static Builder newYearDialogBuilder() {
        return new Builder();
    }

    public static class Builder extends BHCBaseDialogFragment.Builder<Builder, BHCDateDialog> {

        private String type = "date";
        private int yearStart = 1992;
        private int yearEnd = 2050;
        private int selectedYear = 2019;
        private int selectedMonth = 6;
        private int month = 3;
        private int selectedDay = 1;
        private List<String> data = new ArrayList<>();

        public Builder setType(String type) {
            this.type = type;
            return this;
        }

        public Builder setYearStart(int yearStart) {
            this.yearStart = yearStart;
            return this;
        }

        public Builder setYearEnd(int yearEnd) {
            this.yearEnd = yearEnd;
            return this;
        }

        public Builder setSelectedYear(int selectedYear) {
            this.selectedYear = selectedYear;
            return this;
        }

        public Builder setSelectedMonth(int selectedMonth) {
            this.selectedMonth = selectedMonth;
            return this;
        }

        public Builder setMonth(int month) {
            this.month = month;
            return this;
        }

        public Builder setSelectedDay(int selectedDay) {
            this.selectedDay = selectedDay;
            return this;
        }

        public Builder setListData(List<String> data) {
            this.data = data;
            return this;
        }


        @Override
        public BHCDateDialog build() {
            return BHCDateDialog.getInstance(this);
        }
    }
}
