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

    @Override
    public void dartsThrown(List<Integer> values) {
        int score = values.get(0);
        int darts = values.get(1);
        rest = rest - score;
        noOfThrows = noOfThrows + noOfThrows;
        associatedPanel.updateFooPanel(rest);


    }
}
