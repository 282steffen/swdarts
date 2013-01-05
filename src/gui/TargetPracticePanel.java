package gui;

import app.TargetHelper;
import app.TargetPractice;
import com.alee.managers.popup.PopupStyle;
import com.alee.managers.popup.WebPopup;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;

/**
 * Created with IntelliJ IDEA.
 * User: steffenwitt
 * Date: 02.01.13
 * Time: 21:37
 * To change this template use File | Settings | File Templates.
 */
public class TargetPracticePanel {
    private JTextField textField1;
    private JButton okButton;
    private JPanel targetPracticePanel;
    private JLabel hprTodayLabel;
    private JLabel hprTotalLabel;

    private JLabel hprLabel;
    private JComboBox targetBox;
    private JButton beendenButton;
    private TargetPractice associateGame;
    WebPopup wrongInputPopup = new WebPopup();


    public TargetPracticePanel() {
        wrongInputPopup.setSize(300,50);
        wrongInputPopup.add(new JLabel("<html><font color='white'>Nur Zahlen zwischen 1 und 12 gültig</font></html>"));
        wrongInputPopup.setPopupStyle(PopupStyle.gray);
        textField1.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e){
                if(e.getKeyCode() == KeyEvent.VK_ENTER)
                sendThrow();
            }
        });
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                sendThrow();
            }
        });
        associateGame = new TargetPractice();
        targetBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hprLabel.setText("");
            }
        });
        beendenButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                associateGame.saveGame();
            }
        });
    }

    private void sendThrow() {
        try{
        int numOfHits = Integer.parseInt(textField1.getText());
            if(numOfHits < 0 || numOfHits > 12){
                wrongInputPopup.showPopup(okButton);
            }else{
                wrongInputPopup.hidePopup();
                updateGui(associateGame.addThrow(numOfHits, targetBox.getSelectedItem().toString()));

            }
            textField1.setText("");


        }catch (NumberFormatException e){
            wrongInputPopup.showPopup(okButton);

        }
    }

    private void updateGui(TargetHelper targetHelper) {
       DecimalFormat twoDForm = new DecimalFormat("#.##");
       hprLabel.setText(""+Double.valueOf(twoDForm.format(targetHelper.getHpr())));
    }


    public JPanel getTargetPracticePanel() {


        return targetPracticePanel;
    }

    public JTextField getTextField1() {
        return textField1;
    }

    public void setTextField1(JTextField textField1) {
        this.textField1 = textField1;
    }

    public JLabel getHprTodayLabel() {
        return hprTodayLabel;
    }

    public void setHprTodayLabel(JLabel hprTodayLabel) {
        this.hprTodayLabel = hprTodayLabel;
    }

    public JLabel getHprTotalLabel() {
        return hprTotalLabel;
    }

    public void setHprTotalLabel(JLabel hprTotalLabel) {
        this.hprTotalLabel = hprTotalLabel;
    }


}
