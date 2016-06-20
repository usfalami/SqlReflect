package usf.java.sql.core.field;

import java.util.List;

public interface HasColumns {
	
	void setColumns(List<? extends Column> columns);
	List<? extends Column> getColumns();

}
