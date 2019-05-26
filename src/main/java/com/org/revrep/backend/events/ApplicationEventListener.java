package com.org.revrep.backend.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.org.revrep.backend.events.entity.NewReviewAdded;
import com.org.revrep.backend.jms.producer.KEventProducer;

/**
 * @author ANVIP
 *
 */
@Component
public class ApplicationEventListener {
	
	@Autowired
	private KEventProducer eventProducer;
	
	@EventListener
	public void broadCastForNewReview(NewReviewAdded reviewInfo) {
		eventProducer.send(NewReviewAdded.EVENT_NAME, reviewInfo); 
	}
}
