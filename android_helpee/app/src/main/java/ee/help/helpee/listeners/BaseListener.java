package ee.help.helpee.listeners;

import ee.help.helpee.errors.ErrorType;

/**
 * Created by ian on 13/01/15.
 */
public interface BaseListener<T> {

     void onSuccess(T success);

     void onFail(ErrorType errorType);
}

