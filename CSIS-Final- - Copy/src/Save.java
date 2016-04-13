package saveTest;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Save
{
	public void save()
	{
		int hp = 100;
		boolean level1 = false;
		boolean level2 = false;
		boolean level3 = false;
		
		//~ask user what save option they want(quick save or normal save)
		boolean QuickSave = false;
		//if quicksave is false user chooses name of save file
		
		Scanner input = new Scanner(System.in);
		System.out.println("Press q for quick save or s for normal save. q/s");
		String qs = input.next();
		if (qs.equals("q"))
		{
			QuickSave = true;
		}
		else
		{
			QuickSave = false;
		}			
			
		if(QuickSave == true)
		{			
			try 
			{
				File dir = new File("Saves");
				dir.mkdir();	
				//~fix saveint changing or settle with one quicksave
				FileWriter fw = new FileWriter("Saves\\quciksave.sav");
				PrintWriter pw = new PrintWriter (fw);
				
				pw.println("hp: "+ hp);
				pw.println("level 1: "+ level1);
				pw.println("level 2: "+ level2);
				pw.println("level 3: "+ level3);
				
				pw.close();
				
				System.out.println("Your game has been saved as quicksave.sav");
		
			} catch (IOException e) 
			{
				System.out.println("ERROR");
				e.printStackTrace();
			}
		}
		else
		{
			try 
			{
				Scanner input1 = new Scanner(System.in);
			    System.out.print("What would you like to save as (no spaces)?\n");
			    String saveString = input1.next();
			    
				File dir = new File("Saves");
				dir.mkdir();
				FileWriter fw = new FileWriter("Saves\\" + saveString + ".sav");
				PrintWriter pw = new PrintWriter (fw);
				
				pw.println("hp: "+ hp);
				pw.println("level 1: "+ level1);
				pw.println("level 2: "+ level2);
				pw.println("level 3: "+ level3);
				
				pw.close();
				
				
				System.out.println("You game has been saved as "+ saveString + ".sav");
				
			} catch (IOException e) 
			{
				System.out.println("ERROR");
				e.printStackTrace();
			}
		}

		
	}
}