package com.Lubee.Lubee.anniversary.repository;

import com.Lubee.Lubee.anniversary.domain.Anniversary;
import com.Lubee.Lubee.couple.domain.Couple;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnniversaryRepository extends JpaRepository<Anniversary, Long> {
    List<Anniversary> findAnniversariesByCouple(Couple couple);
}
