

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;

public class Popup extends JDialog{
	private JLabel Text;
	private JButton PayButton;
	private int doit=0;
	private JButton CancelButton;
	
	public Popup(JFrame parent,String a, String b) {
		super(parent,true);
		getContentPane().setBackground(new Color(255, 228, 225));
		getContentPane().setLayout(null);
		setBounds(300,250,500,90);
		
		Text = new JLabel(a);
		Text.setFont(new Font("Arial", Font.PLAIN, 13));
		Text.setBackground(new Color(255, 192, 203));
		Text.setBounds(30, 10, 200, 30);
		getContentPane().add(Text);
		
		PayButton = new JButton(b);
		PayButton.setFont(new Font("Arial", Font.PLAIN, 11));
		PayButton.setBackground(new Color(255, 182, 193));
		PayButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doit=1;
				setVisible(false);
			}
		});
		PayButton.setBounds(300, 10, 80, 30);
		getContentPane().add(PayButton);
		
		CancelButton = new JButton("Cancel");
		CancelButton.setFont(new Font("Arial", Font.PLAIN, 11));
		CancelButton.setBackground(new Color(255, 182, 193));
		CancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doit=0;
				setVisible(false);
			}
		});
		CancelButton.setBounds(385, 10, 80, 30);
		getContentPane().add(CancelButton);
		setVisible(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}

	public JButton getPayButton() {
		return PayButton;
	}

	public void setPayButton(JButton payButton) {
		PayButton = payButton;
	}

	public int getDoit() {
		return doit;
	}

	public void setDoit(int doit) {
		this.doit = doit;
	}
	


}
