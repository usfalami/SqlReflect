package usf.tera.field;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * 
 * @author YAH
 *
 */
public class Procedure implements Field {
	
	public static final Pattern patern = Pattern.compile("^call (\\w+)\\.(\\w+)\\s*\\((.+)\\)$", Pattern.CASE_INSENSITIVE);

	protected String name, schema;
	protected Parameter[] parameters;
	
	public Procedure() {

	}
	
	public Procedure(String name, String schema, Parameter[] parameters) {
		super();
		this.name = name;
		this.schema = schema;
		this.parameters = parameters;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Parameter[] getParameters() {
		return parameters;
	}
	public void setParameters(Parameter[] parameters) {
		this.parameters = parameters;
	}
	public String getSchema() {
		return schema;
	}

	public void setSchema(String schema) {
		this.schema = schema;
	}

	public static final Procedure build(String query){
		Matcher m = patern.matcher(query);
		if(m.matches()) {
			String sc = m.group(2);
			String name = m.group(1); 
			String[] params = m.group(3).split(",");
			return new Procedure(sc, name, build(params));
		}
		return null;
	}
	
	public static final Parameter[] build(String[] params){
		Parameter[] paramerter = new Parameter[params.length];
		for(int i=0; i<params.length; i++) {
			paramerter[i] = new Parameter(i, params[i]);
		}
		return paramerter;
	}
	
}
