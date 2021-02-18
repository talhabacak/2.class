package AppManagement;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class GUI extends JFrame {
	private Timer timer;
	private JPanel contentPane;
	private Operations operations = new Operations();
	private int startCounter = 0;
	private JTextField textField;
	/**
	 * Launch the application.
	 */
	public void run() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 392, 329);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton addDeleteCapital = new JButton("Add or Delete a Capital");
		addDeleteCapital.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addOrDeleteCapitalGUI nw = new addOrDeleteCapitalGUI(operations);
				nw.addOrDelete();
			}
		});
		addDeleteCapital.setBounds(72, 10, 233, 21);
		contentPane.add(addDeleteCapital);
		
		JButton addOrDeleteDest = new JButton("Add/Delete/Update a Destination");
		addOrDeleteDest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addOrDeleteDestinationGUI nw = new addOrDeleteDestinationGUI(operations);
				nw.addOrDelete();
			}
		});
		addOrDeleteDest.setBounds(72, 41, 233, 21);
		contentPane.add(addOrDeleteDest);
		
		JButton btnNewButton = new JButton("Add/Cancel/Delay a Flight");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addCanceletDelayFlightGUI nw = new addCanceletDelayFlightGUI(operations);
				nw.addCanUpFlight();
				
			}
		});
		btnNewButton.setBounds(72, 74, 233, 21);
		contentPane.add(btnNewButton);
		
		JLabel timerLabel = new JLabel("Timer Label:");
		timerLabel.setBounds(24, 162, 325, 21);
		contentPane.add(timerLabel);
		
		JLabel resultLabel = new JLabel("Result Label:");
		resultLabel.setBounds(24, 193, 325, 21);
		contentPane.add(resultLabel);
		
		
		JButton start = new JButton("start");
		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(startCounter == 0) {
					resultLabel.setText("Started");
					startCounter = 1;
					timer = new Timer(1, new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
								startCounter = 1;
								operations.toKeepSystemDateAlive();
								operations.startAppropriateFlights();
								timerLabel.setText(operations.getCurrentSystemDate().toString());
	
						}
					});
					timer.start();
				}
	
			}
		});
		start.setBounds(24, 233, 102, 21);
		contentPane.add(start);
		
		JButton pause = new JButton("pause");
		pause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(startCounter == 1) {			
					timer.stop();
					startCounter = 0;
					timerLabel.setText(operations.getCurrentSystemDate().toString());
					resultLabel.setText("Paused");
				}
			}
		});
		pause.setBounds(136, 233, 102, 21);
		contentPane.add(pause);
		
		JButton terminate = new JButton("terminate");
		terminate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resultLabel.setText("Terminated");
				dispose();			}
		});
		terminate.setBounds(247, 233, 102, 21);
		contentPane.add(terminate);
		
		textField = new JTextField();
		textField.setBounds(158, 105, 147, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Airport Name:");
		lblNewLabel.setBounds(72, 108, 90, 13);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton_1 = new JButton("Give Permission To Given Airport");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String airportName = textField.getText();
				if(operations.givePermission(airportName))
					resultLabel.setText("Flights are waiting for to land "+airportName+" are permitted");
				else
					resultLabel.setText("Permission FAIL");
				if(!operations.toFile())
					resultLabel.setText("Reporting to the file FAILED");
				
			}
		});
		btnNewButton_1.setBounds(72, 131, 233, 21);
		contentPane.add(btnNewButton_1);
		

	}
}
