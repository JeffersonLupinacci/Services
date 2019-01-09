package com.jeffersonlupinacci.app.data.domain.person;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.jeffersonlupinacci.app.data.domain.AuditEntity;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Cascade;

/**
 * The Person
 *
 * @author jeffersonlupinacci
 */
@Entity()
@Table(name = "PERSON", schema = "MAIN")
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@JsonRootName(value = "Person")
public class Person extends AuditEntity {

  @JsonInclude(Include.NON_NULL)
  @JsonProperty("id")
  @ApiModelProperty(reference = "Id", hidden = true)
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  private Long id;

  @JsonInclude(Include.NON_NULL)
  @JsonProperty("name")
  @ApiModelProperty(reference = "Name", notes = "Person Name")
  @Column(name = "NAME", length = 300)
  private String name;

  @JsonInclude(Include.NON_NULL)
  @JsonProperty("company")
  @ApiModelProperty(reference = "Company")
  @Column(name = "COMPANY", length = 100)
  private String company;

  @JsonInclude(Include.NON_NULL)
  @JsonProperty("title")
  @ApiModelProperty(reference = "Mr.")
  @Column(name = "TITLE", length = 10)
  private String title;

  @JsonInclude(Include.NON_NULL)
  @JsonProperty("department")
  @ApiModelProperty(reference = "Tecnology")
  @Column(name = "DEPARTMENT", length = 60)
  private String department;

  @JsonInclude(Include.NON_NULL)
  @JsonProperty("phones")
  @ApiModelProperty(reference = "Phones")
  @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true)
  @JoinColumn(name = "PERSON_ID")
  @Cascade({org.hibernate.annotations.CascadeType.ALL})
  private List<PersonPhone> phones;

  @JsonInclude(Include.NON_NULL)
  @JsonProperty("emails")
  @ApiModelProperty(reference = "Emails")
  @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true)
  @JoinColumn(name = "PERSON_ID")
  @Cascade({org.hibernate.annotations.CascadeType.ALL})
  private List<PersonEmail> emails;

  @JsonInclude(Include.NON_NULL)
  @JsonProperty("addresses")
  @ApiModelProperty(reference = "Addresses")
  @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true)
  @JoinColumn(name = "PERSON_ID")
  @Cascade({org.hibernate.annotations.CascadeType.ALL})
  private List<PersonAddress> addresses;

}
