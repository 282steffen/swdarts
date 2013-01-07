package app;

import gui.FiveOOnePanel;

import java.util.ArrayList;
import java.util.List;


public class FiveOOneGame implements Game {

    private int rest;
    private int noOfThrows;
    private double nineDartAvg;
    private double threeDartAvg;
    private static int gameCount = 0;
    private static int no180 = 0;
    private static int no140 = 0;
    private static int no100 = 0;

    public static int getGameCount() {
        return gameCount;
    }

    public static int getNo100() {
        return no100;
    }

    public static int getNo140() {
        return no140;
    }

    public static int getNo180() {
        return no180;
    }

    public double getThreeDartAvg() {
        return threeDartAvg;
    }

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
        if(score >= 0 && score <= 180){
            if(score == 179 ||score == 178 || score == 176 ||score == 175 ||score == 173 ||score == 172|| score == 169) return false;
            return true;

        }
        return false;
    }

    public int getRest() {
        return rest;
    }

    public int getNoOfThrows() {
        return noOfThrows;
    }

    public double getNineDartAvg() {
        return nineDartAvg;
    }

    public void dartsThrown(List<Integer> values) {
        int score = values.get(0);
        int darts = values.get(1);
        if(score == 180){
            no180++;
        }else if (score >= 140){
            no140++;
        }else if(score >= 100){
            no100++;
        }


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
            nineDartAvg = ((double)501-rest)/3;
        }
        threeDartAvg = ((double)(501-rest)*3)/noOfThrows;
        associatedPanel.updateStatsPanel();



    }
    public void gameEnded(){
        noOfThrows = noOfThrows + Integer.parseInt(associatedPanel.askForNofDarts());
        rest = 0;
        gameCount++;
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

    public String calculateFinish(int desiredDouble){
        String result ="-";
        int diff = rest - desiredDouble;
        if(diff > 120 || diff <= 0){
        }
        else{
            result = findOneDartSolution(diff);
            if(result.equals("-")){
                result = findTwoDartSolution(diff);
            }
        }

        if(result.equals("-")) return result;
        return result + "+ D" + desiredDouble/2;
    }

    private String findTwoDartSolution(int diff) {
        String result = "-";
        for(int i = 1; i < diff && i < 21; i++){

            result =  findOneDartSolution(diff - i);
            if(!result.equals("-")){
                return i+" +" + result;
            }
        }
        for (int i = 7; i <21 && i*3<=diff; i++){
            result = findOneDartSolution(diff - 3*i);
            if(!result.equals("-")){
                return "T"+i+" +" + result;
            }
        }
        return result;  //To change body of created methods use File | Settings | File Templates.
    }

    private String findOneDartSolution(int diff) {
        System.out.println("Diff"+diff);
        if(diff > 60){
            return "-";
        }else{
            if(diff == 50){
                return "D25";
            }
            if(diff == 25){
                return "25";
            }
            for (int i = 1; i <21; i++){
                if(i == diff){
                    return ""+i;
                }
            }
            for (int i = 7; i <21 && i*3<=diff; i++){
                if(i*3 == diff){
                    return "T"+i;
                }
            }
        }
        return "-";
    }

}
