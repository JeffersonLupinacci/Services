package com.jeffersonlupinacci.app.authService.domain;

import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

/**
 * The User Entity
 *
 * @author jeffersonlupinacci
 */
@Table(name = "USERS", schema = "SECURITY")
@Entity
@Getter
@Setter
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "ID", nullable = false)
  private Long userId;

  @Column(name = "NAME", nullable = false)
  private String username;

  @Column(name = "PASSWORD", nullable = false)
  private String password;

  @Column(name = "ENABLED", nullable = false)
  private Boolean enabled;

  @Column(name = "ACCOUNT_NON_EXPIRED")
  private Boolean accountNonExpired;

  @Column(name = "CREDENTIALS_NON_EXPIRED")
  private Boolean credentialsNonExpired;

  @Column(name = "ACCOUNT_NON_LOCKED")
  private Boolean accountNonLocked;

  @OneToMany(fetch = FetchType.EAGER, orphanRemoval = true)
  @JoinColumn(name = "USER_ID")
  @Cascade({org.hibernate.annotations.CascadeType.ALL})
  private Collection<UserAuthorization> authorities;

}
