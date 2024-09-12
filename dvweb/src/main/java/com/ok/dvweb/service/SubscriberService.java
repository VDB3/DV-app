package com.ok.dvweb.service;

import com.ok.dvweb.dto.SubscriberDTO;
import com.ok.dvweb.entity.Subscriber;
import com.ok.dvweb.repository.SubscriberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SubscriberService {

    protected final SubscriberRepository repository;

    public void addSubscriber(SubscriberDTO dto){
        final Subscriber entity = new Subscriber();
        entity.setMail(dto.getMail());
        entity.setCreated(dto.getCreated());
        entity.setNotify(dto.getNotify());
        entity.setVerify(false);
        repository.save(entity);
        log.info("Subscribe success");
    }
}
