package ee.help.helpee.mvp.interactors;

import ee.help.helpee.listeners.BaseListener;
import ee.help.helpee.listeners.SimpleBaseListener;
import ee.help.helpee.models.User;
import retrofit.mime.TypedFile;


/**
 * Created by ian on 19/04/15.
 */
public interface RegisterInteractor {

    void sendUserData(String name, String email, String password, SimpleBaseListener stringCallback);

    void uploadUserPicture(TypedFile typedFile, BaseListener<User> uploadCallback);
}
