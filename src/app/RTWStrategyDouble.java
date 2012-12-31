package app;

import java.util.ArrayList;

public class RTWStrategyDouble extends RTWStrategyAbstract {

    public RTWStrategyDouble() {
	targets = new ArrayList<String>();
	for (int i = 1; i < 21; i++) {
	    targets.add("Doppel " + i);
	}
	targets.add("Bull");
    }

    @Override
    public String getName() {

	return "RTW double";
    }

}
