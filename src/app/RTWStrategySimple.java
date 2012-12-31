package app;

import java.util.ArrayList;
import java.util.List;

public class RTWStrategySimple extends RTWStrategyAbstract {



    public RTWStrategySimple() {
	targets = new ArrayList<String>();
	for (int i = 1; i < 21; i++) {
	    targets.add("" + i);
	}
	targets.add("Bull");
    }

    @Override
    public String getName() {

	return "RTW simpel";
    }

    @Override
    public void passThrowToDatabase(List<Integer> values) {
	System.out.println("HAIL SATAN");
	String gameName = "RTWSIMPLE";
	data.addGame(gameName, values);
    }

}
