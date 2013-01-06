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

    public static void main(String[] args) {
        WebLookAndFeel.install();
        JFrame frame = new JFrame("FiveOOnePanel");
        frame.setContentPane(new FiveOOnePanel().fiveOOnePanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
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

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        fiveOOnePanel = new JPanel();
        fiveOOnePanel.setLayout(new GridBagLayout());
        final JScrollPane scrollPane1 = new JScrollPane();
        scrollPane1.setMinimumSize(new Dimension(400, 159));
        scrollPane1.setOpaque(false);
        scrollPane1.setPreferredSize(new Dimension(400, 500));
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.weighty = 0.9;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        fiveOOnePanel.add(scrollPane1, gbc);
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridBagLayout());
        panel1.setAlignmentY(0.0f);
        panel1.setAutoscrolls(false);
        panel1.setMaximumSize(new Dimension(400, 500));
        scrollPane1.setViewportView(panel1);
        innerFooPanel = new JPanel();
        innerFooPanel.setLayout(new GridBagLayout());
        innerFooPanel.setFont(new Font(innerFooPanel.getFont().getName(), innerFooPanel.getFont().getStyle(), 42));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        panel1.add(innerFooPanel, gbc);
        final JLabel label1 = new JLabel();
        label1.setFont(new Font(label1.getFont().getName(), label1.getFont().getStyle(), 42));
        label1.setHorizontalAlignment(2);
        label1.setHorizontalTextPosition(2);
        label1.setText("Score");
        label1.setVerticalAlignment(1);
        label1.setVerticalTextPosition(1);
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        innerFooPanel.add(label1, gbc);
        final JPanel spacer1 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        innerFooPanel.add(spacer1, gbc);
        final JPanel spacer2 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.VERTICAL;
        innerFooPanel.add(spacer2, gbc);
        final JLabel label2 = new JLabel();
        label2.setFont(new Font(label2.getFont().getName(), label2.getFont().getStyle(), 42));
        label2.setText("Rest");
        label2.setVerticalAlignment(1);
        label2.setVerticalTextPosition(1);
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        innerFooPanel.add(label2, gbc);
        final JPanel spacer3 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        innerFooPanel.add(spacer3, gbc);
        final JLabel label3 = new JLabel();
        label3.setFont(new Font(label3.getFont().getName(), label3.getFont().getStyle(), 42));
        label3.setText("501");
        label3.setVerticalAlignment(1);
        label3.setVerticalTextPosition(1);
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        innerFooPanel.add(label3, gbc);
        final JSeparator separator1 = new JSeparator();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 4;
        gbc.fill = GridBagConstraints.BOTH;
        innerFooPanel.add(separator1, gbc);
        firstThrowField = new JTextField();
        firstThrowField.setFont(new Font(firstThrowField.getFont().getName(), firstThrowField.getFont().getStyle(), 42));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        innerFooPanel.add(firstThrowField, gbc);
        final JLabel label4 = new JLabel();
        label4.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        innerFooPanel.add(label4, gbc);
        final JSeparator separator2 = new JSeparator();
        separator2.setOrientation(1);
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        innerFooPanel.add(separator2, gbc);
        final JSeparator separator3 = new JSeparator();
        separator3.setOrientation(1);
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        innerFooPanel.add(separator3, gbc);
        final JSeparator separator4 = new JSeparator();
        separator4.setOrientation(1);
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 3;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        innerFooPanel.add(separator4, gbc);
        final JLabel label5 = new JLabel();
        label5.setFont(new Font(label5.getFont().getName(), label5.getFont().getStyle(), label5.getFont().getSize()));
        label5.setHorizontalAlignment(10);
        label5.setMaximumSize(new Dimension(16, 16));
        label5.setMinimumSize(new Dimension(16, 16));
        label5.setPreferredSize(new Dimension(16, 16));
        label5.setText("3");
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 3;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        innerFooPanel.add(label5, gbc);
        final JPanel spacer4 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.VERTICAL;
        panel1.add(spacer4, gbc);
        bigScoreLabel = new JLabel();
        bigScoreLabel.setFocusable(false);
        bigScoreLabel.setFont(new Font(bigScoreLabel.getFont().getName(), bigScoreLabel.getFont().getStyle(), 96));
        bigScoreLabel.setHorizontalAlignment(0);
        bigScoreLabel.setHorizontalTextPosition(0);
        bigScoreLabel.setText("501");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        gbc.weightx = 1.0;
        gbc.weighty = 0.1;
        gbc.anchor = GridBagConstraints.WEST;
        fiveOOnePanel.add(bigScoreLabel, gbc);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return fiveOOnePanel;
    }
}
