package com.jeffersonlupinacci.app.authService.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;

/**
 * The User Authorities
 *
 * @author jeffersonlupinacci
 */
@Entity()
@Table(name = "USERS_AUTHORIZATION", schema = "SECURITY")
@Getter
public class UserAuthorization {

  @Id
  @Column(name = "ID")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "NAME")
  private String role;

}
