

import javax.swing.JDialog;
import javax.swing.JFrame;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class Upgrades extends JDialog{
	private int buy;
	private int CurrentUpgrade1;
	private int CurrentUpgrade2;
	private JButton buyUpgrade1;
	private JButton buyUpgrade2;
	private JLabel stateUpgrade1;
	private JLabel stateUpgrade2;
	private JLabel change_up1;
	private JLabel change_up2;
	public int updated;
	public int pay;

	public Upgrades(JFrame parentFrame,int mymoney,int up1,int up2) {
		super(parentFrame,true);
		getContentPane().setBackground(new Color(255, 228, 225));
		setBackground(new Color(255, 228, 225));
		setBounds(200, 200, 700, 200);
		setTitle("Upgrades");
		getContentPane().setLayout(null);
		CurrentUpgrade1=up1;
		CurrentUpgrade2=up2;
		String from1=Integer.toString((int)Math.pow(2, up1));
		String to1 =Integer.toString((int)Math.pow(2, up1+1));
		String from2=Integer.toString(2*up2);
		String to2 =Integer.toString(2*(up2+1));
		
		buyUpgrade1 = new JButton("Buy");
		buyUpgrade1.setFont(new Font("Arial", Font.BOLD, 15));
		buyUpgrade1.setBackground(new Color(255, 182, 193));
		buyUpgrade1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("clicked..1");

				if (up1<5 && mymoney>=20*(1+CurrentUpgrade1)) {
					CurrentUpgrade1++;
					pay=20*CurrentUpgrade1;
					updated=1;
					setVisible(false);
					//System.out.println(getUpdated());
				}
				else if (up1<5) {
					Warnings warn = new Warnings(Upgrades.this,1);
					updated=0;
				}
			}
		});
		buyUpgrade1.setBounds(520, 20, 100, 40);
		getContentPane().add(buyUpgrade1);
		
		buyUpgrade2 = new JButton("Buy");
		buyUpgrade2.setFont(new Font("Arial", Font.BOLD, 15));
		buyUpgrade2.setBackground(new Color(255, 182, 193));
		buyUpgrade2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("clicked..2");
				if (up2<3 && mymoney>=100*(1+CurrentUpgrade2)) {
					CurrentUpgrade2++;
					pay=100*CurrentUpgrade2;
					updated=2;					
					setVisible(false);
					//System.out.println(getUpdated());
				}
				else if (up2<3) {
					Warnings warn = new Warnings(Upgrades.this,1);
					updated=0;
				}
			}
		});
		buyUpgrade2.setBounds(520, 90, 100, 40);
		getContentPane().add(buyUpgrade2);
		
		stateUpgrade1 = new JLabel("Upgrade1");
		stateUpgrade1.setFont(new Font("Arial", Font.BOLD, 15));
		if (up1<5) {
			stateUpgrade1.setText("Upgrade Coins per click: "+from1+"->"+to1);
		}
		stateUpgrade1.setBounds(30, 20, 270, 40);
		getContentPane().add(stateUpgrade1);
		
		stateUpgrade2 = new JLabel("Upgrade2");
		stateUpgrade2.setFont(new Font("Arial", Font.BOLD, 15));
		if (up2<3) {
			stateUpgrade2.setText("Upgrade Coins per time: "+from2+"->"+to2);
		}
		stateUpgrade2.setBounds(30, 90, 270, 40);
		getContentPane().add(stateUpgrade2);
		
		change_up1 = new JLabel();
		change_up1.setFont(new Font("Arial", Font.BOLD, 15));
		if (up1==5) {
			change_up1.setText("MAX");
		}
		else
			change_up1.setText((up1+1)*20+" Coins");
		change_up1.setBounds(350, 20, 120, 40);
		getContentPane().add(change_up1);
		
		change_up2 = new JLabel();
		change_up2.setFont(new Font("Arial", Font.BOLD, 15));
		if (up2==3) {
			change_up2.setText("MAX");
		}
		else
			change_up2.setText((up2+1)*100+" Coins");
		change_up2.setBounds(350, 90, 120, 40);
		getContentPane().add(change_up2);

		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	
	
}
