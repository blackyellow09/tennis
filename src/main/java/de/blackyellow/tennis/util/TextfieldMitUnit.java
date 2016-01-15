package de.blackyellow.tennis.util;

import com.vaadin.data.Property;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;

public class TextfieldMitUnit extends CustomComponent {
	    
	public TextfieldMitUnit(String caption, String unit, Property dataSource) {
	        // A layout structure used for composition
	        HorizontalLayout panelContent = new HorizontalLayout();
	        panelContent.setSpacing(true);
	        
	        // Compose from multiple components
	        TextField textfield = new TextField(dataSource);
	        textfield.setStyleName(ValoTheme.TEXTFIELD_ALIGN_RIGHT);
			panelContent.addComponent(textfield);
	        Label label = new Label(unit);
	        label.setSizeUndefined(); // Shrink
	        panelContent.addComponent(label);
	        panelContent.setComponentAlignment(label, Alignment.MIDDLE_LEFT);
	        

	        // Set the size as undefined at all levels
	        panelContent.setSizeUndefined();
	        setSizeUndefined();

	        this.setCaption(caption);
	        // The composition root MUST be set
	        setCompositionRoot(panelContent);
	    }
	    
	}