package com.microservice.subscription.service;

import com.microservice.subscription.entity.Subscription;
import com.microservice.subscription.repository.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor()
public class SubscriptionService implements ISubscriptionService {

    @Autowired
    private SubscriptionRepository subscriptionRepository;


    @Override
    public Subscription createSubscription(Subscription subscription) {
        subscription.setStartDate(LocalDateTime.now());
        subscription.setEndDate(subscription.getStartDate().plusMonths(1));
        subscription.setActive(true);
        return subscriptionRepository.save(subscription);
    }

    @Override
    public List<Subscription> getSubscriptionsByUserId(String userId) {
        return subscriptionRepository.findByUserId(userId);
    }

    @Override
    public Subscription updateSubscription(Long id, Subscription subscriptionDetails) {
        Subscription subscription = subscriptionRepository.findById(id).orElseThrow(null);
        subscription.setPlan(subscriptionDetails.getPlan());
        subscription.setEndDate(subscriptionDetails.getEndDate());
        subscription.setActive(subscriptionDetails.isActive());
        return subscriptionRepository.save(subscription);
    }


    @Override
    public void cancelSubscription(Long id) {
        Subscription subscription = subscriptionRepository.findById(id).orElseThrow(null);
        subscription.setActive(false);
        subscriptionRepository.save(subscription);
    }

    @Override
    public Subscription findById(Long id) {
        return subscriptionRepository.findById(id).orElseThrow(null);
    }
}

