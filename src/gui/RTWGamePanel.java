package gui;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import app.RTWGame;
import com.alee.laf.WebLookAndFeel;

public class RTWGamePanel extends JPanel implements ActionListener, KeyListener {

    private static RTWGamePanel instance_;

    JLabel dartsNeededDescriptionLabel = new JLabel("Benötigte Darts");
    JTextField dartsNeededField = new JTextField("1");

    JButton submitButton = new JButton("OK");
    JLabel toThrowDescriptionLabel = new JLabel("Ziel");
    JLabel toThrowLabel = new JLabel("");

    private RTWGamePanel() {
	setLayout();
	registerActionsListeners();
    }

    private void registerActionsListeners() {
	submitButton.addActionListener(this);
	dartsNeededField.addKeyListener(this);
    }

    public static RTWGamePanel getInstance() {
	if (instance_ == null) {
	    instance_ = new RTWGamePanel();
	}
	return instance_;
    }

    private void setLayout() {
        WebLookAndFeel.install();
	this.setLayout(new GridBagLayout());
	GridBagConstraints gbc = new GridBagConstraints();
	// gbc.fill = GridBagConstraints.BOTH;
	gbc.anchor = GridBagConstraints.FIRST_LINE_START;
	Font curFont = dartsNeededField.getFont();

	dartsNeededField.setFont(new Font(curFont.getFontName(), curFont
		.getStyle(), 42));
	submitButton.setFont(new Font(curFont.getFontName(),
		curFont.getStyle(), 42));
	toThrowLabel.setFont(new Font(curFont.getFontName(),
		curFont.getStyle(), 42));

	gbc.gridx = 0;
	gbc.gridy = 0;
	gbc.ipadx = 100;
	this.add(toThrowDescriptionLabel, gbc);
	gbc.gridx = 1;
	gbc.gridy = 0;
	this.add(dartsNeededDescriptionLabel, gbc);
	gbc.gridx = 0;
	gbc.gridy = 1;
	this.add(toThrowLabel, gbc);
	gbc.gridx = 1;
	gbc.gridy = 1;
	this.add(dartsNeededField, gbc);
	gbc.gridx = 2;
	gbc.gridy = 1;
	this.add(submitButton, gbc);


    }

    public void updateToThrow(String newTarget) {
	toThrowLabel.setText(newTarget);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	if (e.getSource() == submitButton) {

	    sendThrow();
	}


    }

    private void sendThrow() {
	try {
	    List<Integer> values = new ArrayList<Integer>();
	    Integer dartsNeeded = Integer.parseInt(dartsNeededField.getText());
	    values.add(dartsNeeded);

	    RTWGame.getInstance().dartsThrown(values);
	} catch (NumberFormatException E) {
	    System.out.println("Only Number accepted");
	}
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
	if (e.getKeyCode() == KeyEvent.VK_ENTER) {
	    sendThrow();
	}

    }

    @Override
    public void keyReleased(KeyEvent e) {
	// TODO Auto-generated method stub

    }


}
