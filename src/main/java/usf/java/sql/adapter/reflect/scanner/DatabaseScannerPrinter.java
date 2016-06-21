package usf.java.sql.adapter.reflect.scanner;

import java.sql.SQLException;

import usf.java.sql.adapter.formatter.Formatter;
import usf.java.sql.core.connection.ConnectionManager;
import usf.java.sql.core.field.Database;
import usf.java.sql.core.mapper.DatabaseMapper;
import usf.java.sql.core.parser.SqlParser;
import usf.java.sql.core.reflect.scanner.DatabaseScanner;

public class DatabaseScannerPrinter extends AbstractScannerAdapter<Database> {

	public DatabaseScannerPrinter(SqlParser sqlParser, Formatter formatter) {
		super(sqlParser, new DatabaseMapper(), formatter);
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
	public void end() {
		formatter.endRows();
		formatter.endTable();
	}


	
	public void list(ConnectionManager cm) throws SQLException{
		this.list(cm, null);
	}
	public void list(ConnectionManager cm, String pattern) throws SQLException{
		new DatabaseScanner(cm).run(this, pattern);
	}
}
