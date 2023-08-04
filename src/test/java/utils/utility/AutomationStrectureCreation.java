package utils.utility;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class AutomationStrectureCreation {

	private static final String targetFolder = "src" + File.separator + "test" + File.separator + "java"+File.separator+"api";
	private static final String folderOne = "requestDTO"+File.separator+"aaaaa"+File.separator+"zzz";
	private static final String folderTwo = "responseDTO";
	private static final String tests = "NewTest";

	

	public static void main(String[] args) throws IOException {
		File folder_One = Utility.createFolder(targetFolder,folderOne);
		File folder_Two = Utility.createFolder(targetFolder,folderTwo);

		Scanner sc = new Scanner(System.in);
		System.out.println("<<<==========Provide FileOne Nmae===========>>>");
		String requestFileName = sc.next();
		System.out.println("<<<==========Provide FileTwo Nmae==========>>>");
		String responseFileName = sc.next();

		File fileOne = new File(folder_One, requestFileName);
		String requestDetails = "package " + Utility.captureStringAfterSpecificString(folder_One, "java/").replace("/", ".") + ";\n"
				+ "import lombok.Builder;\n" + "import lombok.Data;\n" + "import lombok.Getter;\n" + "@Getter\n"
				+ "@Data\n" + "@Builder(toBuilder = true)\n" + "public class " + requestFileName + "{}";
		Utility.writeTextsToFile(fileOne, requestDetails);
		
		File fileTwo = new File(folder_Two, responseFileName);
		String responseDetails = "package " + Utility.captureStringAfterSpecificString(folder_Two, "java").replace("/", ".") + ";\n"
				+ "import lombok.Builder;\n" + "import lombok.Data;\n" + "import lombok.Getter;\n" + "@Getter\n"
				+ "@Data\n" + "@Builder(toBuilder = true)\n" + "public class " + responseFileName + "{}";
		Utility.writeTextsToFile(fileTwo, responseDetails);
		
		System.out.println("Files Successfully Created");

	}

}
