package com.tennisscore.board.controller;

// import org.hibernate.query.ResultListTransformer;
// import org.hibernate.engine.spi.Resolution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
// import org.apache.tomcat.util.http.ResponseUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.tennisscore.board.model.Board;
import com.tennisscore.board.repo.BoardRepo;


@RestController
public class Umpire {
    
    @Autowired
    private BoardRepo repo;//BoardRepo;

    private String plusScore(String t1,String t2){
        String result= "0";
        if(!t2.equals("A")){
                if(t1.equals("0"))    result= "15";
                else if(t1.equals("15"))   result= "30";
                else if(t1.equals("30"))   result= "40";
                else if(t1.equals("40") && t2.equals("40"))   result= "A";
                else if(t1.equals("40") && t2.equals("A"))   result= "Duece";
                else if (t1.equals("40")) result = "Game";
                else if(t1.equals("A"))   result= "Game";
        }
        else  result= "Duece";
        return result;
    }

    //startMatch() getmethod
    //getScore() getMethod
    //team1() getMothod when team1 wins a point
    //team2() getMothod when team2 wins a point

    @GetMapping("/")
    public ResponseEntity<String> startMatch(){
        try{
            repo.deleteAll();
        }
        catch(java.lang.IllegalArgumentException e){};
        
        Board board = new Board();
        board.setTeam1Score("0");
        board.setTeam2Score("0");
        repo.save(board);
        return new ResponseEntity<String>("Match started succesfully",HttpStatus.OK);
    }
    
    @GetMapping("/Score")
    public ResponseEntity<Board> geScore(){
        return new ResponseEntity<Board>(repo.findAll().get(0), HttpStatus.OK); //repo.findOne(null)
    }

    @GetMapping("/t1")
    public ResponseEntity<Board> t1(){
        Board b = repo.findAll().get(0);
        String result = plusScore(b.getTeam1Score(), b.getTeam2Score());

        if(result.equals("Game")) b.setWinner("Team1");
        if(result == "0" || result == "15" || result == "30" || result == "40" ||  result == "A") b.setTeam1Score(result);
        if(result.equals("Duece")) b.setTeam2Score("40");

        repo.save(b);
        return new ResponseEntity<Board>(repo.findAll().get(0), HttpStatus.OK); //repo.findOne(null)
    }


    @GetMapping("/t2")
    public ResponseEntity<Board> t2(){
        Board b = repo.findAll().get(0);
        String result = plusScore(b.getTeam2Score(), b.getTeam1Score());
        
        if(result == "Game")  b.setWinner("Team2");
        if(result == "0" || result == "15" || result == "30" || result == "40" ||  result == "A")  b.setTeam2Score(result);
        if(result == "Duece")  b.setTeam1Score("40");

        repo.save(b);
        return new ResponseEntity<Board>(repo.findAll().get(0), HttpStatus.OK); //repo.findOne(null)
    
    }
}
