package projektkurs.world.builder;

import projektkurs.entity.EntityBoomBarrier;
import projektkurs.entity.EntityBoy;
import projektkurs.entity.EntityDoor;
import projektkurs.entity.EntityFerry;
import projektkurs.entity.EntityFerryhouse;
import projektkurs.entity.EntityFerryman;
import projektkurs.entity.EntityGramophone;
import projektkurs.entity.EntityItem;
import projektkurs.entity.EntityNest;
import projektkurs.entity.EntityRedNPC;
import projektkurs.entity.EntityTrashCan;
import projektkurs.entity.EntityVilleCar;
import projektkurs.entity.EntityWitchCauldron;
import projektkurs.entity.Entityhouse_1_4x3;
import projektkurs.entity.Entityhouse_2_4x3;
import projektkurs.entity.Entityhouse_3b_3x4;
import projektkurs.inventory.Inventory;
import projektkurs.item.ItemStack;
import projektkurs.lib.Integers;
import projektkurs.lib.Items;
import projektkurs.lib.Raster;
import projektkurs.lib.Scripts;
import projektkurs.raster.AbstractRaster;
import projektkurs.raster.extra.ExtraInformation;
import projektkurs.raster.extra.ExtraInformationChest;
import projektkurs.raster.extra.ExtraInformationDoor;
import projektkurs.story.StoryManager;
import projektkurs.story.trigger.AbstractTrigger;
import projektkurs.story.trigger.AreaTrigger;
import projektkurs.story.trigger.CombinedAndTrigger;
import projektkurs.story.trigger.InventoryHasItemStackTrigger;
import projektkurs.story.trigger.PosTrigger;
import projektkurs.util.Direction;
import projektkurs.util.MapUtil;
import projektkurs.util.MathUtil;
import projektkurs.util.ReflectionUtil;
import projektkurs.world.Spielfeld;

/**
 * Spielfeld-Generierung.
 */
public final class MapBuilder {

    /**
     * Level 0 - Spielfeld 0.
     *
     * @param map
     *            Spielfeld
     */
    
