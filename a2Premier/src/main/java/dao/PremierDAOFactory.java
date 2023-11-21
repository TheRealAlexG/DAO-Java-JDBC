package dao;

import java.sql.SQLException;

public class PremierDAOFactory {
	
	

	public PremierDAO createPremierDAO() throws SQLException {
		return new PremierDAOJDBCImpl();
	}

}
