package com.blogspot.chingovan.jsf_example.converter;

import com.blogspot.chingovan.jsf_example.util.WeightUnit;

import javax.faces.application.Application;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

/**
 * Created by ChiNV on 11/26/2017.
 */
@FacesConverter(value = "com.blogspot.chingovan.jsf_example.convert.Weight")
public class WeightConverter implements Converter {
    private static final double POUNDS_TO_KILOGRAM_RATE = 2.2;

    private Locale locale = null;
    private NumberFormat numberFormat = null;

    public WeightConverter() {
        locale = null;
        numberFormat = null;
    }

    private int getUnitFromUserPreferences(FacesContext context) {

        if( null == context) {
            return -1;
        }

        Integer result = null;
        int unit = 1;
        Application application = context.getApplication();
        if( null == application) {
            return unit;
        }

        return unit;
    }

    private NumberFormat getNumberFormat(FacesContext context) {
        if( null == numberFormat) {
            numberFormat = NumberFormat.getInstance(getLocale(context));
        }

        return numberFormat;
    }

    private Locale getLocale(FacesContext context) {
        Locale locale = this.locale;

        if (locale == null) {

            UIViewRoot root = context.getViewRoot();

            locale = root != null ? root.getLocale() : Locale.getDefault();
        }
        return locale;
    }

    public Locale getLocale() {
        if( this.locale == null) {
            this.locale = getLocale( FacesContext.getCurrentInstance());
        }
        return this.locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        if (facesContext == null || uiComponent == null) {
            throw new NullPointerException();
        }

        int unit = getUnitFromUserPreferences(facesContext);
        double value = 0D;

        try {
            value = getNumberFormat(facesContext).parse(s).doubleValue();
        } catch (ParseException e) {
            throw new ConverterException(e.getMessage());
        }

        //A pound equals 2.2 kilogram
        if( unit == WeightUnit.POUNDS.unit) {
            value /= POUNDS_TO_KILOGRAM_RATE;
        }
        return new Double(value);
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {

        if(facesContext == null || uiComponent == null  ) {
            throw new NullPointerException();
        }

        System.out.println("o: " + o);
        if( o == null || o.toString().isEmpty()) {
            return null;
        }

        String result = null;
        double value = ((Double)o).doubleValue();
        int unit = getUnitFromUserPreferences(facesContext);

        if( unit == WeightUnit.POUNDS.unit) {
            value *= POUNDS_TO_KILOGRAM_RATE;
        }
        System.out.println("value: " + value);

        result = getNumberFormat(facesContext).format(value);
        if( unit == WeightUnit.POUNDS.unit) {
            result = result + " lbs.";
        } else {
            result = result + " kg.";
        }
        System.out.println("result: " + result);

        return result;
    }
}
