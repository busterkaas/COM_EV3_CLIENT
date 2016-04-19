package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Logic.PCClient;

public class PCClientUI extends JFrame {

	JTextField middlemanIpInput;
	JButton btnForward, btnBack, btnRight, btnLeft, btnStop, btnConnect, btnDisconnect;
	JPanel mainPanel;
	PCClient client;
	String command;

	public PCClientUI() {
		super("PC -> MIDDLEMAN -> SLAVE");
		setupTextField();
		setupButtons();
		setupPanel();
		pack();
		setupFrame();
	}

	// BRUG EVT BAGEFTER DENNE TIL AT GIVE BESKEDER I, SÅSOM connected, ev3
	// says... osv. INDTIL DER TRYKKES PÅ "DISCONNECT".
	private void setupTextField() {
		middlemanIpInput = new JTextField("EV3 ip-address");
		Color colorRed = Color.BLACK;
		Color colorGreen = Color.GREEN;
		middlemanIpInput.setForeground(colorGreen);
		middlemanIpInput.setBackground(colorRed);
		middlemanIpInput.setHorizontalAlignment(middlemanIpInput.CENTER);

		middlemanIpInput.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				middlemanIpInput.setText("");
			}

			public void mouseExited(MouseEvent e) {
				if (middlemanIpInput.getText().equals(""))
					middlemanIpInput.setText("IP");
			}
		});
	}

	// button : forward
	private void setupButtons() {
		btnForward = new JButton("FORWARD");
		btnForward.setPreferredSize(new Dimension(120, 80));
		btnForward.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				command = "forward";
				informMiddleman();
			}
		});

		// button : back
		btnBack = new JButton("BACK");
		btnBack.setPreferredSize(new Dimension(120, 80));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				command = "back";
				informMiddleman();
			}
		});

		// button : right
		btnRight = new JButton("RIGHT");
		btnRight.setPreferredSize(new Dimension(120, 80));
		btnRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				command = "right";
				informMiddleman();
			}
		});

		// button : left
		btnLeft = new JButton("LEFT");
		btnLeft.setPreferredSize(new Dimension(120, 80));
		btnLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				command = "left";
				informMiddleman();
			}
		});

		// button : stop
		btnStop = new JButton("STOP");
		btnStop.setPreferredSize(new Dimension(120, 80));
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				command = "stop";
				informMiddleman();
			}
		});

		// button : connect
		btnConnect = new JButton("CONNECT");
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				command = "connect";
				informMiddleman();
			}
		});

		// button : disconnect
		btnDisconnect = new JButton("DISCONNECT");
		btnDisconnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				command = "disconnect";
				informMiddleman();
			}
		});
	}

	private void setupFrame() {
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		middlemanIpInput.requestFocus();
	}

	private void setupPanel() {
		mainPanel = new JPanel();
		mainPanel.setLayout(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(15, 15, 15, 15);
		gbc.fill = GridBagConstraints.HORIZONTAL;

		gbc.gridx = 1;
		gbc.gridy = 0;
		mainPanel.add(btnForward, gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		mainPanel.add(btnLeft, gbc);

		gbc.gridx = 1;
		gbc.gridy = 1;
		mainPanel.add(btnStop, gbc);

		gbc.gridx = 2;
		gbc.gridy = 1;
		mainPanel.add(btnRight, gbc);

		gbc.gridx = 1;
		gbc.gridy = 2;
		mainPanel.add(btnBack, gbc);

		gbc.gridx = 0;
		gbc.gridy = 3;
		mainPanel.add(middlemanIpInput, gbc);

		gbc.gridx = 1;
		gbc.gridy = 3;
		mainPanel.add(btnConnect, gbc);

		gbc.gridx = 2;
		gbc.gridy = 3;
		mainPanel.add(btnDisconnect, gbc);

		add(mainPanel);
	}

	private void informMiddleman() {
		try {
			client.sendMessage(command);
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
}