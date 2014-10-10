/*
 * Name: Xudong Song
 * Andrew ID: xudongs
 * Course#: 08-600
 * Homework #5
 * Date OCT/03/2014
 */


package HW5;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;



public class Game implements ActionListener{

	private JFrame frame;
	private JPanel pane;
	private JPanel panel;
	private JPanel panelShow;
	
	private JButton startButton;
	private JLabel timeLabel;
	private JLabel scoreLabel;
	private JTextField timeTextField;
	private JTextField scoreTextField;
	
	private int numLights;
	
	private JButton[] buttons;
	private static boolean ifTimeOver;
	private static int score = 0;


	public static void main(String[] args) {
		int numLights = 100;
		Game window = new Game(numLights);
		window.frame.setVisible(true);
	}


	public Game(int numLights) {

		this.numLights = numLights;
		frame = new JFrame("Whack-a-Mole!!! by xudongs");
		frame.setSize(650, 630);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		pane = new JPanel();
		frame.setContentPane(pane);
		pane.setLayout(new BorderLayout(0, 0));
		
		panel = new JPanel();
		pane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		panelShow = new JPanel();
		pane.add(panelShow, BorderLayout.CENTER);
		frame.setVisible(true);
		
		startButton = new JButton("Start");
		startButton.addActionListener(this);
		panel.add(startButton);
		
		timeLabel = new JLabel("Time Left:");
		panel.add(timeLabel);
		
		timeTextField = new JTextField();
		timeTextField.setHorizontalAlignment(SwingConstants.CENTER);
		timeTextField.setEditable(false);
		panel.add(timeTextField);
		timeTextField.setColumns(10);
		
		scoreLabel = new JLabel("Score:");
		panel.add(scoreLabel);
		
		scoreTextField = new JTextField();
		scoreTextField.setHorizontalAlignment(SwingConstants.CENTER);
		scoreTextField.setEditable(false);
		panel.add(scoreTextField);
		scoreTextField.setColumns(10);
		
		Font font = new Font(Font.MONOSPACED, Font.BOLD, 14);
		buttons = new JButton[numLights];
		for (int i = 0; i < buttons.length; i++) {
			buttons[i] = new JButton("   ");
			buttons[i].setFont(font);
			buttons[i].setBackground(Color.LIGHT_GRAY);
			buttons[i].setOpaque(true);
			buttons[i].setEnabled(false);
			buttons[i].addActionListener(this);
			panelShow.add(buttons[i]);
		}
	}
	
	
	public void actionPerformed(ActionEvent event) {
		//Random random = new Random();
		
		Object source = event.getSource();
		if(source == startButton){
			startButton.setEnabled(false);
			Thread newTimer = new Timer(timeTextField, startButton, buttons, scoreTextField);
			newTimer.start();
			
			Thread t[] = new Thread[buttons.length];
			for (int i = 0; i < buttons.length; i++) {
				t[i] = new Mole(buttons[i]);
				t[i].start();
			}
			
		}
		
		if((source instanceof JButton) && (source != startButton) && (((JButton) source).isEnabled())){
			JButton b = (JButton)source;
			b.setText(":-(");
			b.setBackground(Color.RED);
			b.setEnabled(false);
			score++;
			scoreTextField.setText(Integer.toString(score));
			
		}
	}
	
	private static class Timer extends Thread{
		private JTextField timeTextField;
		private JTextField scoreTextField;
		private JButton startButton;
		private JButton[] buttons;
		
		public Timer(JTextField timeTextField, JButton startButton, JButton[] buttons, JTextField scoreTextField){
			this.timeTextField = timeTextField;
			this.scoreTextField = scoreTextField;
			this.startButton = startButton;
			this.buttons = buttons;
		}
		
		public void run(){
			JTextField tf = timeTextField;
			tf.setText("20");
			
			ifTimeOver = false;
			
			for(int i=20; i>=0; i--){
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				tf.setText(Integer.toString(i));
			}
			try {
				ifTimeOver = true;
				Thread.sleep(5000);
				score = 0;
				scoreTextField.setText("");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			startButton.setEnabled(true);
			timeTextField.setText("");
		}
	}
	
	
	private static class Mole extends Thread{
		private JButton butt;
		private Random random = new Random();
		
		public Mole(JButton butt){
			this.butt = butt;
		}
		
		public void run(){
			while(!ifTimeOver){
				int randomStartChooseTime = random.nextInt(4000);
				int randomStayTime = random.nextInt(3000);
				int randomNextChooseTime = random.nextInt(1000);
				try {
					Thread.sleep(randomStartChooseTime);
					butt.setEnabled(true);
					butt.setText(":-)");
					butt.setBackground(Color.GREEN);
					Thread.sleep(1000+randomStayTime);
					butt.setEnabled(false);
					butt.setText("   ");
					butt.setBackground(Color.LIGHT_GRAY);
					Thread.sleep(2000+randomNextChooseTime);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
		}
	}
}
