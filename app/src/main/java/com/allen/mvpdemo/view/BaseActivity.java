package com.allen.mvpdemo.view;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 *
 * Created by Allen on 2017/3/22.
 */
public class BaseActivity extends AppCompatActivity implements BaseView {

    ProgressDialog progressDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        progressDialog = new ProgressDialog(this);
    }

    @Override
    public void toast(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showProgress() {
        Toast.makeText(getApplicationContext(), "showProgress", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void dismissProgress() {
        Toast.makeText(getApplicationContext(), "dismissProgress", Toast.LENGTH_SHORT).show();
    }
}
