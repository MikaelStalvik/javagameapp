package com.imploded.javagameapp.viewmodels;

import com.imploded.javagameapp.interfaces.OnLoginCallback;
import com.imploded.javagameapp.interfaces.OnUpdateUiCallback;
import com.imploded.javagameapp.repository.LoginRepository;
import com.imploded.javagameapp.utils.Utils;

public class LoginViewModel {
    private OnUpdateUiCallback onUpdateUiCallback;

    private String userName;
    public String getUserName() {
        return userName;
    }
    public void setUserName(String value) {
        userName = value;
        onUpdateUiCallback.updateUi(IsValid());
    }

    private String password;
    public String getPassword() {
        return password;
    }
    public void setPassword(String value) {
        password = value;
        onUpdateUiCallback.updateUi(IsValid());
    }

    private boolean IsValid() {
        return Utils.IsNotEmpty(userName) && Utils.IsNotEmpty(password);
    }

    public LoginViewModel(OnUpdateUiCallback updateUiCallback) {
        onUpdateUiCallback = updateUiCallback;
    }

    public void login(OnLoginCallback loginCallback) {
        onUpdateUiCallback.updateUi(false);
        LoginRepository repository = new LoginRepository();
        repository.login(userName, password, loginCallback);
    }
}
