package pt.zenit.helpers.db.sql;

public class OracleQueries {

  public static final String DB_INFO_QUERY =
      "SELECT OWNER,TABLE_NAME,STATUS FROM all_tables WHERE OWNER NOT IN ('SYS', 'MDSYS','CTXSYS','XDB','SYSTEM', 'EXFSYS','OLAPSYS') AND OWNER NOT LIKE 'APEX_%'";
  public static final String TABLE_INFO_QUERY =
      "SELECT column_name,data_type,data_length,data_precision FROM all_tab_columns WHERE TABLE_NAME = ? ORDER BY COLUMN_ID";
}
