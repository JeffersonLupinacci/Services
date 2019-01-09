package com.jeffersonlupinacci.app.data.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * The Audit Entity
 *
 * @author jeffersonlupinacci
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
public class AuditEntity implements Serializable {

  @JsonInclude(Include.NON_NULL)
  @ApiModelProperty(hidden = true)
  @Getter
  @Version
  private Integer version;

  @JsonInclude(Include.NON_NULL)
  @ApiModelProperty(hidden = true)
  @Getter
  @Basic(optional = false)
  @Column(name = "CREATION_DATE")
  @Temporal(TemporalType.TIMESTAMP)
  private Date creationDate;

  @JsonInclude(Include.NON_NULL)
  @ApiModelProperty(hidden = true)
  @Getter
  @Column(name = "UPDATE_DATE")
  @Temporal(TemporalType.TIMESTAMP)
  private Date updateDate;

  @JsonIgnore
  @ApiModelProperty(hidden = true)
  @Getter
  @Basic(optional = false)
  @Column(name = "CREATION_USER")
  private String creationUser;

  @JsonIgnore
  @ApiModelProperty(hidden = true)
  @Getter
  @Basic(optional = false)
  @Column(name = "UPDATE_USER")
  private String updateUser;

  /**
   * Insert Audit
   */
  public void auditInsert() {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    this.creationUser = auth.getName();
    this.creationDate = new Date();
  }

  /**
   * Update Audit
   */
  public void auditUpdate() {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    this.updateUser = auth.getName();
    this.updateDate = new Date();
  }
}
