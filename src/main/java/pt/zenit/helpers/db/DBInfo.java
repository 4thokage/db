package pt.zenit.helpers.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pt.zenit.helpers.db.domain.DBColumn;
import pt.zenit.helpers.db.domain.DBTable;
import pt.zenit.helpers.db.sql.OracleQueries;

public class DBInfo {
	
	private static final Logger logger = LoggerFactory.getLogger(DBInfo.class);

	private static Connection conn;
	
	private DBInfo() {
		throw new AssertionError();
	}
	
    public static Collection<DBTable> getDBInfo() {
        List<DBTable> result= new ArrayList<>();
        if(getConn() != null) {
            try (PreparedStatement smt = getConn().prepareStatement(OracleQueries.DB_INFO_QUERY);
                 ResultSet rs = smt.executeQuery()) {

                while (rs.next()) {
                    DBTable dbTable = new DBTable(rs.getString("OWNER"), rs.getString("TABLE_NAME"), rs.getString("STATUS"));
                    result.add(dbTable);
                }

            } catch (SQLException e) {
                logger.error("Erro a obter os dados da DB");
            }
        }
        return result;
    }

    public static Collection<DBColumn> getTableInfo(String tableName) {
        List<DBColumn> result= new ArrayList<>();
        if(getConn() != null) {
            try (PreparedStatement smt = getConn().prepareStatement(OracleQueries.TABLE_INFO_QUERY)) {

                smt.setString(1, tableName);
                ResultSet rs = smt.executeQuery();
                while (rs.next()) {
                    DBColumn dbTable = new DBColumn(rs.getString("COLUMN_NAME"), rs.getString("DATA_TYPE"), rs.getInt("DATA_LENGTH"), rs.getInt("DATA_PRECISION"));
                    result.add(dbTable);
                }

            } catch (SQLException e) {
                logger.error("Erro a obter os dados da DB");
            }
        }
        return result;
    }

	public static Connection getConn() {
		return conn;
	}

	public static void setConn(Connection conn) {
		DBInfo.conn = conn;
	}
}
