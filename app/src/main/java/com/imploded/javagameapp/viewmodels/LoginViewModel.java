package com.imploded.javagameapp.viewmodels;

import com.imploded.javagameapp.interfaces.OnLoginCallback;
import com.imploded.javagameapp.interfaces.OnUpdateUiCallback;
import com.imploded.javagameapp.repository.LoginRepository;
import com.imploded.javagameapp.utils.Utils;

/**
 * Created by Mikael on 2017-12-04.
 */

public class LoginViewModel {
    private OnUpdateUiCallback _updateUiCallback;

    private String _userName;
    public String get_userName() {
        return _userName;
    }
    public void set_userName(String value) {
        _userName = value;
        _updateUiCallback.updateUi(IsValid());
    }

    private String _password;
    public String get_password() {
        return _password;
    }
    public void set_password(String value) {
        _password = value;
        _updateUiCallback.updateUi(IsValid());
    }

    private boolean IsValid() {
        return Utils.IsNotEmpty(_userName) && Utils.IsNotEmpty(_password);
    }

    public LoginViewModel(OnUpdateUiCallback updateUiCallback) {
        _updateUiCallback = updateUiCallback;
    }

    public void login(OnLoginCallback loginCallback) {
        LoginRepository repository = new LoginRepository();
        repository.login(_userName, _password, loginCallback);
    }
}
