package ee.help.helpee.activities;

import com.gc.materialdesign.widgets.Dialog;
import com.gc.materialdesign.widgets.ProgressDialog;

import android.support.v7.app.ActionBarActivity;

import ee.help.helpee.HelpeeApplication;
import ee.help.helpee.R;
import ee.help.helpee.errors.ErrorHandler;
import ee.help.helpee.errors.ErrorType;
import ee.help.helpee.models.User;

/**
 * Created by ian on 12/04/15.
 */
public abstract class BaseActionBarActivity extends ActionBarActivity {

    ProgressDialog mProgressDialog;

    Dialog mDialog;

    public User getUser(){
        return HelpeeApplication.getUserInstance();
    }


    public void showProgress() {

        mProgressDialog = new ProgressDialog(this, HelpeeApplication.getInstance().getString(R.string.loading_msg),
                R.color.maroon);
        mProgressDialog.show();

    }

    public void showProgress(int message) {

        mProgressDialog = new ProgressDialog(this, HelpeeApplication.getInstance().getString(message), R.color.maroon);
        mProgressDialog.show();

    }

    ;

    public void hideProgress() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    public void showError(String message) {

        showDialog(HelpeeApplication.getInstance().getString(R.string.error_occured_title), message);
    }

    public void showError(ErrorType type) {
        ErrorHandler.handleError(type, this);
    }


    void showDialog(String title, String message) {
        mDialog = new Dialog(this, title, message);
        mDialog.show();

    }


    @Override
    public void onStop() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
        super.onStop();
    }

}
