package com.jeffersonlupinacci.app.authService.repository;

import com.jeffersonlupinacci.app.authService.domain.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The User Repository
 *
 * @author jeffersonlupinacci
 */
public interface UserRepository extends JpaRepository<User, Long> {

  /**
   * Find a User by Name - EAGER
   *
   * @param name the User Name
   * @return the Database User
   */
  Optional<User> getFirst1ByUsername(String name);

}
