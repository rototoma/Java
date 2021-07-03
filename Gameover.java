

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;

public class Gameover extends JDialog {
	private JLabel Text;
	private JButton Exitbutton;

	public Gameover(JFrame parent) {
		super(parent, true);
		getContentPane().setFont(new Font("Arial", Font.PLAIN, 14));
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setAlwaysOnTop(true);
		getContentPane().setBackground(new Color(220, 220, 220));
		getContentPane().setLayout(null);
		setBounds(300, 250, 600, 100);

		Text = new JLabel("Game over. Your hamster(s) died of starvation.TT");
		Text.setBounds(10, 15, 430, 30);
		Text.setHorizontalAlignment(SwingConstants.CENTER);
		Text.setFont(new Font("Arial", Font.PLAIN, 17));
		Text.setBackground(new Color(255, 255, 255));
		getContentPane().add(Text);

		Exitbutton = new JButton("Exit");
		Exitbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File file = new File("savefile.txt");
				if (file.exists()) {
					if (file.delete()) {
						System.out.println("del");
					} else {
						System.out.println("noo");
					}
				}

				System.exit(1);
			}
		});
		Exitbutton.setFont(new Font("Arial", Font.PLAIN, 15));
		Exitbutton.setBackground(new Color(169, 169, 169));
		getContentPane().add(Exitbutton);
		Exitbutton.setBounds(450, 15, 60, 30);
		setVisible(true);

	}
}
