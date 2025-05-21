package prt.springbootthymeleafcrudwebapp;

import org.junit.jupiter.api.Test;
import prt.springbootthymeleafcrudwebapp.service.HashPass;

import java.sql.*;
import static org.junit.jupiter.api.Assertions.*;

class SpringbootThymeleafCrudWebAppApplicationTests {
	// Database Credentials
	private static final String URL = "jdbc:mysql://localhost:3306/softwareimplementation";
	private static final String USER = "OH25895";
	private static final String PASSWORD = "0T0n1ytodaynew?";

	@Test
	void testDatabaseConnection() {
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
			assertNotNull(connection, "Database connection should not be null.");
			System.out.println("Connection successful!");
		} catch (SQLException e) {
			fail("Connection failed: " + e.getMessage());
		}
	}

	@Test
	void testPasswordLogin() {
		String plainPassword = "password123";
		String hashedPassword = HashPass.hashSHA512(plainPassword);

		String query = "SELECT password_hash FROM users WHERE id = 1";

		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
			 PreparedStatement ps = connection.prepareStatement(query);
			 ResultSet rs = ps.executeQuery()) {

			if (rs.next()) {
				String storedPasswordHash = rs.getString("password_hash");
				assertEquals(storedPasswordHash, hashedPassword, "Hashed password should match stored password hash.");
				System.out.println("Password match successful!");
			} else {
				fail("No user with id 1 found.");
			}
		} catch (SQLException e) {
			fail("Database query failed: " + e.getMessage());
		}
	}
}
