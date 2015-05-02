package ee.help.helpee.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import ee.help.helpee.HelpeeApplication;
import ee.help.helpee.R;
import ee.help.helpee.activities.MainActivity;
import ee.help.helpee.dagger.RegisterModule;
import ee.help.helpee.dagger.UserLoginModule;
import ee.help.helpee.dagger.components.DaggerRegisterComponent;
import ee.help.helpee.dagger.components.DaggerUserLoginComponent;
import ee.help.helpee.models.User;
import ee.help.helpee.mvp.presenters.LoginPresenter;
import ee.help.helpee.mvp.presenters.RegisterPresenter;
import ee.help.helpee.mvp.presenters.UserLoginPresenter;
import ee.help.helpee.mvp.views.LoginView;
import ee.help.helpee.mvp.views.RegisterView;
import retrofit.mime.TypedFile;

/**
 * Created by ian on 25/04/15.
 */
public class UserLoginFragment extends BaseFragment implements LoginView {

    @InjectView(R.id.password)
    MaterialEditText passwordInput;

    @InjectView(R.id.user_name)
    MaterialEditText usernameInput;


    @Inject
    UserLoginPresenter loginPresenter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View contentView = inflater.inflate(R.layout.fragment_login, container, false);
        DaggerUserLoginComponent.builder().userLoginModule(new UserLoginModule(this)).build().inject(this);
        ButterKnife.inject(this, contentView);
        return contentView;
    }

    @OnClick(R.id.login_button)
    void loginUser() {
        loginPresenter.loginUser(usernameInput.getText().toString(), passwordInput.getText().toString());
    }


    @Override
    public void userLoggedIn(User user) {
        HelpeeApplication.setUserInstance(user);
        startActivity(new Intent(getActivity(), MainActivity.class));
        getActivity().finish();
    }
}
