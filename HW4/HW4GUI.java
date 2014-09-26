/*
 * Name: Xudong Song
 * Andrew ID: xudongs
 * Course#: 08-600
 * Homework #3
 * Date 17/SEPT/2014
 */

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JTable;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.Color;


public class HW4GUI extends JFrame {

	HW4Data[] HW = new HW4Data[100];
	
	private JPanel contentPane;
	private JTextField tfDate;
	private JTextField tfDescription;
	private JTextField tfAmount;
	private JTextField tfStatus;
	private JTextArea textArea;

	Date d;
	String desc;
	double amount;
	boolean ifDeposit;
	
	//start
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HW4GUI frame = new HW4GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	//create the frame
	public HW4GUI() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 858, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setBounds(40, 10, 58, 29);
		contentPane.add(lblDate);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setBounds(161, 17, 101, 15);
		contentPane.add(lblDescription);
		
		JLabel lblAmount = new JLabel("Amount");
		lblAmount.setBounds(692, 17, 65, 15);
		contentPane.add(lblAmount);
		
		JLabel lbl$ = new JLabel("$");
		lbl$.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl$.setBounds(662, 43, 20, 15);
		contentPane.add(lbl$);
		
		tfDate = new JTextField();
		tfDate.setBounds(40, 40, 101, 21);
		contentPane.add(tfDate);
		tfDate.setColumns(10);
		
		SimpleDateFormat initialDate = new SimpleDateFormat("MM/dd/yyyy");
		Calendar cal = Calendar.getInstance();
		Date today= cal.getTime();
		tfDate.setText(initialDate.format(today));
		
		tfDescription = new JTextField();
		tfDescription.setColumns(10);
		tfDescription.setBounds(161, 40, 487, 21);
		contentPane.add(tfDescription);
		
		tfAmount = new JTextField();
		tfAmount.setColumns(10);
		tfAmount.setBounds(692, 40, 108, 21);
		contentPane.add(tfAmount);
		
		textArea = new JTextArea();
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
		textArea.setBounds(40, 166, 760, 364);
		contentPane.add(textArea);
		
		JButton btnWriteCheck = new JButton("Write Check");
		btnWriteCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				ifDeposit = false;
				
				//check and get the transaction info
				if(!checkAndGetInfo()){
					return;
				}
				
				//check amount range of check
				if(amount>10000000||amount<0.01){
					tfStatus.setText("Amount must bigger than $1, less than $100,000,000");
					return;
				}
				
				//create transaction instance
				HW[HW4Data.num] = new HW4Data(d, desc, amount*(-1));
				
				//sort the instances
				HW4Data.sort(HW);
				
				//refresh
				refreshArea();
				
			}
		});
		btnWriteCheck.setBounds(221, 71, 170, 34);
		contentPane.add(btnWriteCheck);
		
		JButton btnMakeDeposit = new JButton("Make Deposit");
		btnMakeDeposit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ifDeposit = true;
				
				//check and get the transaction info
				if(!checkAndGetInfo()){
					return;
				}
				
				//check amount range of deposit
				if(amount>10000000||amount<1){
					tfStatus.setText("Amount must bigger than $1, less than $100,000,000");
					return;
				}
				
				//create transaction instance
				HW[HW4Data.num] = new HW4Data(d, desc, amount);
				
				//sort the instances
				HW4Data.sort(HW);
				
				//refresh the text area	
				refreshArea();
			}
		});
		btnMakeDeposit.setBounds(423, 71, 170, 34);
		contentPane.add(btnMakeDeposit);
		
		tfStatus = new JTextField();
		tfStatus.setFont(new Font("Monospaced", Font.BOLD, 12));
		tfStatus.setForeground(Color.RED);
		tfStatus.setBounds(161, 126, 487, 21);
		contentPane.add(tfStatus);
		tfStatus.setColumns(10);
		
		JLabel lblStatus = new JLabel("Status");
		lblStatus.setHorizontalAlignment(SwingConstants.RIGHT);
		lblStatus.setBounds(83, 129, 58, 15);
		contentPane.add(lblStatus);
		
	}
	
	//refresh the text area	
	public void refreshArea(){		
		DecimalFormat printFormat = new DecimalFormat("###,###,##0.00");
		SimpleDateFormat dateformat = new SimpleDateFormat("MM/dd/yyyy");   
		
		String str = "Date        Check#        Description                 Amount            Fee             Balance\n";
		double sumFee = 0;
		double sumBalance = 0;
		
		for(int i=0; i<HW4Data.num; i++){
			
			sumFee += HW[i].getFee();
			sumBalance += HW[i].getAmount();
			
			String tempDate = dateformat.format(HW[i].getDate());
			for(int p=tempDate.length(); p<12; p++){
				tempDate+="\b";
			}
			
			String tempCheckNumber = Integer.toString(HW[i].getCheckNumber());
			if(tempCheckNumber.equals("0")){
				tempCheckNumber = "";
				ifDeposit = true;
			}
			for(int p=tempCheckNumber.length(); p<14; p++){
				tempCheckNumber+="\b";
			}
			
			String tempDescription = HW[i].getDescription();
			for(int p=tempDescription.length(); p<28; p++){
				tempDescription+="\b";
			}
			if(tempDescription.length()>=28)
			{
				tempDescription=tempDescription.substring(0,28);
			}
			
			String tempAmount = printFormat.format(HW[i].getAmount());
			if(ifDeposit){
				tempAmount = "\b" + tempAmount;
			}
			for(int p=tempAmount.length(); p<18; p++){
				tempAmount+="\b";
			}
			
			String tempFee = "-";
			tempFee += printFormat.format(HW[i].getFee());
			for(int p=tempFee.length(); p<16; p++){
				tempFee+="\b";
			}
			
			String tempBalance = printFormat.format(sumBalance - sumFee);
			for(int p=tempBalance.length(); p<10; p++){
				tempBalance+="\b";
			}
			
			str = str + tempDate + tempCheckNumber + tempDescription + tempAmount + tempFee + tempBalance + "\n";
		}
		textArea.setText(str);
		tfDescription.setText("");
		tfAmount.setText("");
		tfStatus.setText("");
	}
	
	public boolean checkAndGetInfo(){
		
		//check and get the transaction info
		try {
			d = new SimpleDateFormat("MM/dd/yyyy").parse(tfDate.getText());
			//tfStatus.setText(d.toString());
		} catch (ParseException e) {
			tfStatus.setText("Wrong format of date!(MM/dd/yyyy)");
			return false;
		}
		
		desc = tfDescription.getText().trim();
		if(desc.equals("")){
			tfStatus.setText("please enter the description");
			return false;
		}
		
		if(tfAmount.getText().equals("")){
			tfStatus.setText("please enter the amount");
			return false;
		}
		try{
			amount = Double.parseDouble(tfAmount.getText());
		}catch(NumberFormatException e){
			tfStatus.setText("the amount must be number");
			return false;
		}
		
		return true;
	}
	
}
