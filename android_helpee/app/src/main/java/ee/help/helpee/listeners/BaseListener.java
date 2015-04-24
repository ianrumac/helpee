package ee.help.helpee.listeners;

import ee.help.helpee.errors.ErrorType;

/**
 * Created by ian on 13/01/15.
 */
public interface BaseListener<T> {

    public void onSuccess(T sucess);

    public void onFail(ErrorType errorType);
}

