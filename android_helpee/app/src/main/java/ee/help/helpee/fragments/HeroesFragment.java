package ee.help.helpee.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import ee.help.helpee.R;
import ee.help.helpee.adapters.HeroesAdapter;
import ee.help.helpee.dagger.HeroesModule;
import ee.help.helpee.dagger.components.DaggerHeroesComponent;
import ee.help.helpee.models.User;
import ee.help.helpee.mvp.presenters.HeroesPresenter;
import ee.help.helpee.mvp.views.HeroesView;

/**
 * Created by infinum on 02/05/15.
 */
public class HeroesFragment extends BaseFragment implements HeroesView {


    @Inject
    HeroesPresenter heroesPresenter;

    @InjectView(R.id.heroes_list)
    ListView heroesListView;

    HeroesAdapter heroesListAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.fragment_heroes, container, false);
        DaggerHeroesComponent.builder().heroesModule(new HeroesModule(this)).build().inject(this);
        ButterKnife.inject(this, contentView);
        heroesPresenter.getHeroesList(getUser().getToken());
        return contentView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @Override
    public void showUsers(List<User> heroesList) {
        heroesListAdapter = new HeroesAdapter(heroesList, getActivity());
        heroesListView.setAdapter(heroesListAdapter);

    }
}
