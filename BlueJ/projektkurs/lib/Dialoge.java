package projektkurs.lib;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import projektkurs.dialog.Dialog;
import projektkurs.dialog.DialogPart;
import projektkurs.item.ItemStack;
import projektkurs.util.Init;
import projektkurs.util.Logger;
import projektkurs.util.Pair;
import projektkurs.util.ReflectionUtil;

/**
 * Alle Dialoge.
 */
public final class Dialoge {

	/**
	 * Die Mappings.
	 */
	public static final HashMap<String, Dialog> MAPPINGS = new HashMap<String, Dialog>();
	public static Dialog CS_LVmFrau;
	public static Dialog LVmFaehrmann;
	public static Dialog LVmFaehrmannOne;
	public static Dialog LVmFaehrmannTwo;
	public static Dialog LVmFischer;
	public static Dialog LVmFischerOne;
	public static Dialog LVmFrau;
	public static Dialog LVmFrauOne;
	public static Dialog LVmFrauThree;
	public static Dialog LVmFrauTwo;
	public static Dialog LVmHexerzirkel;
	public static Dialog LVmHexerzirkelOne;
	public static Dialog LVmHexerzirkelTwo;
	public static Dialog LVmJungeAmWegesrand;
	public static Dialog LVmJungeAmWegesrandOne;
	public static Dialog LVmJungeAmWegesrandThree;
	public static Dialog LVmJungeAmWegesrandTwo;
	public static Dialog LVmNest;
	public static Dialog LVmNestOne;
	public static Dialog LVmSchranke;
	/*
	 * public static Dialog LVmKaugummi; public static Dialog LVmMesser; public static Dialog LVmNetz; public static Dialog LVmStein; public static Dialog LVmTeddy; public static Dialog LVmYoyo;
     */
	public static Dialog LVmTrashCan;
	/**
	 * Test-Dialog.
	 */
	public static Dialog test;

	/**
	 * Nicht instanziierbar.
	 */
	private Dialoge() {
	}

	/**
	 * Das Pair, das alle Dialoge enthaelt.
	 *
	 * @return Pair
	 */
	public static Pair<String, List<String>> getPair() {
		return new Pair<String, List<String>>("info.dialogs", new ArrayList<String>(MAPPINGS.keySet()));
	}

