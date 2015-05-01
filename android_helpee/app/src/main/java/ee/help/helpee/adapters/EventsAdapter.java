package ee.help.helpee.adapters;

import com.facebook.drawee.view.SimpleDraweeView;
import com.pkmmte.view.CircularImageView;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import ee.help.helpee.R;
import ee.help.helpee.models.Event;

/**
 * Created by ian on 19/04/15.
 */
public class EventsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

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
        if (holder instanceof EventHolder) {
            bindDataToEventView((EventHolder) holder, eventList.get(position));
        }
    }

    void bindDataToEventView(EventHolder holder, Event event) {

        holder.title.setText(event.getEventTitle());
        holder.userName.setText(String.format(context.getString(R.string.needs_help), event.getCreator().getFullName()));
        /*Parse image URL and sent it into the drawee - if URL exists*/

        if (event.getCreator().getImageUri() != null && !"".equals(event.getCreator().getImageUri())) {
            Uri uri = Uri.parse(event.getCreator().getImageUri());
            holder.image.setImageURI(uri);
        }
        holder.time.setText("What is that?");

        holder.description.setText(event.getDescription());

        if (event.getPoints() > 1)
            holder.points.setText(String.format(context.getString(R.string.points), event.getPoints()));
        else
            holder.points.setText("+".concat(context.getString(R.string.single_point)));

    }


    @Override
    public int getItemCount() {
        return eventList.size();
    }

    public class EventHolder extends RecyclerView.ViewHolder {


        @InjectView(R.id.event_img)
        SimpleDraweeView image;

        @InjectView(R.id.event_time)
        TextView time;

        @InjectView(R.id.event_title)
        TextView title;

        @InjectView(R.id.event_user_name)
        TextView userName;

        @InjectView(R.id.event_description)
        TextView description;

        @InjectView(R.id.event_points)
        TextView points;

        public EventHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }
    }
}
