package ee.help.helpee.mvp.presenters;

import retrofit.mime.TypedFile;

/**
 * Created by ian on 25/04/15.
 */
public interface RegisterPresenter {

    void registerUser(String name, String mail, String password, TypedFile typedFile);
}
