package com.utils.fileReader;

import com.api.requestDTO.PostRequestBodyDTO;
import com.utils.utility.Utility;

public class JSONFileReaderClient extends JSONFileReader{
	public String getDataSetPath() {
		return FileManager.searchFile("PostRequestRequestBody.json").getAbsolutePath();
	}
	
	   public PostRequestBodyDTO getPostRequestBody(String dataKey) {
	        return getValue(dataKey, PostRequestBodyDTO.class);
	    }
	   
	   public PostRequestBodyDTO getBody() {
		   return getPostRequestBody("postRequestBody1");
	   }

}
