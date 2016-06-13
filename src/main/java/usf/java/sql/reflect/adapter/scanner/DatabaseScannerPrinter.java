package usf.java.sql.reflect.adapter.scanner;

import java.sql.SQLException;

import usf.java.sql.connection.ConnectionManager;
import usf.java.sql.db.field.Database;
import usf.java.sql.formatter.Formatter;
import usf.java.sql.reflect.adapter.scanner.AbstractScannerAdapter.DatabasePrinter;
import usf.java.sql.reflect.core.scanner.DatabaseScanner;

public class DatabaseScannerPrinter extends AbstractScannerAdapter implements DatabasePrinter {

	public DatabaseScannerPrinter(ConnectionManager rf, Formatter formatter) {
		super(rf, formatter);
		formatter.configure(DATABASE_NAME_LENGTH);
	}
	
	@Override
	public void start() {
		formatter.startTable();
		formatter.formatHeaders("Database");
		formatter.startRows();
	}

	@Override
	public void adapte(Database db) {
		formatter.formatRow(db.getName());
	}

	@Override
	public void finish() {
		formatter.endRows();
		formatter.endTable();
	}

	@Override
	public void list() throws SQLException{
		this.list(null);
	}
	@Override
	public void list(String pattern) throws SQLException{
		new DatabaseScanner().run(this, pattern);
	}
}
