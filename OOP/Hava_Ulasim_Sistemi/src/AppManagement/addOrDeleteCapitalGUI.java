package AppManagement;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class addOrDeleteCapitalGUI extends JFrame {

	private JPanel contentPane;
	private Operations operations;
	private JTextField textField;
	private JTextField textField_1;
	/**
	 * Launch the application.
	 */
	public void addOrDelete(){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					addOrDeleteCapitalGUI frame = new addOrDeleteCapitalGUI(operations);
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
	public addOrDeleteCapitalGUI(Operations op) {
		this.operations = op;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 430, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Continent :");
		lblNewLabel.setBounds(68, 50, 62, 13);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Capital :");
		lblNewLabel_1.setBounds(79, 89, 46, 13);
		contentPane.add(lblNewLabel_1);
		
		
		JLabel resultLabel = new JLabel("Result:");
		resultLabel.setBounds(79, 168, 250, 49);
		contentPane.add(resultLabel);
		
		JButton addCapital = new JButton("Add Capital");
		addCapital.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String continentName = textField.getText();
				String capitalName = textField_1.getText();
				
				if(operations.addCapital(capitalName, continentName)) {
					resultLabel.setText(capitalName+" added successfully");
				}
				else{
					resultLabel.setText("Adding new capital FAILED");
				}
				if(!operations.toFile())
					resultLabel.setText("Reporting to the file FAILED");
			}
		});
		addCapital.setBounds(79, 137, 120, 21);
		contentPane.add(addCapital);
		
		JButton deleteCapital = new JButton("Delete Capital");
		deleteCapital.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String continentName = textField.getText();
				String capitalName = textField_1.getText();
				
				if(operations.deleteCapital(capitalName, continentName)) {
					resultLabel.setText(capitalName+" removed successfully");
				}
				else{
					resultLabel.setText("Removing capital FAILED");
				}
				if(!operations.toFile())
					resultLabel.setText("Reporting to the file FAILED");
				
				
			}
		});
		deleteCapital.setBounds(209, 137, 120, 21);
		contentPane.add(deleteCapital);
		
		textField = new JTextField();
		textField.setBounds(135, 47, 154, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(135, 86, 154, 19);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JButton close = new JButton("Close");
		close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		close.setBounds(163, 212, 85, 21);
		contentPane.add(close);

		
	}
}
