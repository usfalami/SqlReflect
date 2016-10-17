package usf.java.sqlreflect.sql.type;

import java.sql.DatabaseMetaData;
import java.sql.SQLException;

public enum NativeFunctions {
	
	STRING {
		@Override
		public String[] getFunctions(DatabaseMetaData dm) throws SQLException {
			return dm.getStringFunctions().split(SEPERATOR);
		}
	}, NUMERIC {
		@Override
		public String[] getFunctions(DatabaseMetaData dm) throws SQLException {
			return dm.getNumericFunctions().split(SEPERATOR);
		}
	}, TIME_DATE {
		@Override
		public String[] getFunctions(DatabaseMetaData dm) throws SQLException {
			return dm.getTimeDateFunctions().split(SEPERATOR);
		}
	}, SYSTEM {
		@Override
		public String[] getFunctions(DatabaseMetaData dm) throws SQLException {
			return dm.getSystemFunctions().split(SEPERATOR);
		}
	};
	
	public abstract String[] getFunctions(DatabaseMetaData dm) throws SQLException;
	
	private static final String SEPERATOR = ",";

}
