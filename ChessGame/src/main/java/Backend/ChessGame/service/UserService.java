package Backend.ChessGame.service;


import Backend.ChessGame.dto.GoogleToken;
import Backend.ChessGame.models.Users;
import Backend.ChessGame.repo.UserRepo;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepo usersRepo;

    @Autowired
    private GoogleVerifierService googleTokenVerifierService;

    public Users LoginOrRegisterUser(String idTokenString){
        try {

            GoogleIdToken.Payload payload = googleTokenVerifierService.verifyToken(idTokenString);

            String email = payload.getEmail();
            
            Optional<Users> userOptional = usersRepo.findByEmail(email);

            if(userOptional.isPresent()){
                return userOptional.get();
            } else {
              Users newUser = new Users();
              newUser.setEmail(email);
              newUser.setGoogleId(payload.getSubject());

              String G_name = (String) payload.get("name");
              String username = createUsernameFromEmail(G_name);
              newUser.setUsername(username);

              return usersRepo.save(newUser);
            }

        } catch (Exception e) {
            throw new RuntimeException("Authentication failed: " + e.getMessage(), e);
        }
    }

    private String createUsernameFromEmail(String name) {
        if (name == null || name.isEmpty()) {
            // Fallback if name is not provided
            return "user-" + UUID.randomUUID().toString().substring(0, 8);
        }

        String baseUsername = name.toLowerCase().replaceAll("\\s+", ".");
        String finalUsername = baseUsername;
        int counter = 1;

        // Check if username is already taken
        while (usersRepo.findByUsername(finalUsername).isPresent()) {
            finalUsername = baseUsername + "-" + counter;
            counter++;
        }
        return finalUsername;
    }
}
