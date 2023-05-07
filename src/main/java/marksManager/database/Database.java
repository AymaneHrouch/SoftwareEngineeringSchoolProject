package marksManager.database;

import static marksManager.constants.constants.DB_DBNAME;
import static marksManager.constants.constants.DB_HOSTNAME;
import static marksManager.constants.constants.DB_PASSWORD;
import static marksManager.constants.constants.DB_PORT;
import static marksManager.constants.constants.DB_USERNAME;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
	private static Connection con = null;
	private static Database instance = null;

	private Database() {
	}

	public static Database getInstance() {
		if (instance == null) {
			instance = new Database();
			connect();
		}

		return instance;
	}

	private static boolean connect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://" + DB_HOSTNAME + " :" + DB_PORT + "/" + DB_DBNAME,
					DB_USERNAME, DB_PASSWORD);

			System.out.println("Connected successfully to MySQL database...");
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return false;
	}

	public ResultSet executeQuery(String query) {
		try {
			Statement statement = con.createStatement();
			return statement.executeQuery(query);
		} catch (SQLException e) {
			System.out.println(
					"Erreur lors de l'execution de la requete SQL \n" + e.getMessage());
			return null;
		}
	}

	public boolean executeUpdate(String stmnt) {
		try {
			Statement statement = con.createStatement();
			return statement.executeUpdate(stmnt) != 0;
		} catch (SQLException e) {
			System.out.println(
					"Erreur lors de l'execution de la commande \n" + e.getMessage());
			return false;
		}
	}
}
