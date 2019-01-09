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
import lombok.Getter;
import lombok.Setter;

/**
 * The Person Email
 *
 * @author jeffersonlu
 */
@Entity()
@Table(name = "PERSON_EMAIL", schema = "MAIN")
@Getter
@Setter
public class PersonEmail extends AuditEntity {

  @ApiModelProperty(reference = "Id", hidden = true)
  @Id
  @Column(name = "EMAIL_ID")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ApiModelProperty(hidden = true)
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "PERSON_ID")
  private Person person;

  @ApiModelProperty(hidden = true)
  @Column(name = "PERSON_ID", insertable = false, updatable = false)
  private Long personId;

  @Column(name = "EMAIL_ADDRESS")
  private String address;

}
