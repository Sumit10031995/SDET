package utils.fileReader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class FileManager {
    
    
    public static File searchFile(String targetFile) {
    	String[] arr= targetFile.split(File.separator);
    	File expFile;
		   return ((expFile=searchFile(new File(arr[arr.length - 1])))==null)? createFolders("src",targetFile): expFile;               
    }
    public static File searchFolder(String targetFile) {
    	String[] arr= targetFile.split(File.separator);
    	File expFile;
		   return ((expFile=searchFolder(new File(arr[arr.length - 1])))==null)? createFolders("src",targetFile): expFile;                
    }
    public static File searchFile(String baseFile,String targetFile) {
    	String[] arr= targetFile.split(File.separator);
    	File expFile;
		   return ((expFile=searchFileDetails(new File(baseFile),new File(arr[arr.length - 1])))==null)? createFolders(baseFile,targetFile): expFile;               
    }
    public static File searchFolder(String baseFile,String targetFile) {
    	String[] arr= targetFile.split(File.separator);
    	File expFile;
		   return ((expFile=searchFolderDetails(new File(baseFile),new File(arr[arr.length - 1])))==null)? createFolders(baseFile,targetFile): expFile;               
    }
    private static File searchFile(File targetFile) {
    	File file = new File("src");
		return searchFile(file,targetFile);
    }
    
    
    private static File searchFolder(File targetFile) {
    	File file = new File("src");
		return searchFolder(file,targetFile);
    }
   
    private static File searchFileDetails(File baseFile,File targetFile) {
		return searchFile(baseFile,targetFile);
    }
    
   
    private static File searchFolderDetails(File baseFile,File targetFile) {
		return searchFolder(baseFile,targetFile);
    }
 	    

    
    private static File searchFolder(File directory, String targetFolderName) {
        if (directory.isDirectory()) {
            File[] subdirectories = directory.listFiles(File::isDirectory);
            if (subdirectories != null) {
                for (File subdirectory : subdirectories) {
                    if (subdirectory.getName().equalsIgnoreCase(targetFolderName)) {
                        return subdirectory;
                    } else {
                        File foundFolder = searchFolder(subdirectory, targetFolderName);
                        if (foundFolder != null) {
                            return foundFolder;
                        }
                    }
                }
            }
        }
        return null;
    }
    
    private static File searchFolder(File directory, File targetFolderName) {
        if (directory.isDirectory()) {
            File[] subdirectories = directory.listFiles(File::isDirectory);
            if (subdirectories != null) {
                for (File subdirectory : subdirectories) {
                    if (subdirectory.getName().equalsIgnoreCase(targetFolderName.toString())) {
                        return subdirectory;
                    } else {
                        File foundFolder = searchFolder(subdirectory, targetFolderName);
                        if (foundFolder != null) {
                            return foundFolder;
                        }
                    }
                }
            }
        }
        return null;
    }
    
    private static File searchFile(File directory, String targetFileName) {
        if (directory.isDirectory()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        File foundFile = searchFile(file, targetFileName);
                        if (foundFile != null) {
                            return foundFile;
                        }
                    } else {
                        if (file.getName().equalsIgnoreCase(targetFileName)) {
                            return file;
                        }
                    }
                }
            }
        }
        return null;
    }
    
    private static File searchFile(File directory, File targetFileName) {
        if (directory.isDirectory()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        File foundFile = searchFile(file, targetFileName);
                        if (foundFile != null) {
                            return foundFile;
                        }
                    } else {
                        if (file.getName().equalsIgnoreCase(targetFileName.toString())) {
                            return file;
                        }
                    }
                }
            }
        }
        return null;
    }
	public static void writeTextsToFile(File filePath, String content) {
		 try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
	            writer.write(content.replace(".java", ""));
	            System.out.println("String written to file successfully.");
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	}
	
	
	
	private static File createFolders(String target, String foldersName) {
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

				}else {
					target+=dir+File.separator;
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
	 
}
