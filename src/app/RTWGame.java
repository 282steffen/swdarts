package app;

import gui.RTWGamePanel;

import java.util.ArrayList;
import java.util.List;

import app.RTWStrategy.strategies;

public class RTWGame implements Game {

    private static RTWGame instance_;
    RTWGamePanel panel = RTWGamePanel.getInstance();
    private RTWStrategy strategy;
    private boolean isStarted = false;
    private final List<Integer> scores = new ArrayList<Integer>();

    public static RTWGame getInstance() {
	if (instance_ == null) {
	    instance_ = new RTWGame();
	}
	return instance_;
    }
    @Override
    public void start() {
	strategy = new RTWStrategyBig();
	isStarted = true;
	panel.updateToThrow(strategy.getCurrent());
    }

    @Override
    public void dartsThrown(List<Integer> values) {
	scores.add(values.get(0));
	String nextString = strategy.getNext();
	if (nextString.equals("END")) {
	    strategy.passThrowToDatabase(scores);
	}
	panel.updateToThrow(nextString);

    }

    public boolean getIsStarted() {
	return isStarted;
    }

    public void start(strategies strategy) {
	if (strategy.equals(strategies.SIMPLE)) {
	    this.strategy = new RTWStrategySimple();
	    // DataController.getInstance().calculateGameStats("RTWSIMPLE");
	}
	if (strategy.equals(strategies.BIG)) {
	    this.strategy = new RTWStrategyBig();
	    // DataController.getInstance().calculateGameStats("RTWBIG");
	}
	if (strategy.equals(strategies.SMALL)) {
	    this.strategy = new RTWStrategySmall();
	    // DataController.getInstance().calculateGameStats("RTWSMALL");
	}
	if (strategy.equals(strategies.DOUBLE)) {
	    this.strategy = new RTWStrategyDouble();
	    // DataController.getInstance().calculateGameStats("RTWDOUBLE");
	}
	if (strategy.equals(strategies.TRIPLE)) {
	    this.strategy = new RTWStrategyTriple();
	    // DataController.getInstance().calculateGameStats("RTWTRIPLE");
	}
	panel.updateToThrow(this.strategy.getCurrent());

    }

}
