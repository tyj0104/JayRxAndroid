package com.library.jay.jayrxandroid;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.library.jay.jayrxandroid.view.BaseActivity;


/**
 * Created by zhujunlin on 2018/1/10.
 */

public class MyActivity extends BaseActivity {

    private TextView tv_title;
    private ImageView iv_back;
    private FrameLayout fl_container;
    private LinearLayout ll_base;
    private ViewGroup mContentView;
    private MyActivity mContext;
    private ProgressDialog loadingDialog;
    private View mLoad;
    private ProgressView mIvLoad;
    private TextView mTvError;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_base2);
        mContentView = (ViewGroup) findViewById(android.R.id.content);
        mContext = this;
        addActivity();
        initView(mContentView);
    }

    private void addActivity() {


    }

    @Override
    public void setContentView(int layoutResID) {
        if (ll_base != null) {
            View view = LayoutInflater.from(this).inflate(layoutResID, ll_base, false);
            fl_container.setId(android.R.id.content);
            mContentView.setId(View.NO_ID);
            fl_container.removeAllViews();
            fl_container.addView(view);
        }
    }

    private void initView(View view) {
        ll_base = (LinearLayout) view.findViewById(R.id.ll_base);
        tv_title = (TextView) view.findViewById(R.id.tv_title);
        iv_back = (ImageView) view.findViewById(R.id.iv_back);
        iv_back.setOnClickListener(v -> finish());
        fl_container = (FrameLayout) view.findViewById(R.id.fl_container);

        mLoad = view.findViewById(R.id.rll_loading);
        mIvLoad = (ProgressView) view.findViewById(R.id.iv_loading);
        mTvError = (TextView) view.findViewById(R.id.tv_info);

    }


    protected void showBack() {
        iv_back.setVisibility(View.VISIBLE);
    }

    protected void hideBack() {
        iv_back.setVisibility(View.GONE);
    }

    protected void setTitleString(String title) {
        tv_title.setText(title);
    }

    protected void setTitleId(int id) {
        setTitleString(getString(id));
    }

    protected void setRlLoadviewHide() {
        mLoad.setVisibility(View.GONE);
    }

    protected void setLoadError(String chart) {
        mIvLoad.setVisibility(View.GONE);
        mTvError.setText(chart);
    }

    protected void setOnBackClickListener(View.OnClickListener listener) {
        iv_back.setOnClickListener(listener);
    }


    @Override
    protected void onDestroy() {

        super.onDestroy();
    }

    public void removeAllactivity() {
    }


    //点空白处不会消失的
    public void showLoadingDialog(Context context, String message) {
        if (!checkViewer(context)) {
            return;
        }

        if (null == loadingDialog) {
            loadingDialog = new ProgressDialog(context);
            loadingDialog.setCanceledOnTouchOutside(false);
        }
        loadingDialog.setMessage(message);
        loadingDialog.show();
    }

    public void cancelLoadingDialog(Context context) {
        if (!checkViewer(context)) {
            return;
        }
        if (null != loadingDialog) {
            loadingDialog.cancel();
        }
    }

    /**
     * 检测context是否为null
     * 因为在Viewer销毁时会调用onViewerDestroy,会置空context,所以如果context不是null,则说明Viewer还存在
     *
     * @param context
     */
    private boolean checkViewer(Context context) {
        if (null == context) {
            return false;
        }
        return !(context instanceof Activity && isActivityFinishingOrDestroy((Activity) context));
    }


    private boolean isActivityFinishingOrDestroy(Activity activity) {
        if (Build.VERSION.SDK_INT >= 17) {
            return activity.isFinishing() || activity.isDestroyed();
        } else {
            return activity.isFinishing();
        }
    }

}