	/**
	 * Initialisiert alle Dialoge.
	 */
	@Init
	public static void init() {

		CS_LVmFrau = new Dialog("CS_LVmFrau", new DialogPart("CS_LVmFrauNull", 14));
		registerMapping(CS_LVmFrau);

		LVmFaehrmann = new Dialog("LVmFaehrmann", new DialogPart("LVmFaehrmannNull", 1, 0, 4));
		registerMapping(LVmFaehrmann);

		LVmFaehrmannOne = new Dialog("LVmFaehrmannOne", new DialogPart("LVmFaehrmannOneNull", 0, 1, 2));
		registerMapping(LVmFaehrmannOne);

		LVmFaehrmannTwo = new Dialog("LVmFaehramnnTwo", new DialogPart("LVmFaehrmannTwoNull", 1 << 19, 1 << 18, 2).setEndAction(ReflectionUtil.getMethod(Scripts.class, "removeItem", ItemStack.class), new ItemStack(Items.gramophoneItem)));
		registerMapping(LVmFaehrmannTwo);

		LVmJungeAmWegesrand = new Dialog("LVmJungeAmWegesrand", new DialogPart("LVmJungeAmWegesrandNull", 0b10, 0, 2));
		registerMapping(LVmJungeAmWegesrand);

		LVmJungeAmWegesrandOne = new Dialog("LVmJungeAmWegesrandOne", new DialogPart("LVmJungeAmWegesrandOneNull", 1 << 16, 1 << 13 | 1 << 12 | 1 << 14, 4).setEndAction(ReflectionUtil.getMethod(Scripts.class, "scriptJunge")));
		registerMapping(LVmJungeAmWegesrandOne);

		LVmJungeAmWegesrandTwo = new Dialog("LVmJungeAmWegesrandTwo", new DialogPart("LVmJungeAmWegesrandTwoNull", 1 << 17, 1 << 16, 1));
		registerMapping(LVmJungeAmWegesrandTwo);

		LVmJungeAmWegesrandThree = new Dialog("LVmJungeAmWegesrandThree", new DialogPart("LVmJungeAmWegesrandThreeNull", 1 << 17, 1 << 17, 1));
		registerMapping(LVmJungeAmWegesrandThree);

		LVmFrau = new Dialog("LVmFrau", new DialogPart("LVmFrauNull", 0b100, 1, 3), new DialogPart("LVmFrauEins", 0b1000, 1, 2), new DialogPart("LVmFrauZwei", 1 << 4, 1, 2), new DialogPart("LVmFrauDrei", 1 << 5, 1, 1).setEndAction(ReflectionUtil.getMethod(Scripts.class, "cutscenetwo")));
		registerMapping(LVmFrau);

		LVmFrauOne = new Dialog("LVmFrauOne", new DialogPart("LVmFrauOneNull", 0, 0b100 | 0b1000 | 1 << 4 | 1 << 5, 3));
		registerMapping(LVmFrauOne);

		LVmFrauTwo = new Dialog("LVmFrauTwo", new DialogPart("LVmFrauTwoNull", 1 << 15, 1 << 12 | 1 << 13 | 1 << 14, 9).setEndAction(ReflectionUtil.getMethod(Scripts.class, "scriptWoman")));
		registerMapping(LVmFrauTwo);

		LVmFrauThree = new Dialog("LVmFrauThree", new DialogPart("LVmFrauThreeNull", 1 << 19, 1 << 18, 1).setEndAction(ReflectionUtil.getMethod(Scripts.class, "cutsceneThree")));
		registerMapping(LVmFrauThree);

		LVmFischer = new Dialog("LVmFischer", new DialogPart("LVmFischerNull", 1 << 6, 1 | 2 | 4, 6), new DialogPart("LVmFischerEins", 1 << 7, 1 | 2 | 4, 5), new DialogPart("LVmFischerZwei", 1 << 8, 1 | 2 | 4, 5), new DialogPart("LVmFischerDrei", 1 << 9, 1 | 2 | 4, 2), new DialogPart("LVmFischerVier", 1 << 10,
				1 | 2 | 4, 2), new DialogPart("LVmFischerFuenf", 1 << 11, 1 | 2 | 4, 1).setEndAction(ReflectionUtil.getMethod(Scripts.class, "scriptFisher")));
		registerMapping(LVmFischer);

		LVmFischerOne = new Dialog("LVmFischerOne", new DialogPart("LVmFischerOneNull", 1));
		registerMapping(LVmFischerOne);

		LVmHexerzirkel = new Dialog("LVmHexerzirkel", new DialogPart("LVmHexerzirkelNull", 1 << 12, 1 << 6 | 1 << 7 | 1 << 8 | 1 << 9 | 1 << 10 | 1 << 11, 2), new DialogPart("LVmHexerzirkelEins", 1 << 13, 1 << 6 | 1 << 7 | 1 << 8 | 1 << 9 | 1 << 10 | 1 << 11, 4), new DialogPart("LVmHexerzirkelZwei", 1 << 14, 1 << 6
				| 1 << 7 | 1 << 8 | 1 << 9 | 1 << 10 | 1 << 11, 7));
		registerMapping(LVmHexerzirkel);

		LVmHexerzirkelOne = new Dialog("LVmHexerzirkelOne", new DialogPart("LVmHexerzirkelOneNull", 1 << 17, 1 << 12 | 1 << 13 | 1 << 14, 2));
		registerMapping(LVmHexerzirkelOne);

		LVmHexerzirkelTwo = new Dialog("LVmHexerzirkelTwo", new DialogPart("LVmHexerzirkelTwoNull", 1 << 18, 1 << 15 | 1 << 16 | 1 << 17, 1).setEndAction(ReflectionUtil.getMethod(Scripts.class, "scriptWitchCoul")));
		registerMapping(LVmHexerzirkelTwo);

		LVmTrashCan = new Dialog("LVmTrashCan", new DialogPart("LVmTrashCanNull", 1).setEndAction(ReflectionUtil.getMethod(Scripts.class, "scriptThrashCan")));
		registerMapping(LVmTrashCan);

		// Gleichzusetzten mit neccValue von Hexerzirkel, da DialogValue Sich im Laufe ver�ndert // Testweise, da VilleCar sich auf Stumm gestellt hatte
		LVmNest = new Dialog("LVmNest", new DialogPart("LVmNestNull", 1));
		registerMapping(LVmNest);

		LVmNestOne = new Dialog("LVmNestOne", new DialogPart("LVmNestOneNull", 1 << 17, 1 << 16, 1).setEndAction(ReflectionUtil.getMethod(Scripts.class, "scriptNest")));
		registerMapping(LVmNestOne);

		LVmSchranke = new Dialog("LVmSchranke", new DialogPart("LVmSchrankeNull", 1));
		registerMapping(LVmSchranke);
		// LVmStein = new Dialog("LVmStein", new DialogPart("LVmSteinNull",1)); registerMapping(LVmStein);
		// LVmTeddy = new Dialog("LVmTeddy", new DialogPart("LVmTeddyNull",1)); registerMapping(LVmTeddy);
		// LVmYoyo = new Dialog("LVmYoyo", new DialogPart("LVmYoyoNull",1)); registerMapping(LVmYoyo);
		// LVmKaugummi = new Dialog("LVmKaugummi", new DialogPart("LVmKaugummiNull",1)); registerMapping(LVmKaugummi);
		// LVmNetz = new Dialog("LVmNetz", new DialogPart("LVmNetzNull",1)); registerMapping(LVmNetz);
		// LVmMesser = new Dialog("LVmMesser", new DialogPart("LVmMesserNull",1)); registerMapping(LVmMesser);

	}

	/**
	 * Registriert ein Mapping.
	 *
	 * @param d Dialog
	 */
	private static void registerMapping(Dialog d) {
		if (d != null && !MAPPINGS.containsKey(d.getName())) {
			MAPPINGS.put(d.getName(), d);
		} else {
			Logger.warn("Unable to register Dialog", d);
			throw new IllegalArgumentException("Unable to register Dialog " + d);
		}
	}

}
