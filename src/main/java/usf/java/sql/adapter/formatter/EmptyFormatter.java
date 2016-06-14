package usf.java.sql.adapter.formatter;

public class EmptyFormatter implements Formatter {

	@Override
	public void configure(int... sizes) { }

	@Override
	public void configureAll(int count, int size) { }

	@Override
	public void startTable() { }

	@Override
	public void formatTitle(String title) {	}

	@Override
	public void formatHeaders(Object... obj) {	}

	@Override
	public void startRows() { }

	@Override
	public void formatRow(Object... obj) { }

	@Override
	public void endRows() {	}

	@Override
	public void formatFooter(String footer) { }

	@Override
	public void endTable() { }

}
