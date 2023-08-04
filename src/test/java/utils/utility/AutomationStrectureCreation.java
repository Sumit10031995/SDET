package utils.utility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class AutomationStrectureCreation {

	private static Logger logger = LogManager.getLogger(AutomationStrectureCreation.class);
	private static final String folderOne = "javaP";
	private static final String folderTwo = "new";
	private static final String tests = "NewTest";

	private static final String serviceName = "NewService";
	private static final String targetFolder = "src" + File.separator + "test" + File.separator + "java";

	private static void writeTextsToFile(File filePath, String expTexts) {
		try {
			FileWriter writer = new FileWriter(filePath);
			writer.append(expTexts);
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static File createFolder(String folderName) throws IOException {
		File file = Utility.searchFolder(folderName);
		if (file == null) {
			File dir = new File(targetFolder + File.separator + folderName);
			if (dir.mkdir()) {
				file = dir;
				System.out.println("Folder \"" + folderName + "\" Created Successfully");
			} else
				System.out.println("Folder \"" + folderName + "\" Not Created");

		} else {
			System.out.println("Folder \"" + folderName + "\" Already Exist");
		}
		return file;
	}

	public static void main(String[] args) throws IOException {
		File folder_One = createFolder(folderOne);
		File folder_Two = createFolder(folderTwo);

		Scanner sc = new Scanner(System.in);
		System.out.println("<<<==========Provide Request File Nmae===========>>>");
		String requestFileName = sc.next();
		System.out.println("<<<==========Provide Response File Nmae==========>>>");
		String responseFileName = sc.next();

		File fileOne = new File(folder_One, requestFileName);

		String requestDetails = "package " + folderOne + ";\n" + "import lombok.Builder;\n" + "import lombok.Data;\n"
				+ "import lombok.Getter;\n" + "@Getter\n" + "@Data\n" + "@Builder(toBuilder = true)\n" + "public class "
				+ serviceName + "RequestDTO{}";
		writeTextsToFile(fileOne, requestDetails);
		File fileTwo = new File(folder_Two, requestFileName);

		String responseDetails = "package " + folderTwo + ";\n" + "import lombok.Builder;\n" + "import lombok.Data;\n"
				+ "import lombok.Getter;\n" + "@Getter\n" + "@Data\n" + "@Builder(toBuilder = true)\n" + "public class "
				+ serviceName + "ResponseDTO{}";
		writeTextsToFile(fileTwo, responseDetails);
		System.out.println("Files Successfully Created");

	}

}
