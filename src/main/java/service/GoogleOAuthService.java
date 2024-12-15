package main.java.service;

import java.io.IOException;

import com.google.api.client.auth.oauth2.Credential;

public interface GoogleOAuthService {
    String getOAuthUrl() throws Exception; 
    Credential getCredentialsFromCode(String authorizationCode) throws Exception;
	String getUserInfo(Credential credential) throws IOException; 
}
