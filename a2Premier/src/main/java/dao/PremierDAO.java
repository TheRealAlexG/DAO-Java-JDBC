package dao;


import java.util.ArrayList;

import model.Match;
import model.Team;

public interface PremierDAO {
	
	public boolean AddTeam(Team oneTeam);
	public void ImportTeams(String fileTeams);
	public Team GetTeam(String teamAbbreviation);
	public String GetTeamAbbreviation(String teamName);
	public boolean AddMatch(Match oneMatch);
	public void ImportMatches(String fileMatches);
	public Match GetMatch(java.util.Date date, Team home, Team away);
	public int HomeGoals();
	public int AwayGoals();
	public ArrayList<Match>MatchesOfTeam(Team oneTeam);
	public int RedCards(Team oneTeam);
	public ArrayList<Team> TopRedCards();
	
	

}


