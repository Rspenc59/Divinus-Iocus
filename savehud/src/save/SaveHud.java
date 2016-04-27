package save;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import java.awt.List;

public class SaveHud extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SaveHud frame = new SaveHud();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
		// menu code
		new Data();
	}

	/**
	 * Create the frame.
	 */
	@SuppressWarnings("deprecation")
	public SaveHud() {

		setTitle("Save/Load");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		File folder = new File("Saves");
		File[] listOfFiles = folder.listFiles();

		JLabel text = new JLabel("");
		text.setForeground(Color.GREEN);
		text.setHorizontalAlignment(SwingConstants.CENTER);
		text.setFont(new Font("Tahoma", Font.PLAIN, 25));
		text.setBounds(252, 605, 437, 39);
		contentPane.add(text);

		for (File file : listOfFiles) {
			if (file.isFile()) {
				System.out.println(file.getName());

			}
		}

		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 25));
		textField.setBounds(252, 41, 330, 40);

		contentPane.add(textField);

		textField.setColumns(10);
		setTitle("Save/Load");

		List loadtext = new List();
		JScrollPane scroll = new JScrollPane(loadtext, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setFont(new Font("Tahoma", Font.PLAIN, 25));
		loadtext.setFont(new Font("Tahoma", Font.PLAIN, 25));
		File folder1 = new File("Saves");
		File[] listOfFiles1 = folder1.listFiles();
		loadtext.clear();
		for (File file : listOfFiles1) {
			if (file.isFile()) {

				loadtext.add(file.getName());

			}
		}
		scroll.setBounds(252, 93, 413, 436);
		contentPane.add(scroll);

		Button button_1 = new Button("Save");
		button_1.setFont(new Font("Dialog", Font.PLAIN, 25));
		button_1.setBounds(588, 41, 77, 40);
		button_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// when save button pressed
				String savinput = textField.getText();
				File dir = new File("Saves");
				dir.mkdir();
				FileWriter fw = null;
				try {
					fw = new FileWriter("Saves\\" + savinput);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				PrintWriter pw = new PrintWriter(fw);
				//
				// printing data to save files
				// ~
				pw.println(" hp:");
				pw.println(Data.hp);
				pw.println(" xmap:");
				pw.println(Data.xmap);
				pw.println(" ymap:");
				pw.println(Data.ymap);
				pw.println(" level1:");
				pw.println(Data.level1);
				pw.println(" level2:");
				pw.println(Data.level2);
				pw.println(" level3:");
				pw.println(Data.level3);
				//
				//
				//
				pw.close();
				System.out.println("File Saved");
				text.setText("File Saved");
				text.setForeground(new Color(50, 205, 50));
				// delay
				new java.util.Timer().schedule(new java.util.TimerTask() {
					@Override
					public void run() {
						text.setText("");
					}
				}, 1000);
				File folder1 = new File("Saves");
				File[] listOfFiles1 = folder1.listFiles();
				loadtext.clear();
				for (File file : listOfFiles1) {
					if (file.isFile()) {

						loadtext.add(file.getName());

					}
				}
				textField.setText("");
			}
		});

		contentPane.add(button_1);

		Button button = new Button("Load");
		button.setFont(new Font("Dialog", Font.PLAIN, 25));
		button.setBounds(249, 549, 203, 40);
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// when load button pressed
				String selection = loadtext.getSelectedItem();
				System.out.println();
				System.out.print(selection);
				try {
					FileReader fr = new FileReader("Saves\\" + selection);
					int counter = 0;
					BufferedReader br = new BufferedReader(fr);
					String line;
					Integer lineint;
					Boolean lineboolean;
					while ((line = br.readLine()) != null) {
						// process the line.
						counter++;

						switch (counter) {
						//
						//
						// LOADING DATA (start)
						// (EVERY ODD LINE IN SAVE FILE IS A LABEL. EVEN LINES
						// STORE DATA)
						// ~
						case 2:
							// hp
							lineint = Integer.valueOf(line);
							Data.hp = lineint;
							break;
						case 4:
							// xmap
							lineint = Integer.valueOf(line);
							Data.xmap = lineint;
							break;
						case 6:
							// ymap
							lineint = Integer.valueOf(line);
							Data.ymap = lineint;
							break;
						case 8:
							// level 1
							lineboolean = Boolean.valueOf(line);
							Data.level1 = lineboolean;
							break;
						case 10:
							// level 2
							lineboolean = Boolean.valueOf(line);
							Data.level2 = lineboolean;
							break;
						case 12:
							// level 3
							lineboolean = Boolean.valueOf(line);
							Data.level3 = lineboolean;
							break;
						}
						//
						//
						// LOADING DATA (end)
						//
						//
					}
					br.close();
					//
					// testing data files
					// ~
					System.out.println();
					System.out.println("hp: " + Data.hp);
					System.out.println("xmap: " + Data.xmap);
					System.out.println("ymap: " + Data.ymap);
					System.out.println("level 1: " + Data.level1);
					System.out.println("level 2: " + Data.level2);
					System.out.println("level 3: " + Data.level3);
					//
					// testing data files
					//
					System.out.println("File Loaded");
					text.setText("File Loaded");
					text.setForeground(new Color(50, 205, 50));
					// delay
					new java.util.Timer().schedule(new java.util.TimerTask() {
						@Override
						public void run() {
							text.setText("");
						}
					}, 1000);
				} catch (IOException e) {
					if (selection == null) {
						System.out.print("No File Selected");
						text.setText("No File Selected");
					} else {
						System.out.print("File not found");
						text.setText("File not found");
					}
					text.setForeground(Color.RED);
					// delay
					new java.util.Timer().schedule(new java.util.TimerTask() {
						@Override
						public void run() {
							text.setText("");
						}
					}, 1000);

				}

			}
		});
		contentPane.add(button);

		Button button_2 = new Button("Delete All");
		button_2.setFont(new Font("Dialog", Font.PLAIN, 25));
		button_2.setBounds(779, 605, 200, 40);
		contentPane.add(button_2);

		button_2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// when deleteall pressed
				File index = new File("Saves");
				String[] entries = index.list();
				for (String s : entries) {
					File currentFile = new File(index.getPath(), s);
					currentFile.delete();
				}

				loadtext.clear();
				System.out.println("Deleted All Files");
				text.setText("Deleted All Files");
				text.setForeground(new Color(50, 205, 50));
				// delay
				new java.util.Timer().schedule(new java.util.TimerTask() {
					@Override
					public void run() {
						text.setText("");
					}
				}, 1000);
			}
		});
		Button button_3 = new Button("Delete");
		button_3.setFont(new Font("Dialog", Font.PLAIN, 25));
		button_3.setBounds(462, 549, 203, 40);
		contentPane.add(button_3);
		button_3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// when delete pressed
				String selection = loadtext.getSelectedItem();
				Path path;

				path = Paths.get("Saves\\" + selection);

				try {
					Files.delete(path);
					System.out.println("File Deleted");
					text.setText("File Deleted");
					text.setForeground(new Color(50, 205, 50));
					// delay
					new java.util.Timer().schedule(new java.util.TimerTask() {
						@Override
						public void run() {
							text.setText("");
						}
					}, 1000);
				} catch (NoSuchFileException x) {
					System.err.format("%s: no such" + " file or directory%n", path);
					System.out.println("File Doesn't Exist");
					text.setText("File Doesn't Exist");
					text.setForeground(Color.RED);
					// delay
					new java.util.Timer().schedule(new java.util.TimerTask() {
						@Override
						public void run() {
							text.setText("");
						}
					}, 1000);
				} catch (DirectoryNotEmptyException x) {
					System.err.format("%s not empty%n", path);
					System.out.println("ERROR");
					text.setText("ERROR");
					text.setForeground(Color.RED);
					// delay
					new java.util.Timer().schedule(new java.util.TimerTask() {
						@Override
						public void run() {
							text.setText("");
						}
					}, 1000);
				} catch (IOException x) {
					// File permission problems are caught here.
					System.err.println(x);
					System.out.println("ERROR");
					text.setText("ERROR");
					text.setForeground(Color.RED);
					// delay
					new java.util.Timer().schedule(new java.util.TimerTask() {
						@Override
						public void run() {
							text.setText("");
						}
					}, 1000);
				}

				File folder1 = new File("Saves");
				File[] listOfFiles1 = folder1.listFiles();
				loadtext.clear();
				for (File file : listOfFiles1) {
					if (file.isFile()) {

						loadtext.add(file.getName());

					}
				}
			}

		});
	}
}
