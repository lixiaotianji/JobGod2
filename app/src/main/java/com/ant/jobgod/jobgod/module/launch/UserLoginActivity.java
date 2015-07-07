package com.ant.jobgod.jobgod.module.launch;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.ImageView;

import com.ant.jobgod.jobgod.R;
import com.ant.jobgod.jobgod.app.BaseActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;
import nucleus.factory.RequiresPresenter;

/**
 * Created by Mr.Jude on 2015/6/6.
 */
@RequiresPresenter(UserLoginPresenter.class)
public class UserLoginActivity extends BaseActivity<UserLoginPresenter> {

    @InjectView(R.id.tilNumber)
    TextInputLayout tilNumber;
    @InjectView(R.id.tilPassword)
    TextInputLayout tilPassword;
    @InjectView(R.id.btnModifyPassword)
    AppCompatButton btnModifyPassword;
    @InjectView(R.id.btnLogin)
    AppCompatButton btnLogin;
    @InjectView(R.id.btnQQ)
    ImageView btnQQ;
    @InjectView(R.id.btnWeiChat)
    ImageView btnWeiChat;
    @InjectView(R.id.btnSina)
    ImageView btnSina;
    @InjectView(R.id.btnRegister)
    AppCompatButton btnRegister;
    @InjectView(R.id.btnBiz)
    AppCompatButton btnBiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.launch_activity_login2);
        ButterKnife.inject(this);
        btnRegister.setOnClickListener((View v) -> getPresenter().register());
        btnModifyPassword.setOnClickListener((View v) -> getPresenter().modifyPassword());
        btnLogin.setOnClickListener((View v) -> checkLogin());
        btnQQ.setOnClickListener((View v) -> getPresenter().loginByQQ());
        btnWeiChat.setOnClickListener((View v) -> getPresenter().loginByWeiChat());
        btnSina.setOnClickListener((View v) -> getPresenter().loginBySina());
        btnBiz.setOnClickListener((View v) -> getPresenter().gotoBiz());
    }

    private void checkLogin() {
        String mNumber = tilNumber.getEditText().getText().toString();
        String mPassword = tilPassword.getEditText().getText().toString();
        if (mNumber.length() != 11) {
            tilNumber.setError("手机号格式错误");
            return;
        } else {
            tilNumber.setError("");
        }
        if (mPassword.length() < 6 || mPassword.length() > 12) {
            tilPassword.setError("密码应为6-12位");
            return;
        } else {
            tilPassword.setError("");
        }
        getPresenter().login(mNumber, mPassword);
    }

    public void setPasswordError() {
        btnModifyPassword.setError("密码错误");
    }

    public void setNumberError() {
        btnModifyPassword.setError("手机号还没有注册");
    }


}