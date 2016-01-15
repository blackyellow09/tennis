package de.blackyellow.tennis.bespannung;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Grid.HeaderCell;
import com.vaadin.ui.Grid.HeaderRow;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.themes.ValoTheme;

import de.blackyellow.tennis.person.Kunde;
import de.blackyellow.tennis.person.NeuePersonView;
import de.blackyellow.tennis.schlaeger.Schlaeger;
import de.blackyellow.tennis.schlaeger.SchlaegerDetailsView;

public class BespannungsuebersichtViewImpl extends VerticalLayout implements View, BespannungsuebersichtView {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7051374909271803147L;
	private BespannungsuebersichtViewListener listener;
	private Kunde kunde;
	
	@Override
	public void enter(ViewChangeEvent event) {
		listener.ermittleKundendaten(event.getParameters());

		if(getComponentCount() > 0)
		{
			removeAllComponents();
		}
//		setSizeFull();
//		setMargin(true);
		setSpacing(true);
		
		Label ueberschrift = new Label("Schlägerübersicht von " + kunde.getName());
		ueberschrift.setStyleName(ValoTheme.LABEL_H2);
		addComponent(ueberschrift);
		
		BeanItemContainer<BespannungKurzItem> schlaeger = listener.getBespannungsliste();
		Grid table = new Grid();
		table.setSizeFull();
		table.setContainerDataSource(schlaeger);
		table.setColumns(BespannungKurzItem.NAME, BespannungKurzItem.BILD, BespannungKurzItem.DATUM, 
				BespannungKurzItem.DT, BespannungKurzItem.LAENGS, BespannungKurzItem.QUER);
		table.getColumn(BespannungKurzItem.NAME).setHeaderCaption("Schläger");
		table.getColumn(BespannungKurzItem.BILD).setHeaderCaption("Seitenbild");
		table.getColumn(BespannungKurzItem.DATUM).setHeaderCaption("Letzte Bespannung");
		table.getColumn(BespannungKurzItem.DT).setHeaderCaption("DT");
		HeaderRow extraHeader = table.prependHeaderRow();
		HeaderCell joinedCell = extraHeader.join(BespannungKurzItem.LAENGS, BespannungKurzItem.QUER);
		joinedCell.setText("kg bespannt");
		table.getColumn(BespannungKurzItem.LAENGS).setHeaderCaption("Längsseiten");
		table.getColumn(BespannungKurzItem.QUER).setHeaderCaption("Querseiten");
		
		// Set the selection mode
		table.setSelectionMode(SelectionMode.SINGLE);
		
		// Add filtering fields in the header
//		setColumnFiltering(table, true, Schlaeger.NAME);
		
		table.addItemClickListener(new ItemClickListener() {
			
			@Override
			public void itemClick(ItemClickEvent event) {
				int id = ((BespannungKurzItem)event.getItemId()).getId();
				getUI().getNavigator().navigateTo(BespannungSchlaegerView.BESPANNUNG_SCHLAEGER 
                		+ "/" + id);
			}
		});
		addComponent(table);
		
		Button schlaeger1 = new Button("Schläger 1",
                new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                getUI().getNavigator().navigateTo(BespannungSchlaegerView.BESPANNUNG_SCHLAEGER);
            }
        });
		addComponent(schlaeger1);
		
		@SuppressWarnings("serial")
		Button button = new Button("Neuen Schläger zuordnen",
                new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                getUI().getNavigator().navigateTo(BespannungSchlaegerView.BESPANNUNG_SCHLAEGER_EDITABLE);
            }
        });
        button.setIcon(FontAwesome.CART_PLUS);
        addComponent(button);
	}

	@Override
	public void addListener(BespannungsuebersichtViewListener listener) {
		this.listener = listener;
	}

	@Override
	public void setKunde(Kunde kunde) {
		this.kunde = kunde;
	}

}
