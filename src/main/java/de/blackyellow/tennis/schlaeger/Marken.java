package de.blackyellow.tennis.schlaeger;

public enum Marken {

	BABOLAT("Babolat"),
	WILSON("Wilson"),
	HEAD("Head"),
	PRINCE("Prince"),
	DUNLOP("Dunlop"),
	PACIFIC("Pacific"),
	POWERANGELE("PowerAngle"),
	PROKENNEX("ProKennex"),
	TECNIFIBRE("Tecnifibre"),
	VOLKL("Volkl"),
	YONEX("Yonex"),
	CRANE("Crane"),
	SONSTIGE("Sonstige");
	
	private String name;

	Marken(String name)
	{
		this.name = name;
	}
	
	@Override
	public String toString() {
		return this.name;
	}
}
