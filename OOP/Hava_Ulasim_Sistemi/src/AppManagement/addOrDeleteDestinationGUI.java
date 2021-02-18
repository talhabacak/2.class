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
import java.awt.event.ActionEvent;

public class addOrDeleteDestinationGUI extends JFrame {
	private Operations operations;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public void addOrDelete() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					addOrDeleteDestinationGUI frame = new addOrDeleteDestinationGUI(operations);
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
	public addOrDeleteDestinationGUI(Operations operations) {
		this.operations = operations;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 453, 372);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(147, 39, 141, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(147, 68, 141, 19);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(147, 97, 141, 19);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Capital Name:");
		lblNewLabel.setBounds(71, 42, 66, 13);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Airport Name:");
		lblNewLabel_1.setBounds(71, 71, 66, 13);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("New Airport Name:");
		lblNewLabel_2.setBounds(49, 100, 98, 13);
		contentPane.add(lblNewLabel_2);
		
		JLabel resultLabel = new JLabel("Result:");
		resultLabel.setBounds(91, 221, 271, 53);
		contentPane.add(resultLabel);
		
		JButton addDestination = new JButton("Add Destination");
		addDestination.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String destination = textField_1.getText();
				String capital = textField.getText();
				
				if(operations.addDestinationToTheGivenCapital(capital, destination)) {
					resultLabel.setText(destination+" added to "+capital+" succesfully");
				}
				else{
					resultLabel.setText("Adding destination FAILED");
				}
				if(!operations.toFile())
					resultLabel.setText("Reporting to the file FAILED");
			}
		});
		addDestination.setBounds(147, 126, 141, 21);
		contentPane.add(addDestination);
		
		JButton deleteDestination = new JButton("Delete Destination");
		deleteDestination.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String destination = textField_1.getText();
				String capital = textField.getText();
				
				if(operations.deleteDestinationFromTheGivenCapital(capital, destination)) {
					resultLabel.setText(destination+" removed from "+capital+" succesfully");
				}
				else{
					resultLabel.setText("Removing destination FAILED");
				}
				if(!operations.toFile())
					resultLabel.setText("Reporting to the file FAILED");
			}
		});
		deleteDestination.setBounds(147, 157, 141, 21);
		contentPane.add(deleteDestination);
		
		JButton btnNewButton_1 = new JButton("Update Destination");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String destination = textField_1.getText();
				String capital = textField.getText();
				String newDestination = textField_2.getText();
				
				if(operations.updateDestinationOfGivenCapital(capital, destination,newDestination)) {
					resultLabel.setText(destination+" updated as "+newDestination+" succesfully");
				}
				else{
					resultLabel.setText("Updating destination FAILED");
				}
				if(!operations.toFile())
					resultLabel.setText("Reporting to the file FAILED");
				
			}
		});
		btnNewButton_1.setBounds(147, 188, 141, 21);
		contentPane.add(btnNewButton_1);
		
		JButton close = new JButton("Close");
		close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		close.setBounds(180, 284, 85, 21);
		contentPane.add(close);
		
		
		
		

	}

}
