import java.io.IOException;
import csv.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class CsvReadermine  {

	// This is a function. We can create as many as we want in this class:
	public String[][] readFile(Path pathToFile) throws IOException {

		String[][] myArray = new String[5][13];
		List<String> lines = Files.readAllLines(pathToFile, Charset.defaultCharset());

		lines.forEach(System.out::println); // Print out the List

		int row = 0;
		// For each element(line) in the "lines" list:
		for (String line : lines) { 
			// Add elements(line) of the List(lines) into array:
			String[] InArray = line.split(";"); 
			int numOfColumns = InArray.length;
			for (int column = 0; column < numOfColumns; column++) {
				myArray[row][column] = InArray[column];
			}
			row++; // move to next row

		}

		return myArray;

	}


}
