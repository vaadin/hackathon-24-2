package com.example.application.services;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.vaadin.flow.server.webpush.WebPush;
import com.vaadin.flow.server.webpush.WebPushMessage;

import nl.martijndwars.webpush.Subscription;
import nl.martijndwars.webpush.Subscription.Keys;

public class PushService {
    public static record KeysWrapper(String p256dh, String auth) {
    }

    public static record SubscriptionWrapper(String endpoint,
            KeysWrapper keys) {
        public SubscriptionWrapper(Subscription subscription) {
            this(subscription.endpoint, new KeysWrapper(
                    subscription.keys.p256dh, subscription.keys.auth));
        }

        public Subscription toSubscription() {
            return new Subscription(endpoint, new Keys(keys.p256dh, keys.auth));
        }
    }

    private Map<String, Set<SubscriptionWrapper>> usernameToSubscription = new ConcurrentHashMap<>();

    private WebPush webPush = new WebPush(
            "BJNEPglXfOt7sOSLhHJUe3ypcci2GvonbHWtkbalUnBcsIpnsd5AHMUPfPCg8VffDeJjBfWWM5T4mu4gOCE90Xc",
            "mBhU-b0-tZWSRtAK5a77xzfde9JhAd2uwoFxmH0Fcmo",
            "mailto:leif@vaadin.com");

    public WebPush getWebPush() {
        return webPush;
    }

    public void register(String username, Subscription subscription) {
        usernameToSubscription
                .computeIfAbsent(username,
                        ignore -> Collections
                                .newSetFromMap(new ConcurrentHashMap<>()))
                .add(new SubscriptionWrapper(subscription));
    }

    public void unregister(String username, Subscription subscription) {
        Set<SubscriptionWrapper> set = usernameToSubscription.get(username);
        if (set != null) {
            set.remove(new SubscriptionWrapper(subscription));
            // Can't easily remove from map if empty since someone else might
            // have concurrently added something to the set
        }
    }

    public void push(String username, String message) {
        Set<SubscriptionWrapper> subscription = usernameToSubscription
                .get(username);
        if (subscription == null) {
            System.out.println("Cannot push since there are no subscriptions");
            return;
        }
        WebPushMessage pushMessage = new WebPushMessage("Very important update",
                message);
        subscription.forEach(
                s -> webPush.sendNotification(s.toSubscription(), pushMessage));
    }
}
