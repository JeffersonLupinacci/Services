package com.jeffersonlupinacci.app.data.domain.utils;

import javax.annotation.PostConstruct;
import org.hibernate.SessionFactory;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * The Entity EVent Listener
 *
 * @author jeffersonlupinacci
 */
@Component
public class EntityEventListener {

  @Autowired
  private SessionFactory sessionFactory;

  @PostConstruct
  public void registerListeners() {
    EventListenerRegistry eventListenerRegistry = ((SessionFactoryImplementor) sessionFactory).getServiceRegistry().getService(EventListenerRegistry.class);
    eventListenerRegistry.prependListeners(EventType.PRE_INSERT, AudityPreInsertEntityListener.class);
    eventListenerRegistry.prependListeners(EventType.PRE_UPDATE, AudityPreUpdateEntityListener.class);
  }

}
