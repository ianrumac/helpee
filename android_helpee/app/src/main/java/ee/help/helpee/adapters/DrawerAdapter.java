package ee.help.helpee.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import ee.help.helpee.R;
import ee.help.helpee.models.DrawerItemModel;

/**
 * Created by infinum on 01/05/15.
 */
public class DrawerAdapter extends BaseAdapter {

    List<DrawerItemModel> drawerItems;
    Context context;
    boolean wasDividerShown = false;

    @Override
    public int getCount() {
        return drawerItems.size();
    }

    @Override
    public Object getItem(int position) {
        return drawerItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        DrawerItemModel drawerItem = drawerItems.get(position);
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.drawer_layout_item, parent);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        /*Shows either a normal drawer item, or a secondary (no image, divider on first)*/

        if (drawerItem.isPrimary()) {
            viewHolder.drawerItemContainer.setVisibility(View.VISIBLE);
            viewHolder.simpleDrawerItemContainer.setVisibility(View.GONE);
            viewHolder.drawerItemDrawable.setImageResource(drawerItem.getDrawableResource());
            viewHolder.drawerItemTitle.setText(drawerItem.getItem());
        } else {

            if (!wasDividerShown) {
                viewHolder.divider.setVisibility(View.VISIBLE);
                wasDividerShown = true;
            } else
                viewHolder.divider.setVisibility(View.GONE);
            viewHolder.drawerItemContainer.setVisibility(View.GONE);
            viewHolder.simpleDrawerItemContainer.setVisibility(View.VISIBLE);
            viewHolder.simpleTitle.setText(drawerItem.getItem());

        }
        return convertView;
    }


    class ViewHolder {
        @InjectView(R.id.drawer_item_title)
        TextView drawerItemTitle;
        @InjectView(R.id.drawer_item_drawable)
        ImageView drawerItemDrawable;
        @InjectView(R.id.event_feed_item)
        RelativeLayout drawerItemContainer;
        @InjectView(R.id.divider)
        View divider;
        @InjectView(R.id.simple_title)
        TextView simpleTitle;
        @InjectView(R.id.simple_drawer_item_container)
        RelativeLayout simpleDrawerItemContainer;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
