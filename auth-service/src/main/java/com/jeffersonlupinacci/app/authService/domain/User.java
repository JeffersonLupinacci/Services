package com.jeffersonlupinacci.app.authService.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.Getter;

/**
 * The User Entity
 *
 * @author jeffersonlupinacci
 */
@Table(name = "users", schema = "security")
@Entity
@Getter
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long userId;

  @Column(name = "name")
  private String username;

  @Transient//@Column(name = "PASSWORD")
  private String password;

  @Transient//@Column(name = "ENABLED")
  private boolean enabled;

  @Transient//@Column(name = "ACCOUNT_NON_EXPIRED")
  private boolean accountNonExpired;

  @Transient//@Column(name = "CREDENTIALS_NON_EXPIRED")
  private boolean credentialsNonExpired;

  @Transient//@Column(name = "ACCOUNT_NON_LOCKED")
  private boolean accountNonLocked;

  /*@ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "users", joinColumns = @JoinColumn(name = "userId"),
      inverseJoinColumns = @JoinColumn(name = "authId"))
  Collection<? extends UserAuthority> authorities;*/

}
