package app;

import java.util.ArrayList;

public class RTWStrategyTriple extends RTWStrategyAbstract {

    public RTWStrategyTriple() {
	targets = new ArrayList<String>();
	for (int i = 1; i < 21; i++) {
	    targets.add("Triple " + i);
	}
	targets.add("Bull");
    }

    @Override
    public String getName() {

	return "RTW triple";
    }

}

