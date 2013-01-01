package app;

import java.util.List;

public interface Game {
    public static enum games {
	RTW
    };
    public void start();

    public void dartsThrown(List<Integer> values);
}
