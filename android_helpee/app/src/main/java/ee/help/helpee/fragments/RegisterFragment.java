package ee.help.helpee.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import butterknife.ButterKnife;
import butterknife.InjectView;
import ee.help.helpee.R;

/**
 * Created by ian on 25/04/15.
 */
public class RegisterFragment extends BaseFragment {

    @InjectView(R.id.register_username)
    EditText registerUsername;

    @InjectView(R.id.register_email)
    EditText registerEmail;

    @InjectView(R.id.register_password)
    EditText registerPassword;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View contentView = inflater.inflate(R.layout.fragment_register, container);
        ButterKnife.inject(this, contentView);
        return contentView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
