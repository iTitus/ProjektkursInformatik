package projektkurs.level.editor;

import java.io.File;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import projektkurs.Main;

public class MainLeveleditor {

	public static int Feldgröße = 32;
	public static int FeldlängeX = 38;
	public static int FeldlängeY = 38;
	private static JFrame LevelEditor = new JFrame();
	private static Icon rasen = new ImageIcon(Main.class.getResource("images"
			+ File.separator + "rasen.png"));

	public static void main(String[] args) {

		// Erstellt wird ein Objekt zum testen
		MainLeveleditor test = new MainLeveleditor();

		// Erstellen eines Frames(Ich hab keine Ahnung aber es klappt;D)
		LevelEditor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		LevelEditor.setLayout(null);

		// erstelle test Button
		JButton button = new JButton();
		button = new JButton();
		button.setBounds(10, 10, 32, 32);

		// Button wird zum Frame hinzugefügt
		// LevelEditor.add(button);
		LevelEditor.add(test.StandartFeld());
		// frame wird Definiert und sichtbar gemachtt
		LevelEditor.setBounds(0, 0, 800, 600);
		LevelEditor.setVisible(true);
	}

	public JButton StandartFeld() {

		JButton button2 = new JButton();
		button2 = new JButton(rasen);
		button2.setBounds(10, 10, 32, 32);

		return button2;
	}

}
