package config;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBManager {
	private static DBManager instance = new DBManager();

	private static Connection conn;
	private DBManager() {
	}
	
	public DBManager getInstance() {
		if(instance == null)
			instance = new DBManager();
		return instance;
	}
	public Connection getConn() {
		return conn;
	}
	public void close(PreparedStatement pstmt, ResultSet rs) {
		
	}
}
