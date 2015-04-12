package ee.help.helpee.models;

/**
 * Created by ian on 12/04/15.
 */
public class User {

    int id;

    String name;

    String surname;

    String email;

    String token;

    public User(int id, String name, String surname, String email, String token) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.token = token;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
