import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class DummyFileReader {
	public void readNames(String path) {
		InputStream resourceAsStream = DummyFileReader.class.getResourceAsStream(path);
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(resourceAsStream));
		String name;
		try {
			while ((name = bufferedReader.readLine()) != null) {
				System.out.println(name);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*public static void main(String[] args) throws IOException {
		DummyFileReader dummyFileReader = new DummyFileReader();
		dummyFileReader.readNames("/names.txt");
	}*/
}
