package projektkurs.io;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import projektkurs.Main;
import projektkurs.lib.Images;
import projektkurs.lib.Integers;
import projektkurs.lib.Strings;
import projektkurs.util.I18n;
import projektkurs.util.I18n.SupportedLocales;

/**
 * Menü, wird beim Starten des Spiels aufgerufen. Z.B. um seinen Charakter zu
 * verändern.
 */
public class Option extends JPanel implements ActionListener, ChangeListener {

	private static JFrame frame;
	private static boolean isFinished = false;
	private static final long serialVersionUID = 1L;

	public static void createAndShowGUI() {

		frame = new JFrame(Strings.NAME);
		frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				isFinished = true;
			}
		});

		JComponent contentPane = new Option();
		frame.setContentPane(contentPane);

		frame.setBounds(16, 16, 0, 0);
		frame.setMinimumSize(new Dimension(512, 512));
		frame.setUndecorated(true);
		frame.setResizable(false);

		frame.pack();

		frame.setVisible(true);

	}

	public static boolean isFinished() {
		return isFinished;
	}

	private static void setFinished() {
		isFinished = true;
	}

	private final JLabel charakterBild, nameSightX, nameSightY;
	private final JRadioButton defaultCharacter, altCharakter;

	private final JComboBox<SupportedLocales> langBox;

	private final JButton ok, cancel;

	private final JSlider sliderSightX, sliderSightY;

	private Option() {

		super(new GridLayout(0, 1));

		JPanel langChooser = new JPanel(new GridLayout());

		langBox = new JComboBox<SupportedLocales>(I18n.getSupportedLocales());
		langBox.setSelectedIndex(0);
		langBox.setActionCommand("lang");
		langBox.addActionListener(this);

		langChooser.add(langBox);

		langChooser.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		JPanel charakterAuswahl = new JPanel();
		JPanel buttons = new JPanel(new GridLayout(0, 1));

		defaultCharacter = new JRadioButton(
				I18n.getString("button.chooseCharacter.default"));
		altCharakter = new JRadioButton(
				I18n.getString("button.chooseCharacter.altCharacter"));

		defaultCharacter.setActionCommand("charakter");
		defaultCharacter.addActionListener(this);
		defaultCharacter.setSelected(true);
		altCharakter.setActionCommand("altCharakter");
		altCharakter.addActionListener(this);

		ButtonGroup group = new ButtonGroup();
		group.add(defaultCharacter);
		group.add(altCharakter);

		buttons.add(defaultCharacter);
		buttons.add(altCharakter);

		charakterBild = new JLabel(new ImageIcon(Images.defaultCharakter));

		charakterAuswahl.add(buttons);
		charakterAuswahl.add(charakterBild);

		charakterAuswahl.setBorder(BorderFactory.createEmptyBorder(20, 20, 20,
				20));

		JPanel sightSettings = new JPanel(new GridLayout(0, 1));

		sliderSightX = new JSlider(SwingConstants.HORIZONTAL, 3,
				Integers.SIGHT_X, Integers.SIGHT_X);
		sliderSightY = new JSlider(SwingConstants.HORIZONTAL, 3,
				Integers.SIGHT_Y, Integers.SIGHT_Y);

		sliderSightX.addChangeListener(this);
		sliderSightX.setMajorTickSpacing(5);
		sliderSightX.setMinorTickSpacing(1);
		sliderSightX.setPaintTicks(true);
		sliderSightX.setPaintLabels(true);

		sliderSightY.addChangeListener(this);
		sliderSightY.setMajorTickSpacing(5);
		sliderSightY.setMinorTickSpacing(1);
		sliderSightY.setPaintTicks(true);
		sliderSightY.setPaintLabels(true);

		nameSightX = new JLabel(I18n.getString("description.sightX") + ": "
				+ sliderSightX.getValue());
		nameSightY = new JLabel(I18n.getString("description.sightY") + ": "
				+ sliderSightY.getValue());

		sightSettings.add(nameSightX);
		sightSettings.add(sliderSightX);
		sightSettings.add(nameSightY);
		sightSettings.add(sliderSightY);

		sightSettings
				.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		JPanel specialButtons = new JPanel(new GridLayout(1, 0));

		ok = new JButton(I18n.getString("button.start"));
		cancel = new JButton(I18n.getString("button.cancel"));

		ok.addActionListener(this);
		ok.setActionCommand("ok");

		cancel.addActionListener(this);
		cancel.setActionCommand("cancel");

		specialButtons.add(ok);
		specialButtons.add(cancel);

		specialButtons.setBorder(BorderFactory
				.createEmptyBorder(20, 20, 20, 20));

		add(langChooser);
		add(charakterAuswahl);
		add(sightSettings);
		add(specialButtons);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "lang": {
			I18n.changeLocale((SupportedLocales) langBox.getSelectedItem());
			update();
			break;
		}
		case "charakter": {
			charakterBild.setIcon(new ImageIcon(Images.defaultCharakter));
			Images.setCharakterImage(Images.defaultCharakter);
			break;
		}
		case "altCharakter": {
			charakterBild.setIcon(new ImageIcon(Images.redNPC));
			Images.setCharakterImage(Images.redNPC);
			break;
		}
		case "ok": {
			setFinished();
			frame.setVisible(false);
			frame.dispose();
			break;
		}
		case "cancel": {
			Main.exit();
			break;
		}
		default: {
			// NO-OP
		}
		}
	}

	@Override
	public void stateChanged(ChangeEvent e) {

		Integers.changeSight(sliderSightX.getValue(), sliderSightY.getValue());
		nameSightX.setText(I18n.getString("description.sightX") + ": "
				+ sliderSightX.getValue());
		nameSightY.setText(I18n.getString("description.sightY") + ": "
				+ sliderSightY.getValue());

	}

	/**
	 * Interne Methode, um den Namen/die Texte der Buttons zu ändern
	 */
	private void update() {

		defaultCharacter.setText(I18n
				.getString("button.chooseCharacter.default"));
		altCharakter.setText(I18n
				.getString("button.chooseCharacter.altCharacter"));

		nameSightX.setText(I18n.getString("description.sightX") + ": "
				+ sliderSightX.getValue());
		nameSightY.setText(I18n.getString("description.sightY") + ": "
				+ sliderSightY.getValue());

		ok.setText(I18n.getString("button.start"));
		cancel.setText(I18n.getString("button.cancel"));
	}
}
