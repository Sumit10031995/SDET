package api.request;

import java.util.List;
import java.util.Map;

public class BaseAPI {
	  public static boolean isNotNullAndNotEmpty(String str) {
	        return str != null && !str.isEmpty();
	    }
	  public static <T> boolean isNotNullAndNotEmpty(List<T> list) {
	        return list != null && list.size()!=0;
	    }
	  public static <T> boolean isNotNullAndNotEmpty(Map<T,T> map) {
	        return map != null && map.size()!=0;
	    }
}
