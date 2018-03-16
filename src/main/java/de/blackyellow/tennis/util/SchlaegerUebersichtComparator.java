package de.blackyellow.tennis.util;

import java.util.Comparator;

import de.blackyellow.tennis.bespannung.BespannungKurzItem;

public class SchlaegerUebersichtComparator implements Comparator<BespannungKurzItem> {

	@Override
	public int compare(BespannungKurzItem item1, BespannungKurzItem item2) {
		if(item1.getSchlaeger().isAktiv() && item2.getSchlaeger().isAktiv())
		{
			int datumVergleich;
			if(item2.getBespannung() == null && item1.getBespannung() == null)
			{
				datumVergleich = 0;
			}
			else if(item1.getBespannung() == null)
			{
				return 1;
			}
			else if(item2.getBespannung() == null)
			{
				return -1;
			}
			else
			{
				datumVergleich = item2.getBespannung().getDatum().compareTo(item1.getBespannung().getDatum());
			}
			if(datumVergleich == 0)
			{
				return item1.getSchlaeger().getSchlaegerNr() - item2.getSchlaeger().getSchlaegerNr();
			}
			return datumVergleich;
		}
		else if(item1.getSchlaeger().isAktiv())
		{
			return -1;
		}
		else
		{
			return 1;
		}
	}

}
