package com.tennis.board.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.tennis.board.model.Board;

@Repository
public interface BoardRepo extends JpaRepository<Board,Long>{
}
