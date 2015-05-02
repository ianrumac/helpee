package ee.help.helpee.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import ee.help.helpee.HelpeeApplication;
import ee.help.helpee.R;
import ee.help.helpee.activities.BaseActionBarActivity;
import ee.help.helpee.adapters.EventsAdapter;
import ee.help.helpee.custom.Constants;
import ee.help.helpee.dagger.EventFeedModule;
import ee.help.helpee.dagger.UserEventsModule;
import ee.help.helpee.dagger.components.DaggerEventFeedComponent;
import ee.help.helpee.dagger.components.DaggerUserEventsComponent;
import ee.help.helpee.errors.ErrorType;
import ee.help.helpee.listeners.AdapterClickListener;
import ee.help.helpee.models.Event;
import ee.help.helpee.models.EventListContainer;
import ee.help.helpee.mvp.presenters.UserEventsPresenter;
import ee.help.helpee.mvp.views.UserEventsView;

/**
 * Created by infinum on 02/05/15.
 */
public class CurrentUserEventsFragment extends Fragment implements UserEventsView {



    public static CurrentUserEventsFragment newInstance(List<Event> eventList) {
        Bundle passEventsBundle = new Bundle();
        passEventsBundle.putSerializable(Constants.EVENT_LIST_EXTRA, new EventListContainer(eventList));
        CurrentUserEventsFragment fragment = new CurrentUserEventsFragment();
        fragment.setArguments(passEventsBundle);
        return fragment;
    }

    @InjectView(R.id.event_list)
    RecyclerView eventList;

    @InjectView(R.id.no_events)
    TextView noEvents;

    List<Event> events;

    EventsAdapter eventsAdapter;

    @Inject
    UserEventsPresenter userEventsPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View content = inflater.inflate(R.layout.fragment_myevents_child, container, false);

        DaggerUserEventsComponent.builder().userEventsModule(new UserEventsModule(this)).build().inject(this);
        ButterKnife.inject(this, content);
        events = ((EventListContainer) getArguments().getSerializable(Constants.EVENT_LIST_EXTRA)).getEventList();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        eventList.setLayoutManager(layoutManager);
        eventsAdapter = new EventsAdapter(events, getActivity(), cancelEventListener);
        eventList.setAdapter(eventsAdapter);

        if(events.size()==0){
            noEvents.setVisibility(View.VISIBLE);
        }

        return content;
    }


    AdapterClickListener cancelEventListener = new AdapterClickListener() {
        @Override
        public void onClick(int view, int position) {
            userEventsPresenter.cancelEvent(events.get(position).getEventId(), HelpeeApplication.getUserInstance().getToken());
        }
    };
    @Override
    public void removeEvent(int eventId) {

    }

    @Override
    public void showError(ErrorType message) {
        ((BaseActionBarActivity) getActivity()).showError(message);
    }

    @Override
    public void showProgress() {
        ((BaseActionBarActivity) getActivity()).showProgress();

    }

    @Override
    public void hideProgress() {
        ((BaseActionBarActivity) getActivity()).hideProgress();
    }
}
