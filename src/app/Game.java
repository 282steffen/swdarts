package app;

import java.util.List;

public interface Game {
    public static enum games {
	RTW,TargetPractice
    };
    public void start();

    public void dartsThrown(List<Integer> values);
}
