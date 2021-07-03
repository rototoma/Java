
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;

public class Main extends JFrame {

	private JPanel contentPane;
	private JMenuBar menuBar;
	private JMenu Setting;
	private JMenuItem Setting_Save;
	private JMenu Store;
	private JMenuItem Store_Food1;
	private JMenuItem Store_Food2;
	private JMenuItem Store_Upgrade;
	private JMenuItem Store_NewHam;
	private JButton clicker;
	private JLabel Moneynow;
	private JButton Foodnow;
	private JLabel Hamimg1;
	private JLabel Hamimg2;
	private JProgressBar Hungerbar1;
	private JProgressBar Hungerbar2;
	private JLabel Wheel;
	private JLabel Groundimg;
	private JLabel Hungry1;
	private JLabel Hungry2;

	private Coin Coins;
	private static int[] upgrade = { 0, 0 };
	private static int[] gauge = { 100, 100 };
	private static int stop = 1;
	private JLabel Heart1;
	private JLabel Heart2;
	private static int food = 10;
	private static int numhamster = 1;
	private static int money = 0;
	private static ArrayList<Integer> savelist = new ArrayList();
	private int ex = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				FileInputStream filestream;	// checks if there's a save data
				try {
					filestream = new FileInputStream("savefile.txt");	// if there's a save data, read them and put the data in the savelist
					Scanner sc = new Scanner(filestream);
					while (sc.hasNext()) {
						savelist.add(sc.nextInt());
						sc.nextLine();
					}
					filestream.close();
					sc.close();
				} catch (IOException e1) {								// if there isn't a save data, check stop=0 
					System.out.println("No saveslot");
					stop = 0;
				}
				try {
					if (stop == 1) {									// if there was a save data, set the data with the saved ones
						numhamster = savelist.get(0);
						gauge[0] = savelist.get(1);
						gauge[1] = savelist.get(2);
						food = savelist.get(3);
						upgrade[0] = savelist.get(4);
						upgrade[1] = savelist.get(5);
						money = savelist.get(6);
					}
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void Timerfunc(JProgressBar bar, int i) {					// multithreading part needed to make the HungerBar of each hamster decrease
		SwingWorker worker = new SwingWorker<Boolean, Integer>() {

			@Override
			protected Boolean doInBackground() throws Exception {
				// TODO Auto-generated method stub
				while (true) {
					Hamster hamster = new Hamster(gauge[i]);
					Thread newThread = new Thread(hamster); // make new Thread
					newThread.run();
					gauge[i] = hamster.getHunger();
					bar.setValue(gauge[i]);
					newThread.sleep(100);
					if (ex == 1) {										// if the gameover state occurs, the progressbar should stop
						break;
					}
					if (gauge[i] == 0) {								// if the Hunger gauge became 0, Game Over
						ex = 1;
						Gameover gameover = new Gameover(Main.this);
						break;
					}
					if (gauge[i] < 80) {								// when the hamster is not happy
						if (i == 0) {
							Heart1.setVisible(false);
						} else if (i == 1)
							Heart2.setVisible(false);
					}
					if (gauge[i] < 50) {								// when the hamster is hungry
						if (i == 0) {
							Hungry1.setVisible(true);
						} else if (i == 1)
							Hungry2.setVisible(true);
					} else {
						if (i == 0) {
							Hungry1.setVisible(false);
						} else if (i == 1)
							Hungry2.setVisible(false);
					}
				}

				return true;
			}
		};
		worker.execute();
	}

	public void upgrade2timer(int up2) {								// gives free coins every second
		SwingWorker worker2 = new SwingWorker<Boolean, Integer>() {

			@Override
			protected Boolean doInBackground() throws Exception {
				while (true) {
					if (ex == 1) {										// if the gameover state occurs, the timer should stop
						break;
					}
					Thread newThread = new Thread();
					newThread.run();
					Coins.setAdd(2 * up2);								// the amount of free coins per second differs from upgraded amount
					String setmoney = Integer.toString(Coins.getCurrentCoin());
					Moneynow.setText(setmoney + " Coins");				// increase the amount of money i have
					if (upgrade[1] > up2) {								// if the user made another upgrade, stops to make change
						break;
					}
					newThread.sleep(1000);
				}
				return true;
			}
		};
		worker2.execute();
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		Coins = new Coin(money);										// to make save data maintained
		setTitle("Hamster");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 500);

		menuBar = new JMenuBar();
		menuBar.setBackground(new Color(255, 235, 205));
		setJMenuBar(menuBar);

		Setting = new JMenu("Settings");
		Setting.setFont(new Font("Arial", Font.PLAIN, 20));
		Setting.setBackground(new Color(255, 250, 250));
		menuBar.add(Setting);

		Setting_Save = new JMenuItem("Save");
		Setting_Save.setFont(new Font("Arial", Font.PLAIN, 13));
		Setting_Save.setBackground(new Color(255, 250, 250));
		Setting_Save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				// if user wants save
				Popup popp = new Popup(Main.this, "Overwrites last save. Save?", "Save");	// Popup class : yes/no questions
				
				if (popp.getDoit() == 1) {								// if the user pressed 'agree'
					Save savefunc = new Save(numhamster, gauge[0], gauge[1], food, upgrade[0], upgrade[1],
							Coins.getCurrentCoin());										// Save current state
				}
			}
		});
		Setting.add(Setting_Save);

		Store = new JMenu("Store");
		Store.setFont(new Font("Arial", Font.PLAIN, 20));
		menuBar.add(Store);

		Store_Food1 = new JMenuItem("Buy 10 Food");
		Store_Food1.setFont(new Font("Arial", Font.PLAIN, 13));
		Store_Food1.setBackground(new Color(255, 250, 250));
		Store_Food1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Popup pop = new Popup(Main.this, "Buy 10 Food : 50 Coins.", "Buy");			//Popup class : yes/no questions
				if (pop.getDoit() == 1 && Coins.getCurrentCoin() < 50) {					// if the user pressed 'agree', but not enough money
					Warnings warn = new Warnings(Main.this, 1);			// Show warning
				} else if (pop.getDoit() == 1) {						// if the user pressed 'agree', and enough money
					Coins.setPay(50);									// pay the money
					food += 10;											// get the food
					Foodnow.setText(food + " Hamster Food");			// change current food amount
					String setmoney = Integer.toString(Coins.getCurrentCoin());
					Moneynow.setText(setmoney + " Coins");				// change current coins
				}
			}
		});
		Store.add(Store_Food1);

		Store_Food2 = new JMenuItem("Buy 100 Food");
		Store_Food2.setFont(new Font("Arial", Font.PLAIN, 13));
		Store_Food2.setBackground(new Color(255, 250, 250));
		Store_Food2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Popup pop = new Popup(Main.this, "Buy 100 Food : 500 Coins.", "Buy");		// same as upper, but not 10 foods ; 100 foods
				if (pop.getDoit() == 1 && Coins.getCurrentCoin() < 500) {
					Warnings warn = new Warnings(Main.this, 1);
				} else if (pop.getDoit() == 1) {
					Coins.setPay(500);
					food += 100;
					Foodnow.setText(food + " Hamster Food");
					String setmoney = Integer.toString(Coins.getCurrentCoin());
					Moneynow.setText(setmoney + " Coins");
				}
			}
		});
		Store.add(Store_Food2);

		Store_Upgrade = new JMenuItem("Upgrades");
		Store_Upgrade.setFont(new Font("Arial", Font.PLAIN, 13));
		Store_Upgrade.setBackground(new Color(255, 250, 250));
		Store_Upgrade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Upgrades upgr = new Upgrades(Main.this, Coins.getCurrentCoin(), upgrade[0], upgrade[1]);	// show available upgrades

				System.out.println(upgr.updated);
				if (upgr.updated == 1) {											// 257~262 if the user did upgrade1, change money/click
					System.out.println("Updated!1");
					upgrade[0]++;
					Coins.setPay(upgr.pay);
					String setmoney = Integer.toString(Coins.getCurrentCoin());
					Moneynow.setText(setmoney + " Coins");
				} else if (upgr.updated == 2) {										// 2563~272 if the user did upgrade2, change money/time
					System.out.println("Updated!2");				
					upgrade[1]++;
					Coins.setPay(upgr.pay);
					String setmoney = Integer.toString(Coins.getCurrentCoin());
					Moneynow.setText(setmoney + " Coins");
					upgrade2timer(upgrade[1]);

				}
			}
		});
		Store.add(Store_Upgrade);

		Store_NewHam = new JMenuItem("New Hamster");
		Store_NewHam.setFont(new Font("Arial", Font.PLAIN, 13));
		Store_NewHam.setBackground(new Color(255, 250, 250));
		Store_NewHam.addActionListener(new ActionListener() {					// 279 ~296 : get a new hamster
			public void actionPerformed(ActionEvent e) {
				Popup pop = new Popup(Main.this, "A new hamster : 1000 Coins", "Okay");
				if (numhamster == 2) {											// if you already have two hamsters
					Warnings warn = new Warnings(Main.this, 2);					// you cannot get. warning
				} else if (pop.getDoit() == 1 && Coins.getCurrentCoin() < 1000) {	// if you don't have enough money. warning
					Warnings warn = new Warnings(Main.this, 1);
				} else if (pop.getDoit() == 1) {								// 286~295 : you have enough money. pay 1000 coins and get a new hamster
					Coins.setPay(1000);
					numhamster++;
					Hamimg2.setVisible(true);
					Hungerbar2.setVisible(true);
					String setmoney = Integer.toString(Coins.getCurrentCoin());
					Moneynow.setText(setmoney + " Coins");
					Timerfunc(Hungerbar2, 1);
				}
			}
		});
		Store.add(Store_NewHam);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 250, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		clicker = new JButton("CLICK!");
		clicker.setFont(new Font("Arial", Font.BOLD, 20));
		clicker.setBackground(new Color(255, 228, 181));
		clicker.addActionListener(new ActionListener() {					// Clicker button! it makes money per every click
			public void actionPerformed(ActionEvent e) {
				Coins.setAdd((int) Math.pow(2, upgrade[0]));
				String setmoney = Integer.toString(Coins.getCurrentCoin());
				Moneynow.setText(setmoney + " Coins");
			}
		});
		clicker.setBounds(300, 50, 100, 50);
		contentPane.add(clicker);

		Moneynow = new JLabel(Coins.getCurrentCoin() + " Coins");
		Moneynow.setHorizontalAlignment(SwingConstants.CENTER);
		Moneynow.setFont(new Font("Arial", Font.BOLD, 17));
		Moneynow.setBackground(new Color(255, 182, 193));
		Moneynow.setBounds(450, 55, 110, 40);
		Moneynow.setOpaque(true);
		contentPane.add(Moneynow);

		Foodnow = new JButton(food + " Hamster Food");
		Foodnow.setFont(new Font("Arial", Font.BOLD, 17));
		Foodnow.setBackground(new Color(255, 228, 181));
		Foodnow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (food < numhamster) {										// not enough food, do nothing

				} else {														// 332~ 346: enough food, feed the hamsters (make hunger gauge +=20 )
					food = food - numhamster;
					Heart1.setVisible(true);
					if (numhamster == 2)
						Heart2.setVisible(true);
					Foodnow.setText(food + " Hamster Food");					// give food
					for (int k = 0; k < 2; k++) {
						if (gauge[k] > 80)
							gauge[k] = 100;
						else
							gauge[k] += 20;
					}
				}

			}
		});
		Foodnow.setBounds(600, 55, 200, 40);
		contentPane.add(Foodnow);

		Hamimg1 = new JLabel("");
		Hamimg1.setBounds(100, 230, 130, 130);
		ImageIcon ham1 = new ImageIcon(Main.class.getResource("/image/Hamsterdot.png"));	// get image. the images are from the 'image' folder
		Image temp1 = ham1.getImage();
		Image hambasic1 = temp1.getScaledInstance(130, 130, Image.SCALE_DEFAULT);			// cut image
		ImageIcon ham1fin = new ImageIcon(hambasic1);
		Hamimg1.setIcon(ham1fin);															// set the label have the image
		contentPane.add(Hamimg1);

		Hamimg2 = new JLabel("");
		Hamimg2.setBounds(350, 230, 130, 130);
		ImageIcon ham2 = new ImageIcon(Main.class.getResource("/image/Hamsterdot2.png"));	// same as upper
		Image temp2 = ham2.getImage();
		Image hambasic2 = temp2.getScaledInstance(130, 130, Image.SCALE_DEFAULT);
		ImageIcon ham2fin = new ImageIcon(hambasic2);
		Hamimg2.setIcon(ham2fin);
		Hamimg2.setVisible(false);
		contentPane.add(Hamimg2);

		Hungerbar1 = new JProgressBar();													// make progressbar, initially have 100%
		Hungerbar1.setFont(new Font("Arial", Font.PLAIN, 12));
		Hungerbar1.setForeground(new Color(255, 160, 122));
		Hungerbar1.setBackground(new Color(255, 228, 181));
		Hungerbar1.setStringPainted(true);
		Hungerbar1.setBounds(80, 200, 150, 13);
		Hungerbar1.setValue(100);
		contentPane.add(Hungerbar1);

		Hungerbar2 = new JProgressBar();													// same as upper,
		Hungerbar2.setFont(new Font("Arial", Font.PLAIN, 12));								// except that it is available after buying the second hamster
		Hungerbar2.setForeground(new Color(255, 160, 122));
		Hungerbar2.setBackground(new Color(255, 228, 181));
		Hungerbar2.setStringPainted(true);
		Hungerbar2.setBounds(340, 200, 150, 13);
		Hungerbar2.setValue(100);
		Hungerbar2.setVisible(false);
		contentPane.add(Hungerbar2);

		if (numhamster == 2) {																// if there's a new hamster, make the new one available
			Hamimg2.setVisible(true);
			Hungerbar2.setVisible(true);
			Timerfunc(Hungerbar2, 1);
		}

		Heart1 = new JLabel("¢¾");
		Heart1.setFont(new Font("Arial", Font.BOLD, 12));
		Heart1.setBounds(197, 250, 10, 15);
		Heart1.setVisible(false);
		contentPane.add(Heart1);

		Heart2 = new JLabel("¢¾");
		Heart2.setFont(new Font("Arial", Font.BOLD, 12));
		Heart2.setBounds(470, 250, 10, 15);
		Heart2.setVisible(false);
		contentPane.add(Heart2);

		Wheel = new JLabel("");
		Wheel.setBounds(630, 200, 170, 170);
		ImageIcon wheel = new ImageIcon(Main.class.getResource("/image/wheel.png"));		// just image
		Image temp3 = wheel.getImage();
		Image temp33 = temp3.getScaledInstance(170, 170, Image.SCALE_DEFAULT);
		ImageIcon wheelfin = new ImageIcon(temp33);
		Wheel.setIcon(wheelfin);
		Wheel.setVisible(true);
		contentPane.add(Wheel);

		Groundimg = new JLabel("");
		Groundimg.setBounds(0, 320, 900, 120);
		ImageIcon grou = new ImageIcon(Main.class.getResource("/image/ground.png"));		// just image
		Image temp4 = grou.getImage();
		Image temp44 = temp4.getScaledInstance(900, 100, Image.SCALE_DEFAULT);
		ImageIcon groundfin = new ImageIcon(temp44);
		Groundimg.setIcon(groundfin);
		Groundimg.setVisible(true);
		contentPane.add(Groundimg);

		Hungry1 = new JLabel("<<<");
		Hungry1.setFont(new Font("Arial", Font.BOLD, 12));
		Hungry1.setBounds(197, 266, 50, 15);
		Hungry1.setVisible(false);
		contentPane.add(Hungry1);

		Hungry2 = new JLabel("<<<");
		Hungry2.setFont(new Font("Arial", Font.BOLD, 12));
		Hungry2.setBounds(470, 266, 50, 15);
		Hungry2.setVisible(false);
		contentPane.add(Hungry2);

		Timerfunc(Hungerbar1, 0);															// run the hungerbar of the first hamster
		if (upgrade[1] > 0) {																// if the user bought upgrade2, give coins per second
			upgrade2timer(upgrade[1]);
		}
	}

}
