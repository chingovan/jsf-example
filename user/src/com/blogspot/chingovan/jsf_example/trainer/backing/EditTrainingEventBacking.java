package com.blogspot.chingovan.jsf_example.trainer.backing;

import com.blogspot.chingovan.jsf_example.trainer.accessor.exception.EntityAccessorException;
import com.blogspot.chingovan.jsf_example.trainer.accessor.register.EventRegistry;
import com.blogspot.chingovan.jsf_example.trainer.model.Event;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.event.ComponentSystemEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by ChiNV on 11/26/2017.
 */
@ManagedBean
public class EditTrainingEventBacking extends AbstractBacking {
    private Long selectedEventId;
    private Event selectedEvent;

    public Long getSelectedEventId() {
        return selectedEventId;
    }

    public void setSelectedEventId(Long selectedEventId) {
        this.selectedEventId = selectedEventId;
    }

    public Event getSelectedEvent() {
        return selectedEvent;
    }

    public void setSelectedEvent(Event selectedEvent) {
        this.selectedEvent = selectedEvent;
    }

    //Redirects
    public void forwardToLoginIfNotLoggedIn(ComponentSystemEvent componentSystemEvent) {

        String viewId = getFacesContext().getViewRoot().getViewId();

        if (!isUserLoggedIn() && !viewId.startsWith("/login") && !viewId.startsWith("/register")) {
            getFacesContext().getApplication().getNavigationHandler().handleNavigation(getFacesContext(), null, "/login?faces-redirect=true");
        }
    }

    public void loadTrainingEvent(ComponentSystemEvent componentSystemEvent) {
        // if the event has not yet been set
        if (null == getSelectedEvent()) {

            Long eventId = getSelectedEventId();
            if (null == eventId) {
                eventId = (Long) getFlash().get("selectedEventId");
            }
            if (null == eventId) {
                getFacesContext().addMessage(null, new FacesMessage("The training event you requested is invalid"));
                getFlash().setKeepMessages(true);
                getFacesContext().getApplication().getNavigationHandler().
                        handleNavigation(getFacesContext(), null, "/user/all_events?faces-redirect=true");
            } else {
                Event event = EventRegistry.getInstance().getEventForId(eventId);
                if (null == event) {
                    getFacesContext().addMessage(null, new FacesMessage("The training event you requested does not exist"));
                    getFlash().setKeepMessages(true);
                    getFacesContext().getApplication().getNavigationHandler().handleNavigation(getFacesContext(), null, "/user/all_events?faces-redirect=true");
                } else {
                    getFlash().put("selectedEvent", event);
                    setSelectedEvent(event);
                }
            }
        }
    }

    public String updateExistingTrainingEvent() {
        String result = null;
        EventRegistry eventRegistry = EventRegistry.getInstance();
        Event newEvent = getSelectedEvent();
        try {
            eventRegistry.updateEvent(newEvent);
            result = "/user/all_events?faces-redirect=true";
        } catch (EntityAccessorException ex) {
            Logger.getLogger(EditTrainingEventBacking.class.getName()).
                    log(Level.SEVERE, null, ex);
        }
        getFlash().clear();
        return result;
    }
}
