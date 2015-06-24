package ee.help.helpee.network.models;

/**
 * Created by infinum on 02/05/15.
 */
public class BearerToken{

    private static final String BEARER = "Bearer ";

    public static String authorize(String token) {
        return BEARER.concat(token);

    }

}
