package gsheetvalidation;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.ValueRange;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
public class GSheetCommonUtils {
	
    public ValueRange getSheetValueRange(Sheets service, String spreadsheetId, String dataRange) throws IOException {
        return service.spreadsheets().values()
                .get(spreadsheetId, dataRange)
                .execute();
    }
    public boolean isSheetDataEmpty(List<Object> sheetData){
        return Objects.isNull(sheetData) || sheetData.isEmpty();
    }
    public String getSheetNameFromIndex(Sheets sheetService,String spreadSheetId, int index) throws IOException {
        return sheetService.spreadsheets().get(spreadSheetId).setIncludeGridData(false)
                .execute().getSheets().get(index).getProperties().getTitle();
    }
    public String getFirstSheetName(Sheets sheetService,String spreadSheetId) throws IOException {
        return getSheetNameFromIndex(sheetService,spreadSheetId,0);
    }
    public String getHeaderRowRangeForSheet(String sheetName){
        return String.format("%s!A1:ZZ1", sheetName);
    }
    public String getSheetBodyRangeIncludingHeader(String sheetName){
        return String.format("%s!A1:ZZ", sheetName);
    }
    public String getDataRangeRowForSheet(String sheetName){
        return String.format("%s!A2:ZZ", sheetName);
    }
}
