package projektkurs.io;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import projektkurs.Main;
import projektkurs.lib.Images;
import projektkurs.lib.Integers;
import projektkurs.lib.Strings;

/**
 * Menü, wird beim Starten des Spiels aufgerufen. Z.B. um seinen Charakter zu
 * verändern.
 * 
 */
public class Option extends JPanel implements ActionListener, ChangeListener {

	private static JFrame frame;
	private static boolean isFinished = false;

	private static final long serialVersionUID = 1L;

	public static void createAndShowGUI() {

		frame = new JFrame(Strings.NAME);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowListener() {

			@Override
			public void windowActivated(WindowEvent e) {
			}

			@Override
			public void windowClosed(WindowEvent e) {
				isFinished = true;
			}

			@Override
			public void windowClosing(WindowEvent e) {
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
			}

			@Override
			public void windowIconified(WindowEvent e) {
			}

			@Override
			public void windowOpened(WindowEvent e) {
			}
		});

		JComponent contentPane = new Option();
		frame.setContentPane(contentPane);

		frame.setBounds(10, 10, 512, 512);
		frame.setMinimumSize(new Dimension(512, 512));
		frame.setUndecorated(true);

		frame.pack();

		frame.setVisible(true);

	}

	public static boolean isFinished() {
		return isFinished;
	}

	private JLabel charakterBild;

	private final JSlider sliderSightX, sliderSightY;

	private Option() {

		super(new GridLayout(0, 1));

		JPanel charakterAuswahl = new JPanel();

		JPanel buttons = new JPanel(new GridLayout(0, 1));

		JRadioButton b1 = new JRadioButton("Charakter");
		JRadioButton b2 = new JRadioButton("Baum");
		JRadioButton b3 = new JRadioButton("Rasen");
		JRadioButton b4 = new JRadioButton("Wand");

		b1.setActionCommand("charakter");
		b1.setSelected(true);
		b2.setActionCommand("baum");
		b3.setActionCommand("rasen");
		b4.setActionCommand("wand");

		ButtonGroup group = new ButtonGroup();
		group.add(b1);
		group.add(b2);
		group.add(b3);
		group.add(b4);

		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);

		charakterBild = new JLabel(new ImageIcon(Images.defaultCharakter));

		buttons.add(b1);
		buttons.add(b2);
		buttons.add(b3);
		buttons.add(b4);

		charakterAuswahl.add(buttons);
		charakterAuswahl.add(charakterBild);

		charakterAuswahl.setBorder(BorderFactory.createEmptyBorder(20, 20, 20,
				20));

		JPanel sightSettings = new JPanel(new GridLayout(0, 1));

		sliderSightX = new JSlider(SwingConstants.HORIZONTAL, 3, 53, 19);
		sliderSightY = new JSlider(SwingConstants.HORIZONTAL, 3, 53, 19);

		sliderSightX.addChangeListener(this);
		sliderSightX.setMajorTickSpacing(10);
		sliderSightX.setMinorTickSpacing(1);
		sliderSightX.setPaintTicks(true);
		sliderSightX.setPaintLabels(true);

		sliderSightY.addChangeListener(this);
		sliderSightY.setMajorTickSpacing(10);
		sliderSightY.setMinorTickSpacing(1);
		sliderSightY.setPaintTicks(true);
		sliderSightY.setPaintLabels(true);

		sightSettings.add(sliderSightX);
		sightSettings.add(sliderSightY);

		sightSettings
				.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		JPanel specialButtons = new JPanel(new GridLayout(1, 0));

		JButton ok = new JButton("Start Game");
		JButton cancel = new JButton("Cancel");

		ok.addActionListener(this);
		ok.setActionCommand("ok");

		cancel.addActionListener(this);
		cancel.setActionCommand("cancel");

		specialButtons.add(ok);
		specialButtons.add(cancel);

		add(charakterAuswahl);
		add(sightSettings);
		add(specialButtons);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "baum": {
			charakterBild.setIcon(new ImageIcon(Images.baum));
			Images.setCharakterImage(Images.baum);
			break;
		}
		case "rasen": {
			charakterBild.setIcon(new ImageIcon(Images.rasen));
			Images.setCharakterImage(Images.rasen);
			break;
		}
		case "wand": {
			charakterBild.setIcon(new ImageIcon(Images.wand));
			Images.setCharakterImage(Images.wand);
			break;
		}
		case "charakter": {
			charakterBild.setIcon(new ImageIcon(Images.defaultCharakter));
			Images.setCharakterImage(Images.defaultCharakter);
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

		}
		}
	}

	@Override
	public void stateChanged(ChangeEvent e) {

		Integers.changeSight(sliderSightX.getValue(), sliderSightY.getValue());

	}

	private void setFinished() {
		isFinished = true;
	}
}
