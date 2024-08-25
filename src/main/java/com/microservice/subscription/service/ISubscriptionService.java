package com.microservice.subscription.service;

import com.microservice.subscription.entity.Subscription;

import java.util.List;

public interface ISubscriptionService {
    Subscription createSubscription(Subscription subscription);
    List<Subscription> getSubscriptionsByUserId(String userId);
    Subscription updateSubscription(Long id, Subscription subscriptionDetails);
    void cancelSubscription(Long id);
    Subscription findById(Long id);
}
