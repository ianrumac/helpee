package ee.help.helpee.activities;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.parse.Parse;
import com.parse.ParseInstallation;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import ee.help.helpee.HelpeeApplication;
import ee.help.helpee.R;
import ee.help.helpee.dagger.LoginModule;
import ee.help.helpee.dagger.components.DaggerLoginComponent;
import ee.help.helpee.errors.ErrorType;
import ee.help.helpee.fragments.RegisterFragment;
import ee.help.helpee.fragments.UserLoginFragment;
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


    FragmentManager fragmentManager = getSupportFragmentManager();

    @InjectView(R.id.login_text)
    TextView loginText;

    @InjectView(R.id.login_button)
    TextView normalLoginButton;

    @InjectView(R.id.create_account)
    TextView createAccount;


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

                loginPresenter.loginUserWithFacebookToken(loginResult.getAccessToken().getToken(),
                        ParseInstallation.getCurrentInstallation().getInstallationId()
                );
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


    @OnClick(R.id.login_button)
    void openLoginFragment() {
        fragmentManager.beginTransaction()
                .add(R.id.fragment_container, new UserLoginFragment())
                .addToBackStack("")
                .commit();

        enableDisableButtons(false);

    }


    public void enableDisableButtons(boolean enable) {


        if (enable) {
            normalLoginButton.setVisibility(View.VISIBLE);
            createAccount.setVisibility(View.VISIBLE);
            loginButton.setVisibility(View.VISIBLE);
        } else {
            normalLoginButton.setVisibility(View.GONE);
            createAccount.setVisibility(View.GONE);
            loginButton.setVisibility(View.GONE);
        }


    }



    @OnClick(R.id.create_account)
    void openRegistration() {
        enableDisableButtons(false);
        fragmentManager.beginTransaction()
                .add(R.id.fragment_container, new RegisterFragment())
                .addToBackStack("")
                .commit();
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
