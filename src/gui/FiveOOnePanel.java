package gui;

import app.FiveOOneGame;
import com.alee.laf.WebLookAndFeel;
import com.alee.laf.optionpane.WebOptionPane;
import com.alee.managers.popup.PopupStyle;
import com.alee.managers.popup.WebPopup;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: steffenwitt
 * Date: 03.01.13
 * Time: 12:54
 * To change this template use File | Settings | File Templates.
 */
public class FiveOOnePanel {

    private static FiveOOnePanel instance;
    private JTextField firstThrowField;
    private JPanel fiveOOnePanel;
    private JPanel innerFooPanel;
    private JLabel bigScoreLabel;
    private JLabel tdavgLabel;
    private JLabel ndavgLabel;
    private JLabel fofinishLabel;
    private JLabel ttFinishLabel;
    private JLabel tfFinishLabel;
    private JLabel bullFinishLabel;
    private JLabel playCountLabel;
    private JLabel no100Label;
    private JLabel no140label;
    private JLabel no180label;
    private List<JTextField> scoreFields = new ArrayList<JTextField>();
    private List<JLabel> scoreLabels = new ArrayList<JLabel>();
    private int addIndexY = 4;
    private int roundIndex = 1;
    private FiveOOneGame associatedGame;
    WebPopup wrongInputPopup = new WebPopup();

    private FiveOOnePanel() {
        wrongInputPopup.setSize(300, 50);
        wrongInputPopup.add(new JLabel("<html><font color='white'>Kein möglicher Wurf!</font></html>"));
        wrongInputPopup.setPopupStyle(PopupStyle.gray);
        scoreFields.add(firstThrowField);
        no100Label.setText(""+FiveOOneGame.getNo100());
        no140label.setText(""+FiveOOneGame.getNo140());
        no180label.setText(""+FiveOOneGame.getNo180());
        playCountLabel.setText(""+FiveOOneGame.getGameCount());
        firstThrowField.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                reactToScoreField(e);
            }
        });
        ;

    }

    public static FiveOOnePanel getInstance() {
        if (instance == null) {
            instance = new FiveOOnePanel();
            instance.associatedGame = new FiveOOneGame();

        }
        return instance;

    }

    public JPanel getFooPanel() {
        return fiveOOnePanel;
    }



    private void sendThrow() {
        if (checkThrow()) {
            ArrayList<Integer> values = new ArrayList<Integer>();
            int thrown = Integer.parseInt(scoreFields.get(scoreFields.size() - 1).getText());
            int rest = Integer.parseInt(scoreFields.get(scoreFields.size() - 1).getText()) - thrown;
            int dartsUsed;
            values.add(thrown);
            if (rest == 0) {
                dartsUsed = askForNumberOfDarts();
            } else {
                dartsUsed = 3;
            }
            values.add(dartsUsed);
            associatedGame.dartsThrown(values);
        } else {

        }


    }

    public void updateStatsPanel(){
        DecimalFormat twoDForm = new DecimalFormat("#.##");
        tdavgLabel.setText("" + Double.valueOf(twoDForm.format(associatedGame.getThreeDartAvg())));
        ndavgLabel.setText(""+Double.valueOf(twoDForm.format(associatedGame.getNineDartAvg())));
        fofinishLabel.setText(associatedGame.calculateFinish(40));
        ttFinishLabel.setText(associatedGame.calculateFinish(32));
        tfFinishLabel.setText(associatedGame.calculateFinish(24));
        bullFinishLabel.setText(associatedGame.calculateFinish(50));
        no100Label.setText(""+FiveOOneGame.getNo100());
        no140label.setText(""+FiveOOneGame.getNo140());
        no180label.setText(""+FiveOOneGame.getNo180());
        playCountLabel.setText(""+FiveOOneGame.getGameCount());
    }

    public void updateFooPanel(int newRest) {
        JLabel newScoreLabel = new JLabel();
        Font curFont = newScoreLabel.getFont();

        newScoreLabel.setFont(new Font(curFont.getFontName(), curFont
                .getStyle(), 42));
        newScoreLabel.setText("" + newRest);
        scoreLabels.add(newScoreLabel);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = addIndexY - 1;
        gbc.gridx = 2;
        innerFooPanel.add(newScoreLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = addIndexY;
        gbc.gridwidth = 5;
        gbc.fill = GridBagConstraints.BOTH;
        innerFooPanel.add(new JSeparator(JSeparator.HORIZONTAL), gbc);
        addIndexY++;

        gbc.gridy = addIndexY;
        gbc.gridwidth = 1;
        JTextField newScoreEntry = new JTextField();

        newScoreEntry.setFont(new Font(curFont.getFontName(), curFont
                .getStyle(), 42));
        scoreFields.add(newScoreEntry);
        newScoreEntry.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                reactToScoreField(e);
            }
        });
        newScoreEntry.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent evt) {
                jTextFieldFocusGained(evt);
            }
        });
        innerFooPanel.add(newScoreEntry, gbc);

        gbc.gridx = 1;
        innerFooPanel.add(new JSeparator(JSeparator.VERTICAL), gbc);


        gbc.gridx = 3;
        innerFooPanel.add(new JSeparator(JSeparator.VERTICAL), gbc);

        gbc.gridx = 4;
        roundIndex++;
        innerFooPanel.add(new JLabel("" + roundIndex * 3), gbc);

        newScoreEntry.requestFocus();
        addIndexY++;

        bigScoreLabel.setText("" + newRest);

        innerFooPanel.validate();
        innerFooPanel.repaint();
        fiveOOnePanel.validate();
        fiveOOnePanel.repaint();
    }

    private int askForNumberOfDarts() {
        return 0;  //To change body of created methods use File | Settings | File Templates.
    }

    private boolean checkThrow() {
        try {
            int newScore = Integer.parseInt(scoreFields.get(scoreFields.size() - 1).getText());
            if (!associatedGame.checkThrow(newScore)) {
                wrongInputPopup.showPopup(scoreFields.get(scoreFields.size() - 1));
                return false;
            } else {
                wrongInputPopup.hidePopup();
                return true;

            }


        } catch (NumberFormatException e) {
            wrongInputPopup.showPopup(scoreFields.get(scoreFields.size() - 1));
            return false;

        }

    }

    private void reactToScoreField(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (scoreFields.get(scoreLabels.size()) == e.getSource()) {
                sendThrow();
            }
        }

    }

    public String askForNofDarts() {
        return WebOptionPane.showInputDialog(scoreFields.get(scoreFields.size() - 1), "Wieviele Darts wurden benötigt? (1-3)");
    }

    public void gameEnded(double avg, double nineDartAvg) {
        WebOptionPane.showMessageDialog(scoreFields.get(scoreFields.size() - 1), "Spiel mit einem " + avg * 3 + " 3-Dart-Average und einem " + nineDartAvg + " 9-Dart-Avg beendet.");
    }

    private void jTextFieldFocusGained(FocusEvent evt) {
        Component focusedComponent = evt.getComponent();
        innerFooPanel.scrollRectToVisible(focusedComponent.getBounds(null));
        innerFooPanel.repaint();
    }

    public boolean askForRestart() {
        int i = WebOptionPane.showConfirmDialog(scoreFields.get(scoreFields.size() - 1), "Neues Spiel starten?");
        if (i == WebOptionPane.YES_OPTION) {
            return true;
        }
        return false;
    }

    public void clear() {
        instance = null;
        MainFrame.getInstance().resetFoo();
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
