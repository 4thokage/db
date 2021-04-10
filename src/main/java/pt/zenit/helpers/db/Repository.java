package pt.zenit.helpers.db;

import java.sql.Connection;
import java.sql.SQLException;

/** Simple repository access Interface */
public interface Repository {

  /**
   * Establishes a connection with a DB
   *
   * @param driverClass DB driver identifier {@String}
   * @param connString The connection string
   * @throws SQLException
   * @throws ClassNotFoundException
   */
  static void connect(String driverClass, String connString)
      throws SQLException, ClassNotFoundException {}

  /**
   * Ends the current established connection
   *
   * @throws SQLException
   */
  static void disconnect() throws SQLException {}

  static Connection getConnection() {
    return null;
  }
}
