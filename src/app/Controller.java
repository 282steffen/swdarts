package app;

import gui.MainFrame;
import data.DataController;

public class Controller {
    private static Controller instance_;
    private final MainFrame mf;
    private final DataController datactrl;

    private Controller() {
	mf = MainFrame.getInstance();
	datactrl = DataController.getInstance();
    }

    public static Controller getInstance() {
	if (instance_ == null) {
	    instance_ = new Controller();
	}
	return instance_;
    }
    public static void main(String[] args) {
	Controller ctrl = new Controller();

    }

    public void initiateRTWGame(RTWStrategy.strategies strategy) {
	RTWGame.getInstance().start(strategy);
	mf.addRTWGame();
    }

    public static enum singleFields {
	ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, ELEVEN, TWELVE, THIRTEEN, FOURTEEN, FIVETEEN, SIXTEEN, SEVENTEEN, EIGHTEEN, NINETEEN, TWENTY, BULL
    };

    public static enum doubleFields {
	DOUBLE_ONE, DOUBLE_TWO, DOUBLE_THREE, DOUBLE_FOUR, DOUBLE_FIVE, DOUBLE_SIX, DOUBLE_SEVEN, DOUBLE_EIGHT, DOUBLE_NINE, DOUBLE_TEN, DOUBLE_ELEVEN, DOUBLE_TWELVE, DOUBLE_THIRTEEN, DOUBLE_FOURTEEN, DOUBLE_FIVETEEN, DOUBLE_SIXTEEN, DOUBLE_SEVENTEEN, DOUBLE_EIGHTEEN, DOUBLE_NINETEEN, DOUBLE_TWENTY, BULLSEYE
    };

    public static enum tripleFields {
	TRIPLE_ONE, TRIPLE_TWO, TRIPLE_THREE, TRIPLE_FOUR, TRIPLE_FIVE, TRIPLE_SIX, TRIPLE_SEVEN, TRIPLE_EIGHT, TRIPLE_NINE, TRIPLE_TEN, TRIPLE_ELEVEN, TRIPLE_TWELVE, TRIPLE_THIRTEEN, TRIPLE_FOURTEEN, TRIPLE_FIVETEEN, TRIPLE_SIXTEEN, TRIPLE_SEVENTEEN, TRIPLE_EIGHTEEN, TRIPLE_NINETEEN, TRIPLE_TWENTY
    }

}
