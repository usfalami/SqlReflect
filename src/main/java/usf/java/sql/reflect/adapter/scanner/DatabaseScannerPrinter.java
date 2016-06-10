package usf.java.sql.reflect.adapter.scanner;

import java.sql.SQLException;

import usf.java.sql.connection.ConnectionManager;
import usf.java.sql.db.field.Database;
import usf.java.sql.formatter.Formatter;
import usf.java.sql.reflect.adapter.scanner.AbstractScanner.DatabasePrinter;
import usf.java.sql.reflect.core.scanner.DatabaseScanner;

public class DatabaseScannerPrinter extends AbstractScanner implements DatabasePrinter {

	public DatabaseScannerPrinter(ConnectionManager rf, Formatter formatter) {
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

	@Override
	public void listDatabase() throws SQLException{
		this.listDatabase(null);
	}
	@Override
	public void listDatabase(String pattern) throws SQLException{
		new DatabaseScanner().run(this, pattern);
	}
}
