package usf.java.sqlreflect.reflect;

public class ReflectorUtils {
	
	public static boolean isEmpty(String string) {
		return string == null || string.isEmpty();
	}
	
	public static int sum(int ... values) {
		if(values == null) return 0;
		int sum = 0;
		for(int v : values)
			sum += v;
		return sum;
	}

}
