package ee.help.helpee.mvp.views;

import java.util.List;

import ee.help.helpee.models.User;

/**
 * Created by infinum on 02/05/15.
 */
public interface HeroesView extends BaseView {

    void showUsers(List<User> heroesList);
}
