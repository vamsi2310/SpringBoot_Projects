package com.tennis.board.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "board")
@Getter
@Setter
@ToString
public class Board {
    @Id
    @GeneratedValue
    private Long id;
    private String Player1;
    private String player2;
    private int[] points; // 0, 15,30,40
    private int[] game;// Duece,adv,game
    private boolean duece;
    private boolean advantage;
    private int[] set; // 6 games to win a set
    private boolean avvantageSet; 
    private boolean tieBreakerSet; // either advantage set or tie breaker setcan be true
    private int[] match; // best of 3 :  2 or 3 sets to win a match - best of 5 sets : 3 or more sets to win a match
}
