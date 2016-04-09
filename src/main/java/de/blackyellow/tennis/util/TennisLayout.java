package de.blackyellow.tennis.util;

import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class TennisLayout extends VerticalLayout {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1128512605567759315L;
	private VerticalLayout headerLayout = new VerticalLayout();
	private VerticalLayout bodyLayout = new VerticalLayout();
	private HorizontalLayout footerLayout = new HorizontalLayout();
	
	public void initDefaultLayout()
	{
		setSpacing(true);
		Label label = new Label("CM-super-duper-App");
		label.setStyleName(ValoTheme.LABEL_H1);
		label.addStyleName(ValoTheme.LABEL_NO_MARGIN);
		VerticalLayout overallHeader = new VerticalLayout(label);
		overallHeader.setComponentAlignment(label, Alignment.BOTTOM_LEFT);
		overallHeader.setMargin(new MarginInfo(false, true, false, true));
		addComponent(overallHeader);
		
		headerLayout.setMargin(new MarginInfo(false, true, false, true));
		addComponent(headerLayout);
		
		addComponent(bodyLayout);
		
		footerLayout.addComponent(new LogoutButton());
		footerLayout.addComponent(new HomeButton());
		footerLayout.setSpacing(true);
		footerLayout.setMargin(new MarginInfo(false, true, false, true));
		addComponent(footerLayout);
	}
	
	public void setHeader(Component header, Alignment alignment)
	{
		headerLayout.removeAllComponents();
		headerLayout.addComponent(header);
		headerLayout.setComponentAlignment(header, alignment);
	}
	
	public void setBody(Component body, Alignment alignment, boolean margin)
	{
		bodyLayout.removeAllComponents();
		bodyLayout.addComponent(body);
		bodyLayout.setComponentAlignment(body, alignment);
		bodyLayout.setMargin(new MarginInfo(false, margin, false, margin));
	}
	
	public void setFooter(Component footer, Alignment alignment)
	{
		footerLayout.addComponent(footer);
		footerLayout.setComponentAlignment(footer, alignment);
	}

}
