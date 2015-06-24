package ee.help.helpee.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import ee.help.helpee.HelpeeApplication;
import ee.help.helpee.R;
import de.hdodenhof.circleimageview.CircleImageView;
import ee.help.helpee.models.User;

public class HeroesAdapter extends BaseAdapter {

    List<User> userList;

    Context context;

    public HeroesAdapter(List<User> userList, Context context) {
        this.userList = userList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return userList.size();
    }

    @Override
    public Object getItem(int position) {
        return userList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;

        User user = userList.get(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(context)
                    .inflate(R.layout.heroes_item, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }


        Glide.with(context).load(user.getImageUri()).into(viewHolder.userImg);
        viewHolder.userName.setText(user.getFullName());
        viewHolder.userPoints.setText(String.format(HelpeeApplication.getInstance().getString(R.string.int_points), user.getPoints()));


        return convertView;
    }

    static class ViewHolder {
        @InjectView(R.id.user_img)
        CircleImageView userImg;
        @InjectView(R.id.user_name)
        TextView userName;
        @InjectView(R.id.user_points)
        TextView userPoints;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
