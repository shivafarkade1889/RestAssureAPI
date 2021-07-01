package data.excel;

import java.io.IOException;
import java.util.ArrayList;

public class SampleTest {

	public static void main(String[] args) throws IOException {

		TestData data = new TestData();
		ArrayList<String> dataValue = data.getData("Purchase","TestData");
		int count = dataValue.size();
		for(int i = 0; i<count;i++) {
			System.out.println(dataValue.get(i));
		}

	}

}
