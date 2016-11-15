package usf.java.sqlreflect.binder;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import usf.java.sqlreflect.binder.MultipleBinder;
import usf.java.sqlreflect.sql.entry.Entry;

public class EntryBinder implements MultipleBinder<Entry> {
	
	public void findCityByCountryAndDistrict(CallableStatement pstmt, Entry entry) throws SQLException{
		pstmt.setString(1, entry.get("CountryCode").toString());
		pstmt.setString(2, entry.get("District").toString());
	}
	
	public void findCityByCountryAndDistrict(PreparedStatement pstmt, Entry entry) throws SQLException{
		pstmt.setString(1, entry.get("CountryCode").toString());
		pstmt.setString(2, entry.get("District").toString());
	}
	
}
