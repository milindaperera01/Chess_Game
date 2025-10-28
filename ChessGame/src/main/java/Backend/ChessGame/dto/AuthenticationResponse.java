package Backend.ChessGame.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
    /**
     * This is our own app-specific JWT  that
     * we generate and send back to Flutter.
     */
    private String appToken;

    /**
     * We also send back some basic user info
     * so the app knows who just logged in.
     */
    private Long userId;
    private String username;
    private String email;
}
