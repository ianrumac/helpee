package ee.help.helpee.mvp.presenters.impl;

import com.google.android.gms.maps.model.LatLng;

import javax.inject.Inject;

import ee.help.helpee.errors.ErrorType;
import ee.help.helpee.listeners.SimpleBaseListener;
import ee.help.helpee.models.User;
import ee.help.helpee.mvp.interactors.NewEventInteractor;
import ee.help.helpee.mvp.presenters.NewEventPresenter;
import ee.help.helpee.mvp.views.NewEventView;

/**
 * Created by ian on 26/04/15.
 */
public class NewEventPresenterImpl implements NewEventPresenter {


    NewEventView eventView;

    NewEventInteractor eventInteractor;


    @Inject
    public NewEventPresenterImpl(NewEventView eventView, NewEventInteractor eventInteractor) {
        this.eventView = eventView;
        this.eventInteractor = eventInteractor;
    }



    @Override
    public void createEvent(User user, LatLng location, String eventName, String eventDescription, int points) {
        if (eventName.length() == 0 || eventDescription.length() == 0) {
            eventView.showError(ErrorType.INVALID_INPUT);
        } else {
            eventView.showProgress();
            eventInteractor.postEvent(user, location, eventName, eventDescription, points, new SimpleBaseListener() {
                @Override
                public void onSuccess() {
                    eventView.hideProgress();
                    eventView.eventCreated();
                }

                @Override
                public void onFail(ErrorType errorType) {
                    eventView.hideProgress();
                    eventView.showError(errorType);
                }
            });
        }
    }
    }
