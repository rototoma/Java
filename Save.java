

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class Save {

	public Save(int hamnum, int hunger1, int hunger2, int food, int upgrade1, int upgrade2, int coins) {
		FileOutputStream fileostream;	
		try {
			fileostream = new FileOutputStream ("savefile.txt",false);
			PrintWriter writer = new PrintWriter(fileostream);
			writer.println(hamnum);
			writer.println(hunger1);
			writer.println(hunger2);
			writer.println(food);
			writer.println(upgrade1);
			writer.println(upgrade2);
			writer.println(coins);
			writer.close();
			fileostream.close();
			System.out.println("Saved");
		
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	
	
}
