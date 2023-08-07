package api.clients.APIServiceQA;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class APIServiceQAResponseDTO {
    private List<String> categories = new ArrayList<String>();
    private Boolean showAgeRestricted;
    public List<String> getCategories() {
        return categories;
    }
    public void setCategories(List<String> categories) {
        this.categories = categories;
    }
    public Boolean getShowAgeRestricted() {
        return showAgeRestricted;
    }
    public void setShowAgeRestricted(Boolean showAgeRestricted) {
        this.showAgeRestricted = showAgeRestricted;
    }
}
