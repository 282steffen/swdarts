package app;

import gui.FiveOOnePanel;

import java.util.ArrayList;
import java.util.List;


public class FiveOOneGame implements Game {

    private int rest;
    private int noOfThrows;
    private double nineDartAvg;
    private FiveOOnePanel associatedPanel;
    List<Integer> scorePerRound;
    List<Integer> restPerRound;

    public FiveOOneGame(){
        start();
    }

    @Override
    public void start() {
        rest = 501;
        noOfThrows = 0;
        nineDartAvg = 0;
        scorePerRound = new ArrayList<Integer>();
        restPerRound = new ArrayList<Integer>();
        associatedPanel = FiveOOnePanel.getInstance();
    }

    public boolean checkThrow(int score){
        if(score > 0 && score < 180){
            if(score == 179 ||score == 178 || score == 176 ||score == 175 ||score == 173 ||score == 172|| score == 169) return false;
          return true;

        }
        return false;
    }

    public void dartsThrown(List<Integer> values) {
        int score = values.get(0);
        int darts = values.get(1);


        if(rest - score == 0){
            gameEnded();
        }else if(rest-score < 0){
            noOfThrows += 3;
            associatedPanel.updateFooPanel(rest);
        }else{
            rest -= score;
            noOfThrows += 3;
            associatedPanel.updateFooPanel(rest);
        }if(noOfThrows ==9){
            nineDartAvg = (501-rest)/3;
        }



    }
    public void gameEnded(){
        noOfThrows = noOfThrows + Integer.parseInt(associatedPanel.askForNofDarts());
        rest = 0;
        associatedPanel.gameEnded(501/noOfThrows,nineDartAvg);
        saveGame();
        if(associatedPanel.askForRestart()){
            associatedPanel.clear();
        }
    }
    //TODO: Save Game into DB
    private void saveGame() {
        //Later on
    }

}
