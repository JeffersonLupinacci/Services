package com.jeffersonlupinacci.app.data.domain.utils;

import com.jeffersonlupinacci.app.data.domain.AuditEntity;
import org.hibernate.event.spi.PreUpdateEvent;
import org.hibernate.event.spi.PreUpdateEventListener;

/**
 * The Audity Pre Update Entity Listener
 *
 * @author jeffersonlupinacci
 */
public class AudityPreUpdateEntityListener implements PreUpdateEventListener {

  @Override
  public boolean onPreUpdate(PreUpdateEvent event) {
    AuditEntity auditEntity = (AuditEntity) event.getEntity();
    auditEntity.auditUpdate();
    ;
    return false;
  }
}
