package saveTest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Scanner;

public class Load
{
	public void load()
	{
		System.out.println("The following save files have been located.");
		System.out.println();
		File folder = new File("Saves");
		File[] listOfFiles = folder.listFiles();
		

		for (File file : listOfFiles) {
		    if (file.isFile()) {
		        System.out.println(file.getName());
		        
		    }
		}
		try(Scanner input = new Scanner(System.in))
		{
			System.out.println();
			System.out.println("Enter in text what file you would like to load. \nType \"delete\" to delete ALL of the saved data.");
		    String userload = input.next();
		    if (userload.equals("delete"))
		    {
		    	System.out.println("are you sure you what to delete ALL saved data? y/n");
		    	String del = input.next();
		    	if (del.equals("y")||del.equals("yes")||del.equals("Y")||del.equals("Yes")||del.equals("YES"))
		    	{
		    		File index = new File("Saves");
		    		String[]entries = index.list();
		    		for(String s: entries)
		    		{
		    		    File currentFile = new File(index.getPath(),s);
		    		    currentFile.delete();
		    		}
		    		System.out.println("Data has been deleted");
		    	}
		    	else
		    	{
		    		System.out.println("ok");
		    	}
		    	
		    }
		    else
		    {
			    if(userload.endsWith(".sav"))
			    {
			    	try
					{
						FileReader fr = new FileReader("Saves\\"+ userload);
						BufferedReader br = new BufferedReader(fr);
						
						String data;
						while ((data = br.readLine()) != null)
						{
							System.out.println(data);
						}
						br.close();
					} catch (IOException e)
					{
						//~change to load data and don't print
						System.out.println("File not found");
					}
			    }
			    else
			    {
					try
					{
						FileReader fr = new FileReader("Saves\\"+ userload +".sav");
						BufferedReader br = new BufferedReader(fr);
						
						String data;
						while ((data = br.readLine()) != null)
						{
							System.out.println(data + "\n");
						}
						br.close();
					} catch (IOException e)
					{
						//~change to load data and don't print
						System.out.println("File not found");
					}
			    }	
		    }
	    }    
	}
}