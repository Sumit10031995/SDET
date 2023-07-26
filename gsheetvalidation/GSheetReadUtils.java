package gsheetvalidation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.services.sheets.v4.Sheets;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.*;
import java.util.stream.Collectors;
public class GSheetReadUtils {

    GSheetAuthUtils gSheetAuthUtils = new GSheetAuthUtils();
    GSheetCommonUtils gSheetCommonUtils = new GSheetCommonUtils();

    public List<Map<String, String>> mapSheetRowsToListOfMap(List<Object> headerValues, List<List<Object>> dataValueRows) {
        List<Map<String, String>> result = new ArrayList<>();

        for (List<Object> dataValueRow : dataValueRows) {
            result.add(mapSheetRowToMap(headerValues,dataValueRow));
        }
        return result;
    }
    public Map<String,String> mapSheetRowToMap(List<Object> headerCells,List<Object> dataCells){
        Map<String, String> resultRow = new HashMap<>();
        for (int i = 0; i < headerCells.size(); i++) {
            String cellData = dataCells.size()<=i?"":dataCells.get(i).toString();
            resultRow.put(headerCells.get(i).toString(), cellData);
        }
        return resultRow;
    }
    public <T> List<T> getGoogleSpreadSheetData(String spreadSheetId, Class<T> className) {
        try {
            return mapSheetRowToPOJO(Objects.requireNonNull(getDataFromSheetsAPI(spreadSheetId)), className);
        } catch (IOException | GeneralSecurityException e) {
            throw new RuntimeException(e);
        }
    }
    public <T> List<T> mapSheetRowToPOJO(List<Map<String, String>> sheetDataMap, Class<T> className) {
        ObjectMapper mapper = new ObjectMapper();
        return sheetDataMap.stream().map(e->mapper.convertValue(e,className)).collect(Collectors.toList());
    }

    public List<Map<String, String>> getDataFromSheetsAPI(String spreadsheetId)
            throws IOException, GeneralSecurityException {

        Sheets service = gSheetAuthUtils.getSheetsServiceForReading();
        String sheetName = gSheetCommonUtils.getFirstSheetName(service,spreadsheetId);
        String headerRange = gSheetCommonUtils.getHeaderRowRangeForSheet(sheetName);
        String dataRange = gSheetCommonUtils.getDataRangeRowForSheet(sheetName);

        List<Object> headerValues = gSheetCommonUtils.getSheetValueRange(service, spreadsheetId, headerRange).getValues().iterator().next();
        if (gSheetCommonUtils.isSheetDataEmpty(headerValues)) return null;

        List<List<Object>> dataValueRows = gSheetCommonUtils.getSheetValueRange(service, spreadsheetId, dataRange).getValues();
        if (gSheetCommonUtils.isSheetDataEmpty(dataValueRows.iterator().next())) return null;

        return mapSheetRowsToListOfMap(headerValues, dataValueRows);
    }

}
