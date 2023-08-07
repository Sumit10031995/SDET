package api.clients.APIServiceQA;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class APIServiceQARequestDTO {
    private String dzid;
    private Boolean showAgeRestricted;
    private Boolean filterOos;
    private List<String> productGroupingIds = new ArrayList<String>();
    public String getDzid() {
        return dzid;
    }
    public void setDzid(String dzid) {
        this.dzid = dzid;
    }
    public Boolean getShowAgeRestricted() {
        return showAgeRestricted;
    }
    public void setShowAgeRestricted(Boolean showAgeRestricted) {
        this.showAgeRestricted = showAgeRestricted;
    }
    public Boolean getFilterOos() {
        return filterOos;
    }
    public void setFilterOos(Boolean filterOos) {
        this.filterOos = filterOos;
    }
    public List<String> getProductGroupingIds() {
        return productGroupingIds;
    }
    public void setProductGroupingIds(List<String> productGroupingIds) {
        this.productGroupingIds = productGroupingIds;
    }
}
