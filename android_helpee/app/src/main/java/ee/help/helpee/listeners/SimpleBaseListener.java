package ee.help.helpee.listeners;

import ee.help.helpee.errors.ErrorType;

/**
 * Created by ian on 13/01/15.
 */
public interface SimpleBaseListener {

     void onSuccess();

     void onFail(ErrorType errorType);
}

