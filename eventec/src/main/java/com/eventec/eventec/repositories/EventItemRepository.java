package com.eventec.eventec.repositories;

import com.eventec.eventec.models.EventItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventItemRepository extends JpaRepository<EventItem, Long> {
}
