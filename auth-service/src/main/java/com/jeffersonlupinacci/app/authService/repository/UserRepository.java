package com.jeffersonlupinacci.app.authService.repository;

import com.jeffersonlupinacci.app.authService.domain.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * The User Repository
 *
 * @author jeffersonlupinacci
 */
public interface UserRepository extends JpaRepository<User, Long> {

  /**
   * Find a User by Name
   *
   * @param username the User Name
   * @return the Database User
   */
  @Query("select u.id from security.users u where u.name = :username")
  Optional<User> findOneByUsername(String username);

}
