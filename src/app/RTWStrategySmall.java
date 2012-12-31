package app;

import java.util.ArrayList;

public class RTWStrategySmall extends RTWStrategyAbstract {


    public RTWStrategySmall() {
	targets = new ArrayList<String>();
	for (int i = 1; i < 21; i++) {
	    targets.add("Kleine " + i);
	}
	targets.add("Bull");
    }

    @Override
    public String getName() {

	return "RTW small";
    }


}
