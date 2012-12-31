package app;

import java.util.List;

public interface RTWStrategy {
    public String getName();

    public String getCurrent();

    public String getNext();

    public void passThrowToDatabase(List<Integer> values);
}
