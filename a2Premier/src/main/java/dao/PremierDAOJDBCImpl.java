package dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.mysql.cj.jdbc.CallableStatement;

import model.Match;
import model.Team;
public class PremierDAOJDBCImpl implements PremierDAO {
	
	Connection conn=null;
	public PremierDAOJDBCImpl()throws SQLException{
		
		String connectionUrl="jdbc:mysql://localhost:3306/PREMIER2233?";
		conn=DriverManager.getConnection(connectionUrl,"root","");
		
		
	}
	

	@Override
	public boolean AddTeam(Team oneTeam ) {
		
		boolean Add=true;
		java.sql.CallableStatement cs=null;
		
		try {
			
			String storedProcedureCall="{call AddTeam(?,?,?,?)}";
			
			cs=conn.prepareCall(storedProcedureCall);
			cs.setString(1, oneTeam.getClubName());
			cs.setString(2, oneTeam.getAbv());
			cs.setString(3, oneTeam.getHexCode());
			cs.setString(4, oneTeam.getLogoLink());
			
			cs.executeUpdate();
			
			
			
		}
		
		catch(Exception e){
			
			System.out.println("Sorry no es pot afegir el teu equip"+e);
			Add=false;
			
			
		}finally {
			if(cs != null) {
				try {
					cs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			
			}
			
		}
		
		return Add;
		
	}
			


	@Override
	public void ImportTeams(String fileTeams) {
		BufferedReader bR=null;
		
		
		
		try {
			//StreamReader per llegir el file
			File FILE= new File("clubs.csv");
			bR= new BufferedReader(new FileReader(FILE));
			String linea;
			
			//Saltarse la primera linea
			bR.readLine();
			
			
			//Comensar a llegir i ficar les coses en un array 
			
			while((linea=bR.readLine())!=null) {
				
				String[] camps=linea.split(",");
				String clubName=camps[0];
				String abv=camps[1];
				String hexCode=camps[2];
				String logoLink=camps[3];
				
				Team team=new Team(clubName,abv,hexCode,logoLink);
				
				AddTeam(team);
				
			
			}
			

			
		}
		
		catch(Exception e){
			e.printStackTrace();
			
			
		}finally {
			if(bR != null) {
				try {
					bR.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			
			}
			
		}
		
	}

	@Override
	public Team GetTeam(String teamAbbreviation) {
		
		Team team=null;
		java.sql.CallableStatement cS= null;
		
		try {
			
			
			String storedProcedureCall="{call GetTeamAbv(?)}";
			cS=conn.prepareCall(storedProcedureCall);
			cS.setString(1, teamAbbreviation);
			
			boolean hiHaResults=cS.execute();
			
			if(hiHaResults) {
				ResultSet rS=cS.getResultSet();
				while(rS.next()) {
					
					team = new Team(rS.getString(1),
							rS.getString(2),
							rS.getString(3),
							rS.getString(4));
					
				}
			}
			
			
			
		}
		catch(Exception e) {
			
			System.out.println("No sha trobat per el teu equip"+e);
			
		}finally {
			
			if(cS!= null) {
				
				try {
					cS.close();
				}
				catch(Exception e){
					e.printStackTrace();
					
				}
			}
			
		}
		
		return team;
		
	
	}

	@Override
	public String GetTeamAbbreviation(String teamName) {
		String abv=null;
		java.sql.CallableStatement cS=null;
		
		
		try {
			
			String storedProcedureCall="{call GetAbvTeam(?,?)}";
			cS=conn.prepareCall(storedProcedureCall);
			cS.setString(1,teamName);
			
			cS.registerOutParameter(2, Types.VARCHAR);
			
			cS.execute();
			
			abv=cS.getString(2);
			
			
			
		}
		
		catch(Exception e) {
			
			System.out.println("No s'ha trobar el ABV del equip"+e);
			
		}
		
		finally {
			
			if(cS!=null) {
				try {
					cS.close();
					
				}
				
			catch(Exception e){
				e.printStackTrace();
			}
				
			}
			
		}
		
		return abv;
	}

	@Override
	public boolean AddMatch(Match oneMatch) {
		
		java.sql.CallableStatement cS=null;
		boolean Add=true;
	
		try {
			
			String storedProcedureCall = "{call AddMatch(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
			cS = conn.prepareCall(storedProcedureCall);
			
			java.sql.Date sqlDate = new java.sql.Date(oneMatch.getDateMatch().getTime());
			java.sql.Time sqlTime = new java.sql.Time(oneMatch.getTimeMatch().getTime());
			
			cS.setDate(1, sqlDate);
			cS.setTime(2, sqlTime);
			cS.setString(3, GetTeamAbbreviation(oneMatch.getHomeTeam()));
			cS.setString(4, GetTeamAbbreviation(oneMatch.getAwayTeam()));
			cS.setInt(5, oneMatch.getFTHG());
			cS.setInt(6, oneMatch.getFTAG());
			cS.setString(7, oneMatch.getFTR());
			cS.setInt(8, oneMatch.getHTHG());
			cS.setInt(9, oneMatch.getHTAG());
			cS.setString(10, oneMatch.getHTR());
			cS.setString(11, oneMatch.getReferee());
			cS.setInt(12, oneMatch.getHS());
			cS.setInt(13, oneMatch.getAS());
			cS.setInt(14, oneMatch.getHST());
			cS.setInt(15, oneMatch.getAST());
			cS.setInt(16, oneMatch.getHF());
			cS.setInt(17, oneMatch.getAF());
			cS.setInt(18, oneMatch.getHC());
			cS.setInt(19, oneMatch.getAC());
			cS.setInt(20, oneMatch.getHY());
			cS.setInt(21, oneMatch.getAY());
			cS.setInt(22, oneMatch.getHR());
			cS.setInt(23, oneMatch.getAR());
			
			
			cS.executeUpdate();
			
			
			
		}
		
		catch(Exception e) {
			System.out.println("Sorry No sha pogut afegir el Team"+e);
			Add=false;
			
		}
		
		finally {
			
			if(cS != null) {
				try {
					cS.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			
			
		}
		
		return Add;
			
	}

	@Override
	public void ImportMatches(String fileMatches) {
		
		BufferedReader bR=null;
		
		try {
			File FILE= new File("results.csv");
			bR=new BufferedReader(new FileReader(FILE));
			
			bR.readLine();
			String linea;
			
			SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
			SimpleDateFormat formatTime = new SimpleDateFormat("HH:mm");
			
			
				
			while((linea = bR.readLine()) != null) {
				String[] camps = linea.split(",");
				
				
				Match match=new Match(
						formatDate.parse(camps[1]),
						formatTime.parse(camps[2]),
						GetTeamAbbreviation(camps[3]),
						GetTeamAbbreviation(camps[4]),
						Integer.parseInt(camps[5]),
						Integer.parseInt(camps[5]),
						camps[7],
						Integer.parseInt(camps[8]),
						Integer.parseInt(camps[9]),
						camps[10],
						camps[11],
						Integer.parseInt(camps[12]),
						Integer.parseInt(camps[13]),
						Integer.parseInt(camps[14]),
						Integer.parseInt(camps[15]),
						Integer.parseInt(camps[16]),
						Integer.parseInt(camps[17]),
						Integer.parseInt(camps[18]),
						Integer.parseInt(camps[19]),
						Integer.parseInt(camps[20]),
						Integer.parseInt(camps[21]),
						Integer.parseInt(camps[22]),
						Integer.parseInt(camps[23])
						
					
						
						);
				
				AddMatch(match);
				
				
				
			}
				
			
		}
		
		catch(Exception e) {
			e.printStackTrace();
			
			
		}
		
		finally {
			
			
			if(bR != null)
				try {
					bR.close();
				} catch (Exception e) {
					
					e.printStackTrace();
				}
		
		}
			
	}
		
	
		
	

	@Override
	public int HomeGoals() {
		java.sql.CallableStatement cS=null;
		int totalGoals=0;
		
		
		
		try {
			
			String storedProcedureCall="{call SumAllHGoals(?)}";
			cS=conn.prepareCall(storedProcedureCall);
			cS.registerOutParameter(1, Types.INTEGER);
			
			cS.execute();
			
			totalGoals=cS.getInt(1);
			
			
		
			
		}
		
		catch(Exception e) {
			
			System.out.println(" Sorry No s'ha pogut extreure els Home Goals"+e);
			
			
		}
		
		finally {
			
			if(cS != null) {
				try {
					cS.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			
		}
		
		
		return totalGoals;
			
	}
		
		
		
		
	

	@Override
	public int AwayGoals() {
		
		
		
		java.sql.CallableStatement cS = null;
		int awayHomeGoals = 0;
		
		try {
			String storedProcedureCall = "{call SumAllAwayGoals(?)}";
			cS = conn.prepareCall(storedProcedureCall);
			cS.registerOutParameter(1, Types.INTEGER);
			
			cS.execute();
			
			awayHomeGoals = cS.getInt(1);
		} catch(Exception e) {
			System.out.println("Sorry no sha pogut extreure els Away Goals " + e);
			
		} finally {
			if(cS != null) {
				try {
					cS.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return awayHomeGoals;
		
			
	}
		
		
	

	@Override
	public ArrayList<Match> MatchesOfTeam(Team oneTeam) {
		
		java.sql.CallableStatement cS = null;
		ArrayList<Match> matches = null;
		
		try {
			String teamName = oneTeam.getAbv();
			String storedProcedureCall = "{call GetMatchesOfTeam(?)}";
			cS = conn.prepareCall(storedProcedureCall);
			
			
			
			
			cS.setString(1,teamName);
		 
			boolean hiHaResults = cS.execute();
			if(hiHaResults) {
				matches = new ArrayList<Match>();
				ResultSet rS = cS.getResultSet();
				while(rS.next()) {
					java.util.Date date = new java.util.Date(rS.getDate("match_date").getTime());
					java.util.Date time = new java.sql.Date(rS.getTime("match_time").getTime());
					
					matches.add (new Match(
	                        date,
	                        time,
	                        rS.getString("HomeTeam"),
	                        rS.getString("AwayTeam"),
	                        rS.getInt("FTHG"),
	                        rS.getInt("FTAG"),
	                        rS.getString("FTR"),
	                        rS.getInt("HTHG"),
	                        rS.getInt("HTAG"),
	                        rS.getString("HTR"),
	                        rS.getString("Referee"),
	                        rS.getInt("HS"),
	                        rS.getInt("match_as"),
	                        rS.getInt("HST"),
	                        rS.getInt("AST"),
	                        rS.getInt("HF"),
	                        rS.getInt("AF"),
	                        rS.getInt("HC"),
	                        rS.getInt("AC"),
	                        rS.getInt("HY"),
	                        rS.getInt("AY"),
	                        rS.getInt("HR"),
	                        rS.getInt("AR")
	                ));
				}
			}
		} catch(Exception e) {
			System.out.println("Sorry no hem pogut Extreure els Matches " + e);
		} finally {
			if(cS != null) {
				try {
					cS.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return matches;
		
		
		
	}
	

	@Override
	public int RedCards(Team oneTeam) {
	
		
		
		java.sql.CallableStatement cS = null;
		int redCards = 0;
		
		try {
			String storedProcedureCall = "{call GetTeamRedCards(?, ?)}";
			cS = conn.prepareCall(storedProcedureCall);
			
			
			cS.setString(1, oneTeam.getAbv());
			
			
			cS.registerOutParameter(2, Types.INTEGER);
			
	
			cS.execute();
			
			
			redCards = cS.getInt(2);
			
		} catch(Exception e) {
			System.out.println("Sorry no hem pogut obtenir els RedCards " + e);
		} finally {
			if(cS != null) {
				try {
					cS.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return redCards;
	
	}
		
		
		
	

	@Override
	public ArrayList<Team> TopRedCards() {
		
		PreparedStatement pS = null;
		ArrayList<Team> topRedCards = null;
		
		try {
			pS = conn.prepareStatement("SELECT * FROM TEAM");
			
			ResultSet rS = pS.executeQuery();
			topRedCards = new ArrayList<Team>();
			while(rS.next()) {
				Team team = new Team(
						rS.getString("club_name"),
						rS.getString("abv"),
						rS.getString("hex_code"),
						rS.getString("logo_link")
				);
				if(topRedCards.size() == 0) { topRedCards.add(team);}
				else {
					if(RedCards(team) > RedCards(topRedCards.get(0))) {
						topRedCards.clear();
						topRedCards.add(team);
					}
					else if(RedCards(team) > RedCards(topRedCards.get(0))) {
						topRedCards.add(team);
					}
				}
			}
			
		} catch (SQLException e) {
			System.out.println("Sorry el partit no sha pogut trobar " + e);
		} finally {
			if(pS != null)
				try {
					pS.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		return topRedCards;
		
		
		
		
	}


	@Override
	public Match GetMatch(java.util.Date date, Team home, Team away) {
		
		PreparedStatement pS=null;
		Match MatchSeleccionat=null;
		
		try {
			
			pS=conn.prepareStatement("SELECT * FROM MATCHES "
					+ "WHERE match_date = ? AND  HomeTeam = ? AND AwayTeam = ?");
			
			java.sql.Date sqlDate= new java.sql.Date(date.getTime());
			
			pS.setDate(1, sqlDate);
			pS.setString(2, home.getAbv());
			pS.setString(3, away.getAbv());
			
			ResultSet rS = pS.executeQuery();
			while(rS.next()) {
				java.util.Date date1 = new java.util.Date(rS.getDate("match_date").getTime());
				java.util.Date time = new java.sql.Date(rS.getTime("match_time").getTime());
				
				return new Match(
                        date1,
                        time,
                        rS.getString("HomeTeam"),
                        rS.getString("AwayTeam"),
                        rS.getInt("FTHG"),
                        rS.getInt("FTAG"),
                        rS.getString("FTR"),
                        rS.getInt("HTHG"),
                        rS.getInt("HTAG"),
                        rS.getString("HTR"),
                        rS.getString("Referee"),
                        rS.getInt("HS"),
                        rS.getInt("match_as"),
                        rS.getInt("HST"),
                        rS.getInt("AST"),
                        rS.getInt("HF"),
                        rS.getInt("AF"),
                        rS.getInt("HC"),
                        rS.getInt("AC"),
                        rS.getInt("HY"),
                        rS.getInt("AY"),
                        rS.getInt("HR"),
                        rS.getInt("AR")
                );
				
			}
			
		
		}
		
		catch(Exception e) {
			
			System.out.println("Sorry no sha trobat el partit"+e);
			
		}
		
		finally {
			
			if(pS != null)
				try {
					pS.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

		}
		
		return MatchSeleccionat;
				
	
	}


	
	

}
	
	
	
	
	
	




