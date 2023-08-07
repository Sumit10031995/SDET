package utils.utility;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;

import org.testng.annotations.Test;

import java.awt.Color;
import java.awt.image.BufferedImage;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Utility {

	public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
	
//	  public Set<List<String>> convertObjectToList(CatalogProductIngestionDetailsDTO.ProductIngestionDetails object) {
//	        ObjectMapper oMapper = new ObjectMapper();
//	        Map<String, String> map = oMapper.convertValue(object, Map.class);
//	        Set<List<String>> expDataList = new LinkedHashSet<>();
//	        List<String> keySet = new ArrayList(map.keySet());
//	        List<String> values = new ArrayList(map.values());
//	        for (int i = 0; i < values.size(); i++) {
//	            if (values.get(i).contains(","))
//	                values.set(i, "\"" + values.get(i) + "\"");
//	        }
//	        expDataList.add(keySet);
//	        expDataList.add(values);
//	        return expDataList;
//	    }

	 public void removeColumn(List<List<String>> expData, String clmName) {
	        List<String> columnDetails = new ArrayList();
	        Integer pointer = null;
	        boolean flag = false;
	        try {
	            for (int i = 0; i < expData.get(0).size(); i++) {
	                if (expData.get(0).get(i).equalsIgnoreCase(clmName)) {
	                    pointer = i;
	                    flag = true;
	                    break;
	                }
	            }
	            if(pointer!=null){
	            for (int j = 0; j < expData.size(); j++) {
	                expData.get(j).remove(pointer);
	            }
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	 
	 public List<String> getColumnDetails(List<List<String>> expData, String clmName) {
	        List<String> columnDetails = new ArrayList();
	        Integer pointer = null;
	        boolean flag = false;
	        try {
	            for (int i = 0; i < expData.get(0).size(); i++) {
	                if (expData.get(0).get(i).equalsIgnoreCase(clmName)) {
	                    pointer = i;
	                    flag = true;
	                    break;
	                }
	            }
	            for (int j = 1; j < expData.size(); j++) {
	                columnDetails.add(expData.get(j).get(pointer));
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return columnDetails;
	    }
	 
	   public List<List<String>> convertObjectToListOfList(List objects) {
	        ObjectMapper oMapper = new ObjectMapper();
	        List<List<String>> expDataList = new ArrayList<>();
	        for (int i = 0; i < objects.size(); i++) {
	            Map<String, String> map = oMapper.convertValue(objects.get(i), Map.class);
	            List<String> keySet = new ArrayList(map.keySet());
	            List<String> values = new ArrayList(map.values());
	            for (int j = 0; j < values.size(); j++) {
	                if (values.get(j) == null)
	                    values.set(j, "");
	                if (values.get(j).contains(",")) {
	                    values.set(j, values.get(j));
	                }
	            }
	            if (i == 0)
	                expDataList.add(keySet);
	            expDataList.add(values);
	        }
	        return expDataList;
	    }
	   
	   private static void extractFile(ZipInputStream zipIn, String filePath) throws IOException, FileNotFoundException {
	        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath));
	        byte[] bytesIn = new byte[4096];
	        int read = 0;
	        while ((read = zipIn.read(bytesIn)) != -1) {
	            bos.write(bytesIn, 0, read);
	        }
	        bos.close();
	    }

	    public static List<String> getFileToCrawl(String directory, String fileNameToNotDelete) throws IOException {
	        List<String> list = new ArrayList();
	        File dir = new File(directory);
	        boolean flag = false;

	        String[] children = dir.list();
	        if (children == null) {
	            return null;
	        } else {
	            int j = 0;
	            String filename = children[j];
	            for (; j < children.length; j++) {
	                if (filename.contains(".jpeg") && !filename.equals(fileNameToNotDelete)) {
	                    list.add(children[j]);
	                    flag = true;
	                } else if (!filename.contains(".")) {
	                    list.add(filename);
	                    flag = true;

	                }
	                if (j < children.length - 1)
	                    filename = children[j + 1];
	            }
	            for (File file : dir.listFiles()) {
	                if (!file.getName().equals(fileNameToNotDelete))
	                    file.delete();
	            }
	        }
	        if (flag)
	            return list;
	        return null;

	    }
	    
	    public void unzip(String zipFilePath, String destDirectory) throws IOException, IOException {
	        File destDir = new File(destDirectory);
	        if (!destDir.exists()) {
	            destDir.mkdir();
	        }
	        ZipInputStream zipIn = new ZipInputStream(new FileInputStream(zipFilePath));
	        ZipEntry entry = zipIn.getNextEntry();
	        int count = 0;
	        while (entry != null && count == 0) {
	            count++;
	            String filePath = destDirectory + File.separator + entry.getName();
	            if (!entry.isDirectory()) {
	                extractFile(zipIn, filePath);
	            } else {
	                File dir = new File(filePath);
	                dir.mkdirs();
	            }
	            zipIn.closeEntry();
	            entry = zipIn.getNextEntry();
	        }
	        zipIn.close();
	    }
	    
	    protected static boolean isBracketsBalanced(String expr) {
	        Deque<Character> stack = new ArrayDeque<Character>();
	        for (int i = 0; i < expr.length(); i++) {
	            char x = expr.charAt(i);
	            if (x == '[') {
	                stack.push(x);
	                continue;
	            }
	            if (stack.isEmpty())
	                return false;
	            char check;
	            switch (x) {
	                case ']':
	                    check = stack.pop();
	                    if (check == '(' || check == '{')
	                        return false;
	                    break;
	            }
	        }
	        return (stack.isEmpty());
	    }
	    
	    public static boolean isSameImage(String imageOne, String imageTwo) {
	        DecimalFormat format = new DecimalFormat("#.##");
	        try {
	            BufferedImage img1 = ImageIO.read(new File(imageOne));
	            BufferedImage img2 = ImageIO.read(new File(imageTwo));
	            int w1 = img1.getWidth();
	            int w2 = img2.getWidth();
	            int h1 = img1.getHeight();
	            int h2 = img2.getHeight();
	            if ((w1 != w2) || (h1 != h2)) {
	                System.out.println("Both images should have same dimwnsions");
	            } else {
	                long diff = 0;
	                for (int j = 0; j < h1; j++) {
	                    for (int i = 0; i < w1; i++) {
	                        int pixel1 = img1.getRGB(i, j);
	                        Color color1 = new Color(pixel1, true);
	                        int r1 = color1.getRed();
	                        int g1 = color1.getGreen();
	                        int b1 = color1.getBlue();
	                        int pixel2 = img2.getRGB(i, j);
	                        Color color2 = new Color(pixel2, true);
	                        int r2 = color2.getRed();
	                        int g2 = color2.getGreen();
	                        int b2 = color2.getBlue();
	                        long data = Math.abs(r1 - r2) + Math.abs(g1 - g2) + Math.abs(b1 - b2);
	                        diff = diff + data;
	                    }
	                }
	                double avg = diff / (w1 * h1 * 3);
	                double percentage = (avg / 255) * 100;
	                return (Double.parseDouble(format.format(percentage)) == 0.0) ? true : false;
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return false;
	    }
	    
	    public <T> T doSerializationWithFilteredData(List<List<String>> dataList, List<String> filterItems, String columnName, Class<T> T) {
	        Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
	        T serealized = null;
	        List<Map<String, String>> data = new ArrayList<>();
	        Map<String, List<Map<String, String>>> addDataToMap = new LinkedHashMap<>();
	        Integer pointer = null;
	        boolean flag = false;
	        try {
	            for (int i = 0; i < dataList.get(0).size(); i++) {
	                if (dataList.get(0).get(i).equalsIgnoreCase(columnName)) {
	                    pointer = i;
	                    flag = true;
	                    break;
	                }
	            }
	            for (String item : filterItems) {
	                for (int i = 1; i < dataList.size(); i++) {
	                    if (dataList.get(i).get(pointer).equals(item)) {
	                        Map<String, String> expData = new LinkedHashMap<>();
	                        for (int j = 0; j < dataList.get(0).size(); j++) {
	                            expData.put(dataList.get(0).get(j), dataList.get(i).get(j));
	                        }
	                        data.add(expData);
	                    } else continue;
	                    break;
	                }
	            }
	            addDataToMap.put("data", data);
	            String expJsonString = gson.toJson(addDataToMap);
	            if (data.size() > 0)
	                return serealized = gson.fromJson(expJsonString, T);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return serealized;
	    }

	    public <T> T doSerializationWithFilteredData(List<List<String>> dataList, String columnName, String expData, Class<T> T) {
	        Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
	        T serealized = null;
	        Map<String, String> dataMap = new LinkedHashMap<>();
	        List<Map<String, String>> data = new ArrayList<>();
	        Map<String, List<Map<String, String>>> addDataToMap = new LinkedHashMap<>();
	        Integer pointer = null;
	        boolean flag = false;
	        try {
	            for (int i = 0; i < dataList.get(0).size(); i++) {
	                if (dataList.get(0).get(i).equalsIgnoreCase(columnName)) {
	                    pointer = i;
	                    flag = true;
	                    break;
	                }
	            }

	            for (int i = 1; i < dataList.size(); i++) {
	                if (dataList.get(i).get(pointer).equals(expData)) {
	                    for (int j = 0; j < dataList.get(0).size(); j++) {
	                        dataMap.put(dataList.get(0).get(j), dataList.get(i).get(j));
	                    }
	                    data.add(dataMap);
	                    break;
	                }
	            }
	            addDataToMap.put("data", data);
	            String expJsonString = gson.toJson(addDataToMap);
	            if (data.size() > 0)
	                serealized = gson.fromJson(expJsonString, T);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return serealized;
	    }

	    public <T> T doSerializationWithMultiFilteredData(List<List<String>> dataList, String columnName1, String expData1, String columnName2, String expData2, Class<T> T) {
	        Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
	        T serealized = null;
	        Map<String, String> expData = new LinkedHashMap<>();
	        List<Map<String, String>> data = new ArrayList<>();
	        Map<String, List<Map<String, String>>> addDataToMap = new LinkedHashMap<>();
	        Integer pointer1 = null;
	        Integer pointer2 = null;

	        boolean flag = false;
	        try {
	            for (int i = 0; i < dataList.get(0).size(); i++) {
	                if (dataList.get(0).get(i).equalsIgnoreCase(columnName1)) {
	                    pointer1 = i;
	                    flag = true;
	                    break;
	                }
	            }
	            for (int i = 0; i < dataList.get(0).size(); i++) {
	                if (dataList.get(0).get(i).equalsIgnoreCase(columnName2)) {
	                    pointer2 = i;
	                    flag = true;
	                    break;
	                }
	            }

	            for (int i = dataList.size()-1; i >1 ; i--) {
	                if (dataList.get(i).get(pointer1).equals(expData1) && dataList.get(i).get(pointer2).equals(expData2)) {
	                    for (int j = 0; j < dataList.get(0).size(); j++) {
	                        expData.put(dataList.get(0).get(j), dataList.get(i).get(j));
	                    }
	                    data.add(expData);
	                } else continue;
	                break;
	            }
	            addDataToMap.put("data", data);
	            String expJsonString = gson.toJson(addDataToMap);
	            if (data.size() > 0)
	                return serealized = gson.fromJson(expJsonString, T);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return serealized;
	    }

	    public <T> T doSerialization(List<List<String>> dataList, Class<T> T) {
	        Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
	        T serealized = null;
	        Map<String, String> expData;
	        List<Map<String, String>> data = new ArrayList<>();
	        Map<String, List<Map<String, String>>> addDataToMap = new LinkedHashMap<>();

	        try {
	            for (int i = 1; i < dataList.size(); i++) {
	                expData = new LinkedHashMap<>();
	                for (int j = 0; j < dataList.get(0).size(); j++) {
	                    if(dataList.get(i).get(j).equals(" "))
	                        dataList.get(i).set(j,"");
	                    expData.put(dataList.get(0).get(j), dataList.get(i).get(j));
	                }
	                data.add(expData);
	            }
	            addDataToMap.put("data", data);
	            String expJsonString = gson.toJson(addDataToMap);
	            if (data.size() > 0)
	                return serealized = gson.fromJson(expJsonString, T);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return serealized;
	    }
	    
	
	    
	    public static File searchFile(File directory, String targetFileName) {
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
	    
	    public static File searchFile(String targetFile) {
	    	String[] arr;
			if (targetFile.contains(File.separator)) {
				arr = targetFile.split(File.separator);
			   return (searchFile(arr[0])==null)? createFolders("src",targetFile): searchFile( arr[arr.length - 1]);


			} else {
				 File file = new File("src");
				return searchFile(file, targetFile);
			}                 
	    }
	    
	    public static File searchFolder(String targetFolder) {
	    	String[] arr;
			if (targetFolder.contains(File.separator)) {
				arr = targetFolder.split(File.separator);
				return (searchFolder(arr[0])==null)?createFolders("src",targetFolder): searchFolder( arr[arr.length - 1]);


			} else {
				 File file = new File("src");
				return searchFolder(file, targetFolder);
			}                 
	    }
	 	    
	    @Test
	    public void ssss() {
	    	System.out.println(searchFolder(searchFolder("clients"),"APIServiceQA"));
	    }
	    
	    public static File searchFolder(File directory, String targetFolderName) {
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
	    
		public static void writeTextsToFile(File filePath, String expTexts) {
			try {
				FileWriter writer = new FileWriter(filePath);
				writer.append(expTexts);
				writer.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		public static File createFolder(String targetFolder, String folderName) throws IOException {
			File file = Utility.searchFolder(folderName);
			if (file == null) {
				File dir=createFolders(targetFolder, folderName);
				if (dir!=null) {
					file=dir;
					//System.out.println("New Folder \"" + folderName + "\" Created Successfully");
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
		
		  private static String firstCharToUpperrCase(String str) {

		        if (str == null || str.length() == 0)
		            return "";

		        if (str.length() == 1)
		            return str.toLowerCase();

		        return str.substring(0, 1).toLowerCase() + str.substring(1, str.length());
		    }
		  
		    public static boolean isValidCurlFormat(String curlCommand) {
		        String curlPattern = "^curl\\s+--location\\s+'http[^']+'\\\\\n"
		                           + "\\s*--request\\s+POST\\\\\n"
		                           + "\\s*--header\\s+'Content-Type:\\s+application/json'\\\\\n"
		                           + "\\s*--data\\s+'\\{[^}]+\\}'$";
		        Pattern pattern = Pattern.compile(curlPattern, Pattern.MULTILINE);
		        Matcher matcher = pattern.matcher(curlCommand);
		        return matcher.matches();
		    }
}
