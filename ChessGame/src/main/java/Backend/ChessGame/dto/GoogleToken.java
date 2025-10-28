package Backend.ChessGame.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoogleToken {
    @NotBlank(message = "Token is mandatory")
    private String token;
}
