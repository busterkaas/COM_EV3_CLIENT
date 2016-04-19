package GUI;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Logic.PCClient;

public class UserInterface {

	JFrame frame;
	JPanel panel;
	JButton btnForwards, btnBackwards, btnRight, btnLeft, btnStop, btnQuit;
	PCClient client;
	String command;

	public UserInterface() {
		setupFrame();
	}

	private void setupFrame() {
		frame = new JFrame("Robotics");
		frame.setSize(800, 800);
		frame.setLocationRelativeTo(null);
		panel = new JPanel();
		panel.setLayout(new FlowLayout());
		frame.add(panel);
		

		// Buttons
		btnForwards = new JButton("Forward");
		btnForwards.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				command = "forward";
				informMiddleman();

			}
		});

		btnBackwards = new JButton("Backward");
		btnBackwards.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				command = "backward";
				informMiddleman();

			}
		});

		btnRight = new JButton("Go Right");
		btnRight.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				command = "right";
				informMiddleman();

			}
		});

		btnLeft = new JButton("Go left");
		btnLeft.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				command = "left";
				informMiddleman();

			}
		});

		btnStop = new JButton("Stop");
		btnStop.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				command = "stop";
				informMiddleman();

			}
		});

		btnQuit = new JButton("Quit");
		btnQuit.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				command = "quit";
				informMiddleman();

			}
		});
		
		panel.add(btnForwards);
		panel.add(btnBackwards);
		panel.add(btnLeft);
		panel.add(btnRight);
		panel.add(btnStop);
		panel.add(btnQuit);
		
		
		
		frame.setVisible(true);
	}

	private void informMiddleman() {
		try {
			client.sendMessage(command);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
