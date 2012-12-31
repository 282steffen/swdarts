package app;

import gui.RTWGamePanel;

import java.util.ArrayList;
import java.util.List;

public class RTWGame implements Game {

    private static RTWGame instance_;
    List<String> targets = new ArrayList<String>();
    RTWGamePanel panel = RTWGamePanel.getInstance();

    public static RTWGame getInstance() {
	if (instance_ == null) {
	    instance_ = new RTWGame();
	}
	return instance_;
    }
    @Override
    public void start() {
	// TODO Auto-generated method stub

    }

    @Override
    public void dartsThrown(List<Integer> values) {
	// TODO Auto-generated method stub

    }

}
