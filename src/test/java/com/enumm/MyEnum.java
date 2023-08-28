package com.enumm;

public class MyEnum {
	  public enum Units {
	        KG("kg", "kgs"),
	        GM("gm", "gms"),
	        LTR("ltr", "ltrs"),
	        ML("ml"),
	        INCH("inch", "inches"),
	        FOOT("foot", "feet"),
	        CM("cm", "cms"),
	        METRE("metre", "metres"),
	        WATT("watt", "watts"),
	        CAPSULE("capsule", "capsules"),
	        TABLET("tablet", "tablets"),
	        SACHET("sachet", "sachets"),
	        PACK("pack", "packs"),
	        BOX("box", "boxes"),
	        CARTRIDGE("cartridge", "cartridges"),
	        BOTTLE("bottle", "bottles"),
	        BASKET("basket", "baskets"),
	        BUNCH("bunch", "bunches"),
	        PULL("pull", "pulls"),
	        CONE("cone", "cones"),
	        CUBE("cube", "cubes"),
	        LEAF("leaf", "leaves"),
	        WIPE("wipe", "wipes"),
	        PAD("pad", "pads"),
	        PANT("pant", "pants"),
	        ROLL("roll", "rolls"),
	        SERVING("serving", "servings"),
	        SHEET("sheet", "sheets"),
	        SLICE("slice", "slices"),
	        TIN("tin", "tins"),
	        BAG("bag", "bags"),
	        CUP("cup", "cups"),
	        PAGE("page", "pages"),
	        PAPER("paper", "papers"),
	        PALLET("pallet", "pallets"),
	        SEED("seed", "seeds"),
	        STICK("stick", "sticks"),
	        DAY("day", "days"),
	        BLADE("blade", "blades"),
	        CHEW("chew", "chews"),
	        BEAD("bead", "beads"),
	        PIECE("piece", "pieces"),
	        SACK("sack", "sacks"),
	        DOZEN("dozen"),
	        CAN("can", "cans"),
	        JAR("jar", "jars"),
	        PAIR("pair", "pairs");

	        private final String[] labels;

	        Units(String... labels) {
	            this.labels = labels;
	        }

	        public String getSingular() {
	            return labels[0];
	        }

	        public String getPlural() {
	            return labels.length > 1 ? labels[1] : null;
	        }
	    }
}
