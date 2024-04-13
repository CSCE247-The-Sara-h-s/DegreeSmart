package degreesmart;

import java.util.HashMap;
import java.util.ArrayList;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DataLoaderTest {
	private static HashMap<String, Byte[]> state;
	private static String[] files;

	private static Byte[] readFileToByteArray(String path) {
		Byte[] bytes = null;

		try {
			File f = new File(path);

			byte[] original = Files.readAllBytes(f.toPath());

			bytes = new Byte[original.length];
			for (int i = 0; i < original.length; i++) {
				bytes[i] = original[i];
			}
		} catch (Exception e) {
		}

		return bytes;
	}

	private static void writeByteArrayToFile(String path, Byte[] bytes) {
		try {
			byte[] toPrimitive = new byte[bytes.length];
			for (int i = 0; i < bytes.length; i++) {
				toPrimitive[i] = bytes[i];
			}

			// https://stackoverflow.com/a/4350109
			FileOutputStream stream = new FileOutputStream(path);
			stream.write(toPrimitive);
		} catch (Exception e) {
		}
	}

	@BeforeAll
	public static void saveState() {
		state = new HashMap<String, Byte[]>();
		files = new String[] {
			DataConstants.USER_FILE,
			DataConstants.COURSE_FILE,
			DataConstants.REQUIREMENT_FILE
		};

		for (String path : files) {
			state.put(path, readFileToByteArray(path));
			(new File(path)).delete();
		}
	}

	@AfterAll
	public static void restoreState() {
		for (String path : files) {
			writeByteArrayToFile(path, state.get(path));
		}
	}

	@BeforeEach
	public void resetDataLoader() throws NoSuchFieldException, IllegalAccessException {
		// https://www.geoffhayward.eu/2018/02/junit-reset-a-sigleton
		Field instance = DataLoader.class.getDeclaredField("dataLoader");
		instance.setAccessible(true);
		instance.set(DataLoader.getInstance(), null);
		instance.setAccessible(false);
	}

	@BeforeEach
	private void clearFiles() {
		for (String path : files) {
			(new File(path)).delete();
		}
	}

	private void writeDataToFile(String data, String path) {
		try {
			FileWriter file = new FileWriter(path);
			file.write(data);
			file.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testTest() {
		assertEquals(true, 1 == 1);
	}
}