	public static void generateAndPopulateLevel2Map0(Spielfeld map) {
		int inttemp1;
		int inttemp2;
		
		for (int y = 0; y < map.getMapSizeY(); y++) {
            for (int x = 0; x < map.getMapSizeX(); x++) {
                map.setRasterAt(x, y, Raster.grass_2);
            }
		}
		//Häuser 
		for (int y = 0; y < map.getMapSizeY(); y++) {
            for (int x = 0; x < 3; x++) {
                map.setRasterAt(x, y, Raster.street_asphalt);
            }
		}
		for (int y = 0; y < map.getMapSizeY(); y++) {
            for (int x = map.getMapSizeX()-2; x < map.getMapSizeX(); x++) {
                map.setRasterAt(x, y, Raster.street_asphalt);
            }
		}
		for (int y = 0; y < 3; y++) {
            for (int x = 4; x < map.getMapSizeX(); x++) {
                map.setRasterAt(x, y, Raster.street_asphalt);
            }
		}
		
		for (int y = map.getMapSizeY()-3; y < map.getMapSizeY(); y++) {
            for (int x = 4; x < map.getMapSizeX()-4; x++) {
                map.setRasterAt(x, y, Raster.street_asphalt);
            }
		}
		for (int x = 0; x < map.getMapSizeX(); x=x+4) {
			map.spawn(new Entityhouse_1_4x3(map, x, 0));
        }
		
		for (int x = 0; x < 40; x=x+4) {
			map.spawn(new Entityhouse_2_4x3(map, x, map.getMapSizeY()-3));
        }
		
		for (int x = 44; x < map.getMapSizeX(); x=x+4) {
			map.spawn(new Entityhouse_2_4x3(map, x, map.getMapSizeY()-3));
        }
		for (int y = 3; y < map.getMapSizeY()-4; y=y+4) {
			map.spawn(new Entityhouse_3b_3x4(map, 0, y));
        }
		
		for (int y = 3; y < map.getMapSizeY()-4; y=y+4) {
			map.spawn(new Entityhouse_3b_3x4(map, map.getMapSizeX()-2, y));
        }
		
		//See
            for (int y = 25; y < 50; y++) {
                for (int x = 32; x < 48; x++) {
                    map.setRasterAt(x, y, Raster.water);
                }
            }
        
            for (int y = 23; y < 25; y++) {
                for (int x = 35; x < 47; x++) {
                    map.setRasterAt(x, y, Raster.water);
                }
            }
            
            for (int y = 50; y < 52; y++) {
                for (int x = 37; x < 44; x++) {
                    map.setRasterAt(x, y, Raster.water);
                }
            }
            
            for (int y = 29; y < 47; y++) {
               
                    map.setRasterAt(31, y, Raster.water);
                
            }
            
            for (int y = 28; y < 43; y++) {
                for (int x = 48; x < 50; x++) {
                    map.setRasterAt(x, y, Raster.water);
                }
            }
            for (int y = 33; y < 40; y++) {
                map.setRasterAt(30, y, Raster.water);
            }
            for (int x = 38; x < 42; x++) {
                map.setRasterAt(x,22, Raster.water);
            }
            map.setRasterAt(48,27, Raster.water);

            //Bäume
            inttemp1 = 4;
            //Wald oben
            for (int y = 6; y < 20; y = y + 2) {
                for (int x = 6; x < 64; x = x + 2) {
                	inttemp2 = MathUtil.randomInt(9);
                    if (inttemp1 < inttemp2) {
                    AbstractRaster[] temp = MapUtil.getRanTree();
                    setTree(x, y, temp[0], temp[1], temp[2], temp[3], map);
                    }
                }
            }
            //wald links
            for (int y = 20; y < 64; y = y + 2) {
                for (int x = 6; x < 28; x = x + 2) {
                	inttemp2 = MathUtil.randomInt(9);
                    if (inttemp1 < inttemp2) {
                    AbstractRaster[] temp = MapUtil.getRanTree();
                    setTree(x, y, temp[0], temp[1], temp[2], temp[3], map);
                    }
                }
            }
            //Wald rechts
            for (int y = 20; y < 64; y = y + 2) {
                for (int x = 52; x < 64; x = x + 2) {
                	inttemp2 = MathUtil.randomInt(8);
                    if (inttemp1 < inttemp2) {
                    AbstractRaster[] temp = MapUtil.getRanTree();
                    setTree(x, y, temp[0], temp[1], temp[2], temp[3], map);
                    }
                }
            }
            //Wald unten
            for (int y = 52; y < 64; y = y + 2) {
                for (int x = 32; x < 48; x = x + 2) {
                	inttemp2 = MathUtil.randomInt(6);
                    if (inttemp1 < inttemp2) {
                    AbstractRaster[] temp = MapUtil.getRanTree();
                    setTree(x, y, temp[0], temp[1], temp[2], temp[3], map);
                    }
                }
            }
            
          //Weg
            //um den See
            for (int y = 20; y < map.getMapSizeY()-3; y++) {
                for (int x = 29; x < 31; x++) {
                    map.setRasterAt(x, y, Raster.cobbles_1);
                }
    		}
            
            for (int y = 20; y < map.getMapSizeY()-3; y++) {
                for (int x = 49; x < 51; x++) {
                    map.setRasterAt(x, y, Raster.cobbles_1);
                }
    		}
            for (int y = 20; y < 22; y++) {
                for (int x = 31; x < 49; x++) {
                    map.setRasterAt(x, y, Raster.cobbles_1);
                }
    		}
            //Weg unten
            for (int y = map.getMapSizeY()-5; y < map.getMapSizeY()-3; y++) {
                for (int x = 3; x < map.getMapSizeX()-2; x++) {
                    map.setRasterAt(x, y, Raster.cobbles_1);
                }
    		}
            //Weg links
            
            
            for (int y = 2; y <map.getMapSizeY()-5 ; y++) {
                for (int x = 3; x < 5; x++) {
                    map.setRasterAt(x, y, Raster.cobbles_1);
                }
    		}
            //Weg rechts
            for (int y = 2; y <map.getMapSizeY()-5 ; y++) {
                for (int x = map.getMapSizeX()-4; x < map.getMapSizeX()-2; x++) {
                    map.setRasterAt(x, y, Raster.cobbles_1);
                }
    		}
            //Weg oben
            for (int y = 3; y < 4; y++) {
                for (int x = 4; x < map.getMapSizeX()-4; x++) {
                    map.setRasterAt(x, y, Raster.cobbles_1);
                }
    		}
            //Weg Ausgang
            
            for (int y = map.getMapSizeY() - 3; y < map.getMapSizeY(); y++) {
                for (int x = 40; x < 44; x++) {
                    map.setRasterAt(x, y, Raster.cobbles_1);
                }
    		}
            
            setStreet_2(40, 60, map);
            setStreet_3(40,55,map);
	StoryManager st = map.getStoryManager();
	AbstractTrigger area = new AreaTrigger(40, 69, 4, 1);
	//st.registerTrigger(area, ReflectionUtil.getMethod(Scripts.class, "switchMap", Integer.TYPE), 1);
	//st.registerTrigger(new AreaTrigger(40, 68, 4, 1), ReflectionUtil.getMethod(Scripts.class, "setSpawn", Integer.TYPE, Integer.TYPE ), 41, 68);

    st.registerTrigger(area,ReflectionUtil.getMethod(Scripts.class, "switchMap", Integer.TYPE,AbstractTrigger.class, AbstractTrigger.class, Integer.TYPE, Integer.TYPE ), 4, new AreaTrigger(40, 68, 4, 1),new AreaTrigger(40, 69, 4, 1), 41, 68);

	}
	
	
	
	
	public static void generateAndPopulateLevel2Map1(Spielfeld map) {
		int inttemp1;
		int inttemp2;
		//Holzboden
		for (int y = 0; y < map.getMapSizeY(); y++) {
            for (int x = 0; x < map.getMapSizeX(); x++) {
                map.setRasterAt(x, y, Raster.grass_2);
            }
		}
		
		for (int y = map.getMapSizeY()-8; y < map.getMapSizeY(); y++) {
            for (int x = 15; x < 17; x++) {
                map.setRasterAt(x, y, Raster.floor_12);
            }
		}
		for (int y = 5; y < map.getMapSizeY()-8; y++) {
            for (int x = 0; x < map.getMapSizeX(); x++) {
                map.setRasterAt(x, y, Raster.floor_12);
            }
		}
		for (int y = 0; y < 5; y++) {
            for (int x = 26; x < 28; x++) {
                map.setRasterAt(x, y, Raster.floor_12);
            }
		}
		
	//Stühle vor der Bar
		for (int x = 1; x < 13; x = x+2){
			map.setRasterAt(x, 5, Raster.chair_5);
		}
	//Stühle um Tisch
		//links
		map.setRasterAt(7, 11, Raster.chair_2);
		map.setRasterAt(5, 13, Raster.chair_1);
		map.setRasterAt(9, 13, Raster.chair_3);
		map.setRasterAt(7, 15, Raster.chair_7);

		//rechts
		map.setRasterAt(16, 9, Raster.chair_2);
		map.setRasterAt(14, 11, Raster.chair_1);
		map.setRasterAt(18, 11, Raster.chair_3);
		map.setRasterAt(16, 13, Raster.chair_7);
	//Trigger
		StoryManager st = map.getStoryManager();
		AbstractTrigger area = new AreaTrigger(15, 29, 2, 1);
//		st.registerTrigger(area, ReflectionUtil.getMethod(Scripts.class, "switchMap", Integer.TYPE), 0);
		//st.registerTrigger(new AreaTrigger(14, 27, 2, 1), ReflectionUtil.getMethod(Scripts.class, "setSpawn", Integer.TYPE, Integer.TYPE ), 14, 26);
		//st.registerTrigger(new AreaTrigger(15, 29, 2, 1),ReflectionUtil.getMethod(Scripts.class, "setSpawn", Integer.TYPE, Integer.TYPE), 15, 28);

		st.registerTrigger(area,ReflectionUtil.getMethod(Scripts.class, "switchMap", Integer.TYPE,AbstractTrigger.class, AbstractTrigger.class, Integer.TYPE, Integer.TYPE ),0, new AreaTrigger(15, 28, 2, 1),new AreaTrigger(15, 29, 2, 1), 15, 28);
		//st.registerTrigger(new AreaTrigger(26, 0, 2, 1),ReflectionUtil.getMethod(Scripts.class, "setSpawn", Integer.TYPE, Integer.TYPE), 26, 1);

	    st.registerTrigger(new AreaTrigger(26, 0, 2, 1),ReflectionUtil.getMethod(Scripts.class, "switchMap", Integer.TYPE,AbstractTrigger.class, AbstractTrigger.class, Integer.TYPE, Integer.TYPE ),2, new AreaTrigger(26, 1, 2, 1),new AreaTrigger(26, 0, 2, 1), 26, 1);
	    
	
	}
	public static void generateAndPopulateLevel2Map2(Spielfeld map){
		for (int y = 0; y < map.getMapSizeY(); y++) {
            for (int x = 0; x < map.getMapSizeX(); x++) {
                map.setRasterAt(x, y, Raster.grass_2);
            }
		}
	StoryManager st = map.getStoryManager();
    st.registerTrigger(new AreaTrigger(0, 10, 1, 2),ReflectionUtil.getMethod(Scripts.class, "switchMap", Integer.TYPE,AbstractTrigger.class, AbstractTrigger.class, Integer.TYPE, Integer.TYPE ),1, new AreaTrigger(1, 10, 1, 2),new AreaTrigger(0, 10, 1, 2), 1, 10);

	}
public static void generateAndPopulateLevel2Map3(Spielfeld map) {
		
		//Rasen
		for (int y = 0; y < map.getMapSizeY(); y++) {
            for (int x = 0; x < map.getMapSizeX(); x++) {
                map.setRasterAt(x, y, Raster.grass_2);
            }
		}
		
		//Bäume
		for (int x = 0; x < 80; x = x + 2) {
            for (int y = 0; y < 32; y = y + 2) {
                AbstractRaster[] temp = MapUtil.getRanTree();
                setTree(x, y, temp[0], temp[1], temp[2], temp[3], map);
            }
        }
		
		int EBE;
		int EBZ;
		EBE = 2;
        for (int x = 0; x < 80; x = x + 2) {
            for (int y = 0; y < 32; y = y + 2) {
            	EBZ = MathUtil.randomInt(2);
                if (EBE == EBZ) {
                    map.setRasterAt(x, y, Raster.grass_2);
                    map.setRasterAt(x + 1, y, Raster.grass_2);
                    map.setRasterAt(x, y + 1, Raster.grass_2);
                    map.setRasterAt(x + 1, y + 1, Raster.grass_2);
                }
            }
        }
        for (int x = 0; x < map.getMapSizeX(); x = x + 4) {
            for (int y = 0; y < map.getMapSizeY(); y = y + 4) {
            	EBZ = MathUtil.randomInt(2);
                if (EBE == EBZ) {
                    for (int xx = x; xx < x + 4; xx++) {
                        for (int yy = y; yy < y + 4; yy++) {
                            map.setRasterAt(xx, yy, Raster.grass_2);
                        }
                    }
                }
            }
        }
        
		
		//Wasser
		for (int y = 70; y < map.getMapSizeY(); y++) {
            for (int x = 0; x < map.getMapSizeX(); x++) {
                map.setRasterAt(x, y, Raster.water);
            }
		}
		
		//Strasse
		for (int y = 69; y > 39; y--) {
			for (int x = 80; x < 84; x++) {
				map.setRasterAt(x, y, Raster.cobbles_1);
			}
		}
		
		for (int ESint = 0; ESint < 36; ESint = ESint +8){
			setStreet(80, ESint, map);
		}
		
		map.setRasterAt(80, 39, Raster.cobbles_1);
		map.setRasterAt(81, 39, Raster.cobbles_1);
		map.setRasterAt(83, 39, Raster.cobbles_1);
		
		
		for (int x = 45; x < 80; x++) {
			map.setRasterAt(x, 32, Raster.cobbles_2);
		}
		for (int x = 45; x < 80; x++) {
			map.setRasterAt(x, 33, Raster.grass);
		}
		for (int x = 45; x < 80; x++) {
			map.setRasterAt(x, 34, Raster.cobbles_2);
		}
		map.setRasterAt(80, 32, Raster.cobbles_2);
		map.setRasterAt(79, 31, Raster.cobbles_2);
		map.setRasterAt(79, 34, Raster.cobbles_2);
		map.setRasterAt(78, 34, Raster.cobbles_2);
		map.setRasterAt(79, 35, Raster.cobbles_2);
		map.setRasterAt(78, 31, Raster.grass_2);
		map.setRasterAt(78, 30, Raster.grass_2);
		map.setRasterAt(79, 30, Raster.grass_2);
		
		
		//Haus
		for (int y = 16; y < 21; y++) {
			for (int x = 88 ; x < 92; x++) {
				map.setRasterAt(x, y, Raster.floor_7);
			}
		}
		
		for (int y = 18; y < 19; y++) {
			for (int x = 84 ; x < 88; x++) {
				map.setRasterAt(x, y, Raster.floor_10);
			}
		}
		
		//Mühle
		for (int y = 30; y < 35; y++) {
			for (int x = 40 ; x < 45; x++) {
				map.setRasterAt(x, y, Raster.floor_11);
			}
		}
		
		
		//Anlegeplatz
		for (int y = 74; y > 65; y--) {
			for (int x = 72 ; x < 92; x++) {
				map.setRasterAt(x, y, Raster.cobbles_2);
			}
		}
		
		map.setRasterAt(84, 65, Raster.cobbles_2);
		map.setRasterAt(79, 64, Raster.cobbles_2);
		map.setRasterAt(79, 65, Raster.cobbles_2);
		map.setRasterAt(72, 66, Raster.grass_2);
		map.setRasterAt(73, 66, Raster.grass_2);
		map.setRasterAt(72, 67, Raster.grass_2);
		map.setRasterAt(91, 66, Raster.grass_2);
		map.setRasterAt(90, 66, Raster.grass_2);
		map.setRasterAt(91, 67, Raster.grass_2);
		
		for (int y = 74; y > 70; y--) {
			for (int x = 78 ; x < 86; x++) {
				map.setRasterAt(x, y, Raster.water);
			}
		}
		
		int EZint; 
		for (int y=70 ; y<75; y++){
			for (int x = 72 ; x <92; x++){
				EZint = MathUtil.randomInt(3);
				if(EZint == 1){
					map.setRasterAt(x, y, Raster.water);
				}
			}
		}
		
		
		
		map.setRasterAt(81, 66, Raster.cobbles_1);
		map.setRasterAt(82, 66, Raster.cobbles_1);
		map.setRasterAt(83, 66, Raster.cobbles_1);
		map.setRasterAt(80, 67, Raster.cobbles_1);
		map.setRasterAt(81, 67, Raster.cobbles_1);
		map.setRasterAt(82, 68, Raster.cobbles_1);
		
		//Fischstand
		for (int y = 51; y > 47; y--) {
			for (int x = 87 ; x < 89; x++) {
				map.setRasterAt(x, y, Raster.floor_5);
			}
		}
		
		for (int y = 50; y > 48; y--) {
			for (int x = 84 ; x < 87; x++) {
				map.setRasterAt(x, y, Raster.cobbles_2);
			}
		}
		
	}
public static void generateAndPopulateLevel2Map4(Spielfeld map) {
	//Hintergrund
	for (int y = 0; y < map.getMapSizeY(); y++) {
        for (int x = 0; x < map.getMapSizeX(); x++) {
            map.setRasterAt(x, y, Raster.street_asphalt);
        }
    }
	for (int y = 96; y > 67; y = y - 4){
		setStreet_2( 48, y, map);
	}
	for (int x = 44; x > 16; x = x - 4){
		setStreet_3( x, 64, map);
	}
	for (int x = 52; x < 80; x = x + 4){
		setStreet_3( x, 64, map);
	}
	//Kreuzung Hafen
	 for (int y = 65; y < 68; y++) {
         for (int x = 48; x < 52; x++) {
             map.setRasterAt(x, y, Raster.street_m_e2_3);
         }
     }
	 for (int x = 48; x < 52; x++) {
         map.setRasterAt(x, 64, Raster.street_m_wage_e2_1);
     }
	 //mehr Straßen
	 for (int y = 28; y < 61; y = y + 4){
			setStreet_2( 16, y, map);
		}
	 for (int y = 28; y < 61; y = y + 4){
			setStreet_2( 80, y, map);
		}
	 //Kreuzung links unten 
	 for (int y = 64; y < 67; y++) {
         for (int x = 17; x < 20; x++) {
             map.setRasterAt(x, y, Raster.street_m_e2_3);
         }
     }
	 for (int x = 17; x < 20; x++) {
         map.setRasterAt(x, 67, Raster.street_m_wage_e2_2);
     }
	 for (int y = 64; y < 67;y ++){
         map.setRasterAt(16, y, Raster.street_m_e2_1);
		}
	 
	 //Kreuzung rechts unten
	 for (int y = 64; y < 67; y++) {
         for (int x = 80; x < 83; x++) {
             map.setRasterAt(x, y, Raster.street_m_e2_3);
         }
     }
	 for (int x = 80; x < 83; x++) {
         map.setRasterAt(x, 67, Raster.street_m_wage_e2_2);
     }

	 for (int y = 64; y < 67;y ++){
         map.setRasterAt(83, y, Raster.street_m_e2_4);
		}
//Straßen zum Marktplatz
	 for (int x = 20; x < 29; x = x + 4){
			setStreet_3( x, 24, map);
		}
	 for (int x = 72; x < 80; x = x + 4){
			setStreet_3( x, 24, map);
		}
	 //Kreuzung zum Marktplatz links
	 for (int y = 24; y < 28; y++) {
         for (int x = 17; x < 20; x++) {
             map.setRasterAt(x, y, Raster.street_m_e2_3);
         }
     }
	 for (int y = 24; y < 28;y ++){
         map.setRasterAt(16, y, Raster.street_m_e2_1);
		}
	 //Kreuzung zum Marktplatz rechts
	 for (int y = 24; y < 28; y++) {
         for (int x = 80; x < 83; x++) {
             map.setRasterAt(x, y, Raster.street_m_e2_3);
         }
     }
	 for (int y = 24; y < 28;y ++){
         map.setRasterAt(83, y, Raster.street_m_e2_4);
		}
	 //Marktplatz
	 for (int y = 20; y < 62; y++) {
	        for (int x = 32; x < 72; x++) {
	            map.setRasterAt(x, y, Raster.cobbles_1);
	        }
	    }
}
	public static void generateAndPopulateLevel0Map0(Spielfeld map) {

        // RASEN!
        for (int y = 0; y < map.getMapSizeY(); y++) {
            for (int x = 0; x < map.getMapSizeX(); x++) {
                map.setRasterAt(x, y, Raster.grass);
            }
        }

        // BAEUME!
        for (int i = 0; i < MathUtil.randomIntInc(25, 75); i++) {
            map.setRasterAt(MathUtil.nextInt(map.getMapSizeX()), MathUtil.nextInt(map.getMapSizeY()), Raster.tree);
        }

        // KISTEN!
        for (int i = 0; i < MathUtil.randomIntInc(10, 15); i++) {
            map.setRasterAt(MathUtil.nextInt(map.getMapSizeX()), MathUtil.nextInt(map.getMapSizeY()), Raster.chest);
        }
        map.setRasterAt(MathUtil.floorDiv(Integers.sightX, 2) - 1, MathUtil.floorDiv(Integers.sightY, 2) - 1, Raster.chest);

        // WAENDE!
        for (int x = 0; x < map.getMapSizeX(); x++) {
            map.setRasterAt(x, 0, Raster.wall);
            map.setRasterAt(x, map.getMapSizeY() - 1, Raster.wall);
        }
        for (int y = 0; y < map.getMapSizeY(); y++) {
            map.setRasterAt(0, y, Raster.wall);
            map.setRasterAt(map.getMapSizeX() - 1, y, Raster.wall);
        }

        // Animationen
        map.setRasterAt(3, 1, Raster.fire);

        // TUEREN!
        for (int y = 20; y < 25; y++) {
            for (int x = 18; x < 23; x++) {
                map.setRasterAt(x, y, Raster.grass);
            }
        }
        map.setRasterAt(20, 18, Raster.tree);
        map.setRasterAt(21, 18, Raster.tree);
        // map.setRasterAt(22, 18, Raster.tree);
        map.setRasterAt(23, 18, Raster.tree);
        map.setRasterAt(24, 18, Raster.tree);
        map.setRasterAt(24, 19, Raster.tree);
        map.setRasterAt(24, 20, Raster.tree);
        map.setRasterAt(24, 21, Raster.tree);
        map.setRasterAt(24, 22, Raster.tree);
        map.setRasterAt(20, 19, Raster.tree);
        map.setRasterAt(20, 21, Raster.tree);
        map.setRasterAt(20, 22, Raster.tree);
        map.setRasterAt(21, 22, Raster.tree);
        map.setRasterAt(22, 22, Raster.tree);
        map.setRasterAt(23, 22, Raster.tree);
        map.setRasterAt(22, 20, Raster.finish);

        map.setRasterAt(20, 20, Raster.door);
        ExtraInformationDoor door = (ExtraInformationDoor) map.getExtraInformationAt(20, 20);
        door.setDirection(Direction.LEFT);
        door.setOpeningKey(1000);

        map.setRasterAt(22, 18, Raster.door);
        ExtraInformationDoor door2 = (ExtraInformationDoor) map.getExtraInformationAt(22, 18);
        door2.setDirection(Direction.UP);
        door2.setOpeningKey(1000);

        // KISTENINHALTE!
        Inventory inv;
        ExtraInformation extra;
        for (int y = 0; y < map.getMapSizeY(); y++) {
            for (int x = 0; x < map.getMapSizeX(); x++) {
                inv = new Inventory(Integers.CHEST_SIZE);
                inv.addItemStack(new ItemStack(Items.item42, 42));
                inv.addItemStack(new ItemStack(Items.nuke));
                inv.addItemStack(new ItemStack(Items.key));
                extra = map.getExtraInformationAt(x, y);
                if (extra instanceof ExtraInformationChest) {
                    ((ExtraInformationChest) extra).setInventory(inv);
                }
            }
        }

        // ENTITIES!
        for (int x = 0; x < 3; x++) {
            map.spawn(new EntityRedNPC(map, MathUtil.roundMul(Math.random(), 20) + 10, MathUtil.roundMul(Math.random(), 20) + 10));
        }

        // ITEMS
        map.spawn(new EntityItem(map, 5, 5, new ItemStack(Items.key, 1, 1000)));
        map.spawn(new EntityItem(map, 5, 6, new ItemStack(Items.item42, 42, 42)));
        map.spawn(new EntityItem(map, 5, 7, new ItemStack(Items.nuke, 1234)));
        map.spawn(new EntityItem(map, 5, 8, new ItemStack(Items.healthPotion, 1234, 100)));

        // STORYMAGER!
        map.getStoryManager().registerTrigger(new CombinedAndTrigger(new AreaTrigger(50, 50, 10, 10), new InventoryHasItemStackTrigger(new ItemStack(Items.nuke))), ReflectionUtil.getMethod(Scripts.class, "switchMap", Integer.TYPE), 1);

    }

