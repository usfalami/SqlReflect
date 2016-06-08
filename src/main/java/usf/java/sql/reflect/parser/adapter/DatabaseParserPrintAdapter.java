package usf.java.sql.reflect.parser.adapter;

import usf.java.sql.connection.ConnectionManager;
import usf.java.sql.db.field.Database;
import usf.java.sql.formatter.Formatter;

public class DatabaseParserPrintAdapter extends AbstractDatabaseParserAdapter {

	public DatabaseParserPrintAdapter(ConnectionManager rf, Formatter formatter) {
		super(rf, formatter);
		formatter.configure(DATABASE_NAME_LENGTH);
	}
	
	@Override
	public void start() {
		formatter.startTable();
		formatter.formatHeaders("Database");
	}

	@Override
	public void performDatabase(Database db) {
		formatter.formatRow(db.getName());
	}

	@Override
	public void finish() {
		formatter.endTable();
	}
}
