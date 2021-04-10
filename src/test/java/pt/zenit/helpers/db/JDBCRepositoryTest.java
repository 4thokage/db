package pt.zenit.helpers.db;

import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;

/**
 * Unit test for JDBCRepository
 *
 * @author jsilvaro
 */
public class JDBCRepositoryTest {

  private static final int SQL_ERROR_CODE_CONNECTION_REFUSED = 17002;

  @Test(expected = ClassNotFoundException.class)
  public void should_throw_exception_with_no_driver() throws ClassNotFoundException, SQLException {
    JDBCRepository.connect("", "");
  }

  @Test(expected = SQLException.class)
  public void should_throw_exception_on_invalid_connString()
      throws ClassNotFoundException, SQLException {
    JDBCRepository.connect("oracle.jdbc.driver.OracleDriver", null);
  }

  @Test
  public void should_attempt_oracle_connection() throws ClassNotFoundException {
    try {
      JDBCRepository.connect(
          "oracle.jdbc.driver.OracleDriver", "jdbc:oracle:thin:@localhost:1521:xe");
    } catch (SQLException e) {
      Assert.assertTrue(SQL_ERROR_CODE_CONNECTION_REFUSED == e.getErrorCode());
    }
  }
}
