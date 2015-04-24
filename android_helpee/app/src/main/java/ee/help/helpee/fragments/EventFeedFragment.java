package ee.help.helpee.fragments;

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
import ee.help.helpee.R;
import ee.help.helpee.adapters.EventsAdapter;
import ee.help.helpee.models.Event;
import ee.help.helpee.mvp.presenters.EventFeedPresenter;
import ee.help.helpee.mvp.views.EventFeedView;

/**
 * Created by ian on 24/04/15.
 */
public class EventFeedFragment extends BaseFragment implements EventFeedView {

    @InjectView(R.id.event_list)
    RecyclerView eventList;

    EventsAdapter eventsAdapter;

    @Inject
    EventFeedPresenter eventsPresenter;

    List<Event> events;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View contentView = inflater.inflate(R.layout.fragment_eventlist, container, false);

        ButterKnife.inject(this, contentView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        eventList.setLayoutManager(layoutManager);

        return contentView;


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
    public void eventJoined() {
        //TODO animate event
    }
}