    /**
     * Level 0 - Spielfeld 1.
     *
     * @param map
     *            Spielfeld
     */
    public static void generateAndPopulateLevel0Map1(Spielfeld map) {

        // DESTROYED-RASTER HINTERGRUND!
        for (int y = 0; y < map.getMapSizeY(); y++) {
            for (int x = 0; x < map.getMapSizeX(); x++) {
                map.setRasterAt(x, y, Raster.destroyedRaster);
            }
        }

        // WAeNDE!
        for (int x = 0; x < map.getMapSizeX(); x++) {
            map.setRasterAt(x, 0, Raster.wall);
            map.setRasterAt(x, map.getMapSizeY() - 1, Raster.wall);
        }
        for (int y = 0; y < map.getMapSizeY(); y++) {
            map.setRasterAt(0, y, Raster.wall);
            map.setRasterAt(map.getMapSizeX() - 1, y, Raster.wall);
        }

        // STORYMANAGER!
        map.getStoryManager().registerTrigger(new PosTrigger(18, 8), ReflectionUtil.getMethod(Scripts.class, "switchMap", Integer.TYPE), 0);
    
    }

    /**
     * Level 1 - Spielfeld 0.
     *
     * @param map
     *            Spielfeld
     */
    public static void generateAndPopulateLevel1Map0(Spielfeld map) {

        // JoJo
        map.spawn(new EntityItem(map, 4, 65, new ItemStack(Items.yoyoBroken, 1, 100)));

        // Muelltonne
        map.spawn(new EntityTrashCan(map, 46, 23));

        // Tï¿½r Faehrhaus
        map.spawn(new EntityDoor(map, 24, 27));

        // Faehrman
        map.spawn(new EntityFerryman(map, 26, 26));

        // Grammophone

        map.spawn(new EntityGramophone(map, 26, 28));

        // Ferry
        map.spawn(new EntityFerry(map, 11, 12));

        // Auto
        map.spawn(new EntityVilleCar(map, 12, 25));

        // Schranke
        map.spawn(new EntityBoomBarrier(map, 11, 20));

        // Boden

        for (int y = 20; y < map.getMapSizeY(); y++) {
            for (int x = 0; x < map.getMapSizeX(); x++) {
                map.setRasterAt(x, y, Raster.grass_2);
            }
        }

        // Faehrhaus
        EntityFerryhouse fhouse = new EntityFerryhouse(map, 24, 24);
        map.spawn(fhouse);

        // Water

        for (int y = 0; y < 20; y++) {
            for (int x = 0; x < map.getMapSizeX(); x++) {
                map.setRasterAt(x, y, Raster.water);
            }
        }

        // Junge
        map.spawn(new EntityBoy(map, 33, 52));

        // Insel im Fluss
        int inttemp = 4;
        int inttemp2 = 0;
        for (int x = 0; x < 5; x++) {

            for (int y = 0; y < 5 - inttemp; y++) {
                map.setRasterAt(30 + x, 10 + y - inttemp2, Raster.floor_4);
            }
            inttemp = inttemp - 2;
            inttemp2++;
        }
        for (int y = 6; y < 15; y++) {
            for (int x = 35; x < 42; x++) {
                map.setRasterAt(x, y, Raster.floor_4);
            }
        }

        inttemp = 0;
        inttemp2 = 0;
        for (int x = 0; x < 5; x++) {

            for (int y = 0; y < 9 - inttemp; y++) {
                map.setRasterAt(42 + x, 6 + y + inttemp2, Raster.floor_4);
            }
            inttemp = inttemp + 2;
            inttemp2++;
        }
        for (int x = 36; x < 43; x++) {
            map.setRasterAt(x, 14, Raster.water);
        }

        for (int x = 38; x < 42; x++) {
            map.setRasterAt(x, 13, Raster.water);
        }

        for (int x = 34; x < 36; x++) {
            map.setRasterAt(x, 6, Raster.water);
        }

        for (int y = 8; y < 12; y++) {
            for (int x = 35; x < 43; x++) {
                map.setRasterAt(x, y, Raster.grass_2);
            }
        }
        map.setRasterAt(35, 8, Raster.floor_4);

        map.setRasterAt(36, 8, Raster.floor_4);

        map.setRasterAt(41, 11, Raster.floor_4);

        map.setRasterAt(42, 11, Raster.floor_4);

        map.setRasterAt(42, 8, Raster.floor_4);

        map.setRasterAt(38, 11, Raster.floor_4);

        setTree(35, 10, Raster.tree_4nw, Raster.tree_4ne, Raster.tree_4se, Raster.tree_4sw, map);

        setTree(39, 9, Raster.tree_4nw, Raster.tree_4ne, Raster.tree_4se, Raster.tree_4sw, map);
        // Boden

        for (int y = 0; y < 8; y++) {

            for (int x = 0; x < 8 - y; x++) {
                map.setRasterAt(map.getMapSizeX() - x, y, Raster.grass_2);
            }
        }

        // BÃ¯Â¿Â½ume an der StraÃ¯Â¿Â½e

        for (int y = 21; y < map.getMapSizeY() - 2; y = y + 7) {
            setTree(7, y, Raster.tree_11nw, Raster.tree_11ne, Raster.tree_11se, Raster.tree_11sw, map);
            setTree(15, y + 3, Raster.tree_11nw, Raster.tree_11ne, Raster.tree_11se, Raster.tree_11sw, map);
        }

        // BÃ¯Â¿Â½ume linke Seite
        for (int x = 2; x < 10; x = x + 2) {
            AbstractRaster[] temp = MapUtil.getRanTree();
            setTree(x, map.getMapSizeY() - 2, temp[0], temp[1], temp[2], temp[3], map);
        }

        for (int x = 2; x < 8; x = x + 2) {
            AbstractRaster[] temp = MapUtil.getRanTree();
            setTree(x, map.getMapSizeY() - 4, temp[0], temp[1], temp[2], temp[3], map);
        }

        for (int y = 22; y < map.getMapSizeY(); y = y + 2) {
            AbstractRaster[] temp = MapUtil.getRanTree();
            setTree(0, y, temp[0], temp[1], temp[2], temp[3], map);

        }

        for (int y = 48; y < map.getMapSizeY() - 4; y = y + 2) {
            AbstractRaster[] temp = MapUtil.getRanTree();
            setTree(2, y, temp[0], temp[1], temp[2], temp[3], map);
        }

        // Baumfeld in der Mitte

        for (int y = 0; y < 28; y = y + 2) {
            for (int x = 0; x < 10; x = x + 2) {
                AbstractRaster[] temp = MapUtil.getRanTree();
                setTree(40 + x, 28 + y, temp[0], temp[1], temp[2], temp[3], map);

            }

        }

        for (int y = 0; y < 22; y = y + 2) {
            for (int x = 0; x < 6; x = x + 2) {
                AbstractRaster[] temp = MapUtil.getRanTree();
                setTree(34 + x, 33 + y, temp[0], temp[1], temp[2], temp[3], map);

            }

        }

        for (int y = 0; y < 12; y = y + 2) {
            for (int x = 0; x < 4; x = x + 2) {
                AbstractRaster[] temp = MapUtil.getRanTree();
                setTree(30 + x, 38 + y, temp[0], temp[1], temp[2], temp[3], map);

            }

        }

        for (int y = 35; y < 53; y = y + 2) {
            AbstractRaster[] temp = MapUtil.getRanTree();
            setTree(50, y, temp[0], temp[1], temp[2], temp[3], map);
        }

        for (int y = 54; y < 58; y++) {
            for (int x = 44; x < 50; x++) {
                map.setRasterAt(x, y, Raster.grass_2);
            }
        }

        // Baum oben rechts
        setTree(map.getMapSizeX() - 3, 1, Raster.tree_3nw, Raster.tree_3ne, Raster.tree_3se, Raster.tree_3sw, map);

        // Baumreihe ganz unten
        for (int x = 14; x < map.getMapSizeX(); x = x + 2) {
            AbstractRaster[] temp = MapUtil.getRanTree();
            setTree(x, map.getMapSizeY() - 2, temp[0], temp[1], temp[2], temp[3], map);
        }

        for (int x = 22; x < map.getMapSizeX(); x = x + 2) {
            AbstractRaster[] temp = MapUtil.getRanTree();
            setTree(x, map.getMapSizeY() - 4, temp[0], temp[1], temp[2], temp[3], map);
        }

        for (int x = 30; x < 52; x = x + 2) {
            AbstractRaster[] temp = MapUtil.getRanTree();
            setTree(x, map.getMapSizeY() - 6, temp[0], temp[1], temp[2], temp[3], map);
        }

        for (int x = 0; x < 8; x = x + 2) {
            AbstractRaster[] temp = MapUtil.getRanTree();
            setTree(34 + x, map.getMapSizeY() - 8, temp[0], temp[1], temp[2], temp[3], map);
        }

        for (int y = 56; y < map.getMapSizeY() - 4; y = y + 2) {
            for (int x = 66; x < map.getMapSizeX(); x = x + 2) {
                AbstractRaster[] temp = MapUtil.getRanTree();
                setTree(x, y, temp[0], temp[1], temp[2], temp[3], map);

            }
        }

        for (int y = map.getMapSizeY() - 2; y < map.getMapSizeY(); y++) {
            for (int x = 58; x < 64; x++) {
                map.setRasterAt(x, y, Raster.grass_2);
            }
        }

        for (int y = map.getMapSizeY() - 4; y < map.getMapSizeY() - 2; y++) {
            for (int x = 56; x < 66; x++) {
                map.setRasterAt(x, y, Raster.grass_2);
            }
        }

        // Strand am Boot

        for (int y = 36; y < 54; y++) {
            map.setRasterAt(61, y, Raster.floor_4);
        }

        for (int y = 39; y < 52; y++) {
            map.setRasterAt(60, y, Raster.floor_4);
        }

        for (int y = 41; y < 48; y++) {
            map.setRasterAt(59, y, Raster.floor_4);
        }

        inttemp = 0;
        for (int y = 49; y < 55; y++) {
            for (int x = 62; x < 63 + inttemp; x++) {
                map.setRasterAt(x, y, Raster.floor_4);
            }
            inttemp++;
        }

        map.setRasterAt(63, 50, Raster.water);

        map.setRasterAt(64, 51, Raster.water);

        map.setRasterAt(62, 49, Raster.water);

        // Water

        for (int y = 0; y < 20; y++) {

            for (int x = 0; x < 40 - y; x++) {
                map.setRasterAt(map.getMapSizeX() - x, 20 + y, Raster.water);
            }
        }

        for (int y = 30; y < 50; y++) {

            for (int x = map.getMapSizeX() - 28; x < map.getMapSizeX(); x++) {
                map.setRasterAt(x, y, Raster.water);
            }
        }

        for (int y = 0; y < 12; y++) {

            for (int x = 0; x < 28 - y; x++) {
                map.setRasterAt(map.getMapSizeX() - x, 50 + y, Raster.water);
            }
        }
        // Rasen unter dem Boot

        for (int x = 68; x < 72; x++) {
            map.setRasterAt(x, 55, Raster.grass_2);
        }

        for (int x = 65; x < 76; x++) {
            map.setRasterAt(x, 56, Raster.grass_2);
        }

        for (int y = 57; y < 62; y++) {

            for (int x = 65; x < map.getMapSizeX(); x++) {
                map.setRasterAt(x, y, Raster.grass_2);
            }
        }

        for (int y = 62; y < 64; y++) {

            for (int x = 66; x < 74; x++) {
                map.setRasterAt(x, y, Raster.grass_2);
            }
        }

        for (int y = 62; y < 66; y++) {

            for (int x = 66; x < 68; x++) {
                map.setRasterAt(x, y, Raster.grass_2);
            }
        }
        // Sand Kurve unterm Boot
        for (int x = 65; x < 72; x++) {
            map.setRasterAt(x, 55, Raster.floor_4);
        }

        for (int x = 69; x < 76; x++) {
            map.setRasterAt(x, 56, Raster.floor_4);
        }

        for (int x = 74; x < map.getMapSizeX(); x++) {
            map.setRasterAt(x, 57, Raster.floor_4);
        }

        for (int x = 82; x < map.getMapSizeX(); x = x + 2) {
            AbstractRaster[] temp = MapUtil.getRanTree();
            setTree(x, 60, temp[0], temp[1], temp[2], temp[3], map);
        }

        map.setRasterAt(64, 52, Raster.water);

        // Baeume am Wasser

        setTree(42, 19, Raster.tree_5nw_water, Raster.tree_5ne_water, Raster.tree_5se, Raster.tree_5sw, map);

        setTree(53, 23, Raster.tree_5nw_water, Raster.tree_5ne_water, Raster.tree_5se, Raster.tree_5sw, map);

        setTree(51, 23, Raster.tree_5nw, Raster.tree_5ne, Raster.tree_5se, Raster.tree_5sw, map);

        setTree(51, 21, Raster.tree_5nw_water, Raster.tree_5ne_water, Raster.tree_5se_water, Raster.tree_5sw, map);

        setTree(48, 24, Raster.tree_6nw, Raster.tree_6ne, Raster.tree_6se, Raster.tree_6sw, map);

        setTree(49, 22, Raster.tree_5nw, Raster.tree_5ne, Raster.tree_5se, Raster.tree_5sw, map);

        setTree(47, 21, Raster.tree_6nw, Raster.tree_6ne, Raster.tree_6se, Raster.tree_6sw, map);

        setTree(47, 19, Raster.tree_5nw_water, Raster.tree_5ne_water, Raster.tree_5se, Raster.tree_5sw, map);

        map.setRasterAt(49, 20, Raster.water);

        map.setRasterAt(50, 20, Raster.water);
        // Steine am Anfang zum nÃ¤chsen Level

        for (int x = 58; x < 64; x++) {
            map.setRasterAt(x, 69, Raster.cobbles_1);
        }
        map.setRasterAt(59, 69, Raster.grass_2);

        for (int x = 58; x < 62; x++) {
            map.setRasterAt(x, 68, Raster.cobbles_1);
        }
        map.setRasterAt(59, 68, Raster.grass_2);
        for (int x = 61; x < 63; x++) {
            map.setRasterAt(x, 67, Raster.cobbles_1);
        }
        map.setRasterAt(59, 67, Raster.cobbles_1);
        map.setRasterAt(58, 66, Raster.cobbles_1);
        map.setRasterAt(60, 66, Raster.cobbles_1);
        map.setRasterAt(61, 66, Raster.cobbles_1);
        map.setRasterAt(64, 66, Raster.cobbles_1);
        map.setRasterAt(64, 67, Raster.cobbles_1);
        map.setRasterAt(56, 65, Raster.cobbles_1);
        map.setRasterAt(63, 65, Raster.cobbles_1);
        map.setRasterAt(63, 64, Raster.cobbles_1);
        map.setRasterAt(62, 64, Raster.cobbles_1);
        map.setRasterAt(59, 64, Raster.cobbles_1);
        map.setRasterAt(58, 63, Raster.cobbles_1);
        map.setRasterAt(65, 63, Raster.cobbles_1);
        // Haus

        for (int y = 25; y < 30; y++) {
            for (int x = 25; x < 28; x++) {
                map.setRasterAt(x, y, Raster.floor_12);
                map.setRasterAt(x, 24, Raster.ferryhouse_wall_n);
                map.setRasterAt(x, 30, Raster.ferryhouse_wall_s);
            }
            map.setRasterAt(24, y, Raster.ferryhouse_wall_w);
            map.setRasterAt(28, y, Raster.ferryhouse_wall_e);
        }
        map.setRasterAt(24, 24, Raster.ferryhouse_ecke_nw);
        map.setRasterAt(28, 24, Raster.ferryhouse_ecke_ne);
        map.setRasterAt(24, 30, Raster.ferryhouse_ecke_sw);
        map.setRasterAt(28, 30, Raster.ferryhouse_ecke_se);

        map.setRasterAt(26, 27, Raster.chair_2);

        for (int i = 0; i < 8; i++) {
            setStreet(10, 20 + i * 8, map);
        }

        // Hafenbegrenzung

        for (int x = 0; x < 30; x++) {

            map.setRasterAt(x, 20, Raster.cobbles_2);
        }

        for (int x = 0; x < 23; x++) {

            map.setRasterAt(4 + x, 19, Raster.cobbles_2);
        }

        for (int x = 0; x < 8; x++) {

            map.setRasterAt(15 + x, 18, Raster.cobbles_2);
        }

        // Trigger

        StoryManager st = map.getStoryManager();

        // CombinedAndTrigger trigger = new CombinedAndTrigger(new PosTrigger(30, 30), new AreaTrigger(30, 30, 10, 10), new InventoryHasItemStackTrigger(new ItemStack(Items.earrings)));

        // Faehrhaus spawnen/despawnen
        AreaTrigger area = new AreaTrigger(19, 20, 15, 16);
        st.registerTrigger(area, Scripts.REMOVE_ENTITY, fhouse, map);

        // mapwechsel

        // st.registerTrigger(new AreaTrigger(58, 68, 6, 1), ReflectionUtil.getMethod(Scripts.class, "setSwitchMapTrigger", Integer.TYPE, Trigger.class, Trigger.class), 1, new AreaTrigger(58, 68, 6, 1), new AreaTrigger(58, 69, 6, 1));
        //

        st.registerTrigger(new PosTrigger(10, 26), ReflectionUtil.getMethod(Scripts.class, "cutsceneOne"));

        AbstractTrigger setSpawn = new AreaTrigger(56, 67, 10, 1);
        st.registerTrigger(setSpawn, ReflectionUtil.getMethod(Scripts.class, "setSpawn", Integer.TYPE, Integer.TYPE), 60, 69);

    }

