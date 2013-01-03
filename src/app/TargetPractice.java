package app;

/**
 * Created with IntelliJ IDEA.
 * User: steffenwitt
 * Date: 02.01.13
 * Time: 23:53
 * To change this template use File | Settings | File Templates.
 */
public class TargetPractice {
    private static TargetPractice ourInstance = new TargetPractice();
    private int hits = 0;
    private int roundsPlayed = 0;
    private double hprNow;

    public double getHprNow() {
        return hprNow;
    }

    private double hprTotal;

    public static TargetPractice getInstance() {
        return ourInstance;
    }

    private TargetPractice() {
    }

    public void addThrow(int newhits){
        this.hits = this.hits + newhits;
        roundsPlayed = roundsPlayed + 1;
        System.out.println(newhits + "/n" + hits + "/" + roundsPlayed);
        hprNow = hits/roundsPlayed;
    }

    private int getHitsFromHistory(){
           return 0;
    }

    private int getRoundsFromHistory(){
               return 0;
    }
}
