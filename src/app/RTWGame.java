package app;

import gui.RTWGamePanel;

import java.util.ArrayList;
import java.util.List;

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

    public void start(String strategy) {
	if (strategy.equals("simple")) {
	    this.strategy = new RTWStrategySimple();
	}
	if (strategy.equals("big")) {
	    this.strategy = new RTWStrategyBig();
	}
	if (strategy.equals("small")) {
	    this.strategy = new RTWStrategySmall();
	}
	if (strategy.equals("double")) {
	    this.strategy = new RTWStrategyDouble();
	}
	if (strategy.equals("triple")) {
	    this.strategy = new RTWStrategyTriple();
	}
	panel.updateToThrow(this.strategy.getCurrent());

    }

}
