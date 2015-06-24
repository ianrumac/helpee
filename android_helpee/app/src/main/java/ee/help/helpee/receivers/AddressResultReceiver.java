package ee.help.helpee.receivers;

import android.location.Address;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;

import ee.help.helpee.HelpeeApplication;
import ee.help.helpee.activities.SplashActivity;
import ee.help.helpee.custom.Constants;
import ee.help.helpee.errors.ErrorType;
import ee.help.helpee.listeners.BaseListener;
import ee.help.helpee.listeners.SimpleBaseListener;

/**
 * Created by infinum on 29/04/15.
 */
public class AddressResultReceiver extends ResultReceiver {

    private BaseListener<Address> returnResultListener;

    public AddressResultReceiver(Handler handler, BaseListener<Address> resultListener) {
        super(handler);
        returnResultListener = resultListener;
    }

    /**
     * Receives data sent from FetchAddressIntentService and updates the UI in MainActivity.
     */
    @Override
    protected void onReceiveResult(int resultCode, Bundle resultData) {

        // Returns the address string or an error message sent from the intent service.
        if (resultCode == Constants.SUCCESS_RESULT) {
            Address result = resultData.getParcelable(Constants.RESULT_DATA_KEY);
            if (result != null)
                returnResultListener.onSuccess(result);
            else
                returnResultListener.onFail(ErrorType.LOCATION_ERROR);

        } else
            returnResultListener.onFail(ErrorType.LOCATION_ERROR);

    }
}