    /**
     * Level 1 - Spielfeld 1.
     *
     * @param map
     *            Spielfeld
     */
    public static void generateAndPopulateLevel1Map1(Spielfeld map) {

        int inttemp;
        int inttemp2;

        map.spawn(new EntityNest(map, 8, 60));

        map.spawn(new EntityWitchCauldron(map, 49, 48));

        // Rasen
        for (int y = 0; y < map.getMapSizeY(); y++) {
            for (int x = 0; x < map.getMapSizeX(); x++) {
                map.setRasterAt(x, y, Raster.grass_2);
            }
        }

        // WALD

        /*
         * Traditioneller Wald
         */
        for (int x = 0; x < map.getMapSizeX(); x = x + 2) {
            for (int y = 0; y < map.getMapSizeY(); y = y + 2) {
                AbstractRaster[] temp = MapUtil.getRanTree();
                setTree(x, y, temp[0], temp[1], temp[2], temp[3], map);
            }
        }

        /*
         * Wege im Wald
         */
        // inttemp = 1;
        // for (int z = 0; z < map.getMapSizeX(); z = z + 20) {
        // for (int zz = 0; zz < map.getMapSizeY(); zz = zz + 20) {
        // int X = z;
        // int Y = zz;
        // for (int x = X + 10; x < X + 12; x = x + 1) {
        // for (int y = Y; y < Y + 20; y = y + 1) {
        // map.setRasterAt(x, y, Raster.grass_2);
        // }
        // }
        // X = z;
        // Y = zz;
        // for (int x = X; x < X + 20; x = x + 1) {
        // for (int y = Y + 10; y < Y + 12; y = y + 1) {
        // map.setRasterAt(x, y, Raster.grass_2);
        // }
        // }
        // if (inttemp == MathUtil.randomInt(1)) {
        // inttemp2 = 0;
        // for (int x = X + inttemp2; x < X + 1 + inttemp2; x = x + 1) {
        // for (int y = Y + inttemp2; y < Y + 1 + inttemp2; y = y + 1) {
        // map.setRasterAt(x, y, Raster.grass_2);
        // if (inttemp2 < 21) {
        // inttemp2++;
        // }
        // }
        // }
        // }
        // }
        // }
        // WaldflÃ¤chen (nicht genutzt!):
        // inttemp = 1;
        // for (int x = 0; x < map.getMapSizeX(); x = x + 2) {
        // for (int y = 0; y < map.getMapSizeY(); y = y + 2) {
        // inttemp2 = MathUtil.randomInt(45);
        // if (inttemp == inttemp2) {
        // for (int xx = x; xx < x + 16; xx = xx + 2) {
        // for (int yy = y; yy < y + 16; yy = yy + 2) {
        // AbstractRaster[] temp = MapUtil.getRanTree();
        // setTree(xx, yy, temp[0], temp[1], temp[2], temp[3], map);
        // }
        // }
        // }
        // }
        // }

        /*
         * LÃ¶cher im Wald
         */
        inttemp = 2;
        for (int x = 0; x < map.getMapSizeX(); x = x + 2) {
            for (int y = 0; y < map.getMapSizeY(); y = y + 2) {
                inttemp2 = MathUtil.randomInt(2);
                if (inttemp == inttemp2) {
                    map.setRasterAt(x, y, Raster.grass_2);
                    map.setRasterAt(x + 1, y, Raster.grass_2);
                    map.setRasterAt(x, y + 1, Raster.grass_2);
                    map.setRasterAt(x + 1, y + 1, Raster.grass_2);
                }
            }
        }
        for (int x = 0; x < map.getMapSizeX(); x = x + 4) {
            for (int y = 0; y < map.getMapSizeY(); y = y + 4) {
                inttemp2 = MathUtil.randomInt(2);
                if (inttemp == inttemp2) {
                    for (int xx = x; xx < x + 4; xx++) {
                        for (int yy = y; yy < y + 4; yy++) {
                            map.setRasterAt(xx, yy, Raster.grass_2);
                        }
                    }
                }
            }
        }

        /*
         * Waldring um den See
         */
        for (int x = 12; x < 38; x = x + 2) {
            for (int y = 12; y < 26; y = y + 2) {
                AbstractRaster[] temp = MapUtil.getRanTree();
                setTree(x, y, temp[0], temp[1], temp[2], temp[3], map);
            }
        }

        /*
         * WaldGrenze Oben
         */
        for (int x = 0; x < map.getMapSizeX(); x = x + 2) {
            for (int y = 0; y < 2; y = y + 2) {
                AbstractRaster[] temp = MapUtil.getRanTree();
                setTree(x, y, temp[0], temp[1], temp[2], temp[3], map);
            }
        }

        /*
         * WaldGrenze Rechts
         */
        for (int x = map.getMapSizeX() - 2; x < map.getMapSizeX(); x = x + 2) {
            for (int y = 0; y < map.getMapSizeY(); y = y + 2) {
                AbstractRaster[] temp = MapUtil.getRanTree();
                setTree(x, y, temp[0], temp[1], temp[2], temp[3], map);
            }
        }

        /*
         * WaldGrenze Links
         */
        for (int x = 0; x < 2; x = x + 2) {
            for (int y = 0; y < map.getMapSizeY(); y = y + 2) {
                AbstractRaster[] temp = MapUtil.getRanTree();
                setTree(x, y, temp[0], temp[1], temp[2], temp[3], map);
            }
        }

        /*
         * WaldGrenze Unten
         */
        for (int x = 0; x < map.getMapSizeX(); x = x + 2) {
            for (int y = map.getMapSizeY() - 2; y < map.getMapSizeY(); y = y + 2) {
                AbstractRaster[] temp = MapUtil.getRanTree();
                setTree(x, y, temp[0], temp[1], temp[2], temp[3], map);
            }
        }

        // Weg

        inttemp = 1;

        /*
         * Eingangsweg Runter
         */
        for (int x = 10; x < 12; x++) {
            for (int y = 0; y < 28; y++) {
                map.setRasterAt(x, y, Raster.cobbles_1);
                inttemp2 = MathUtil.randomInt(10);
                if (inttemp == inttemp2) {
                    map.setRasterAt(x, y, Raster.grass_2);
                }
            }
        }
        /*
         * Ecke Eingangsweg/Unterer Weg
         */
        for (int x = 12; x < 14; x++) {
            for (int y = 24; y < 26; y++) {
                map.setRasterAt(x, y, Raster.cobbles_1);
                inttemp2 = MathUtil.randomInt(4);
                if (inttemp == inttemp2) {
                    map.setRasterAt(x, y, Raster.grass_2);
                }
            }
        }

        /*
         * Unterer Weg am See
         */
        for (int x = 10; x < 40; x++) {
            for (int y = 26; y < 28; y++) {
                map.setRasterAt(x, y, Raster.cobbles_1);
                inttemp2 = MathUtil.randomInt(2);
                if (inttemp == inttemp2) {
                    map.setRasterAt(x, y, Raster.grass_2);
                }
            }
        }

        /*
         * Rechter Weg am See
         */
        for (int x = 38; x < 40; x++) {
            for (int y = 12; y < 28; y++) {
                map.setRasterAt(x, y, Raster.cobbles_1);
                inttemp2 = MathUtil.randomInt(2);
                if (inttemp == inttemp2) {
                    map.setRasterAt(x, y, Raster.grass_2);
                }
            }
        }

        /*
         * Oberer Weg am See
         */
        for (int x = 10; x < 38; x++) {
            for (int y = 10; y < 12; y++) {

                map.setRasterAt(x, y, Raster.cobbles_1);
                inttemp2 = MathUtil.randomInt(4);
                if (inttemp == inttemp2) {
                    map.setRasterAt(x, y, Raster.grass_2);
                }
            }
        }

        /*
         * Ecke Oberer Weg/Eingangsweg
         */
        for (int x = 12; x < 14; x++) {
            for (int y = 8; y < 14; y++) {
                map.setRasterAt(x, y, Raster.cobbles_1);
                inttemp2 = MathUtil.randomInt(4);
                if (inttemp == inttemp2) {
                    map.setRasterAt(x, y, Raster.grass_2);
                }
            }
        }

        /*
         * Ecke Oberer weg/Rechter Weg
         */
        for (int x = 36; x < 38; x++) {
            for (int y = 12; y < 14; y++) {
                map.setRasterAt(x, y, Raster.cobbles_1);
                inttemp2 = MathUtil.randomInt(4);
                if (inttemp == inttemp2) {
                    map.setRasterAt(x, y, Raster.grass_2);
                }
            }
        }

        /*
         * LÃ¼cke zum See
         */
        for (int x = 12; x < 14; x++) {
            for (int y = 14; y < 16; y++) {
                map.setRasterAt(x, y, Raster.grass_2);
            }
        }

        /*
         * Weg runter zur Lichtung
         */
        for (int x = 38; x < 40; x++) {
            for (int y = 28; y < 40; y++) {
                map.setRasterAt(x, y, Raster.cobbles_1);
                inttemp2 = MathUtil.randomInt(1);
                if (inttemp == inttemp2) {
                    map.setRasterAt(x, y, Raster.grass_2);
                }
            }
        }

        for (int x = 38; x < 40; x++) {
            for (int y = 40; y < 46; y++) {
                map.setRasterAt(x, y, Raster.grass_2);
            }
        }

        /*
         * Ecke Weg runter zur Lichtung/ Unterer Weg am See
         */
        for (int x = 36; x < 38; x++) {
            for (int y = 28; y < 32; y++) {
                map.setRasterAt(x, y, Raster.cobbles_1);
                inttemp2 = MathUtil.randomInt(4);
                if (inttemp == inttemp2) {
                    map.setRasterAt(x, y, Raster.grass_2);
                }
            }
        }

        /*
         * LÃ¼cke zur Lichtung
         */
        for (int x = 40; x < 42; x++) {
            for (int y = 44; y < 46; y++) {
                map.setRasterAt(x, y, Raster.grass_2);
            }
        }

        // See + Sand
        for (int x = 15; x < 35; x++) {
            for (int y = 15; y < 23; y++) {
                map.setRasterAt(x, y, Raster.water);
            }
        }

        inttemp = 5;

        for (int x = 14; x < 36; x++) {
            for (int y = 14; y < 15; y++) {
                map.setRasterAt(x, y, Raster.floor_4);
            }
        }

        for (int x = 14; x < 36; x++) {
            for (int y = 23; y < 24; y++) {
                map.setRasterAt(x, y, Raster.floor_4);
            }
        }

        for (int x = 14; x < 15; x++) {
            for (int y = 14; y < 24; y++) {
                map.setRasterAt(x, y, Raster.floor_4);
            }
        }

        for (int x = 35; x < 36; x++) {
            for (int y = 14; y < 24; y++) {
                map.setRasterAt(x, y, Raster.floor_4);
            }
        }

        for (int x = 35; x < 36; x++) {
            for (int y = 14; y < 24; y++) {
                map.setRasterAt(x, y, Raster.floor_4);
            }
        }

        inttemp = 20;
        for (int y = 14; y < 20; y++) {
            for (int x = 14; x < inttemp; x++) {

                map.setRasterAt(x, y, Raster.floor_4);
            }
            inttemp--;
        }

        inttemp = 32;
        for (int y = 14; y < 18; y++) {
            for (int x = inttemp; x < 36; x++) {

                map.setRasterAt(x, y, Raster.floor_4);
            }
            inttemp++;
        }

        inttemp = 18;
        inttemp2 = 24;
        for (int y = 22; y > 20; y--) {
            for (int x = inttemp; x < inttemp2; x++) {

                map.setRasterAt(x, y, Raster.floor_4);
            }
            inttemp++;
            inttemp2--;
        }

        for (int x = 31; x < 36; x++) {
            for (int y = 22; y < 24; y++) {
                map.setRasterAt(x, y, Raster.water);
            }
        }

        for (int x = 30; x < 37; x++) {
            for (int y = 24; y < 26; y++) {
                map.setRasterAt(x, y, Raster.floor_4);
            }
        }

        map.setRasterAt(28, 24, Raster.floor_4);
        map.setRasterAt(29, 24, Raster.floor_4);
        map.setRasterAt(29, 25, Raster.floor_4);
        map.setRasterAt(28, 25, Raster.grass_2);
        map.setRasterAt(36, 20, Raster.grass_2);

        for (int y = 21; y < 24; y++) {
            map.setRasterAt(36, y, Raster.floor_4);
        }

        for (int y = 20; y < 26; y++) {
            map.setRasterAt(37, y, Raster.grass_2);
        }

        map.setRasterAt(14, 14, Raster.grass_2);
        map.setRasterAt(14, 15, Raster.grass_2);
        map.setRasterAt(15, 14, Raster.grass_2);

        for (int y = 20; y < 22; y++) {
            map.setRasterAt(15, y, Raster.floor_4);
        }

        for (int x = 19; x < 22; x++) {
            map.setRasterAt(x, 23, Raster.grass_2);
        }

        map.setRasterAt(21, 22, Raster.grass_2);
        map.setRasterAt(35, 14, Raster.grass_2);

        for (int x = 18; x < 24; x++) {
            map.setRasterAt(x, 12, Raster.grass_2);
        }

        for (int x = 18; x < 24; x++) {
            map.setRasterAt(x, 13, Raster.floor_4);
        }

        // Lichtung

        for (int x = 42; x < 60; x++) {
            for (int y = 40; y < 60; y++) {
                map.setRasterAt(x, y, Raster.grass_2);
            }
        }

        inttemp = 52;
        for (int y = 58; y > 50; y = y - 2) {
            for (int x = inttemp; x < 60; x = x + 2) {
                AbstractRaster[] temp = MapUtil.getRanTree();
                setTree(x, y, temp[0], temp[1], temp[2], temp[3], map);
            }
            inttemp = inttemp + 2;
        }

        inttemp = 52;
        for (int y = 40; y < 46; y = y + 2) {
            for (int x = inttemp; x < 60; x = x + 2) {
                AbstractRaster[] temp = MapUtil.getRanTree();
                setTree(x, y, temp[0], temp[1], temp[2], temp[3], map);
            }
            inttemp = inttemp + 2;
        }

        inttemp = 49;
        for (int y = 58; y > 48; y = y - 2) {
            for (int x = 42; x < inttemp; x = x + 2) {
                AbstractRaster[] temp = MapUtil.getRanTree();
                setTree(x, y, temp[0], temp[1], temp[2], temp[3], map);
            }
            inttemp = inttemp - 2;
        }

        inttemp = 42;
        for (int y = 40; y < 45; y = y + 2) {
            for (int x = inttemp; x < 52; x = x + 2) {
                AbstractRaster[] temp = MapUtil.getRanTree();
                setTree(x, y, temp[0], temp[1], temp[2], temp[3], map);
            }
            inttemp = inttemp + 4;
        }
        // Weg zum Nest
        for (int y = 57; y < 68; y++) {
            for (int x = 48; x < 50; x++) {
                map.setRasterAt(x, y, Raster.grass_2);
            }
        }

        for (int x = 47; x > 27; x--) {
            for (int y = 66; y < 68; y++) {
                map.setRasterAt(x, y, Raster.grass_2);
            }
        }

        for (int y = 58; y < 66; y++) {
            for (int x = 28; x < 30; x++) {
                map.setRasterAt(x, y, Raster.grass_2);
            }
        }

        for (int x = 18; x < 29; x++) {
            for (int y = 58; y < 60; y++) {
                map.setRasterAt(x, y, Raster.grass_2);
            }
        }

        for (int y = 54; y < 58; y++) {
            for (int x = 18; x < 20; x++) {
                map.setRasterAt(x, y, Raster.grass_2);
            }
        }

        for (int x = 2; x < 19; x++) {
            for (int y = 54; y < 56; y++) {
                map.setRasterAt(x, y, Raster.grass_2);
            }
        }

        for (int y = 56; y < 62; y++) {
            for (int x = 2; x < 4; x++) {
                map.setRasterAt(x, y, Raster.grass_2);
            }
        }

        for (int x = 4; x < 8; x++) {
            for (int y = 60; y < 62; y++) {
                map.setRasterAt(x, y, Raster.grass_2);
            }
        }

        AbstractRaster[] temp = MapUtil.getRanTree();
        setTree(8, 60, temp[0], temp[1], temp[2], temp[3], map);

        // ENDE Lvl1 Map1
        StoryManager st = map.getStoryManager();

        st.registerTrigger(new AreaTrigger(10, 1, 2, 1), ReflectionUtil.getMethod(Scripts.class, "setSwitchMapTrigger", Integer.TYPE, AbstractTrigger.class, AbstractTrigger.class), 0, new AreaTrigger(10, 1, 2, 1), new AreaTrigger(10, 0, 2, 1));
    }

