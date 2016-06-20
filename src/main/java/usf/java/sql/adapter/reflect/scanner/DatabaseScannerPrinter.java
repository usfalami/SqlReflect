package usf.java.sql.adapter.reflect.scanner;

import java.sql.SQLException;

import usf.java.sql.adapter.formatter.Formatter;
import usf.java.sql.adapter.reflect.scanner.AbstractScannerAdapter.DatabasePrinter;
import usf.java.sql.core.connection.ConnectionManager;
import usf.java.sql.core.field.Database;
import usf.java.sql.core.mapper.DatabaseMapper;
import usf.java.sql.core.reflect.scanner.DatabaseScanner;

public class DatabaseScannerPrinter extends AbstractScannerAdapter<Database> implements DatabasePrinter<Database> {

	public DatabaseScannerPrinter(ConnectionManager cm, Formatter formatter) {
		super(cm, new DatabaseMapper(), formatter);
		formatter.configure(DATABASE_NAME_LENGTH);
	}
	
	@Override
	public void start() {
		formatter.startTable();
		formatter.formatHeaders("Database");
		formatter.startRows();
	}

	@Override
	public void adapte(Database db, int index) {
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
