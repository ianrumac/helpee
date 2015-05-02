package ee.help.helpee.mvp.interactors;

import java.util.List;

import ee.help.helpee.listeners.BaseListener;
import ee.help.helpee.models.User;

/**
 * Created by infinum on 02/05/15.
 */
public interface HeroesInteractor {


    void getHeroes(String token, BaseListener<List<User>> heroesListener);
}
