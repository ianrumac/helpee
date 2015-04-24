package ee.help.helpee.activities;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.accountswitcher.AccountHeader;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.InjectView;
import ee.help.helpee.R;


public class MainActivity extends ActionBarActivity {

    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    PrimaryDrawerItem eventsDrawerItem;

    PrimaryDrawerItem profileDrawerItem;

    SecondaryDrawerItem settingsDrawerItem;

    AccountHeader.Result accountHeaderResult;

    Drawer navigationDrawerBuilder;
    Drawer.Result navigationDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        setSupportActionBar(toolbar);
        buildDrawer();


    }


    void buildDrawer() {
        navigationDrawerBuilder = new Drawer().withActivity(this)
                .withToolbar(toolbar);
        createDrawerItems();
        setDrawerListeners();



    }

    void createDrawerItems() {
        accountHeaderResult = new AccountHeader().withActivity(this).build();
        accountHeaderResult.addProfile(
                new ProfileDrawerItem().withName("Ian Rumac").withIcon(getResources().getDrawable(R.drawable.ic_launcher)),0);


        eventsDrawerItem = new PrimaryDrawerItem().withName(R.string.events_title);
        profileDrawerItem = new PrimaryDrawerItem().withName(R.string.profile_title);
        settingsDrawerItem = new SecondaryDrawerItem().withName(R.string.action_settings);
        navigationDrawerBuilder.addDrawerItems(
                eventsDrawerItem,
                profileDrawerItem,
                new DividerDrawerItem(),
                settingsDrawerItem

        ).withAccountHeader(accountHeaderResult);


        setDrawerListeners();
        navigationDrawer = navigationDrawerBuilder.build();

    }

    void setDrawerListeners() {
        navigationDrawerBuilder.withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l, IDrawerItem iDrawerItem) {
                if (iDrawerItem.equals(eventsDrawerItem)) {
                    Toast.makeText(MainActivity.this, "Events", Toast.LENGTH_SHORT).show();
                }
                if (iDrawerItem.equals(profileDrawerItem)) {
                    Toast.makeText(MainActivity.this, "Profile", Toast.LENGTH_SHORT).show();
                }
                if (iDrawerItem.equals(settingsDrawerItem)) {
                    Toast.makeText(MainActivity.this, "Settings", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}
