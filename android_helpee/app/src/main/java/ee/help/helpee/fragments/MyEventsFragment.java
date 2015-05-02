package ee.help.helpee.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.viewpagerindicator.TabPageIndicator;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import ee.help.helpee.R;
import ee.help.helpee.activities.BaseActionBarActivity;
import ee.help.helpee.adapters.MyEventsPagerAdapter;
import ee.help.helpee.dagger.MyEventsModule;
import ee.help.helpee.dagger.components.DaggerMyEventsComponent;
import ee.help.helpee.models.DoubleEventListContainer;
import ee.help.helpee.mvp.presenters.MyEventsPresenter;
import ee.help.helpee.mvp.views.MyEventsView;

/**
 * Created by infinum on 01/05/15.
 */
public class MyEventsFragment extends BaseFragment implements MyEventsView {

    @InjectView(R.id.viewpageindicator)
    TabPageIndicator pagerIndicator;

    @InjectView(R.id.viewpager)
    ViewPager viewPager;

    MyEventsPagerAdapter viewpagerAdapter;

    @Inject
    MyEventsPresenter eventsPresenter;
    CurrentHelpeeEventsFragment helpeeEventsFragment;
    CurrentUserEventsFragment userEventsFragment;
    FragmentActivity fragmentActivity;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {



        DaggerMyEventsComponent.builder().myEventsModule(new MyEventsModule(this)).build().inject(this);
        View contentView = inflater.inflate(R.layout.fragment_myevents, container, false);
        eventsPresenter.fetchUserEvents(getUser().getUserId(), getUser().getToken());
        ButterKnife.inject(this, contentView);
        fragmentActivity = (FragmentActivity) getActivity();
        viewPager.setVisibility(View.GONE);
        pagerIndicator.setVisibility(View.GONE);

        return contentView;
    }

    @Override
    public void createFragmentsAndPager(DoubleEventListContainer eventsContainer) {

        helpeeEventsFragment = CurrentHelpeeEventsFragment.newInstance(eventsContainer.getUserHelpingEvents());

        userEventsFragment = CurrentUserEventsFragment.newInstance(eventsContainer.getUsersEvents());
        List<android.support.v4.app.Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(userEventsFragment);
        fragmentList.add(helpeeEventsFragment);
        viewpagerAdapter = new MyEventsPagerAdapter(fragmentActivity.getSupportFragmentManager(), fragmentList);
        viewPager.setAdapter(viewpagerAdapter);
        pagerIndicator.setViewPager(viewPager);
        viewPager.setVisibility(View.VISIBLE);
        pagerIndicator.setVisibility(View.VISIBLE);


    }
}
