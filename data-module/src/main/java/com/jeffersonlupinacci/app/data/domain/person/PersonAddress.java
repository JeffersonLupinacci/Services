package com.jeffersonlupinacci.app.data.domain.person;

import com.jeffersonlupinacci.app.data.domain.AuditEntity;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The Person Address
 *
 * @author jeffersonlupinacci
 */
@Entity()
@Table(name = "PERSONS_ADDRESS", schema = "MAIN")
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PersonAddress extends AuditEntity {

  @ApiModelProperty(reference = "Id", hidden = true)
  @Id
  @Column(name = "ID")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @ApiModelProperty(hidden = true)
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "PERSON_ID")
  private Person person;

  @ApiModelProperty(hidden = true)
  @Column(name = "PERSON_ID", insertable = false, updatable = false)
  private Long personId;

  @Column(name = "STREET", length = 200, nullable = false)
  private String street;

  @Column(name = "CITY", length = 60)
  private String city;

  @Column(name = "STATE", length = 60)
  private String state;

  @Column(name = "ZIP", length = 15)
  private String zip;

  @Column(name = "COUNTRY", length = 60)
  private String country;

}
