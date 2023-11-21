package model;

public class Team {
	
	private String clubName;
	private String  abv;
	private String hexCode;
	private String logoLink;
	
	
	public Team (String ClubName, String Abv, String HexCode,String LogoLink) {
		
		this.clubName=ClubName;
		this.abv=Abv;
		this.hexCode=HexCode;
		this.logoLink=LogoLink;
		
		
	}
	
	
	public String getClubName() {
		return clubName;
	}
	
	public String getAbv() {
		
		return abv;
	}
	
	public String getHexCode() {
		
		return hexCode;
	}
	
	
	public String getLogoLink() {
		
		return logoLink;
	}
	
	
	

}
