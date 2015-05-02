package ee.help.helpee.adapters;

import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import java.util.List;

import ee.help.helpee.HelpeeApplication;
import ee.help.helpee.R;

/**
 * Created by infinum on 02/05/15.
 */
public class MyEventsPagerAdapter extends android.support.v4.app.FragmentPagerAdapter {
    List<Fragment> fragmentList;

    public MyEventsPagerAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);

        this.fragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }


    @Override
    public String getPageTitle(int position) {
        switch (position) {
            case 0:
                return HelpeeApplication.getInstance().getString(R.string.helping);
            case 1:
                return HelpeeApplication.getInstance().getString(R.string.getting_help);
            default:
                return HelpeeApplication.getInstance().getString(R.string.helping);

        }
    };

}
