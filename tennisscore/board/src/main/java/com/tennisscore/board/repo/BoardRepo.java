package com.tennisscore.board.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.tennisscore.board.model.Board;

@Repository
public interface BoardRepo extends JpaRepository<Board, Long>{    
}
