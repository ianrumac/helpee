package ee.help.helpee.network.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ian on 13/01/15.
 */
public class BaseResponseSingle<T> {

    @SerializedName("response")
    T response;


    public BaseResponseSingle(T response) {
        this.response = response;
    }

    public T getResponse() {
        return response;
    }

    public void setResponse(T response) {
        this.response = response;
    }
}
