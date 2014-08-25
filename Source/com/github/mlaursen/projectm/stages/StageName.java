/**
 * 
 */
package com.github.mlaursen.projectm.stages;

import com.github.mlaursen.projectm.ScreenPosition;

/**
 * @author mlaursen
 *
 */
public enum StageName {
	BATTLEFIELD,
	FINAL_DESTINATION,
	DELFINO_PLAZA,
	DRACULAS_CASTLE,
	METAL_CAVERN,
	TEMPLE,
	YOSHIS_STORY,
	PEACHS_CASTLE,
	RUMBLE_FALLS,
	HYRULE_CASTLE,
	PIRATE_SHIP,
	NORFAIR,
	JUNGLE_JAPES,
	ONETT,
	FRIGATE_ORPHEON,
	YOSHIS_ISLAND,
	HALBERD,
	LYLAT_CRUISE,
	POKEMON_STADIUM2,
	DREAMLAND,
	RAINBOW_CRUISE,
	SAFFRON_CITY,
	PORT_TOWN_AERO_DIVE,
	CASTLE_SIEGE,
	WARIOWARE_INC,
	DISTANT_PLANET,
	CORNERIA,
	BIG_BLUE,
	SMASHVILLE,
	FOURSIDE,
	SUMMIT,
	SKYWORLD,
	KONGO_JUNGLE,
	BRINSTAR,
	POKEMON_STADIUM,
	FOUNTAIN_OF_DREAMS,
	FLAT_ZONE_2,
	PICTO_CHAT,
	SKYLOFT,
	SHADOW_MOSES_ISLAND,
	CUSTOM_STAGES,
	GREEN_HILL_ZONE;
	
	public ScreenPosition getPosition() {
		int row;
		int x = this.ordinal();
		if(x <= YOSHIS_STORY.ordinal()) {
			row = 0;
		}
		else if(x <= ONETT.ordinal()) {
			row = 1;
		}
		else if(x <= RAINBOW_CRUISE.ordinal()) {
			row = 2;
		}
		else if(x <= BIG_BLUE.ordinal()) {
			row = 3;
		}
		else if(x <= POKEMON_STADIUM.ordinal()) {
			row = 4;
		}
		else if(x <= CUSTOM_STAGES.ordinal()) {
			row = 5;
		}
		else {
			row = 6;
		}
		int col = row == 6 ? 0 : x % 7;
		if(row == 5) {
			col = x - POKEMON_STADIUM.ordinal() - 1;
		}
		return new StageScreenPosition(row, col);
	}
	
	public String pretty() {
		String[] split = this.name().split("_");
		String s = "";
		for(String sp : split) {
			sp = sp.toLowerCase();
			s += sp.substring(0, 1).toUpperCase() + sp.substring(1) + " ";
		}
		return s.trim();
	}
}
