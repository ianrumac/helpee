package ee.help.helpee.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import ee.help.helpee.R;
import ee.help.helpee.models.Event;

/**
 * Created by ian on 19/04/15.
 */
public class EventsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    List<Event> eventList;

    Context context;

    public EventsAdapter(List<Event> eventList, Context context) {
        this.eventList = eventList;
        this.context = context;
    }




    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        return new EventHolder(LayoutInflater.from(context).inflate(R.layout.card_event, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        Event chosenEvent = eventList.get(position);

    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    private class EventHolder extends  RecyclerView.ViewHolder{

        @InjectView(R.id.event_img)
        ImageView imageViewEventImage;

        @InjectView(R.id.event_time)
        TextView textViewEventTime;

        @InjectView(R.id.event_title)
        TextView textViewEventTitle;

        @InjectView(R.id.event_user_name)
        TextView textViewUserName;

        public EventHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }
    }
}
