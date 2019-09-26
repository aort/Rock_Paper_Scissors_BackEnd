package com.lottoland.rps.api.dao;

import com.lottoland.rps.api.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameDAO extends JpaRepository<Game, Long> {

}
