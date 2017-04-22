package com.williamsumitromytextview.pikachisemarket.activities;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.dd.processbutton.iml.ActionProcessButton;
import com.williamsumitromytextview.pikachisemarket.R;
import com.williamsumitromytextview.pikachisemarket.sql.DatabaseHelper;
import com.williamsumitromytextview.pikachisemarket.validation.inputValidation;

import java.util.Timer;
import java.util.TimerTask;

import com.williamsumitromytextview.pikachisemarket.Session.SessionManagement;

public class Login extends AppCompatActivity implements View.OnClickListener{
    private final AppCompatActivity activity = Login.this;

    private NestedScrollView nestedScrollView;

    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutPassword;

    private TextInputEditText textInputEditTextEmail;
    private TextInputEditText textInputEditTextPassword;

    private ActionProcessButton ButtonLogin;

    private AppCompatTextView textViewLinkRegister;

    private com.williamsumitromytextview.pikachisemarket.validation.inputValidation inputValidation;
    private DatabaseHelper databaseHelper;

    private SessionManagement session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        initViews();
        initListeners();
        initObjects();
    }


    private void initViews() {
        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);
        textInputLayoutEmail = (TextInputLayout) findViewById(R.id.textInputLayoutEmail);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.textInputLayoutPassword);
        textInputEditTextEmail = (TextInputEditText) findViewById(R.id.textInputEditTextEmail);
        textInputEditTextPassword = (TextInputEditText) findViewById(R.id.textInputEditTextPassword);
        ButtonLogin = (ActionProcessButton) findViewById(R.id.btn_Login);
        textViewLinkRegister = (AppCompatTextView) findViewById(R.id.textViewLinkRegister);
    }

    private void initListeners() {
        ButtonLogin.setOnClickListener(this);
        textViewLinkRegister.setOnClickListener(this);
    }
    private void initObjects() {
        databaseHelper = new DatabaseHelper(activity);
        inputValidation = new inputValidation(activity);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_Login:
                verifyFromSQLite();
                break;
            case R.id.textViewLinkRegister:
                Intent intentRegister = new Intent(getApplicationContext(), Register.class);
                startActivity(intentRegister);
                break;
        }
    }
    private void verifyFromSQLite() {
        if (!inputValidation.isInputEditTextFilled(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextEmail(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextPassword, textInputLayoutPassword, getString(R.string.error_message_email))) {
            return;
        }

        if (databaseHelper.checkUser(textInputEditTextEmail.getText().toString().trim()
                , textInputEditTextPassword.getText().toString().trim())) {

            ButtonLogin.setMode(ActionProcessButton.Mode.ENDLESS);
            ButtonLogin.setProgress(1);
            ButtonLogin.setEnabled(false);

            Timer buttonTimer = new Timer();
            buttonTimer.schedule(new TimerTask() {

                @Override
                public void run() {
                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            ButtonLogin.setEnabled(true);
                            ButtonLogin.setMode(ActionProcessButton.Mode.PROGRESS);
                            //Intent accountsIntent = new Intent(activity, MainActivity.class);
                            //accountsIntent.putExtra("EMAIL", textInputEditTextEmail.getText().toString().trim());
                            //startActivity(accountsIntent);

                            session = new SessionManagement(getApplicationContext());
                            session.createLoginSession("nama",textInputEditTextEmail.getText().toString().trim() );

                            emptyInputEditText();
                            // Staring MainActivity
                            Intent i = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(i);
                            finish();
                        }
                    });
                }
            }, 2000);



        } else {
            Snackbar.make(nestedScrollView, getString(R.string.error_valid_email_password), Snackbar.LENGTH_LONG).show();
        }
    }

    private void emptyInputEditText() {
        textInputEditTextEmail.setText(null);
        textInputEditTextPassword.setText(null);
    }
}
