基类公共方法
- 设置宽高：setSize()
- 设置显示位置：setGravity()
- 设置动画属性：setAnimation()
- 设置左边按钮监听事件：setDialogDismissListener()
- 设置右边按钮监听事件：setDialogResultListener()


###### 提示dialog（带标题、内容、取消、确认）
基本使用
```
   BHCCommonConfirmDialog.newConfirmBuilder()
                        .setTitle("提示")//标题不设置默认隐藏
                        .setLeftText("暂不更新")
                        .setRightText("立即更新")
                        .setMessage("发现新版本")
                        .build()
                        //点击确认回调监听
                        .setDialogResultListener(new BHCBaseDialogFragment.DialogResultListener<String>() {
                            @Override
                            public void result(String result) {
                                Toast.makeText(TestDialogActivity.this, "你点击了立即更新", Toast.LENGTH_SHORT).show();
                            }
                        })
                        //取消回调监听
                        .setDialogDismissListener(new BHCBaseDialogFragment.DialogDismissListener() {
                            @Override
                            public void dismiss() {
                                Toast.makeText(TestDialogActivity.this, "你点击了暂不更新", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show(getSupportFragmentManager(), "confirmDialog");
```
涉及的方法：
- 设置标题：setTitle()
- 设置内容：setMessage()
- 设置左边按钮文案：setLeftText()
- 设置右边按钮文案：setRightText()
- 设置是否显示左边按钮，默认显示：setIsShowLeftBtn()


##### 带编辑框的dialog
基本使用
```
// 1个输入框
 BHCCommonInputDialog.newInputBuilder()
                        .setHint("请输入...")
                        .setTitle("标题")
                        .setIsShowSubTitle(true)
                        .setSubTitle("hahahahahaha")
                        .setIsShowLastEdit(false)
                        .setSize((int) (ScreenUtils.getScreenWidth(TestDialogActivity.this) * 0.8),
                                DensityUtils.dip2px(TestDialogActivity.this, 200))
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
               
// 2个输入框      
BHCCommonInputDialog.newInputBuilder()
                        .setHint("区号")
                        .setHint1("座机号码")
                        .setTitle("请输入联系人方式")
                        .setIsShowLastEdit(true)
                        .setSize((int) (ScreenUtils.getScreenWidth(TestDialogActivity.this) * 0.8),
                                DensityUtils.dip2px(TestDialogActivity.this, 200))
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
```
涉及方法：
- 设置标题文案：setTitle()
- 设置副标题文案，默认隐藏: setSubTitle()
- 设置编辑框默认显示的文案: setHint()
- 设置是否显示第二个编辑框，默认隐藏: setIsShowLastEdit()
- 设置第二个编辑框默认显示的文案: setHint1()


##### 加载dialog
基本使用

```
  final BHCCommonLoadingDialog loadingDialog = BHCCommonLoadingDialog.newBHCCommonLoadingDialog().build();
        loadingDialog.show(getSupportFragmentManager(), "BHCCommonLoadingDialog");
                // 模拟网络请求，请求结束后取消弹框
                
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadingDialog.dismiss();
                    }
                }, 2000);
```

##### 带WebView的弹框
基本使用

```
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
```
涉及方法
-  设置标题：setTitle()
-  设置左边按钮文案：setLeftText()
-  设置右边按钮文案：setRightText()
-  设置url：setUrl()

