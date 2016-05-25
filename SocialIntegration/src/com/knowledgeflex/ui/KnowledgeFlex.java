package com.knowledgeflex.ui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import chrriis.dj.nativeswing.swtimpl.NativeInterface;

import com.knowledgeflex.business.ConnectivitySocket;

public class KnowledgeFlex extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;

	private static final String APP_TITLE = "SocialIntegration";

	private JButton facebook = new JButton("Facebook");
	private JButton google = new JButton("Google");
//	private static JButton linkedin = new JButton("Linked In");
	private static JLabel labelStatus = new JLabel("Status :-");
	private static JLabel labelUserName = new JLabel("Not Logged In");
	private static boolean flag = false;

	ConnectivitySocket connection = null;

	public static void main(String[] args) {
		JFrame.setDefaultLookAndFeelDecorated(true);
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				init();
			}
		});

	}

	public KnowledgeFlex() {
		NativeInterface.initialize();
		NativeInterface.open();
		// set flow layout for the frame
		this.getContentPane().setLayout(new GridLayout(2, 2));
		this.setTitle(APP_TITLE);
		//set action listeners for buttons
		facebook.addActionListener(this);
		google.addActionListener(this);

		//add buttons to the frame
		add(facebook);
		add(google);
//		add(linkedin);
		add(labelStatus);
		add(labelUserName);

		connection = new ConnectivitySocket();
	}

	private static void init() {

		JFrame frame = new KnowledgeFlex();
		frame.setPreferredSize(new Dimension(500, 400));

		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@SuppressWarnings("unused")
	private static void showInfoDialog(String title,String message) {
		JOptionPane.showMessageDialog(null, message, title, JOptionPane.OK_OPTION);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == facebook) {
			System.out.println("Facebook");
			connection.faceBookLogin();
			flag = false;
		} else if(e.getSource() == google) {
			System.out.println("Google");
			connection.googleLogin();
			flag = true;
		} /*else if(e.getSource() == linkedin) {
			System.out.println("LinkedIn");
		}*/
	}

	public static void setUserName(String userName) {
		if(flag){
			labelStatus.setText("Google User :-");
			System.out.println("Google User :- " + userName);
			labelUserName.setText(userName);
//			showInfoDialog("Google","UserName : " + userName);
		} else {
			labelStatus.setText("Facebook User :-");
			System.out.println("Facebook User :- " + userName);
			labelUserName.setText(userName);
//			showInfoDialog("Facebook","UserName : " + userName);
		}
	}
}
