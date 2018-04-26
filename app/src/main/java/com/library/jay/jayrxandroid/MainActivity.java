package com.library.jay.jayrxandroid;


import android.databinding.DataBindingUtil;
import android.os.Bundle;

import com.jakewharton.rxbinding2.view.RxView;
import com.library.jay.jayrxandroid.databinding.ActivityMainBinding;
import com.library.jay.jayrxandroid.modle.RxBus;
import com.library.jay.jayrxandroid.view.BaseActivity;


import io.reactivex.android.schedulers.AndroidSchedulers;

public class MainActivity extends BaseActivity {
    private ActivityMainBinding mainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding =  DataBindingUtil.setContentView(this, R.layout.activity_main);
        init();
    }

    private void init() {
        RxView.clicks(mainBinding.btn).subscribe(
                v ->startActivity(SecondActivity.class)
        );
        RxBus.getInstance().<String>asObservable("flag")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        str ->{
                            mainBinding.tv.setText(str);
                        }
                );
    }
}
