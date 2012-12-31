package app;

import java.util.ArrayList;

public class RTWStrategyBig extends RTWStrategyAbstract {


    public RTWStrategyBig() {
	targets = new ArrayList<String>();
	for (int i = 1; i < 21; i++) {
	    targets.add("Große " + i);
	}
	targets.add("Bull");
    }

    @Override
    public String getName() {
	return "RTW große Zahlen";
    }


}

