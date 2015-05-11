package ee.help.helpee.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import ee.help.helpee.HelpeeApplication;
import ee.help.helpee.R;
import ee.help.helpee.activities.BaseActionBarActivity;
import ee.help.helpee.activities.MainActivity;
import ee.help.helpee.adapters.EventsHelpeeAdapter;
import ee.help.helpee.custom.Constants;
import ee.help.helpee.dagger.HelpingEventsModule;
import ee.help.helpee.dagger.components.DaggerHelpingEventComponent;
import ee.help.helpee.errors.ErrorType;
import ee.help.helpee.listeners.AdapterClickListener;
import ee.help.helpee.models.Event;
import ee.help.helpee.models.EventListContainer;
import ee.help.helpee.mvp.presenters.HelpingEventsPresenter;
import ee.help.helpee.mvp.views.HelpingEventsView;

/**
 * Created by infinum on 02/05/15.
 */
public class HelpingEventsFragment extends Fragment implements HelpingEventsView {


    public static HelpingEventsFragment newInstance(List<Event> eventList) {
        Bundle passEventsBundle = new Bundle();
        passEventsBundle.putSerializable(Constants.EVENT_LIST_EXTRA, new EventListContainer(eventList));
        HelpingEventsFragment fragment = new HelpingEventsFragment();
        fragment.setArguments(passEventsBundle);
        return fragment;
    }



    @InjectView(R.id.event_list)
    RecyclerView eventList;

    @InjectView(R.id.no_events)
    TextView noEvents;

    List<Event> events;

    @Inject
    HelpingEventsPresenter eventsPresenter;

    EventsHelpeeAdapter eventsAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View content = inflater.inflate(R.layout.fragment_myevents_child, container, false);
        DaggerHelpingEventComponent.builder().helpingEventsModule(new HelpingEventsModule(this)).build().inject(this);
        ButterKnife.inject(this, content);
        setUpRecyclerView();

        return content;
    }

    void setUpRecyclerView(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        eventList.setLayoutManager(layoutManager);
    }


    /**
     * Hooks into recycler view adapter for onClick function
     * */
    AdapterClickListener cancelHelpClickListener = new AdapterClickListener() {
        @Override
        public void onClick(int view, int position) {
                eventsPresenter.cancelHelp(position,events.get(position).getEventId(), HelpeeApplication.getUserInstance().getUserId(), HelpeeApplication.getUserInstance().getToken());
        }
    };


    @Override
    public void removeEvent(int position) {
        events.remove(position);
        eventsAdapter.notifyItemRemoved(position);
        if(events.size()==0){
            noEvents.setVisibility(View.VISIBLE);
            HelpeeApplication.changePoints(HelpeeApplication.getUserInstance().getPoints() + 1);
            ((MainActivity)getActivity()).updatePoints();
        }

    }

    @Override
    public void showEvents(List<Event> eventResultList) {
        events = eventResultList;
        eventsAdapter = new EventsHelpeeAdapter(events, getActivity(), cancelHelpClickListener);
        eventList.setAdapter(eventsAdapter);
        eventsAdapter.notifyDataSetChanged();



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

    @Override
    public void onResume() {
        super.onResume();
        eventsPresenter.fetchUserEvents(HelpeeApplication.getUserInstance().getUserId(), HelpeeApplication.getUserInstance().getToken());

    }
}
