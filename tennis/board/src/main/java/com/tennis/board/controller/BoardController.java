package com.tennis.board.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tennis.board.model.Board;
import com.tennis.board.repo.BoardRepo;

@RestController
public class BoardController {
    
    @Autowired
    private BoardRepo boardRepo;

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<Board>> getBoards(){
        List<Board> boarList = new ArrayList<>();
        boardRepo.findAll().forEach(boarList::add);   
        return (boarList.isEmpty())
            ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
            : new ResponseEntity<>(boarList.stream().sorted(Comparator.comparing(Board::getId).reversed()).collect(Collectors.toList()), HttpStatus.OK);
    }

    @RequestMapping(value = "/player1", method = RequestMethod.POST)
    @ResponseBody
    public Board startMatch(){
        Board board = new Board();
        board.setPlayer1(null);
        board.setPlayer2(null);
        board.setTieBreakerSet(true);
        Board boardres = boardRepo.save(board);
        return boardres;
    }
}
