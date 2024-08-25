package com.microservice.subscription.repository;

import com.microservice.subscription.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SubscriptionRepository extends CrudRepository<Subscription, Long> {
    List<Subscription> findByUserId(String userId);
}
