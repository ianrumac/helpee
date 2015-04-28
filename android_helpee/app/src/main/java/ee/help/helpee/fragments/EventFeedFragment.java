package ee.help.helpee.fragments;

import com.melnykov.fab.FloatingActionButton;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import ee.help.helpee.R;
import ee.help.helpee.activities.NewEventActivity;
import ee.help.helpee.adapters.EventsAdapter;
import ee.help.helpee.dagger.EventFeedModule;
import ee.help.helpee.dagger.components.DaggerEventFeedComponent;
import ee.help.helpee.models.Event;
import ee.help.helpee.mvp.presenters.EventFeedPresenter;
import ee.help.helpee.mvp.views.EventFeedView;

/**
 * Created by ian on 24/04/15.
 */

public class EventFeedFragment extends BaseFragment implements EventFeedView {

    public final static String TAG = "EVENT_FEED_FRAGMENT";

    @InjectView(R.id.event_list)
    RecyclerView eventList;

    @InjectView(R.id.fab)
    FloatingActionButton newEventButton;

    EventsAdapter eventsAdapter;

    @Inject
    EventFeedPresenter eventsPresenter;

    List<Event> events;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View contentView = inflater.inflate(R.layout.fragment_eventlist, container, false);

        ButterKnife.inject(this, contentView);
        DaggerEventFeedComponent.builder().eventFeedModule(new EventFeedModule(this)).build().inject(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        eventList.setLayoutManager(layoutManager);
        newEventButton.attachToRecyclerView(eventList);
        return contentView;


    }

    @OnClick(R.id.fab)
    void createNewEvent(){
        startActivity(new Intent(getActivity(), NewEventActivity.class));
    }
    @Override
    public void onResume() {
        super.onResume();
        if (events == null) {
            eventsPresenter.loadEventList();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @Override
    public void showEventList(List<Event> eventResults) {
        events = eventResults;
        eventsAdapter = new EventsAdapter(events, getActivity());
        eventList.setAdapter(eventsAdapter);
    }

    @Override
    public void eventJoined(int id) {
        //TODO animate event

    }

}
