package pt.zenit.helpers.db;

import org.junit.Assert;
import org.junit.Test;
import pt.zenit.helpers.db.domain.DBTable;

import java.util.Collection;

/** Unit test for DBInfo class. */
public class DBInfoTest {

  @Test
  public void should_return_empty_collection_when_conn_is_null() {
    Collection<DBTable> tables = DBInfo.getDBInfo();
    Assert.assertTrue(tables.isEmpty());
  }
}
