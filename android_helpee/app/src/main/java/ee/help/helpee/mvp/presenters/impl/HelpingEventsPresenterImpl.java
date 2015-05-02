package ee.help.helpee.mvp.presenters.impl;

import javax.inject.Inject;

import ee.help.helpee.errors.ErrorType;
import ee.help.helpee.listeners.SimpleBaseListener;
import ee.help.helpee.mvp.interactors.HelpingEventsInteractor;
import ee.help.helpee.mvp.presenters.HelpingEventsPresenter;
import ee.help.helpee.mvp.views.HelpingEventsView;

/**
 * Created by infinum on 02/05/15.
 */
public class HelpingEventsPresenterImpl implements HelpingEventsPresenter {

    HelpingEventsInteractor eventsInteractor;

    HelpingEventsView helpingEventsView;

    @Inject
    public HelpingEventsPresenterImpl(HelpingEventsInteractor eventsInteractor, HelpingEventsView helpingEventsView) {
        this.eventsInteractor = eventsInteractor;
        this.helpingEventsView = helpingEventsView;
    }

    @Override
    public void cancelHelp(final int position, final int eventId, String userId, String token) {
        helpingEventsView.showProgress();
            eventsInteractor.cancelHelp(eventId,userId, token, new SimpleBaseListener() {
                @Override
                public void onSuccess() {
                    helpingEventsView.hideProgress();
                    helpingEventsView.removeEvent(position);
                }

                @Override
                public void onFail(ErrorType errorType) {
                    helpingEventsView.hideProgress();
                    helpingEventsView.showError(errorType);
                }
            });
    }
}
