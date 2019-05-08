package com.mk.dialogfragmentdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.mk.mklib.dialog.BHCBaseDialogFragment;
import com.mk.mklib.dialog.BHCCommonConfirmDialog;
import com.mk.mklib.dialog.BHCConfirmWithImageDialog;
import com.mk.mklib.dialog.BHCCommonInputDialog;
import com.mk.mklib.dialog.BHCWithWebViewDialog;
import com.mk.mklib.dialog.BHCDateDialog;
import com.mk.mklib.utils.DensityUtils;
import com.mk.mklib.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * created by mbm on 2019/5/7
 */
public class TestDialogActivity extends AppCompatActivity {

    Button btn_confirm;
    Button btn_confirm_with_image;
    Button btn_webview;
    Button btn_edit;
    Button btn_edit_tel;
    Button btn_date;
    Button btn_year;
    Button btn_month;
    Button btn_day;
    Button btn_common;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_dialog);

        btn_confirm = findViewById(R.id.confirm_dialog);
        btn_confirm_with_image = findViewById(R.id.confirm_with_image_dialog);
        btn_webview = findViewById(R.id.webview_dialog);
        btn_edit = findViewById(R.id.edit_dialog);
        btn_edit_tel = findViewById(R.id.edit_tel_dialog);
        btn_date = findViewById(R.id.date_dialog);
        btn_year = findViewById(R.id.year_dialog);
        btn_month = findViewById(R.id.month_dialog);
        btn_day = findViewById(R.id.day_dialog);
        btn_common = findViewById(R.id.common_dialog);

        btn_webview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BHCWithWebViewDialog.newWithWebViewDialog()
                        .setTitle("条款公约")
                        .setRightText("同意")
                        .setLeftText("不同意")
                        .setUrl("https://www.baidu.com")
                        .build()
                        .setDialogResultListener(new BHCBaseDialogFragment.DialogResultListener() {
                            @Override
                            public void result(Object result) {

                            }
                        })
                        .setDialogDismissListener(new BHCBaseDialogFragment.DialogDismissListener() {
                            @Override
                            public void dismiss() {

                            }
                        })
                        .show(getSupportFragmentManager(), "withwebviewdialog");
            }
        });

        //确认dialog
        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BHCCommonConfirmDialog.newConfirmBuilder()
                        .setTitle("提示")//标题不设置默认隐藏
                        .setLeftText("暂不更新")
                        .setRightText("立即更新")
                        .setMessage("发现新版本")
                        .build()
                        //点击确认/取消回调监听
                        .setDialogResultListener(new BHCBaseDialogFragment.DialogResultListener<String>() {
                            @Override
                            public void result(String result) {
                                Toast.makeText(TestDialogActivity.this, "你点击了" + result, Toast.LENGTH_SHORT).show();
                            }
                        })
                        //关闭dialogfragment回调
                        .setDialogDismissListener(new BHCBaseDialogFragment.DialogDismissListener() {
                            @Override
                            public void dismiss() {
                            }
                        })
                        .show(getSupportFragmentManager(), "confirmDialog");
            }
        });


        btn_confirm_with_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BHCConfirmWithImageDialog.newConfirmBuilder()
                        .setBtnText("按钮")
                        .build()
                        .show(getSupportFragmentManager(), "confirmwithimageDialog");
            }
        });

        //带编辑框dialog
        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BHCCommonInputDialog.newInputBuilder()
                        .setHint("请输入...")
                        .setTitle("联系方式")
                        .setIsShowLastEdit(false)
                        .setSize((int) (ScreenUtils.getScreenWidth(TestDialogActivity.this) * 0.8),
                                DensityUtils.dip2px(TestDialogActivity.this, 150))
                        .build()
                        .setDialogResultListener(new BHCBaseDialogFragment.DialogResultListener<String>() {
                            @Override
                            public void result(String result) {
                                if (!TextUtils.isEmpty(result)) {
                                    Toast.makeText(TestDialogActivity.this, "你输入了：" + result, Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .setDialogDismissListener(new BHCBaseDialogFragment.DialogDismissListener() {
                            @Override
                            public void dismiss() {

                            }
                        })
                        .show(getSupportFragmentManager(), "inputDialog");
            }
        });

        //带编辑框dialog
        btn_edit_tel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BHCCommonInputDialog.newInputBuilder()
                        .setHint("区号")
                        .setHint1("座机号码")
                        .setTitle("请输入联系人方式")
                        .setIsShowLastEdit(true)
                        .setSize((int) (ScreenUtils.getScreenWidth(TestDialogActivity.this) * 0.8),
                                DensityUtils.dip2px(TestDialogActivity.this, 150))
                        .build()
                        .setDialogResultListener(new BHCBaseDialogFragment.DialogResultListener<String>() {
                            @Override
                            public void result(String result) {
                                if (!TextUtils.isEmpty(result)) {
                                    Toast.makeText(TestDialogActivity.this, "你输入了：" + result, Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .setDialogDismissListener(new BHCBaseDialogFragment.DialogDismissListener() {
                            @Override
                            public void dismiss() {

                            }
                        })
                        .show(getSupportFragmentManager(), "inputDialog");
            }
        });

        //年月日dialog
        btn_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BHCDateDialog.newYearDialogBuilder()
                        .setType("date")
                        .setSize((int) (ScreenUtils.getScreenWidth(TestDialogActivity.this) * 0.8),
                                DensityUtils.dip2px(TestDialogActivity.this, 200))
                        .build()
                        .setDialogResultListener(new BHCBaseDialogFragment.DialogResultListener<String>() {
                            @Override
                            public void result(String result) {
                                Toast.makeText(TestDialogActivity.this, "你输入了：" + result, Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setDialogDismissListener(new BHCBaseDialogFragment.DialogDismissListener() {
                            @Override
                            public void dismiss() {
                            }
                        })
                        .show(getSupportFragmentManager(), "yearMonthDayDialog");
            }
        });

        //年dialog
        btn_year.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BHCDateDialog.newYearDialogBuilder()
                        .setType("year")
                        .setSelectedYear(2019)
                        .setYearStart(1992)
                        .setYearEnd(2050)
                        .build()
                        .setDialogResultListener(new BHCBaseDialogFragment.DialogResultListener<Integer>() {
                            @Override
                            public void result(Integer result) {
                                Toast.makeText(TestDialogActivity.this, "你输入了：" + result, Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setDialogDismissListener(new BHCBaseDialogFragment.DialogDismissListener() {
                            @Override
                            public void dismiss() {
                            }
                        })
                        .show(getSupportFragmentManager(), "yearMonthDayDialog");
            }
        });

        //月dialog
        btn_month.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BHCDateDialog.newYearDialogBuilder()
                        .setType("month")
                        .setSelectedMonth(3)
                        .build()
                        .setDialogResultListener(new BHCBaseDialogFragment.DialogResultListener<Integer>() {
                            @Override
                            public void result(Integer result) {
                                Toast.makeText(TestDialogActivity.this, "你输入了：" + result, Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setDialogDismissListener(new BHCBaseDialogFragment.DialogDismissListener() {
                            @Override
                            public void dismiss() {
                            }
                        })
                        .show(getSupportFragmentManager(), "yearMonthDayDialog");
            }
        });

        //日dialog
        btn_day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BHCDateDialog.newYearDialogBuilder()
                        .setType("day")
                        .setMonth(3)
                        .setSelectedDay(4)
                        .build()
                        .setDialogResultListener(new BHCBaseDialogFragment.DialogResultListener<Integer>() {
                            @Override
                            public void result(Integer result) {
                                Toast.makeText(TestDialogActivity.this, "你输入了：" + result, Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setDialogDismissListener(new BHCBaseDialogFragment.DialogDismissListener() {
                            @Override
                            public void dismiss() {
                            }
                        })
                        .show(getSupportFragmentManager(), "yearMonthDayDialog");
            }
        });

        //自定义数据dialog
        btn_common.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                List<String> data = new ArrayList<>();
                for (int i = 0; i < 10; i++) {
                    data.add("mk" + i);
                }
                BHCDateDialog.newYearDialogBuilder()
                        .setType("common")
                        .setListData(data)
                        .build()
                        .setDialogResultListener(new BHCBaseDialogFragment.DialogResultListener<String>() {
                            @Override
                            public void result(String result) {
                                Toast.makeText(TestDialogActivity.this, "你输入了：" + result, Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setDialogDismissListener(new BHCBaseDialogFragment.DialogDismissListener() {
                            @Override
                            public void dismiss() {
                            }
                        })
                        .show(getSupportFragmentManager(), "yearMonthDayDialog");
            }
        });
    }
}