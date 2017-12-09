package com.imploded.javagameapp.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.imploded.javagameapp.R;
import com.imploded.javagameapp.interfaces.OnLoginCallback;
import com.imploded.javagameapp.interfaces.OnUpdateUiCallback;
import com.imploded.javagameapp.viewmodels.LoginViewModel;

public class LoginActivity extends AppCompatActivity {

    private Button loginButton;

    private LoginViewModel viewModel = new LoginViewModel(new OnUpdateUiCallback() {
        @Override
        public void updateUi(boolean isValid) {
            loginButton.setEnabled(isValid);
        }
    });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginButton = findViewById(R.id.loginButton);
        EditText usernameEditText = findViewById(R.id.userNameEditText);
        EditText passwordEditText = findViewById(R.id.passwordEditText);

        usernameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                viewModel.setUserName(s.toString());
            }
        });
        usernameEditText.setText("mikael");
        passwordEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                viewModel.setPassword(s.toString());
            }
        });
        passwordEditText.setText("12345");

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.login(new OnLoginCallback() {
                    @Override
                    public void OnLogin(boolean isValid) {
                        if (isValid) {
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(LoginActivity.this, "Failed to login!", Toast.LENGTH_SHORT).show();
                            loginButton.setEnabled(true);
                        }
                    }
                });
            }
        });
    }
}
