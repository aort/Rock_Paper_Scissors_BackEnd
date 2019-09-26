package com.lottoland.rps.api.dao;

import com.lottoland.rps.api.model.Round;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoundDAO extends JpaRepository<Round, Long> {

}
