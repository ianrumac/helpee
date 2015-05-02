package ee.help.helpee.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ee.help.helpee.errors.ErrorType;
import ee.help.helpee.models.User;
import ee.help.helpee.mvp.views.ProfileView;

/**
 * Created by infinum on 02/05/15.
 */
public class ProfileFragment extends BaseFragment implements ProfileView{


    User user;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
