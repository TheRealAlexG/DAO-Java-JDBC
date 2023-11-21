package test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.google.protobuf.TextFormat.ParseException;

import dao.PremierDAO;
import dao.PremierDAOFactory;
import model.Match;
import model.Team;

public class Main {
	
	
	public static void main(String[] args) throws SQLException, IOException, ParseException {
		PremierDAOFactory factory= new PremierDAOFactory();
		PremierDAO dao = factory.createPremierDAO();
		
		//1
		/*
		Team teamToAdd = new Team("Real Madrid ", "RM", "#124stF", "linkLogo");
		if(dao.AddTeam(teamToAdd)) System.out.println("I -> Team added!");
		*/
		
		
		//2
		//dao.ImportTeams("clubs.csv");
		
		
		//3
		
		/*
		Team team = dao.GetTeam("EVE");
		System.out.println("3 : Selected team: " + team.getClubName() + " " + team.getAbv() + " " + team.getHexCode() + " " + team.getLogoLink());
		*/
		
		//4
		/*
		String teamAbbreviation = dao.GetTeamAbbreviation("Arsenal");
		System.out.println("4 -> Team Abbreviation for Arsenal: " + teamAbbreviation);
		*/
		
		//5
		/*
		Match matchToAdd = new Match(new Date(), new Date(),
               "Arsenal", "NewCastle", 3, 2, "W", 2, 0, "H", "RefName", 12, 4, 8, 2, 10, 6, 8, 3, 8, 2, 0, 2);
		if(dao.AddMatch(matchToAdd)) System.out.println("5 -> Match added!");
		*/
		
		//6
		
		//dao.ImportMatches("results.csv");
		
	
		
		//7
		/*
		String dateString = "2022-08-06";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date = null;
		try {
			date = (java.util.Date) sdf.parse(dateString);
		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Match matchFound = dao.GetMatch((java.util.Date) date, dao.GetTeam("FUL"), dao.GetTeam("LIV"));
		if(matchFound != null)System.out.println("6 ->  Congratulations Match found! -> " + matchFound.getHomeTeam() + " vs " + matchFound.getAwayTeam() + " | Result: " + matchFound.getFTHG() + ":" + matchFound.getFTAG());
		
		Match matchNotFound = dao.GetMatch((java.util.Date) new Date(), dao.GetTeam("FUL"), dao.GetTeam("LIV"));
		if(matchNotFound == null) System.out.println("7 -> Sorry Match not found!");
		*/
		
		
		//8
		/*
		int homeGoals = dao.HomeGoals();
		System.out.println("8 -> Home goals -> " + homeGoals);
		*/
		
		/*
		//9
		int awayGoals = dao.AwayGoals();
		System.out.println("9 -> Away goals -> " + awayGoals);
		*/
		
		
		/*
		//10
		ArrayList<Match> matchesOfChelsea = dao.MatchesOfTeam(dao.GetTeam("CHE"));
		System.out.println("10 -> Chelsea Matches: " + matchesOfChelsea.size());
		*/
	
		//11
		/*
		Team crystalPalace = dao.GetTeam("CRY");
		System.out.println("11 -> " + crystalPalace.getClubName() + " has: " + dao.RedCards(crystalPalace));
		*/
		
		/*
		//12
		ArrayList<Team> teamsRedCards = dao.TopRedCards();
		System.out.print("12 ->  red cards: ");
		for(Team teamFromList : teamsRedCards){
			System.out.print(teamFromList.getClubName() + " ");
		}
		 	*/
	}
	
}
	

	
	
	
	
	
	
	
		
	
	
	
	
	


