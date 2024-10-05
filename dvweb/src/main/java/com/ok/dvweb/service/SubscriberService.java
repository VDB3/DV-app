package com.ok.dvweb.service;

import com.ok.dvweb.dto.SubscriberDTO;
import com.ok.dvweb.entity.Subscriber;
import com.ok.dvweb.repository.SubscriberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class SubscriberService {

    protected final SubscriberRepository repository;

    public String addSubscriber(SubscriberDTO dto){
        final Subscriber entity = new Subscriber();
        entity.setMail(dto.getMail());
        entity.setCreated(dto.getCreated());
        entity.setNotify(dto.getNotify());
        entity.setVerify(false);
        entity.setUuid(UUID.randomUUID().toString());
        repository.save(entity);
        log.info("Subscribe success");
        return entity.getUuid();
    }

    public boolean confirmEmail(String token) {
        Optional<Subscriber> userOptional = repository.findByUuid(token);
        if (userOptional.isPresent()) {
            Subscriber user = userOptional.get();
            user.setVerify(true);
            repository.save(user);
            return true;
        }
        return false;
    }
}
