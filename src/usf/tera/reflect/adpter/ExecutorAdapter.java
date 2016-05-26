package usf.tera.reflect.adpter;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface ExecutorAdapter extends Adapter {
	
	void beforeExec(PreparedStatement s) throws SQLException ;
	void afterExec(ResultSet rs) throws SQLException ;

}
