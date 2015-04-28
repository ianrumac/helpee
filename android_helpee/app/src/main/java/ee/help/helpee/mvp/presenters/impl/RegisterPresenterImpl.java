package ee.help.helpee.mvp.presenters.impl;

import javax.inject.Inject;

import ee.help.helpee.errors.ErrorType;
import ee.help.helpee.listeners.BaseListener;
import ee.help.helpee.listeners.SimpleBaseListener;
import ee.help.helpee.models.User;
import ee.help.helpee.mvp.interactors.RegisterInteractor;
import ee.help.helpee.mvp.presenters.RegisterPresenter;
import ee.help.helpee.mvp.views.RegisterView;
import retrofit.mime.TypedFile;

/**
 * Created by ian on 25/04/15.
 */
public class RegisterPresenterImpl implements RegisterPresenter {

    RegisterView registerView;

    RegisterInteractor registerInteractor;

    @Inject
    public RegisterPresenterImpl(RegisterView registerView, RegisterInteractor registerInteractor) {
        this.registerView = registerView;
        this.registerInteractor = registerInteractor;
    }

    @Override
    public void registerUser(String name, String email, String password, final TypedFile typedFile) {
        registerView.showProgress();
        registerInteractor.sendUserData(name, email, password, new SimpleBaseListener() {
            @Override
            public void onSuccess() {
                registerInteractor.uploadUserPicture(typedFile, uploadImageBaseListener);
            }

            @Override
            public void onFail(ErrorType errorType) {
                registerView.hideProgress();
                registerView.showError(errorType);
            }
        });
    }

    BaseListener<User> uploadImageBaseListener = new BaseListener<User>() {

        @Override
        public void onSuccess(User success) {
            registerView.hideProgress();
            registerView.userRegistered(success);

        }

        @Override
        public void onFail(ErrorType errorType) {
            registerView.hideProgress();
            registerView.showError(errorType);
        }
    };
}