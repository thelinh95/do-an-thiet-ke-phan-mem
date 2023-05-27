package com.baouyen.doan.repository;

import com.baouyen.doan.entity.GamePlay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GamePlayRepository extends JpaRepository<GamePlay, Integer>{

}
