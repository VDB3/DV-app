package com.ok.dvweb.service;

import com.ok.dvweb.entity.Subscriber;
import com.ok.dvweb.repository.SubscriberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsService {

    protected final SubscriberRepository repository;
    protected final MessagingService messagingService;

    public void notifySubs(String imgBase64){
        List<Subscriber> subscriberList = repository.findAll();
        List<String> listMail = subscriberList.stream()
                .filter(Subscriber::isVerify)
                .map(Subscriber::getMail)
                .toList();
        messagingService.sendNotifySubsVerified(listMail, imgBase64);
    }

}
