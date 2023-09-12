package com.eventec.eventec.services;

import com.eventec.eventec.models.EventItem;
import com.eventec.eventec.repositories.EventItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EventItemService {
    @Autowired
    private EventItemRepository eventItemRepository;

    public Iterable<EventItem> getAll() {
        return eventItemRepository.findAll();
    }

    public Optional<EventItem> getById(Long id) {
        return eventItemRepository.findById(id);
    }

    public EventItem save(EventItem eventItem) {

        return eventItemRepository.save(eventItem);
    }

    public void delete(EventItem eventItem) {
        eventItemRepository.delete(eventItem);
    }
}
