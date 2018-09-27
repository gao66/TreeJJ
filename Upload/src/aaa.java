import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class aaa {
	
	public static void main(String[] args) {
		Properties prop = System.getProperties();
		Set<String> keys = prop.stringPropertyNames();
		for(String key: keys) {
			System.out.println(key+"="+ prop.getProperty(key));
		}
	}
}
