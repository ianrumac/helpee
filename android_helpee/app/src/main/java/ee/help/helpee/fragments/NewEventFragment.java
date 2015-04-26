package ee.help.helpee.fragments;

import com.gc.materialdesign.views.Slider;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import ee.help.helpee.R;
import ee.help.helpee.mvp.presenters.NewEventPresenter;
import ee.help.helpee.mvp.views.NewEventView;

/**
 * Created by ian on 26/04/15.
 */
public class NewEventFragment extends BaseFragment implements NewEventView{


    @InjectView(R.id.event_title)
    EditText eventTitle;

    @InjectView(R.id.event_description)
    EditText eventDescription;

    @InjectView(R.id.event_points)
    Slider eventPoints;

    @InjectView(R.id.create_event_btn)
    TextView createEventBtn;

    @Inject
    NewEventPresenter newEventPresenter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.fragment_new_event, container, false);


        eventPoints.setMax(getUser().getPoints());
        return contentView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @Override
    public void eventCreated() {
        getActivity().getFragmentManager().popBackStack();

    }

    @OnClick(R.id.create_event_btn)
    void createNewEvent(){
        newEventPresenter.createEvent(getUser(),null,eventTitle.getText().toString(),
                eventDescription.getText().toString(), eventPoints.getValue());
    }
}
