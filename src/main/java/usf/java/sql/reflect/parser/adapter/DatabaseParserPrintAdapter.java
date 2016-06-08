package usf.java.sql.reflect.parser.adapter;

import usf.java.sql.connection.ConnectionManager;
import usf.java.sql.db.field.Database;
import usf.java.sql.formatter.Formatter;
import usf.java.sql.reflect.parser.adapter.AbstractParserAdapter.DatabaseParserAdapter;

public class DatabaseParserPrintAdapter extends DatabaseParserAdapter {

	public DatabaseParserPrintAdapter(ConnectionManager rf, Formatter formatter) {
		super(rf, formatter);
		formatter.configure(-50);
	}

	@Override
	public void performDatabase(Database db) {
		if(db == null) return;
		formatter.formatRow(db.getName());
	}

	@Override
	public void start() {
		formatter.startTable();
		formatter.formatHeaders("Database");
	}

	@Override
	public void finish() {
		formatter.endTable();
	}
}
