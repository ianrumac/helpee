package ee.help.helpee.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.astuetz.PagerSlidingTabStrip;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import ee.help.helpee.R;
import ee.help.helpee.adapters.MyEventsPagerAdapter;
import ee.help.helpee.dagger.MyEventsModule;
import ee.help.helpee.dagger.components.DaggerMyEventsComponent;
import ee.help.helpee.mvp.presenters.MyEventsPresenter;
import ee.help.helpee.mvp.views.MyEventsView;

/**
 * Created by infinum on 01/05/15.
 */
public class MyEventsFragment extends BaseFragment implements MyEventsView {

    public final static String TAG = "my-events-fragment";

    @InjectView(R.id.viewpageindicator)
    PagerSlidingTabStrip pagerIndicator;

    @InjectView(R.id.viewpager)
    ViewPager viewPager;

    MyEventsPagerAdapter viewpagerAdapter;

    @Inject
    MyEventsPresenter eventsPresenter;
    HelpingEventsFragment helpeeEventsFragment;
    CurrentUserEventsFragment userEventsFragment;
    List<android.support.v4.app.Fragment> fragmentList;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        DaggerMyEventsComponent.builder().myEventsModule(new MyEventsModule(this)).build().inject(this);
        View contentView = inflater.inflate(R.layout.fragment_myevents, container, false);
        ButterKnife.inject(this, contentView);
        viewPager.setVisibility(View.GONE);
        pagerIndicator.setVisibility(View.GONE);
        createFragmentsAndPager();

        return contentView;
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    public void createFragmentsAndPager() {
        fragmentList = new ArrayList<>();
        helpeeEventsFragment = new HelpingEventsFragment();
        userEventsFragment = new CurrentUserEventsFragment();
        fragmentList.add(userEventsFragment);
        fragmentList.add(helpeeEventsFragment);
        viewpagerAdapter = new MyEventsPagerAdapter(getChildFragmentManager(), fragmentList);
        viewPager.setAdapter(viewpagerAdapter);
        pagerIndicator.setViewPager(viewPager);
        viewPager.setVisibility(View.VISIBLE);
        pagerIndicator.setVisibility(View.VISIBLE);


    }

    @Override
    public void onPause() {
        super.onPause();
    }
}
