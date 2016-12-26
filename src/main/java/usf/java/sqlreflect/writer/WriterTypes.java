package usf.java.sqlreflect.writer;

import java.sql.Date;

import usf.java.sqlreflect.stream.StreamWriter;

public enum WriterTypes {
	
	BOOLEAN {
		@Override
		public void write(StreamWriter sw, String name, Object obj) throws Exception {
			sw.writeBoolean(name, (Boolean)obj);
		}
	},
	INTEGER {
		@Override
		public void write(StreamWriter sw, String name, Object obj) throws Exception {
			sw.writeInt(name, (Integer)obj);
		}
	},
	LONG {
		@Override
		public void write(StreamWriter sw, String name, Object obj) throws Exception {
			sw.writeLong(name, (Long)obj);
		}
	},
	FLOAT {
		@Override
		public void write(StreamWriter sw, String name, Object obj) throws Exception {
			sw.writeFloat(name, (Float)obj);
		}
	},
	DOUBLE {
		@Override
		public void write(StreamWriter sw, String name, Object obj) throws Exception {
			sw.writeDouble(name, (Double)obj);
		}
	},
	STRING {
		@Override
		public void write(StreamWriter sw, String name, Object obj) throws Exception {
			sw.writeString(name, (String)obj);
		}
	},
	DATE {
		@Override
		public void write(StreamWriter sw, String name, Object obj) throws Exception {
			sw.writeDate(name, (Date)obj);
		}
	},
	
	DEFAULT {
		@Override
		public void write(StreamWriter sw, String name, Object obj) throws Exception {
			sw.writeString(name, obj+"");
		}
	};
	
	public abstract void write(StreamWriter sw, String name, Object obj) throws Exception;
	
	public static final WriterTypes writerfor(String className){
		WriterTypes[] classNames = values();
		int i=0, size = classNames.length -1;
		int index = className.lastIndexOf(".");
		className = className.substring(index+1, className.length()).toUpperCase();
		while(i<size && !classNames[i].name().equals(className)) i++;
		return classNames[i];
	}

}
