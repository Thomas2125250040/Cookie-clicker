import static java.awt.Image.SCALE_DEFAULT;  

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Timer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class CookieClicker {
	
	// Window
	JFrame window;
	
	// Cookie
	JPanel cookiePanel;
	JButton cookie;
	
	// Cookie Counter
	JPanel counterPanel;
	JLabel counterLabel, persecLabel;
	int counter;
	
	// Font
	Font font1 = new Font("Comic Sans MS", Font.PLAIN, 32);
	Font font2 = new Font("Comic Sans MS", Font.PLAIN, 15);
	
	// Cursor Button
	JPanel cursorPanel;
	JButton cursor, grandpa, button3, button4;
	int cursorNumber;
	int cursorPrice;

	// Grandpa
	int grandpaNumber;
	int grandpaPrice;
	boolean grandpaActivated;
	
	// Cookie Handler
	CookieHandler cHandler = new CookieHandler();
	
	// Timer
	Timer timer;
	float timerSpeed;
	boolean timerOn;

	// TextArea
	JPanel textAreaPanel;
	JTextArea textArea;
	MouseHandler mHandler = new MouseHandler();
	
	public CookieClicker() {

		timerSpeed=0;
		timerOn=false;
		counter=0;
		cursorNumber=0;
		cursorPrice=10;
		grandpaNumber=0;
		grandpaPrice=50;
		grandpaActivated=false;

		createWindow();
		createCookie();
		createCookieCounter();
		createCookieCursor();
		
		window.setVisible(true);
	}
	
	public void createWindow() {
		
		window = new JFrame("Cookie Clicker");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLayout(null);
		window.getContentPane().setBackground(Color.black);
		window.setSize(800, 600);		
	}
	
	public void createCookie() {
		
		ImageIcon icCookie = new ImageIcon(getClass().getClassLoader().getResource("cookie.png"));
		ImageIcon rzCookie = new ImageIcon(icCookie.getImage().getScaledInstance(200, 200, SCALE_DEFAULT));
		
		cookiePanel = new JPanel();
		cookiePanel.setBounds(100, 220, 200, 200);
		cookiePanel.setBackground(Color.BLACK);
		
		cookie = new JButton(rzCookie);
		cookie.setBackground(Color.black);
		cookie.setFocusPainted(false);
		cookie.setBorder(null);
		cookie.addActionListener(cHandler);
		cookie.setActionCommand("Click");
	
		window.add(cookiePanel);
		cookiePanel.add(cookie);
		
	}
	
	public void createCookieCounter() {
		
		counterPanel = new JPanel();
		counterPanel.setBackground(Color.black);
		counterPanel.setBounds(100, 100, 200, 120);
		counterPanel.setLayout(new GridLayout(2, 1));
		
		counterLabel = new JLabel(counter +" cookies");
		counterLabel.setForeground(Color.white);
		counterLabel.setFont(font1);
		counterPanel.add(counterLabel);
		
		persecLabel = new JLabel("per second : " + timerSpeed);
		persecLabel.setForeground(Color.white);
		persecLabel.setFont(font2);
		counterPanel.add(persecLabel);
		
		window.add(counterPanel);
		
	}
	
	public void createCookieCursor() {
		
		cursorPanel = new JPanel();
		cursorPanel.setBounds(400, 220, 250, 200);
		cursorPanel.setLayout(new GridLayout(4,1));
		
		cursor = new JButton("Cursor");
		cursor.setFocusPainted(false);
		cursor.setFont(font1);
		cursor.addActionListener(cHandler);
		cursor.setActionCommand("Cursor");
		cursor.addMouseListener(mHandler);
		
		grandpa = new JButton("?");
		grandpa.setFocusPainted(false);
		grandpa.setFont(font1);
		grandpa.addActionListener(cHandler);
		grandpa.setActionCommand("Grandpa");
		grandpa.addMouseListener(mHandler);
		
		button3 = new JButton("?");
		button3.setFocusPainted(false);
		button3.setFont(font1);
		button3.addMouseListener(mHandler);
		
		button4 = new JButton("?");
		button4.setFocusPainted(false);
		button4.setFont(font1);
		button4.addMouseListener(mHandler);
		
		cursorPanel.add(cursor);
		cursorPanel.add(grandpa);
		cursorPanel.add(button3);
		cursorPanel.add(button4);
		
		window.add(cursorPanel);

		textAreaPanel = new JPanel();
		textAreaPanel.setBackground(Color.black);
		textAreaPanel.setBounds(400, 120, 250, 80);
		

		textArea = new JTextArea();
		textAreaPanel.add(textArea);
		textArea.setBackground(Color.black);
		textArea.setForeground(Color.white);
		textArea.setBounds(400, 120, 250, 80);
		textArea.setLineWrap(true);
		textArea.setFont(font2);
		textArea.setWrapStyleWord(true);
		textArea.setEditable(false);
		

		window.add(textAreaPanel);
	}
	
	public void setTimer(int speed) {

		timer = new Timer(speed, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				counter++;
				counterLabel.setText(counter + " cookies");

				if(grandpaActivated==false){
					if(counter >= grandpaPrice){
						grandpaActivated=true;
						grandpa.setText("Grandpa");
					}
				}
			}
		});
	}

	public void updateTimer() {
		
		if(timerOn) {
			timer.stop();
		} else {
			timerOn = true;
		}
		float newSpeed = (1/timerSpeed)*1000;
		int newSpeed2 = (int) Math.round(newSpeed);
		
		setTimer(newSpeed2);
		timer.start();
		persecLabel.setText("per second : " + timerSpeed);
	}
	
	public static void main(String[] args) {
		
		new CookieClicker();
	}
	
	public class CookieHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			String command = e.getActionCommand();
			switch(command) {
			case "Click":
				counter++;
				counterLabel.setText(counter + " cookies");
				break;
			case "Cursor":
				
				if (counter >= cursorPrice) {
					counter -= cursorPrice;
					cursorNumber++;
					cursorPrice += 5;
					
					counterLabel.setText(counter + "cookies");
					cursor.setText("Cursor ("+cursorNumber+")");
					textArea.setText("[CursorPrice: "+cursorPrice+"]\n"+"Autoclicks once every 10 seconds");
					
					timerSpeed += 0.1;
					updateTimer();
					break;
				} else {
					textArea.setText("You don't have enough cookies!");
				}
				break;
			case "Grandpa":
				if (counter >= grandpaPrice && grandpaActivated==true) {
						counter -= grandpaPrice;
						grandpaNumber++;
						grandpaPrice += 50;
						
						counterLabel.setText(counter + "cookies");
						grandpa.setText("Grandpa ("+grandpaNumber+")");
						textArea.setText("[GrandpaPrice: "+grandpaPrice+"]\n"+"Each grandpa produce one cookie every second");
						
						timerSpeed += 1;
						updateTimer();
						break;
					} else if (grandpaActivated==false){
						textArea.setText("The item is still locked");
					}
					else {
						textArea.setText("You don't have enough cookies!");
					}
					break;
			}
			
		}
		
	}
	public class MouseHandler implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {}

		@Override
		public void mousePressed(MouseEvent e) {}

		@Override
		public void mouseReleased(MouseEvent e) {}

		@Override
		public void mouseEntered(MouseEvent e) {
			JButton btn = (JButton)e.getSource();

			if (btn==cursor){
				textArea.setText("[CursorPrice: "+cursorPrice+"]\n"+"Autoclicks once every 10 seconds");
			} else if (btn==grandpa){
				if (grandpaActivated==true){
					textArea.setText("[GrandpaPrice: "+grandpaPrice+"]\n"+"Each grandpa produce one cookie every second");
				} else {
					textArea.setText("The item is still locked");
				}
			} else if (btn==button3){
				textArea.setText("The item is still locked");
			} else if (btn==button4){
				textArea.setText("The item is still locked");
			} 
		}

		@Override
		public void mouseExited(MouseEvent e) {}
		}
}
