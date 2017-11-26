package com.blogspot.chingovan.jsf_example.trainer.accessor.register;

import com.blogspot.chingovan.jsf_example.trainer.accessor.AbstractEntityAccessor;
import com.blogspot.chingovan.jsf_example.trainer.accessor.exception.EntityAccessorException;
import com.blogspot.chingovan.jsf_example.trainer.constant.ApplicationConstant;
import com.blogspot.chingovan.jsf_example.trainer.model.Event;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by ChiNV on 11/27/2017.
 */
public class EventRegistry extends AbstractEntityAccessor implements Serializable{

    private static EventRegistry ourInstance = new EventRegistry();

    private EventRegistry() {
    }

    public static EventRegistry getInstance() {

        EventRegistry result = null;
        Map<String, Object> appMap = FacesContext.getCurrentInstance().getExternalContext().getApplicationMap();
        result = (EventRegistry) appMap.get(ApplicationConstant.USER_REGISTRY);
        assert(null != result);

        return result;
    }

    public void addEvent(Event e) {
    }

    public Event removeEventForId(Long id) {
        return null;
    }

    public void removeEventFromRegistryAndFromUsers(Long id) {

    }

    public Event getEventForId(Long id) {
        return null;
    }

    public List<Event> getEventList() {
        return null;
    }

    public DataModel<Event> getEvents() {
        return null;
    }

    @PostConstruct
    public void perSessionConstructor() {
    }

    public void updateEvent(Event toUpdate) throws EntityAccessorException{
    }
}
