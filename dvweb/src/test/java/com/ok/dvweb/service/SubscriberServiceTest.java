package com.ok.dvweb.service;

import com.ok.dvweb.dto.SubscriberDTO;
import com.ok.dvweb.entity.Subscriber;
import com.ok.dvweb.repository.SubscriberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;

public class SubscriberServiceTest {

    @Mock
    private SubscriberRepository repository;

    @InjectMocks
    private SubscriberService service;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this); // Inicializa los mocks
    }

    @Test
    public void testAddSubscriber() {
        SubscriberDTO dto = new SubscriberDTO("test@example.com");
        dto.setCreated(LocalDateTime.now());
        dto.setNotify(0);
        Mockito.when(repository.save(any(Subscriber.class))).thenReturn(new Subscriber());
        service.addSubscriber(dto);
        Mockito.verify(repository, Mockito.times(1)).save(any(Subscriber.class));
    }
}
