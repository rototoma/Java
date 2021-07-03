
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

public class Warnings extends JDialog{
	private JLabel Warn;
	public Warnings (JFrame parent, int where) {
		super(parent);
		getContentPane().setBackground(new Color(255, 228, 225));
		Warn = new JLabel("Error!");
		Warn.setFont(new Font("Arial", Font.PLAIN, 13));
		setBounds(500, 250, 200, 100);
		if (where == 1) {
			Warn.setText("    Not enough money!");
		}
		if (where == 2) {
			Warn.setText("    You already have 2 hamsters!");
		}
		
		getContentPane().add(Warn, BorderLayout.CENTER);
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	
	public Warnings(JDialog parent,int where) {
		super(parent);
		getContentPane().setBackground(new Color(255, 228, 225));
		Warn = new JLabel("Error!");
		Warn.setFont(new Font("Arial", Font.PLAIN, 13));
		setBounds(500, 250, 200, 100);
		if (where == 1) {
			Warn.setText("    Not enough money!");
		}
		
		getContentPane().add(Warn, BorderLayout.CENTER);
		
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	
}
