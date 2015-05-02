package ee.help.helpee.mvp.presenters.impl;

import java.util.List;

import javax.inject.Inject;

import ee.help.helpee.errors.ErrorType;
import ee.help.helpee.listeners.BaseListener;
import ee.help.helpee.models.User;
import ee.help.helpee.mvp.interactors.HeroesInteractor;
import ee.help.helpee.mvp.presenters.HeroesPresenter;
import ee.help.helpee.mvp.views.HeroesView;

/**
 * Created by infinum on 02/05/15.
 */
public class HeroesPresenterImpl implements HeroesPresenter{

    HeroesView heroesView;

    HeroesInteractor heroesInteractor;

    @Inject
    public HeroesPresenterImpl(HeroesView heroesView, HeroesInteractor heroesInteractor) {
        this.heroesView = heroesView;
        this.heroesInteractor = heroesInteractor;
    }

    @Override
    public void getHeroesList(String token) {
        heroesView.showProgress();
        heroesInteractor.getHeroes(token, new BaseListener<List<User>>() {
            @Override
            public void onSuccess(List<User> success) {
                heroesView.hideProgress();
                heroesView.showUsers(success);
            }

            @Override
            public void onFail(ErrorType errorType) {
                heroesView.hideProgress();
                heroesView.showError(errorType);
            }
        });
    }
}
