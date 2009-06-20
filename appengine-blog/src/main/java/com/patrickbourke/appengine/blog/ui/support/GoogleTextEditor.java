package com.patrickbourke.appengine.blog.ui.support;

import java.beans.PropertyEditorSupport;

import com.google.appengine.api.datastore.Text;

/**
 * A custom property editor for binding Google Text
 * fields.
 *
 * @author Patrick Bourke <pbourke@gmail.com>
 */
public class GoogleTextEditor extends PropertyEditorSupport {

    @Override
    public String getAsText() {
        if ( getValue() == null ) {
            return super.getAsText();
        }
        return ((Text) getValue()).getValue();
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if ( text == null ) {
            super.setAsText(text);            
        } else {
            setValue( new Text(text) );
        }
    }
}
