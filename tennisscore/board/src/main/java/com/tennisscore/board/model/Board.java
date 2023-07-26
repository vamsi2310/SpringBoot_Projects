package com.tennisscore.board.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "board")
@Getter
@Setter
public class Board {
    @Id
    @GeneratedValue
    private Long id;
    // points 0,15,30,40,A
    // match int array [2,1] 
    // private String team1;
    // private String team2;
    private String team1Score;
    private String team2Score;
    private String winner;
    // private int[] match;
}
