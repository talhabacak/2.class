package AppManagement;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class addCanceletDelayFlightGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JButton cancelFlight;
	private JLabel resultLabel;
	private JButton close;
	private Operations operations;
	private JTextField textField_9;

	/**
	 * Launch the application.
	 */
	public void addCanUpFlight() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					addCanceletDelayFlightGUI frame = new addCanceletDelayFlightGUI(operations);
					frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
	public addCanceletDelayFlightGUI(Operations operations) {
		this.operations = operations;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 499, 471);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(117, 20, 36, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(117, 49, 96, 19);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(117, 78, 96, 19);
		contentPane.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(117, 107, 96, 19);
		contentPane.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(117, 136, 96, 19);
		contentPane.add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(117, 165, 96, 19);
		contentPane.add(textField_5);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(117, 194, 96, 19);
		contentPane.add(textField_6);
		
		JLabel lblNewLabel = new JLabel("Flight Number");
		lblNewLabel.setBounds(22, 23, 85, 13);
		contentPane.add(lblNewLabel);
		
		JLabel lblDepartureDate = new JLabel("Departure Date");
		lblDepartureDate.setBounds(22, 52, 85, 13);
		contentPane.add(lblDepartureDate);
		
		JLabel lblArrivalDate = new JLabel("Arrival Date");
		lblArrivalDate.setBounds(22, 81, 85, 13);
		contentPane.add(lblArrivalDate);
		
		JLabel lblAircraft = new JLabel("Aircraft Model");
		lblAircraft.setBounds(22, 110, 85, 13);
		contentPane.add(lblAircraft);
		
		JLabel lblAirline = new JLabel("Airline");
		lblAirline.setBounds(22, 139, 62, 13);
		contentPane.add(lblAirline);
		
		JLabel lblFrom = new JLabel("From Airport");
		lblFrom.setBounds(22, 168, 69, 13);
		contentPane.add(lblFrom);
		
		JLabel lblDestination = new JLabel("Destination Airport");
		lblDestination.setBounds(22, 197, 85, 13);
		contentPane.add(lblDestination);
		
		JButton addFlight = new JButton("Add");
		addFlight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int flightNumber;
				String departureDate, arrivalDate, aircraft, airline, from, destination;
				try {
					flightNumber = Integer.parseInt(textField.getText());
					departureDate = textField_1.getText();
					arrivalDate = textField_2.getText();
					aircraft = textField_3.getText();
					airline = textField_4.getText();
					from = textField_5.getText();
					destination = textField_6.getText();
					
					if(operations.addFlight(flightNumber, departureDate, arrivalDate, aircraft, airline, from, destination))
						resultLabel.setText("Addin a new flight successfuly completed");
					else
						resultLabel.setText("Addin a new flight FAILED");
				}catch(Exception e1) {
					resultLabel.setText("Addin a new flight FAILED");
				}
				if(!operations.toFile())
					resultLabel.setText("Reporting to the file FAILED");

			}
		});
		addFlight.setBounds(117, 229, 96, 21);
		contentPane.add(addFlight);
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBounds(319, 52, 96, 19);
		contentPane.add(textField_7);
		
		textField_8 = new JTextField();
		textField_8.setColumns(10);
		textField_8.setBounds(319, 78, 96, 19);
		contentPane.add(textField_8);
		
		lblNewLabel_1 = new JLabel("Airport Name");
		lblNewLabel_1.setBounds(240, 55, 69, 13);
		contentPane.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("Flight Number");
		lblNewLabel_2.setBounds(240, 81, 69, 13);
		contentPane.add(lblNewLabel_2);
		
		cancelFlight = new JButton("Cancel Flight");
		cancelFlight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int flightNumber = Integer.parseInt(textField_8.getText());
					String airportName = textField_7.getText();
					if(operations.cancelFlight(airportName, flightNumber)) {
						resultLabel.setText("The flight" + flightNumber+" cancelled");
					}else {
						resultLabel.setText("Cancel FAIL");
					}
				}catch(Exception e2) {
					resultLabel.setText("Cancel FAIL");
				}
				if(!operations.toFile())
					resultLabel.setText("Reporting to the file FAILED");

				
				
			}
		});
		cancelFlight.setBounds(319, 103, 96, 26);
		contentPane.add(cancelFlight);
		
		resultLabel = new JLabel("Result:");
		resultLabel.setBounds(117, 260, 298, 117);
		contentPane.add(resultLabel);
		
		close = new JButton("close");
		close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		close.setBounds(224, 387, 85, 21);
		contentPane.add(close);
		
		textField_9 = new JTextField();
		textField_9.setBounds(319, 194, 46, 19);
		contentPane.add(textField_9);
		textField_9.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Flight Number");
		lblNewLabel_3.setBounds(240, 197, 85, 13);
		contentPane.add(lblNewLabel_3);
		
		JButton setDelay = new JButton("Set 10mins Delay");
		setDelay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int flightNumber = Integer.parseInt(textField_9.getText());
					if(operations.setDelay10Min(flightNumber))
						resultLabel.setText("Flight"+flightNumber+ "delayed for 10mins succesfully");
					else {
						resultLabel.setText("Delay FAILED");
					}	
				}catch(Exception e2) {
					resultLabel.setText("Delay FAILED");
				}
				if(!operations.toFile())
					resultLabel.setText("Reporting to the file FAILED");
			}
		});
		setDelay.setBounds(285, 229, 130, 21);
		contentPane.add(setDelay);
	}
}
