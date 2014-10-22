package projektkurs.level.editor;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import projektkurs.Main;

public class main {

	public static int FeldlaengeX = 38;
	public static int FeldlaengeY = 38;
	public static int Feldgroeße = 32;
	public int positionX = 0;
	public int positionY = 0;
	public static Icon rasen = new ImageIcon(
			Main.class.getResource("images/rasen.png"));
	public static JFrame LevelEditor = new JFrame();

	public static void main(String args[]) {

		// Erstellt wird ein Objekt zum testen
		main test = new main();

		// Erastellen eines Frames(Ich hab keine Ahnung aber es klappt;D)
		LevelEditor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		LevelEditor.setLayout(null);

		test.macheStandardFeld();

		// frame wird Definiert und sichtbar gemachtt
		LevelEditor.setBounds(0, 0, 512, 512);
		LevelEditor.setVisible(true);
	}

	public void macheStandardFeld() {

		JButton[][] buttons = new JButton[FeldlaengeX][FeldlaengeY];
		int itest = 0;
		int i2test = 0;
		for (int i = 0; i < buttons.length; i++) {
			itest = i;
			for (int i2 = 0; i2 < buttons[i].length; i2++) {

				i2test = i2;
				buttons[i][i2] = new JButton(rasen);
				LevelEditor.add(buttons[i][i2]);
				buttons[i][i2].setBounds(positionX, positionY, Feldgroeße,
						Feldgroeße);

				positionY = positionY + Feldgroeße;
			}

			positionY = 0;
			positionX = positionX + Feldgroeße;
		}

	}
}
