package usf.java.sqlreflect.binder;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import usf.java.sqlreflect.sql.entry.GenericType;

public class EntryMultiBinder implements MultipleBinder<GenericType> {
	
	public void findCityByCountryAndDistrict(CallableStatement pstmt, GenericType entry) throws SQLException{
		pstmt.setString(1, entry.get("CountryCode").toString());
		pstmt.setString(2, entry.get("District").toString());
	}
	
	public void findCityByCountryAndDistrict(PreparedStatement pstmt, GenericType entry) throws SQLException{
		pstmt.setString(1, entry.get("code").toString());
	}
	
}
