package com.ok.dvweb.repository;

import com.ok.dvweb.entity.Subscriber;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SubscriberRepository extends JpaRepository<Subscriber, Long> {
    Optional<Subscriber> findByUuid(String uuid);
}
