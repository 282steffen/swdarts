package app;

import java.util.List;

import data.DataController;

public abstract class RTWStrategyAbstract implements RTWStrategy {
    protected DataController data = DataController.getInstance();
    protected List<String> targets = null;
    protected int currentTarget;


    @Override
    public String getName() {
	// TODO Auto-generated method stub
	return null;
    }


    @Override
    public String getCurrent() {
	return targets.get(this.currentTarget);
    }

    @Override
    public String getNext() {

	this.currentTarget = this.currentTarget + 1;
	if (currentTarget < targets.size()) {
	    return targets.get(currentTarget);
	} else {
	    return "END";
	}
    }
    @Override
    public void passThrowToDatabase(List<Integer> values) {

    }
}
