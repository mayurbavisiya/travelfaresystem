package com.ae.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ae.entity.Card;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

}
