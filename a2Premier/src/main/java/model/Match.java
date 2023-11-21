package model;

import java.sql.Date;

public class Match {
	
	
	//Propietats
	private java.util.Date matchDate;
	private java.util.Date matchTime;
	private String homeTeam;
	private String awayTeam;
	private int FTHG;
	private int FTAG;
	private String FTR;
    private int HTHG;
    private int HTAG;
    private String HTR;
    private String Referee;
    private int HS;
    private int AS;
    private int HST;
    private int AST;
    private int HF;
	private int AF;
    private int HC;
    private int AC;
    private int HY;
    private int AY;
    private int HR;
    private int AR;
	
	
    //Constructor
    
    public Match (java.util.Date MatchDate, java.util.Date MatchTime, String HomeTeam, String AwayTeam, int fTHG, int fTAG, String fTR, int hTHG,
			int hTAG, String hTR, String referee, int hS, int aS, int hST, int aST, int hF, int aF, int hC, int aC,
			int hY, int aY, int hR, int aR) {
    	
    	this.matchDate= MatchDate;
    	this.matchTime= MatchTime;
    	this.homeTeam=HomeTeam;
    	this.awayTeam=AwayTeam;
    	this.FTHG=fTHG;
    	this.FTAG=fTAG;
    	this.FTR=fTR;
    	this.HTHG=hTHG;
    	this.HTAG=hTAG;
    	this.HTR=hTR;
    	this.Referee=referee;
    	this.HS=hS;
    	this.AS=aS;
    	this.HST=hST;
    	this.AST=aST;
    	this.HF=hF;
    	this.AF=aF;
    	this.HC=hC;
    	this.AC=aC;
    	this.HY=hY;
    	this.AY=aY;
    	this.HR=hR;
    	this.AR=aR;
    	
    }
    
    //Propietats
    
    public java.util.Date getDateMatch() {
		return matchDate;
	}
	public java.util.Date getTimeMatch() {
		return matchTime;
	}
	public String getHomeTeam() {
		return homeTeam;
	}
	public String getAwayTeam() {
		return awayTeam;
	}
	public int getFTHG() {
		return FTHG;
	}
	public int getFTAG() {
		return FTAG;
	}
	public String getFTR() {
		return FTR;
	}
	public int getHTHG() {
		return HTHG;
	}
	public int getHTAG() {
		return HTAG;
	}
	public String getHTR() {
		return HTR;
	}
	public String getReferee() {
		return Referee;
	}
	public int getHS() {
		return HS;
	}
	public int getAS() {
		return AS;
	}
	public int getHST() {
		return HST;
	}
	public int getAST() {
		return AST;
	}
	public int getHF() {
		return HF;
	}
	public int getAF() {
		return AF;
	}
	public int getHC() {
		return HC;
	}
	public int getAC() {
		return AC;
	}
	public int getHY() {
		return HY;
	}
	public int getAY() {
		return AY;
	}
	public int getHR() {
		return HR;
	}
	public int getAR() {
		return AR;
	}
    
    
    
	
	
	
	
	

}
