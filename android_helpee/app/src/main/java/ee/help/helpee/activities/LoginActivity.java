package ee.help.helpee.activities;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import ee.help.helpee.HelpeeApplication;
import ee.help.helpee.R;
import ee.help.helpee.dagger.LoginModule;
import ee.help.helpee.dagger.components.DaggerLoginComponent;
import ee.help.helpee.errors.ErrorType;
import ee.help.helpee.models.User;
import ee.help.helpee.mvp.presenters.LoginPresenter;
import ee.help.helpee.mvp.views.LoginView;

/**
 * Created by ian on 19/04/15.
 */
public class LoginActivity extends BaseActivity implements LoginView {

    @InjectView(R.id.facebook_login_button)
    LoginButton loginButton;

    CallbackManager callbackManager;

    @Inject
    LoginPresenter loginPresenter;


    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        ButterKnife.inject(this);

        DaggerLoginComponent.builder().loginModule(new LoginModule(this)).build().inject(this);

        /*Facebook callbacks*/
        callbackManager = CallbackManager.Factory.create();

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                loginPresenter.loginUserWithFacebookToken(loginResult.getAccessToken().getToken());
            }

            @Override
            public void onCancel() {
            }

            @Override
            public void onError(FacebookException exception) {
                showError(ErrorType.CONNECTION_ERROR);
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);

    }

    @Override
    public void userLoggedIn(User user) {
        loginPresenter.saveUser(user);
        HelpeeApplication.setUserInstance(user);
        Intent openHome = new Intent(this, MainActivity.class);
        startActivity(openHome);
        finish();

    }
}
