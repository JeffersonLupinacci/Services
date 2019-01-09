package com.jeffersonlupinacci.app.authService.service;

import com.jeffersonlupinacci.app.authService.domain.User;
import com.jeffersonlupinacci.app.authService.repository.UserRepository;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * The User Details Service
 *
 * @author jeffersonlupinacci
 */
@Service
public class AppUserDetailsService implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<User> userOptional = userRepository.findOneByUsername(username);
    User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("Invalid User"));

    return new org.springframework.security.core.userdetails.User(username, user.getPassword(), getGrants(user));
  }

  private Collection<? extends GrantedAuthority> getGrants(User user) {
    Set<SimpleGrantedAuthority> authorities = new HashSet<>();
    /*user.getAuthorities()
        .forEach(p -> authorities.add(new SimpleGrantedAuthority(p.getAuthority().toUpperCase())));*/
    return authorities;
  }
}
