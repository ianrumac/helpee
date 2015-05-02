package ee.help.helpee.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import ee.help.helpee.R;
import ee.help.helpee.activities.EventDetailsActivity;
import ee.help.helpee.custom.Constants;
import ee.help.helpee.listeners.AdapterClickListener;
import ee.help.helpee.listeners.SimpleBaseListener;
import ee.help.helpee.models.Event;
import ee.help.helpee.utils.TimeUtils;

/**
 * Created by ian on 19/04/15.
 */
public class EventsOwnerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<Event> eventList;

    Context context;

    AdapterClickListener itemClickListener;

    public EventsOwnerAdapter(List<Event> eventList, Context context, AdapterClickListener itemClickListener) {
        this.eventList = eventList;
        this.context = context;
        this.itemClickListener = itemClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new EventHolder(LayoutInflater.from(context).inflate(R.layout.card_event_owner, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof EventHolder) {
            bindDataToEventView((EventHolder) holder, eventList.get(position),position);
        }
    }

    void bindDataToEventView(EventHolder holder, final Event event, final int position) {

        holder.title.setText(event.getEventTitle());
        holder.userName.setText(String.format(context.getString(R.string.needs_help), event.getCreator().getFullName()));
        /*Parse image URL and sent it into the drawee - if URL exists*/

        if (event.getCreator().getImageUri() != null && !"".equals(event.getCreator().getImageUri())) {
            Uri uri = Uri.parse(event.getCreator().getImageUri());
            holder.image.setImageURI(uri);
        }
        holder.time.setText(TimeUtils.parseDateTimeIntoShowableString(event.getEventDate()));

        holder.description.setText(event.getDescription());

        if (event.getPoints() > 1)
            holder.points.setText(String.format(context.getString(R.string.points), event.getPoints()));
        else
            holder.points.setText("+".concat(context.getString(R.string.single_point)));

        holder.wholeCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openEventDetails = new Intent(context, EventDetailsActivity.class);
                openEventDetails.putExtra(Constants.EVENT_EXTRA, event);
                context.startActivity(openEventDetails);
            }
        });

        holder.cancelEventBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.onClick(R.id.btn_cancel, position);
            }
        });

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

        @InjectView(R.id.event_card)
        CardView wholeCard;

        @InjectView(R.id.btn_cancel)
        TextView cancelEventBtn;

        public EventHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }
    }

}
