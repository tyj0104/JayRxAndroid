package com.library.jay.jayrxandroid;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;


import com.jakewharton.rxbinding2.view.RxView;
import com.library.jay.jayrxandroid.databinding.ActivitySecondBinding;
import com.library.jay.jayrxandroid.modle.RxBus;
import com.library.jay.jayrxandroid.view.BaseActivity;

/**
 * Created by jay on 2018/4/26.
 */

public class SecondActivity extends BaseActivity {
private ActivitySecondBinding mBinding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding =  DataBindingUtil.setContentView(this,R.layout.activity_second);
        init();


    }

    private void init() {
        RxView.clicks(mBinding.update).subscribe(
                v ->{
                    RxBus.getInstance().post("flag","在secondActivity中修改了");
                }
        );
        RxView.clicks(mBinding.back).subscribe(
                v ->{
                    this.finish();
                }
        );
    }
}
