package utils.utility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class AutomationStrectureCreation {
	 private static String apiClientsFolderPath = "." + File.separator + "src" + File.separator + "main" + File.separator + "java" + File.separator + "com" + File.separator + "dunzo" + File.separator + "api" + File.separator + "clients";
	    private static final String catalogTestFilsFolderPath = "." + File.separator + "src" + File.separator + "main" + File.separator + "java" + File.separator + "com" + File.separator + "dunzo" + File.separator + "api" + File.separator + "tests" + File.separator + "catalog";
	    private static String newApiClientFolderName;
	    private static String serviceName;
	    private static Logger logger = LogManager.getLogger(AutomationStrectureCreation.class);

	    public static ArrayList<String> getFolderNames(String folderPath) {
	        ArrayList<String> expData = new ArrayList<>();
	        try {
	            File directoryPath = new File(folderPath);
	            String[] folderNames = directoryPath.list();
	            for (String folderName : folderNames) {
	                expData.add(folderName.toLowerCase().toLowerCase());
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return expData;
	    }

	    public static boolean isFolderExist(String folderPath, String newFolderName) {
	        boolean flag = false;
	        try {
	            ArrayList<String> folderNames = getFolderNames(folderPath);
	            if (folderNames.contains(newFolderName.toLowerCase()))
	                flag = true;
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return flag;
	    }

	    public static void writeTextsToFile(String filePath, String expTexts) {
	        try {
	            FileWriter writer = new FileWriter(filePath);
	            writer.append(expTexts);
	            writer.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }


	    public static void createAutomationStructureCreation() {
	        List<String> fileNames = Arrays.asList("Client", "Request", "Response", "RequestBuilder", "Service", "Tests");
	        Scanner scannerObject = new Scanner(System.in);
	        String expText = null;
	        String javaClass;
	        logger.info("==============Add Folder Name==============");
	        newApiClientFolderName = scannerObject.next();
	        logger.info("==============Add Service Name=============");
	        serviceName = scannerObject.next();
	        try {
	            boolean isExist = isFolderExist(apiClientsFolderPath, newApiClientFolderName);
	            if (!isExist) {
	                apiClientsFolderPath += "" + File.separator + "" + newApiClientFolderName;
	                File folder = new File(apiClientsFolderPath);
	                if (folder.mkdir()) {
	                    logger.info("Folder with name " + newApiClientFolderName + " successfully created");
	                    for (String file : fileNames) {
	                        String className = serviceName + file;
	                        if (!file.equals("Tests"))
	                            javaClass = apiClientsFolderPath + "" + File.separator + "" + serviceName + file + ".java";
	                        else
	                            javaClass = catalogTestFilsFolderPath + "" + File.separator + "" + serviceName + file + ".java";

	                        File fil = new File(javaClass);
	                        if (fil.createNewFile()) {
	                            if (javaClass.endsWith("Tests.java")) {
	                                String clientClassName = className.replace("Tests", "Client");
	                                expText = "package com.dunzo.api.tests.catalog;\n" + "import java.util.NoSuchElementException;\n" +"import com.dunzo.api.clients."+newApiClientFolderName+"."+clientClassName+";\n"+"import com.testvagrant.ekam.commons.LayoutInitiator;\n"+ "import net.jodah.failsafe.RetryPolicy;\n"
	                                        + "import java.time.Duration;\n" + "import com.testvagrant.ekam.testBases.testng.APITest;\n\n"
	                                        + "public class " + className + " extends APITest {\n  RetryPolicy<Object> retryPolicy = new RetryPolicy<>()\n" +
	                                        "            .handle(NullPointerException.class)\n" +
	                                        "            .handle(NoSuchElementException.class)\n" +
	                                        "            .handle(AssertionError.class)\n" +
	                                        "            .withDelay(Duration.ofSeconds(30))\n" +
	                                        "            .withMaxRetries(16);" + "\n "+
	                                        ""+clientClassName+" "+firstCharToLowerCase(clientClassName)+" = LayoutInitiator.Client("+clientClassName+".class);\n}";
	                                writeTextsToFile(javaClass, expText);
	                            } else if (javaClass.endsWith("Client.java")) {
	                                String requestService = className.replace("Client", "Request");
	                                String requestBuilder = className.replace("Client", "RequestBuilder");
	                                expText = expText = "package com.dunzo.api.clients." + newApiClientFolderName +";\n import com.dunzo.api.clients.DunzoRetrofitBaseClient;\n"+"import com.google.inject.Inject;\n" +
	                                        "import com.google.inject.name.Named;\n" +"import com.dunzo.api.clients."+newApiClientFolderName+"."+requestService+";\n" +
	                                        "import com.dunzo.api.clients."+newApiClientFolderName+"."+requestBuilder+";"+
	                                        "import io.qameta.allure.Step;"+ ";\n" + "public class " + className + " extends DunzoRetrofitBaseClient\n" +
	                                        "{\n private final " + requestService + " service;\n" +
	                                        "    private final " + requestBuilder + " " + firstCharToLowerCase(requestBuilder) + ";\n" +
	                                        "\n" +
	                                        "\n" +
	                                        "    @Inject\n" +
	                                        "    public "+className+"(@Named(\"<baseURL>\") String baseUrl) {\n" +
	                                        "        super(baseUrl);\n" +
	                                        "        service = httpClient.getService(" + requestService + ".class);\n" +
	                                        "        " + firstCharToLowerCase(requestBuilder) + " = new " + requestBuilder + "();\n" +
	                                        "    }}";
	                                writeTextsToFile(javaClass, expText);
	                            } else if (javaClass.endsWith("Service.java")) {
	                                String requestClassName = className.replace("Service", "Request");
	                                expText = "package com.dunzo.api.clients." + newApiClientFolderName + ";\n" + "import retrofit2.http.*;\n" + "import retrofit2.Call;\n" + "public interface " + className + "{" + "\n" + "@POST()\n" +
	                                        "    @Headers({\n" +
	                                        "            \"Content-Type: application/json\",\n" +
	                                        "    })\n" +
	                                        "    Call<" + className.replace("Service", "Response") + "> <methodName>(\n" +
	                                        "            @Path(\"\") String taskRefId,\n" +
	                                        "            @Header(\"\") String userId,\n" +
	                                        "            @Body " + requestClassName + " " + firstCharToLowerCase(requestClassName) + ";" + "\n" +
	                                        "    );" + "}";
	                                writeTextsToFile(javaClass, expText);
	                            } else {
	                                expText = "package com.dunzo.api.clients." + newApiClientFolderName + ";\n" +"import lombok.Builder;\n" +
	                                        "import lombok.Data;\n" +
	                                        "import lombok.Getter;\n"+"@Getter\n" +
	                                        "@Data\n" +
	                                        "@Builder(toBuilder = true)\n" +
	                                        "public class " + className + "{}";
	                                writeTextsToFile(javaClass, expText);
	                            }

	                            logger.info("Java Class " + javaClass + " Successfully Created");
	                        }
	                    }
	                }
	            } else
	                logger.info("Folder With Name " + newApiClientFolderName + " Already Exist");

	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	    }

	    private static String firstCharToLowerCase(String str) {

	        if (str == null || str.length() == 0)
	            return "";

	        if (str.length() == 1)
	            return str.toLowerCase();

	        return str.substring(0, 1).toLowerCase() + str.substring(1, str.length());
	    }

}
