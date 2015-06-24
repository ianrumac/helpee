package ee.help.helpee.errors;

import com.gc.materialdesign.widgets.SnackBar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.provider.Settings;
import android.view.View;

import ee.help.helpee.HelpeeApplication;
import ee.help.helpee.R;
import ee.help.helpee.activities.BaseActivity;
import ee.help.helpee.activities.MainActivity;

/**
 * Created by ian on 03/04/15.
 */
public class ErrorHandler {


    private static Context errorScopeContext;

    public static void handleError(ErrorType errorType, Context context) {
        errorScopeContext = context;

        switch (errorType) {

            case CONNECTION_ERROR:
                handleConnectionError();

            case LOCATION_ERROR:
                handleLocationError();
            case NULL_RESPONSE:
                handleNullResponse();
            case NO_DATA:
                handleNullResponse();
            case AUTH_ERROR:
                handleAuthError();
            case INVALID_INPUT:
        }

    }


    public ErrorHandler(Activity errorScopeContext) {
        this.errorScopeContext = errorScopeContext;
    }

    private static void handleConnectionError() {

        SnackBar snackBar = new SnackBar((Activity) errorScopeContext,
                errorScopeContext.getString(R.string.check_connection));
        snackBar.setColorButton(Color.RED);
        snackBar.show();

    }

    private static void handleLocationError() {
        SnackBar snackbar = new SnackBar((Activity) errorScopeContext,
                errorScopeContext.getString(R.string.check_location),
                errorScopeContext.getString(R.string.open_settings),
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent viewIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        errorScopeContext.startActivity(viewIntent);

                    }
                });
        snackbar.setColorButton(Color.RED);
        snackbar.show();
    }

    private static void handleNullResponse() {
        new SnackBar((Activity) errorScopeContext,
                errorScopeContext.getString(R.string.request_error)).show();

    }

    private static void handleAuthError() {
        new SnackBar((Activity) errorScopeContext,
                errorScopeContext.getString(R.string.session_expired), errorScopeContext.getString(R.string.logout),
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        errorScopeContext.startActivity( new Intent( errorScopeContext, MainActivity.class));
                        ((Activity) errorScopeContext).finish();

                    }
                });
    }
}
