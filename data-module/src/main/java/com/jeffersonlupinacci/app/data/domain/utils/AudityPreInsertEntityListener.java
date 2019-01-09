package com.jeffersonlupinacci.app.data.domain.utils;

import com.jeffersonlupinacci.app.data.domain.AuditEntity;
import org.hibernate.event.spi.PreInsertEvent;
import org.hibernate.event.spi.PreInsertEventListener;

/**
 * The Audity Pre Insert Entity Listener
 *
 * @author jeffersonlupinacci
 */
public class AudityPreInsertEntityListener implements PreInsertEventListener {

  @Override
  public boolean onPreInsert(PreInsertEvent event) {
    AuditEntity auditEntity = (AuditEntity) event.getEntity();
    auditEntity.auditInsert();
    return false;
  }

}
