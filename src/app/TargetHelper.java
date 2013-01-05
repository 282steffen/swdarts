package app;

/**
 * Created with IntelliJ IDEA.
 * User: steffenwitt
 * Date: 05.01.13
 * Time: 21:23
 * To change this template use File | Settings | File Templates.
 */
public class TargetHelper {
    public String getTarget() {
        return target;
    }

    String target;
    int hits;
    int rounds;
    double hpr;

    public TargetHelper(String target){
        this.target = target;
        hits = 0;
        rounds = 0;
        hpr = 0;
    }

    public void addRound(int newHits){
        hits +=  newHits;
        rounds += 1;
        hpr = (double)hits/rounds;
        System.out.println("new hpr:"+ hpr + "=" +hits +"/" + rounds);
    }

    public double getHpr() {
        return hpr;
    }

    public void setHpr(double hpr) {
        this.hpr = hpr;
    }

    public int getRounds() {
        return rounds;
    }

    public void setRounds(int rounds) {
        this.rounds = rounds;
    }

    public int getHits() {
        return hits;
    }

    public void setHits(int hits) {
        this.hits = hits;
    }
}
