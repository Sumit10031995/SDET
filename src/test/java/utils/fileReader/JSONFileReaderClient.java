package utils.fileReader;

import api.requestDTO.PostRequestBodyDTO;

public class JSONFileReaderClient extends JSONFileReader{
	public String getDataSetPath() {
		return "./src/JSON/PostRequestRequestBody.json";
	}
	
	   public PostRequestBodyDTO getPostRequestBody(String dataKey) {
	        return getValue(dataKey, PostRequestBodyDTO.class);
	    }
	   
	   public PostRequestBodyDTO getBody() {
		   return getPostRequestBody("postRequestBody1");
	   }

}