    private static void setStreet(int x, int y, Spielfeld map) {
        map.setRasterAt(x + 1, y, Raster.street_l_b_1_senk);
        map.setRasterAt(x + 1, y + 1, Raster.street_l_b_2_senk);
        map.setRasterAt(x + 1, y + 2, Raster.street_l_b_3_senk);
        map.setRasterAt(x + 1, y + 3, Raster.street_l_b_4_senk);
        map.setRasterAt(x + 1, y + 4, Raster.street_l_b_5_senk);
        map.setRasterAt(x + 1, y + 5, Raster.street_l_b_6_senk);
        map.setRasterAt(x + 1, y + 6, Raster.street_l_b_7_senk);
        map.setRasterAt(x + 1, y + 7, Raster.street_l_b_8_senk);
        map.setRasterAt(x + 2, y, Raster.street_l_t_1_senk);
        map.setRasterAt(x + 2, y + 1, Raster.street_l_t_2_senk);
        map.setRasterAt(x + 2, y + 2, Raster.street_l_t_3_senk);
        map.setRasterAt(x + 2, y + 3, Raster.street_l_t_4_senk);
        map.setRasterAt(x + 2, y + 4, Raster.street_l_t_5_senk);
        map.setRasterAt(x + 2, y + 5, Raster.street_l_t_6_senk);
        map.setRasterAt(x + 2, y + 6, Raster.street_l_t_7_senk);
        map.setRasterAt(x + 2, y + 7, Raster.street_l_t_8_senk);
        for (int i = 0; i < 8; i++) {
            map.setRasterAt(x, y + i, Raster.street_asphalt);
            map.setRasterAt(x + 3, y + i, Raster.street_asphalt);
        }

    }
private static void setStreet_2(int x, int y, Spielfeld map){
	map.setRasterAt(x, y, Raster.street_m_e1_1);
    map.setRasterAt(x, y + 1, Raster.street_m_e2_1);
    map.setRasterAt(x, y + 2, Raster.street_m_e3_1);
    map.setRasterAt(x, y + 3, Raster.street_m_e4_1);
    map.setRasterAt(x + 3, y, Raster.street_m_e1_4);
    map.setRasterAt(x + 3, y + 1, Raster.street_m_e2_4);
    map.setRasterAt(x + 3, y + 2, Raster.street_m_e3_4);
    map.setRasterAt(x + 3, y + 3, Raster.street_m_e4_4);    
    for (int i = 0; i < 4; i++) {
        map.setRasterAt(x + 1, y + i, Raster.street_m_e1_2);
        map.setRasterAt(x + 2, y + i, Raster.street_m_e1_2);
    }
}

private static void setStreet_3(int x, int y, Spielfeld map){
	map.setRasterAt(x, y, Raster.street_m_wage_e1_1);
    map.setRasterAt(x + 1, y, Raster.street_m_wage_e2_1);
    map.setRasterAt(x + 2, y, Raster.street_m_wage_e3_1);
    map.setRasterAt(x + 3, y, Raster.street_m_wage_e4_1);
    map.setRasterAt(x, y + 3, Raster.street_m_wage_e1_2);
    map.setRasterAt(x + 1, y + 3, Raster.street_m_wage_e2_2);
    map.setRasterAt(x + 2, y + 3, Raster.street_m_wage_e3_2);
    map.setRasterAt(x + 3, y + 3, Raster.street_m_wage_e4_2);    
    for (int i = 0; i < 4; i++) {
        map.setRasterAt(x + i, y + 1, Raster.street_m_e1_2);
        map.setRasterAt(x + i, y + 2, Raster.street_m_e1_2);
    }
}
    private static void setTree(int x, int y, AbstractRaster one, AbstractRaster two, AbstractRaster three, AbstractRaster four, Spielfeld map) {

        map.setRasterAt(x, y, one);
        map.setRasterAt(x + 1, y, two);
        map.setRasterAt(x + 1, y + 1, three);
        map.setRasterAt(x, y + 1, four);
    }

    /**
     * Nicht instanziierbar.
     */
    private MapBuilder() {
    }
}
