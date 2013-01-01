package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import app.Controller;
import app.Game;
import app.RTWStrategy;

public class GameStartPanel extends JPanel implements ActionListener {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    JComboBox<Game.games> game = new JComboBox<Game.games>(Game.games.values());
    JComboBox<RTWStrategy.strategies> strategy = new JComboBox<RTWStrategy.strategies>(
	    RTWStrategy.strategies.values());
    JButton startButton = new JButton("Start");

    public GameStartPanel() {
	setLayout();
	startButton.addActionListener(this);
    }

    private void setLayout() {

	this.setLayout(new GridBagLayout());
	GridBagConstraints gbc = new GridBagConstraints();
	// gbc.fill = GridBagConstraints.BOTH;
	gbc.anchor = GridBagConstraints.FIRST_LINE_START;
	gbc.gridx = 0;
	gbc.gridy = 0;
	gbc.ipadx = 100;
	this.add(game, gbc);
	gbc.gridx = 1;
	gbc.gridy = 0;
	this.add(strategy, gbc);
	gbc.gridx = 3;
	gbc.gridy = 0;
	this.add(startButton, gbc);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
	if(e.getSource() == startButton){
	    Controller.getInstance().initiateRTWGame(
		    (RTWStrategy.strategies) strategy.getSelectedItem());
	}

    }
}
