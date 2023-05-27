package com.baouyen.doan.repository;

import com.baouyen.doan.entity.Game;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, Integer>{
    Page<Game> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
