package com.example.compieCsv.repository;

import com.example.compieCsv.entity.Players;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayersRepository extends JpaRepository<Players, Integer> {
}
