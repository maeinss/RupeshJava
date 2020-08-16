package com.tts.subscriberList.repositories;

import com.tts.subscriberList.subscriber.Subscriber;
import org.springframework.data.repository.CrudRepository;

public interface SubscriberRepository extends CrudRepository<Subscriber,Long> {
}
