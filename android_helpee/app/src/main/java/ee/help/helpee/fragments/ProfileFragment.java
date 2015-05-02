package ee.help.helpee.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.login.widget.ProfilePictureView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import ee.help.helpee.R;
import ee.help.helpee.models.User;
import ee.help.helpee.mvp.views.ProfileView;

/**
 * Created by infinum on 02/05/15.
 */
public class ProfileFragment extends BaseFragment implements ProfileView {


    User user;
    @InjectView(R.id.profile_name)
    TextView profileUserName;
    @InjectView(R.id.profile_picture)
    ProfilePictureView profilePic;

    @InjectView(R.id.profile_points_left)
    TextView profilePointsLeft;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View contentView = inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.inject(this, contentView);
        return contentView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
