package ee.help.helpee.mvp.views;

import ee.help.helpee.errors.ErrorType;

/**
 * Created by ian on 12/04/15.
 */
public interface BaseView {


    void showError(ErrorType message);

    void showProgress();

    void hideProgress();


}
