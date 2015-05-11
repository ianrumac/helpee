package ee.help.helpee.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import butterknife.ButterKnife;
import butterknife.InjectView;
import ee.help.helpee.R;
import de.hdodenhof.circleimageview.CircleImageView;
import ee.help.helpee.fragments.EventFeedFragment;
import ee.help.helpee.fragments.HeroesFragment;
import ee.help.helpee.fragments.MyEventsFragment;
import ee.help.helpee.fragments.ProfileFragment;


public class MainActivity extends BaseActionBarActivity {

    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    @InjectView(R.id.fragment_container)
    FrameLayout fragmentContainer;

    PrimaryDrawerItem eventsDrawerItem;

    PrimaryDrawerItem myEventsDrawerItem;
    PrimaryDrawerItem heroesFragmentDrawerItem;
    SecondaryDrawerItem profileDrawerItem;


    Drawer navigationDrawerBuilder;
    Drawer.Result navigationDrawer;

    FragmentManager fragmentManager = getSupportFragmentManager();
    View drawerHeader;
    TextView toolbarTitle;
    @InjectView(R.id.toolbar_chips)
    TextView toolbarChips;

    MyEventsFragment myEventsFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        setSupportActionBar(toolbar);
        buildDrawer();
        fragmentManager.beginTransaction().add(R.id.fragment_container, new EventFeedFragment()).addToBackStack(EventFeedFragment.TAG).commit();
        toolbarChips.setText(String.format(getString(R.string.chips_left), getUser().getPoints()));
        getSupportActionBar().setTitle(getString(R.string.event_feed));

    }


    void buildDrawer() {

        navigationDrawerBuilder = new Drawer().withActivity(this)
                .withToolbar(toolbar);
        setupHeader();
        createDrawerItems();
        setDrawerListeners();


    }

    void createDrawerItems() {

        eventsDrawerItem = new PrimaryDrawerItem().withName(R.string.event_feed)
                .withIcon(R.drawable.ic_events)
                .withSelectedIcon(R.drawable.ic_events_active)
                .withSelectedTextColor(R.color.main_blue);
        myEventsDrawerItem = new PrimaryDrawerItem().withName(R.string.my_events)
                .withIcon(R.drawable.ic_myevents)
                .withSelectedIcon(R.drawable.ic_myevents_active)
                .withSelectedTextColor(R.color.main_blue);


        heroesFragmentDrawerItem = new PrimaryDrawerItem().withName(R.string.heroes)
                .withIcon(R.drawable.ic_heroes)
                .withSelectedIcon(R.drawable.ic_heroes_active)
                .withSelectedTextColor(R.color.main_blue);

        profileDrawerItem = new SecondaryDrawerItem().withName(R.string.profile_title);
        navigationDrawerBuilder.addDrawerItems(
                eventsDrawerItem,
                myEventsDrawerItem,
                heroesFragmentDrawerItem,
                new DividerDrawerItem(),
                profileDrawerItem

        ).withHeader(drawerHeader);


        setDrawerListeners();
        navigationDrawer = navigationDrawerBuilder.build();


    }

    void setDrawerListeners() {
        navigationDrawerBuilder.withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l, IDrawerItem iDrawerItem) {
                if (iDrawerItem.equals(eventsDrawerItem)) {
                    fragmentManager.beginTransaction().replace(R.id.fragment_container, new EventFeedFragment(), EventFeedFragment.TAG).commit();
                    getSupportActionBar().setTitle(getString(R.string.event_feed));
                    fragmentContainer.setForeground(getResources().getDrawable(R.drawable.bottom_shadow));

                }

                if (iDrawerItem.equals(myEventsDrawerItem)) {
                    getSupportActionBar().setTitle(getString(R.string.my_events));
                    fragmentManager.beginTransaction().replace(R.id.fragment_container,new MyEventsFragment() ,MyEventsFragment.TAG).addToBackStack(MyEventsFragment.TAG).commit();
                    fragmentContainer.setForeground(null);

                }
                if (iDrawerItem.equals(heroesFragmentDrawerItem)) {

                    getSupportActionBar().setTitle(getString(R.string.heroes));
                    fragmentContainer.setForeground(getResources().getDrawable(R.drawable.bottom_shadow));
                    fragmentManager.beginTransaction().replace(R.id.fragment_container, new HeroesFragment()).commit();
                }

                if (iDrawerItem.equals(profileDrawerItem)) {
                    getSupportActionBar().setTitle(getString(R.string.profile_title));
                    fragmentContainer.setForeground(getResources().getDrawable(R.drawable.bottom_shadow));
                    fragmentManager.beginTransaction().replace(R.id.fragment_container, new ProfileFragment()).commit();
                }
            }
        });

    }

    void setupHeader() {
        drawerHeader = getLayoutInflater().inflate(R.layout.drawer_layout, null);



        TextView drawerTitle = (TextView) drawerHeader.findViewById(R.id.user_name);
        drawerTitle.setText(getUser().getFullName());

        /*How many chips does user have?*/
        TextView drawerChips = (TextView) drawerHeader.findViewById(R.id.user_chips_left);
        drawerChips.setText(String.format(getString(R.string.chips_left), getUser().getPoints()));

        CircleImageView profilePicture = (CircleImageView) drawerHeader.findViewById(R.id.profile_image_view);
        Glide.with(this).load(getUser().getImageUri()).into(profilePicture);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ((TextView) navigationDrawer.getHeader().findViewById(R.id.user_chips_left)).setText(String.format(getString(R.string.chips_left), getUser().getPoints()));
        toolbarChips.setText(String.format(getString(R.string.chips_left), getUser().getPoints()));

    }

    public void updatePoints() {
        ((TextView) navigationDrawer.getHeader().findViewById(R.id.user_chips_left)).setText(String.format(getString(R.string.chips_left), getUser().getPoints()));
        toolbarChips.setText(String.format(getString(R.string.chips_left), getUser().getPoints()));

    }
}
