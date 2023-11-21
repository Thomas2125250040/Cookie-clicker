import static java.awt.Image.SCALE_DEFAULT; 

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CookieClicker {
	
	// Window
	JFrame window;
	
	// Cookie
	JPanel cookiePanel;
	JButton cookie;
	
	// Cookie Counter
	JPanel counterPanel;
	JLabel counterLabel, persecLabel;
	int counter=0;
	float persec=0;
	
	// Font
	Font font1 = new Font("Comic Sans MS", Font.PLAIN, 32);
	Font font2 = new Font("Comic Sans MS", Font.PLAIN, 15);
	
	// Cursor Button
	JPanel cursorPanel;
	JButton cursor, grandpa, button3, button4;
	
	// Cookie Handler
	CookieHandler cHandler = new CookieHandler();
	
	public CookieClicker() {
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
		
		persecLabel = new JLabel("per second : " + persec);
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
		
		grandpa = new JButton("Grandpa");
		grandpa.setFocusPainted(false);
		grandpa.setFont(font1);
		
		button3 = new JButton("?");
		button3.setFocusPainted(false);
		button3.setFont(font1);
		
		button4 = new JButton("?");
		button4.setFocusPainted(false);
		button4.setFont(font1);
		
		cursorPanel.add(cursor);
		cursorPanel.add(grandpa);
		cursorPanel.add(button3);
		cursorPanel.add(button4);
		
		window.add(cursorPanel);
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
				
			}
			
		}
		
	}

}
