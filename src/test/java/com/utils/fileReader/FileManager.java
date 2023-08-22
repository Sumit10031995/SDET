package com.utils.fileReader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
		System.out.println(target);
		return new File(target);
	}
	
	
	public static List<String> getLogs(String filePath, String keyword) {
        List<String> retryLogs = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            Pattern pattern = Pattern.compile(keyword, Pattern.CASE_INSENSITIVE);

            while ((line = reader.readLine()) != null) {
                Matcher matcher = pattern.matcher(line);
                if (matcher.find()) {
                    retryLogs.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return retryLogs;
    }

}
