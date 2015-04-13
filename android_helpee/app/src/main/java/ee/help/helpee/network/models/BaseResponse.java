package ee.help.helpee.network.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by ian on 13/01/15.
 */
public class BaseResponse<T> {

    @SerializedName("response")
    ArrayList<T> response;

    @SerializedName("count")
    int count;


    public BaseResponse(ArrayList<T> response, int count) {
        this.response = response;
        this.count = count;
    }

    public ArrayList<T> getResponse() {
        return response;
    }

    public void setResponse(ArrayList<T> response) {
        this.response = response;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
