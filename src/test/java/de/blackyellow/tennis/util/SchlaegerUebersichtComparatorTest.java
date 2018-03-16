package de.blackyellow.tennis.util;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.sql.Date;

import org.junit.Test;

import de.blackyellow.tennis.bespannung.Bespannung;
import de.blackyellow.tennis.bespannung.BespannungKurzItem;
import de.blackyellow.tennis.schlaeger.Schlaeger;

public class SchlaegerUebersichtComparatorTest {

	SchlaegerUebersichtComparator underTest = new SchlaegerUebersichtComparator();
	
	@Test
	public void wennLetzteBespannungVonSchlaeger1AktuellerAlsVonSchlaeger2_dannSortiere1Vor2() {
		
		Bespannung bespannung2 = new Bespannung(0, Date.valueOf("2018-02-05"), 0, 0, 0);
		BespannungKurzItem item2 = new BespannungKurzItem(new Schlaeger(), bespannung2);
		Bespannung bespannung1 = new Bespannung(1, Date.valueOf("2018-03-05"), 0, 0, 0);;
		BespannungKurzItem item1 = new BespannungKurzItem(new Schlaeger(), bespannung1);
		
		int compareResult = underTest.compare(item1, item2);
		
		assertThat(compareResult, is(-1));
	}
	
	@Test
	public void wennLetzteBespannungVonSchlaeger1AelterAlsVonSchlaeger2_dannSortiere2Vor1() {
		
		Bespannung bespannung2 = new Bespannung(0, Date.valueOf("2018-04-05"), 0, 0, 0);
		BespannungKurzItem item2 = new BespannungKurzItem(new Schlaeger(), bespannung2);
		Bespannung bespannung1 = new Bespannung(1, Date.valueOf("2018-03-05"), 0, 0, 0);;
		BespannungKurzItem item1 = new BespannungKurzItem(new Schlaeger(), bespannung1);
		
		int compareResult = underTest.compare(item1, item2);
		
		assertThat(compareResult, is(1));
	}

	@Test
	public void wennFuerEinenSchlaegerKeineBespannungVorhanden_dannSortiereDiesenNachHinten() {
		
		Bespannung bespannung = new Bespannung(0, Date.valueOf("2018-03-05"), 0, 0, 0);
		BespannungKurzItem item2 = new BespannungKurzItem(new Schlaeger(), null);
		BespannungKurzItem item1 = new BespannungKurzItem(new Schlaeger(), bespannung);
		
		int compareResult = underTest.compare(item1, item2);
		
		assertThat(compareResult, is(-1));
	}
	
	@Test
	public void wennFuerBeideKeineBespannungVorhanden_dannSortiereNachSchlaegerNr() {
		
		Schlaeger schlaeger2 = new Schlaeger();
		schlaeger2.setSchlaegerNr(2);
		BespannungKurzItem item2 = new BespannungKurzItem(schlaeger2, null);
		Schlaeger schlaeger1 = new Schlaeger();
		schlaeger1.setSchlaegerNr(1);
		BespannungKurzItem item1 = new BespannungKurzItem(schlaeger1, null);
		
		int compareResult = underTest.compare(item1, item2);
		
		assertThat(compareResult, is(-1));
	}
	
	@Test
	public void wennBespannungsdatumGleich_dannSortiereNachSchlaegerNr() {
		
		Bespannung bespannung = new Bespannung(0, Date.valueOf("2018-03-05"), 0, 0, 0);
		Schlaeger schlaeger2 = new Schlaeger();
		schlaeger2.setSchlaegerNr(2);
		BespannungKurzItem item2 = new BespannungKurzItem(schlaeger2, bespannung);
		Schlaeger schlaeger1 = new Schlaeger();
		schlaeger1.setSchlaegerNr(1);
		BespannungKurzItem item1 = new BespannungKurzItem(schlaeger1, bespannung);
		
		int compareResult = underTest.compare(item1, item2);
		
		assertThat(compareResult, is(-1));
	}
	
	@Test
	public void wennEinSchlaegerNichtAktivIst_dannSortiereDiesenNachHinten() {
		
		Schlaeger schlaeger2 = new Schlaeger();
		schlaeger2.setSchlaegerNr(2);
		schlaeger2.setAktiv(true);
		BespannungKurzItem item2 = new BespannungKurzItem(schlaeger2, null);
		Schlaeger schlaeger1 = new Schlaeger();
		schlaeger1.setSchlaegerNr(1);
		schlaeger1.setAktiv(false);
		BespannungKurzItem item1 = new BespannungKurzItem(schlaeger1, null);
		
		int compareResult = underTest.compare(item1, item2);
		
		assertThat(compareResult, is(1));
	}
}
