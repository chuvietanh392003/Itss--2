package main.java.service.implement;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.GenericUrl;

import main.java.service.GoogleOAuthService;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;

public class GoogleOAuthServiceImp implements GoogleOAuthService {

    private static final String CLIENT_ID = "511934853040-iarp2hdgam5rd9sarc50arq7rj3ona0c.apps.googleusercontent.com";
    private static final String CLIENT_SECRET = "your_";
    private static final String REDIRECT_URI = "https://www.example.com/";
    private static final String USER_INFO_URL = "https://www.googleapis.com/oauth2/v1/userinfo?alt=json";

    private GoogleAuthorizationCodeFlow flow;
    
    public GoogleOAuthServiceImp() throws GeneralSecurityException, IOException {
        JsonFactory jsonFactory = GsonFactory.getDefaultInstance();
        HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();

        flow = new GoogleAuthorizationCodeFlow.Builder(
                httpTransport,
                jsonFactory,
                CLIENT_ID,
                CLIENT_SECRET,
                Arrays.asList("https://www.googleapis.com/auth/userinfo.email", "https://www.googleapis.com/auth/userinfo.profile"))
                .setAccessType("offline")
                .build();
    }

    @Override
    public String getOAuthUrl() {
        return flow.newAuthorizationUrl().setRedirectUri(REDIRECT_URI).build();
    }

    @Override
    public Credential getCredentialsFromCode(String authorizationCode) throws IOException {
        GoogleAuthorizationCodeTokenRequest tokenRequest = flow.newTokenRequest(authorizationCode).setRedirectUri(REDIRECT_URI);
        var tokenResponse = tokenRequest.execute();
        return flow.createAndStoreCredential(tokenResponse, null);
    }

    @Override
    public String getUserInfo(Credential credential) throws IOException {
        HttpRequestFactory requestFactory = flow.getTransport().createRequestFactory(new HttpRequestInitializer() {
            @Override
            public void initialize(HttpRequest request) throws IOException {
                credential.initialize(request);
            }
        });

        HttpRequest request = requestFactory.buildGetRequest(new GenericUrl(USER_INFO_URL));
        return request.execute().parseAsString();
    }
}