package ee.help.helpee.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.FacebookSdk;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.login.LoginManager;
import com.facebook.login.widget.ProfilePictureView;

import java.io.File;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import ee.help.helpee.HelpeeApplication;
import ee.help.helpee.R;
import ee.help.helpee.activities.LoginActivity;
import ee.help.helpee.models.User;
import ee.help.helpee.mvp.views.ProfileView;

/**
 * Created by infinum on 02/05/15.
 */
public class ProfileFragment extends Fragment {


    User user;
    @InjectView(R.id.profile_name)
    TextView profileUserName;

    @InjectView(R.id.profile_picture)
    SimpleDraweeView profilePic;

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
        try {
            profilePic.setImageURI(Uri.parse(HelpeeApplication.getUserInstance().getImageUri()));
        } catch (Exception e) {
        }
        ;
        userPointsLeft.setText(String.format(getString(R.string.int_points), HelpeeApplication.getUserInstance().getPoints()));


        return contentView;
    }

    @OnClick(R.id.sign_out_btn)
    void signOutPressed() {

            LoginManager.getInstance().logOut();
        try {
            File dir = HelpeeApplication.getInstance().getCacheDir();
            if (dir != null && dir.isDirectory()) {
                deleteDir(dir);
            }
        } catch (Exception e) {
        }

        startActivity(new Intent(getActivity(), LoginActivity.class));
        getActivity().finish();

    }


    /**
     * Delete cache
     */


    public static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        return dir.delete();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
