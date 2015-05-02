package ee.help.helpee.fragments;

import com.gc.materialdesign.widgets.Dialog;
import com.gc.materialdesign.widgets.ProgressDialog;

import android.app.Application;
import android.app.Fragment;

import ee.help.helpee.HelpeeApplication;
import ee.help.helpee.R;
import ee.help.helpee.errors.ErrorHandler;
import ee.help.helpee.errors.ErrorType;
import ee.help.helpee.models.User;


/**
 * Created by ian on 23/04/15.
 */
public abstract class BaseFragment extends Fragment {

    ProgressDialog mProgressDialog;

    Dialog mDialog;


    User getUser(){
        return HelpeeApplication.getUserInstance();
    }

    Application getApplication(){
        return HelpeeApplication.getInstance();
    }


    public void showProgress() {

        mProgressDialog = new ProgressDialog(getActivity(), HelpeeApplication.getInstance().getString(R.string.loading_msg),
                R.color.maroon);
        mProgressDialog.show();

    }

    public void showProgress(int message) {

        mProgressDialog = new ProgressDialog(getActivity(), HelpeeApplication.getInstance().getString(message), R.color.maroon);
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
        ErrorHandler.handleError(type, getActivity());
    }


    void showDialog(String title, String message) {
        mDialog = new Dialog(getActivity(), title, message);
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
