package Backend.ChessGame.repo;


import Backend.ChessGame.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<Users, Long> {

    /**
     * Finds a user by their email address.
     * We use Optional in case no user is found.
     */
    Optional<Users> findByEmail(String email);

    /**
     * Finds a user by their unique Google ID.
     */
    Optional<Users> findByGoogleId(String googleId);

    Optional<Object> findByUsername(String finalUsername);
}
