package uy.montdeo.orion.database.listener;

import static org.springframework.context.i18n.LocaleContextHolder.getLocale;
import static org.springframework.util.Assert.notNull;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManagerFactory;

import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.event.spi.PostLoadEvent;
import org.hibernate.event.spi.PostLoadEventListener;
import org.hibernate.event.spi.PreInsertEvent;
import org.hibernate.event.spi.PreInsertEventListener;
import org.hibernate.event.spi.PreUpdateEvent;
import org.hibernate.event.spi.PreUpdateEventListener;
import org.hibernate.internal.SessionFactoryImpl;
import org.hibernate.jpa.HibernateEntityManagerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import uy.montdeo.orion.database.AbstractTranslatableEntity;

/**
 * Class implementing the {@link PostLoadEventListener}, {@link PreInsertEventListener} and 
 * {@link PreUpdateEventListener} interfaces to customize the data retrieved from /sent to the database.
 * 
 * @author fabian.lobo
 * @since 1.0
 * @see PostLoadEventListener
 * @see PreInsertEventListener
 * @see PreUpdateEventListener
 */
@Component
public class HibernateEventListener implements PostLoadEventListener, PreInsertEventListener, PreUpdateEventListener {

	private static final long serialVersionUID = 1783096678479913500L;
	
	private static Logger log = LoggerFactory.getLogger(HibernateEventListener.class.getName());
	
	private EntityManagerFactory entityManagerFactory;
	private MessageSource messageSource;
	
	public HibernateEventListener(EntityManagerFactory entityManagerFactory, MessageSource messageSource) {
		super();
		this.entityManagerFactory = entityManagerFactory;
		this.messageSource = messageSource;
	}

	/**
	 * Method to verify that all dependencies and requirements have been set.
	 * 
	 * @author fabian.lobo
	 * @since 1.0
	 */
	@PostConstruct
	public void init() {
		notNull(entityManagerFactory, "The property [entityManagerFactory] has not been set.");
		notNull(messageSource, "The property [messageSource] has not been set.");
		
		HibernateEntityManagerFactory hibernateEntityManagerFactory = (HibernateEntityManagerFactory) this.entityManagerFactory;
        SessionFactoryImpl sessionFactoryImpl = (SessionFactoryImpl) hibernateEntityManagerFactory.getSessionFactory();
        EventListenerRegistry registry = sessionFactoryImpl.getServiceRegistry().getService(EventListenerRegistry.class);
        registry.appendListeners(EventType.POST_LOAD, this);
        registry.appendListeners(EventType.PRE_INSERT, this);
        registry.appendListeners(EventType.PRE_UPDATE, this);
                		
		log.info("Orion - HibernateEventListener has been successfully initialized.");
	}

	/*
	 * (non-Javadoc)
	 * @see org.hibernate.event.spi.PreUpdateEventListener#onPreUpdate(org.hibernate.event.spi.PreUpdateEvent)
	 */
	@Override
	public boolean onPreUpdate(PreUpdateEvent event) {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * @see org.hibernate.event.spi.PreInsertEventListener#onPreInsert(org.hibernate.event.spi.PreInsertEvent)
	 */
	@Override
	public boolean onPreInsert(PreInsertEvent event) {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * @see org.hibernate.event.spi.PostLoadEventListener#onPostLoad(org.hibernate.event.spi.PostLoadEvent)
	 */
	@Override
	public void onPostLoad(PostLoadEvent event) {
		Object entity = event.getEntity();
		
		//Loading translation for the translatable entities
		if (entity instanceof AbstractTranslatableEntity) {
			AbstractTranslatableEntity source = (AbstractTranslatableEntity) entity;
			String translation = source.getTranslatable() ? 
				messageSource.getMessage(source.getKey(), null, source.getKey(), getLocale()).toUpperCase(getLocale()) : 
				source.getKey();
			
			source.setTranslation(translation);
		}

	}

}
