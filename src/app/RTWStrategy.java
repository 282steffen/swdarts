package app;

import java.util.List;

public interface RTWStrategy {

    public static enum strategies {
	SIMPLE, BIG, SMALL, DOUBLE, TRIPLE
    };
    public String getName();

    public String getCurrent();

    public String getNext();

    public void passThrowToDatabase(List<Integer> values);
}
