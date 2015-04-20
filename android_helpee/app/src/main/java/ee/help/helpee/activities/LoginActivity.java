package ee.help.helpee.activities;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import ee.help.helpee.R;
import ee.help.helpee.dagger.LoginModule;
import ee.help.helpee.dagger.components.DaggerLoginComponent;
import ee.help.helpee.errors.ErrorType;
import ee.help.helpee.mvp.presenters.LoginPresenter;
import ee.help.helpee.mvp.views.LoginView;

/**
 * Created by ian on 19/04/15.
 */
public class LoginActivity extends BaseActivity implements LoginView{

    @InjectView(R.id.login_button)
    LoginButton loginButton;

    CallbackManager callbackManager;

    @Inject
    LoginPresenter loginPresenter;


    FragmentManager fm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        ButterKnife.inject(this);

        DaggerLoginComponent.builder().loginModule(new LoginModule(this)).build().inject(this);
        fm = getFragmentManager();

        callbackManager = CallbackManager.Factory.create();

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Toast.makeText(LoginActivity.this,
                        loginResult.getAccessToken().getToken() + "User id:" + loginResult.getAccessToken().getUserId(), Toast.LENGTH_LONG)
                        .show();
            }

            @Override
            public void onCancel() {
                Toast.makeText(LoginActivity.this, "CANCELED", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(FacebookException exception) {
                Toast.makeText(LoginActivity.this, exception.toString(), Toast.LENGTH_LONG).show();
            }
        });

    }


    @Override
    public void showError(ErrorType message) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);

    }
}
