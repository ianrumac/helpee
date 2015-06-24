package ee.help.helpee.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.facebook.login.LoginManager;

import java.io.File;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import ee.help.helpee.HelpeeApplication;
import ee.help.helpee.R;
import ee.help.helpee.activities.LoginActivity;
import de.hdodenhof.circleimageview.CircleImageView;
import ee.help.helpee.custom.Constants;

/**
 * Created by infinum on 02/05/15.
 */
public class ProfileFragment extends Fragment {


    @InjectView(R.id.profile_name)
    TextView profileUserName;

    @InjectView(R.id.profile_picture)
    CircleImageView profilePic;

    @InjectView(R.id.profile_points_left)
    TextView userPointsLeft;

    @InjectView(R.id.sign_out_btn)
    TextView signOut;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View contentView = inflater.inflate(R.layout.fragment_profile, container, false);

        ButterKnife.inject(this, contentView);
        profileUserName.setText(HelpeeApplication.getUserInstance().getFullName());
            Glide.with(this).load(HelpeeApplication.getUserInstance().getImageUri()).into(profilePic);

        userPointsLeft.setText(String.format(getString(R.string.int_points), HelpeeApplication.getUserInstance().getPoints()));


        return contentView;
    }

    @OnClick(R.id.sign_out_btn)
    void signOutPressed() {

            LoginManager.getInstance().logOut();
        getActivity().getSharedPreferences(Constants.HELPEE_PREFS, Context.MODE_PRIVATE).edit().clear().commit();
        startActivity(new Intent(getActivity(), LoginActivity.class));
        getActivity().finish();

    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
