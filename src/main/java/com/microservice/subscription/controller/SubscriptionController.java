package com.microservice.subscription.controller;

import com.microservice.subscription.entity.Subscription;
import com.microservice.subscription.service.ISubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/subscriptions")
public class SubscriptionController {

    @Autowired
    private ISubscriptionService subscriptionService;


    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public void createSubscription(@RequestBody Subscription subscription) {
        subscriptionService.createSubscription(subscription);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Subscription>> getSubscriptionsByUserId(@PathVariable String userId) {
        List<Subscription> subscriptions = subscriptionService.getSubscriptionsByUserId(userId);
        return ResponseEntity.ok(subscriptions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Subscription> findSubscriptionById(@PathVariable Long id) {
        try {
            Subscription subscription = subscriptionService.findById(id);
            return ResponseEntity.ok(subscription);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Subscription> updateSubscription(@PathVariable Long id, @RequestBody Subscription subscriptionDetails) {
        try {
            Subscription existingSubscription = subscriptionService.findById(id);
            if (existingSubscription != null) {
                existingSubscription.setPlan(subscriptionDetails.getPlan());
                existingSubscription.setEndDate(subscriptionDetails.getEndDate());
                existingSubscription.setActive(subscriptionDetails.isActive());
                subscriptionService.updateSubscription(id, existingSubscription);
                return ResponseEntity.ok(existingSubscription);
            }
            return ResponseEntity.notFound().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> cancelSubscription(@PathVariable Long id) {
        try {
            Subscription existingSubscription = subscriptionService.findById(id);
            if (existingSubscription != null) {
                subscriptionService.cancelSubscription(id);
                return ResponseEntity.ok().build();
            }
            return ResponseEntity.notFound().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

