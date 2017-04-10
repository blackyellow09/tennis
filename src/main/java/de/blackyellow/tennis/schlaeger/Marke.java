package de.blackyellow.tennis.schlaeger;

public class Marke {

	private int id;
	private String name;
	private String urlLogo;
	
	public Marke(String name, String url) {
		this.name = name;
		urlLogo = url;
	}
	public Marke(int id, String name, String url) {
		this.id = id;
		this.name = name;
		urlLogo = url;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrlLogo() {
		return urlLogo;
	}
	public void setUrlLogo(String urlLogo) {
		this.urlLogo = urlLogo;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
