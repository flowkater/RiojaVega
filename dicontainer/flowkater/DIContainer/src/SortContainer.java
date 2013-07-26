import java.io.File;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

public class SortableContainer {

	public Sort getInstance() throws InstantiationException,
			IllegalAccessException, ClassNotFoundException {

		Class cls = Class.forName(getConfig());
		Sort sortContainer = (Sort) cls.newInstance();

		System.out.println(sortContainer.toString());

		return sortContainer;
	}

	/*
	 * simple framework 를 이용해서 config.xml 에서 설정을 읽어온다.
	 */
	public String getConfig() {
		Serializer serializer = new Persister();

		Config config = null;
		File source = new File("src/config.xml");

		try {
			config = serializer.read(Config.class, source);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return config.getSort();
	}
}
