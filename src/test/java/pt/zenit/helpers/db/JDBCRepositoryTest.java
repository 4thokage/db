package pt.zenit.helpers.db;

import java.sql.SQLException;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit test for JDBCRepository
 * 
 * @author jsilvaro
 */
public class JDBCRepositoryTest {
	
	@Test(expected = ClassNotFoundException.class)
	public void should_throw_exception_with_no_driver() throws ClassNotFoundException, SQLException {
		JDBCRepository.connect("", "");
	}
	
	@Test(expected = SQLException.class)
	public void should_throw_exception_on_invalid_connString() throws ClassNotFoundException, SQLException {
		JDBCRepository.connect("oracle.jdbc.driver.OracleDriver", null);
	}
	
	@Test
	public void should_attempt_oracle_connection() throws ClassNotFoundException {
		try {
			JDBCRepository.connect("oracle.jdbc.driver.OracleDriver", "jdbc:oracle:thin:@localhost:1521:xe");
		} catch (SQLException e) {
			Assert.assertTrue(1017 == e.getErrorCode());
		}
	}
}
