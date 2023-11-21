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
	
	
	public CookieClicker() {
		createWindow();
		createCookie();
		createCookieCounter();
		
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
		cookie.addActionListener(new CookieHandler());
	
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

	public static void main(String[] args) {
		
		new CookieClicker();
	}
	
	public class CookieHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			counter++;
			counterLabel.setText(counter + " cookies");
		}
		
	}

}
