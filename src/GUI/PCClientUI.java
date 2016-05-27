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
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Helpers.ComSupport;
import IHelpers.ICommunicationObserver;

public class PCClientUI extends JFrame implements ICommunicationObserver {

	JTextField middlemanIpInput;
	JTextArea textArea;
	JScrollPane scrollPane;
	JButton btnForward, btnBack, btnRight, btnLeft, btnStop, btnConnect,
			btnDisconnect, btnGoExplore, btnUsercontrol, btnSlaveFollow,
			btnSlaveStop;
	JPanel mainPanel;
	PCClient client;
	
	private String request;
	private final static String newline = "\n";

	private int socketPort = 6000;

	// setup constructor...
	public PCClientUI() {
		super("PC -> MIDDLEMAN -> SLAVE");
		setupTextField();
		setupTextArea();
		setupButtons();
		setupPanel();
		pack();
		setupFrame();

	}

	// textfield setup...
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
					middlemanIpInput.setText("EV3 ip-address");
			}
		});
	}

	private void setupTextArea() {
		textArea = new JTextArea(10, 30);
		scrollPane = new JScrollPane(textArea);
		textArea.setEditable(false);
	}

	// buttons setup...
	// button : forward
	private void setupButtons() {
		btnForward = new JButton("FORWARD");
		btnForward.setPreferredSize(new Dimension(120, 80));
		btnForward.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				request = "forward";
				informMiddleman();
			}
		});

		// button : back
		btnBack = new JButton("BACKWARD");
		btnBack.setPreferredSize(new Dimension(120, 80));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				request = "backward";
				informMiddleman();
			}
		});

		// button : right
		btnRight = new JButton("RIGHT");
		btnRight.setPreferredSize(new Dimension(120, 80));
		btnRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				request = "right";
				informMiddleman();
			}
		});

		// button : left
		btnLeft = new JButton("LEFT");
		btnLeft.setPreferredSize(new Dimension(120, 80));
		btnLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				request = "left";
				informMiddleman();
			}
		});

		// button : stop
		btnStop = new JButton("STOP");
		btnStop.setPreferredSize(new Dimension(120, 80));
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				request = "stop";
				informMiddleman();
			}
		});

		// button : connect
		btnConnect = new JButton("CONNECT");
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					connectToEV3();
				} catch (Exception e) {

				}
			}
		});

		// button : disconnect
		btnDisconnect = new JButton("DISCONNECT");
		btnDisconnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				request = "quit";
				informMiddleman();
			}
		});

		// button : Go explore
		btnGoExplore = new JButton("Go explore");
		btnGoExplore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				request = "explore";
				informMiddleman();
			}
		});

		// button : User control
		btnUsercontrol = new JButton("Take control");
		btnUsercontrol.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				request = "usercontrol";
				informMiddleman();
			}
		});

		// button : Slave stop
		btnSlaveStop = new JButton("Make slave stop");
		btnSlaveStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				request = "slavestop";
				informMiddleman();
			}
		});

		// button : Slave follow
		btnSlaveFollow = new JButton("Make slave follow");
		btnSlaveFollow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				request = "slavego";
				informMiddleman();
			}
		});
	}

	// frame setup...
	private void setupFrame() {
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		middlemanIpInput.requestFocus();
	}

	// panel setup...
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

		gbc.gridx = 4;
		gbc.gridy = 0;
		mainPanel.add(btnUsercontrol, gbc);

		gbc.gridx = 4;
		gbc.gridy = 1;
		mainPanel.add(btnGoExplore, gbc);
		
		gbc.gridx = 5;
		gbc.gridy = 0;
		mainPanel.add(btnSlaveFollow, gbc);

		gbc.gridx = 5;
		gbc.gridy = 1;
		mainPanel.add(btnSlaveStop, gbc);

		gbc.gridx = 5;
		gbc.gridy = 2;
		gbc.weighty = 2;
		gbc.weightx = 2;

		mainPanel.add(scrollPane, gbc);

		add(mainPanel);

	}

	// send request to middleman...
	private void informMiddleman() {
		try {
			if (client != null) {
				client.sendMessage(request);
			} else {
				textArea.append("No client connected" + newline);
			}
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	private void connectToEV3() throws UnknownHostException, IOException {
		String ip = middlemanIpInput.getText();
		client = new PCClient(ip, socketPort);

		client.setupMsgRecieving(PCClientUI.this);

	}

	public void ev3Message(String ev3Message) {
		textArea.append(ev3Message + newline);
	}
}