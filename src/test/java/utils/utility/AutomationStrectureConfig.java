package utils.utility;

import java.io.File;

public class AutomationStrectureConfig {
	static String serviceName;
	static String testFileName;
	static String requestFileName;
	static String responseFileName;
	static String targetFolder;
	static String folderToCreateRequestDTO;
	static String folderToCreateResponseDTO;
	static String apiTtests = "tests";

	AutomationStrectureConfig(String serviceName) {
		this.serviceName = serviceName;
		testFileName = serviceName + "Tests.java";
		this.requestFileName = serviceName + "RequestDTO.java";
		this.responseFileName = serviceName + "ResponseDTO.java";
		this.targetFolder = "src" + File.separator + "test" + File.separator + "java" + File.separator + "api";
		this.folderToCreateRequestDTO = "clients" + File.separator + serviceName;
		this.folderToCreateResponseDTO = "clients" + File.separator + serviceName;
		this.apiTtests = "tests";
	}
}
