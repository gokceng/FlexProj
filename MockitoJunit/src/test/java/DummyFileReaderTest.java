import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doThrow;

public class DummyFileReaderTest {
	@Mock
	DummyFileReader dummyFileReader;

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		doThrow(new RuntimeException("Intended Exception")).when(dummyFileReader).readNames(anyString());
	}

	@Test
	public void testReadNames() throws Exception {
		expectedException.expect(RuntimeException.class);
		expectedException.expectMessage("Intended Exception");

		dummyFileReader.readNames("names");
	}
}
