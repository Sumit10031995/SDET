package utils.utility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class AutomationStrectureCreation {

	private static Logger logger = LogManager.getLogger(AutomationStrectureCreation.class);
	private static final String folderOne = "requestDTO"+File.separator+"aaaaa"+File.separator+"zzz";
	private static final String folderTwo = "responseDTO";
	private static final String tests = "NewTest";

	private static final String serviceName = "NewService";
	private static final String targetFolder = "src" + File.separator + "test" + File.separator + "java"+File.separator+"api";
	
	

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
			File dir=createFolders(targetFolder, folderName);
			if (dir!=null) {
				file=dir;
				System.out.println("New Folder \"" + folderName + "\" Created Successfully");
			}
			else
				System.out.println("Folder \"" + folderName + "\" Not Created");

		} else {
			System.out.println("Folder \"" + folderName + "\" Already Exist");
		}
		return file;
	}


	public static String captureStringAfterSpecificString(File input, String specificString) {
		int index = input.getAbsolutePath().indexOf(specificString);
		if (index != -1) {
			index += specificString.length();
			return input.getAbsolutePath().substring(index).trim();
		}
		return "";
	}
	
	public static File createFolders(String target, String foldersName) {
		boolean flag = false;
		if (foldersName.contains(File.separator)) {
			String files[] = foldersName.split(File.separator);
			for (String dir : files) {
				flag = false;
				File fil = new File(target, dir);
				if (fil.mkdir()) {
					target += File.separator+dir;
					flag = true;
					System.out.println("Folder \""+dir+"\" Successfully Created");

				}
			}
		} else {
			File fil = new File(target, foldersName);
			if (fil.mkdir()) {
				flag = true;
				System.out.println("Folder \""+foldersName+"\" Successfully Created");
			}
		}
		return new File(target);
	}
	
	public static void main(String[] args) throws IOException {
		File folder_One = createFolder(folderOne);
		File folder_Two = createFolder(folderTwo);

		Scanner sc = new Scanner(System.in);
		System.out.println("<<<==========Provide FileOne Nmae===========>>>");
		String requestFileName = sc.next();
		System.out.println("<<<==========Provide FileTwo Nmae==========>>>");
		String responseFileName = sc.next();

		File fileOne = new File(folder_One, requestFileName);
		String requestDetails = "package " + captureStringAfterSpecificString(folder_One, "java/").replace("/", ".") + ";\n"
				+ "import lombok.Builder;\n" + "import lombok.Data;\n" + "import lombok.Getter;\n" + "@Getter\n"
				+ "@Data\n" + "@Builder(toBuilder = true)\n" + "public class " + serviceName + "RequestDTO{}";
		writeTextsToFile(fileOne, requestDetails);
		
		File fileTwo = new File(folder_Two, responseFileName);
		String responseDetails = "package " + captureStringAfterSpecificString(folder_Two, "java").replace("/", ".") + ";\n"
				+ "import lombok.Builder;\n" + "import lombok.Data;\n" + "import lombok.Getter;\n" + "@Getter\n"
				+ "@Data\n" + "@Builder(toBuilder = true)\n" + "public class " + serviceName + "ResponseDTO{}";
		writeTextsToFile(fileTwo, responseDetails);
		
		System.out.println("Files Successfully Created");

	}

}
