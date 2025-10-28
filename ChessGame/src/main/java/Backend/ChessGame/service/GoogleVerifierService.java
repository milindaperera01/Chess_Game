package Backend.ChessGame.service;


import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;

@Service
public class GoogleVerifierService {

    private final GoogleIdTokenVerifier verifier;

    @Autowired
    public GoogleVerifierService(GoogleIdTokenVerifier verifier) {
        this.verifier = verifier;
    }

    public GoogleIdToken.Payload verifyToken(String tokenString) throws
            GeneralSecurityException, IOException, IllegalArgumentException{
        GoogleIdToken idToken = verifier.verify(tokenString);

        if (idToken != null) {
            return idToken.getPayload();
        } else {
            throw new IllegalArgumentException("Invalid ID token.");
        }
    }
}
