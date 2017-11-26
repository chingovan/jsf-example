package com.blogspot.chingovan.jsf_example.trainer.backing;

import com.blogspot.chingovan.jsf_example.trainer.accessor.exception.EntityAccessorException;
import com.blogspot.chingovan.jsf_example.trainer.accessor.register.EventRegistry;
import com.blogspot.chingovan.jsf_example.trainer.accessor.register.UserRegistry;
import com.blogspot.chingovan.jsf_example.trainer.model.Event;

import javax.faces.bean.ManagedBean;
import javax.faces.component.UIData;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by ChiNV on 11/26/2017.
 */
@ManagedBean
public class EventTableBacking extends AbstractBacking {
    private UIData events;
    private List<Long> subscribedEventIds;

    public UIData getEvents() {
        return events;
    }

    public void setEvents(UIData events) {
        this.events = events;
    }

    public List<Long> getSubscribedEventIds() {
        if (null == subscribedEventIds) {
            subscribedEventIds = getCurrentUser().getSubscribedEventIds();
        }
        return subscribedEventIds;
    }

    public boolean isSubscribedToEvent() {
        Event currentEvent = (Event) getEvents().getRowData();
        boolean result = false;
        result = getSubscribedEventIds().contains(currentEvent.getId());
        return result;
    }

    public void setSubscribedToEvent(boolean subscribedToEvent) {
        Event currentEvent = (Event) getEvents().getRowData();
        Long id = currentEvent.getId();
        boolean isCurrentlySubscribed = getSubscribedEventIds().contains(id);
        boolean doPersist = false;
        if (true == subscribedToEvent) {
            if (!isCurrentlySubscribed) {
                getSubscribedEventIds().add(id);
                doPersist = true;
            }
        } else if (isCurrentlySubscribed) {
            getSubscribedEventIds().remove(id);
            doPersist = true;
        }
        if (doPersist) {
            try {
                UserRegistry.getInstance().updateUser(getCurrentUser());
            } catch (EntityAccessorException ex) {
                Logger.getLogger(EventTableBacking.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void setDeleteEvent(boolean deleteEvent) {
        if (deleteEvent) {
            Event currentEvent = (Event) getEvents().getRowData();
            Long eventId = currentEvent.getId();
            EventRegistry.getInstance().removeEventFromRegistryAndFromUsers(eventId);
        }
    }
}
