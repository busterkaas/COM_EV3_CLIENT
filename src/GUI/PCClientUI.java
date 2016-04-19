package GUI;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Logic.PCClient;

public class PCClientUI extends JFrame {

	JPanel mainPanel;
	JButton btnForwards, btnBackwards, btnRight, btnLeft, btnStop, btnQuit;
	JTextField middlemanIpInput;
	PCClient client;
	String command;

	public PCClientUI() {
		super("Robotics");
		middlemanIpInput = new JTextField();
		setupFrame();

	}

	private void setupFrame() {
		setLayout(new FlowLayout());
		setSize(800, 800);
		setLocationRelativeTo(null);

		mainPanel = new JPanel(new FlowLayout());

		setupButtons();
		middlemanIpInput.setText("MOJN");
		
		
		mainPanel.add(middlemanIpInput);
		
		mainPanel.add(btnForwards);

		mainPanel.add(btnBackwards);

		mainPanel.add(btnLeft);

		mainPanel.add(btnRight);

		mainPanel.add(btnStop);

		mainPanel.add(btnQuit);

		add(mainPanel);

	}

	private void setupButtons() {
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
	}

	private void informMiddleman() {
		try {
			client.sendMessage(command);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
