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

import butterknife.ButterKnife;
import butterknife.InjectView;
import ee.help.helpee.R;
import ee.help.helpee.adapters.EventsAdapter;
import ee.help.helpee.custom.Constants;
import ee.help.helpee.listeners.AdapterClickListener;
import ee.help.helpee.models.Event;
import ee.help.helpee.models.EventListContainer;

/**
 * Created by infinum on 02/05/15.
 */
public class CurrentHelpeeEventsFragment extends Fragment {


    public static CurrentHelpeeEventsFragment newInstance(List<Event> eventList) {
        Bundle passEventsBundle = new Bundle();
        passEventsBundle.putSerializable(Constants.EVENT_LIST_EXTRA, new EventListContainer(eventList));
        CurrentHelpeeEventsFragment fragment = new CurrentHelpeeEventsFragment();
        fragment.setArguments(passEventsBundle);
        return fragment;
    }



    @InjectView(R.id.event_list)
    RecyclerView eventList;

    @InjectView(R.id.no_events)
    TextView noEvents;

    List<Event> events;

    EventsAdapter eventsAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View content = inflater.inflate(R.layout.fragment_myevents_child, container, false);

        ButterKnife.inject(this, content);
        events = ((EventListContainer) getArguments().getSerializable(Constants.EVENT_LIST_EXTRA)).getEventList();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        eventList.setLayoutManager(layoutManager);


        eventsAdapter = new EventsAdapter(events, getActivity(), cancelHelpClickListener);


        eventList.setAdapter(eventsAdapter);

        return content;
    }

    AdapterClickListener cancelHelpClickListener = new AdapterClickListener() {
        @Override
        public void onClick(int view, int position) {

        }
    };


    @Override
    public void onResume() {
        super.onResume();
                /*No events, so let's drop a text that tells that*/
        if(events.size()==0){
            noEvents.setVisibility(View.VISIBLE);
        }


    }
}
