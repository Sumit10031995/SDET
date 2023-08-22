package com.utils.gsheetvalidation;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;

import java.io.*;
import java.security.GeneralSecurityException;
import java.util.*;
public class GSheetAuthUtils {

    /**
     * Global instance of the scopes required by this quickstart.
     * If modifying these scopes, delete your previously saved tokens/ folder.
     */
    private final List<String> READ_SCOPE =
            Collections.singletonList(SheetsScopes.SPREADSHEETS);
    private final String CREDENTIALS_FILE_PATH = "/CatalogCredentials.json";
    public Sheets getSheetsService(List<String> scope) throws GeneralSecurityException, IOException {
        String APPLICATION_NAME = "Catalog Sheet Reading";
        JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
        NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        InputStream in = GSheetAuthUtils.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
        if (in == null) {
            throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
        }
        GoogleCredentials googleCredentials = GoogleCredentials.fromStream(in).createScoped(scope);
        return new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, new HttpCredentialsAdapter(googleCredentials))
                .setApplicationName(APPLICATION_NAME)
                .build();
    }
    public Sheets getSheetsServiceForReading() throws GeneralSecurityException, IOException {
        return getSheetsService(Collections.singletonList(SheetsScopes.SPREADSHEETS_READONLY));
    }
    public Sheets getSheetsServiceForUpdates() throws GeneralSecurityException, IOException {
        return getSheetsService(Collections.singletonList(SheetsScopes.SPREADSHEETS));
    }
}
