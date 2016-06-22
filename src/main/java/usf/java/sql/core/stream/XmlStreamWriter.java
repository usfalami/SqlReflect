package usf.java.sql.core.stream;

import java.io.Writer;
import java.sql.Date;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

public class XmlStreamWriter implements StreamWriter {

	protected XMLStreamWriter xmlStreamWriter;
	
	public XmlStreamWriter(Writer writer) throws XMLStreamException {
		XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newInstance();	
		xmlStreamWriter = xmlOutputFactory.createXMLStreamWriter(writer);
	}
	
	@Override
	public void writeBoolean(String name, boolean bool) throws Exception {
		xmlStreamWriter.writeStartElement(name);
		xmlStreamWriter.writeCharacters(""+bool);
		xmlStreamWriter.writeEndElement();
	}

	@Override
	public void writeInt(String name, int number) throws Exception {
		xmlStreamWriter.writeStartElement(name);
		xmlStreamWriter.writeCharacters(""+number);
		xmlStreamWriter.writeEndElement();
	}

	@Override
	public void writeLong(String name, long number) throws Exception {
		xmlStreamWriter.writeStartElement(name);
		xmlStreamWriter.writeCharacters(""+number);
		xmlStreamWriter.writeEndElement();
	}

	@Override
	public void writeFloat(String name, float number) throws Exception {
		xmlStreamWriter.writeStartElement(name);
		xmlStreamWriter.writeCharacters(""+number);
		xmlStreamWriter.writeEndElement();
	}

	@Override
	public void writeDouble(String name, double number) throws Exception {
		xmlStreamWriter.writeStartElement(name);
		xmlStreamWriter.writeCharacters(""+number);
		xmlStreamWriter.writeEndElement();
	}

	@Override
	public void writeString(String name, String string) throws Exception {
		xmlStreamWriter.writeStartElement(name);
		xmlStreamWriter.writeCharacters(""+string);
		xmlStreamWriter.writeEndElement();
	}

	@Override
	public void writeDate(String name, Date date) throws Exception {
		xmlStreamWriter.writeStartElement(name);
		xmlStreamWriter.writeCharacters(""+DATE_FORMATTER.format(date));
		xmlStreamWriter.writeEndElement();
	}

	@Override
	public void startList(String name) throws Exception {
		xmlStreamWriter.writeStartElement(name);
	}
	@Override
	public void endList() throws Exception {
		xmlStreamWriter.writeEndElement();
	}

	@Override
	public void startObject(String name, String[] columns) throws Exception {
		xmlStreamWriter.writeStartElement(name);
	}
	@Override
	public void endObject() throws Exception {
		xmlStreamWriter.writeEndElement();
	}
	@Override
	public void start(String name) throws Exception {
		xmlStreamWriter.writeStartDocument();
		startList(name);
	}
	@Override
	public void end() throws Exception {
		endList();
		xmlStreamWriter.writeEndDocument();
		xmlStreamWriter.flush();
		//close stream
	}

}
