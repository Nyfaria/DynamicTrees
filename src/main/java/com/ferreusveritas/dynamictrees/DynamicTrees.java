package com.ferreusveritas.dynamictrees;

import com.ferreusveritas.dynamictrees.init.DTClient;
import com.ferreusveritas.dynamictrees.init.DTConfigs;
import com.ferreusveritas.dynamictrees.init.DTRegistries;
import com.ferreusveritas.dynamictrees.init.DTTrees;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("dynamictrees")
public class DynamicTrees
{

    public static final String MODID = "dynamictrees";
    public static final String NAME = "Dynamic Trees";
    public static final String VERSION = "1.14.2-9999.9999.9999z";//Maxed out version to satisfy dependencies during dev, Assigned from gradle during build, do not change

    public static final String OPTAFTER = "after:";
    public static final String OPTBEFORE = "before:";
    public static final String REQAFTER = "required-after:";
    public static final String REQBEFORE = "required-before:";
    public static final String NEXT = ";";
    public static final String AT = "@[";
    public static final String GREATERTHAN = "@(";
    public static final String ORGREATER = ",)";

    //Other mods can use this string to depend on the latest version of Dynamic Trees
    public static final String DYNAMICTREES_LATEST = MODID + AT + VERSION + ORGREATER;

//    //Other Add-on Mods
//    public static final String DYNAMICTREESBOP = "dynamictreesbop";
//    public static final String DYNAMICTREESTC = "dynamictreestc";
//    public static final String DYNAMICTREESPHC = "dynamictreesphc";
//    public static final String DYNAMICTREESTRAVERSE = "dttraverse";
//    public static final String DYNAMICTREESHNC = "dynamictreeshnc";
//    public static final String RUSTIC = "rustic";
//
//    //Other Mod Versions.. These have been added to avoid the whole "Duh.. How come my mod is crashing?" bullshit bug reports.
//    public static final String DYNAMICTREESBOP_VER = GREATERTHAN + "1.4.1d" + ORGREATER;
//    public static final String DYNAMICTREESTC_VER =	 GREATERTHAN + "1.4.1d" + ORGREATER;
//    public static final String DYNAMICTREESPHC_VER = GREATERTHAN + "1.4.1e" + ORGREATER;
//    public static final String DYNAMICTREESTRAVERSE_VER =  GREATERTHAN + "1.4" + ORGREATER;//Traverse will need a new build. Display an error rather than crash.
//    public static final String DYNAMICTREESHNC_VER =  GREATERTHAN + "1.1" + ORGREATER;//Heat and Climate Add-on has not be updated in a while and the latest 1.1 is not longer supported
//    public static final String RUSTIC_VER = GREATERTHAN + "1.0.14" + ORGREATER;
//    public static final String RECURRENT_COMPLEX = "reccomplex";//Load after recurrent complex to allow it to generate it's content first
//
//    //Forge
//    private static final String FORGE = "forge";
//    public static final String FORGE_VER = FORGE + AT + "14.23.5.2768" + ORGREATER;
//
//    public static final String DEPENDENCIES
//            = REQAFTER + FORGE_VER
//            + NEXT
//            + OPTBEFORE + RUSTIC + RUSTIC_VER
//            + NEXT
//            + OPTBEFORE + DYNAMICTREESBOP + DYNAMICTREESBOP_VER
//            + NEXT
//            + OPTBEFORE + DYNAMICTREESTC + DYNAMICTREESTC_VER
//            + NEXT
//            + OPTBEFORE + DYNAMICTREESTC + DYNAMICTREESPHC_VER
//            + NEXT
//            + OPTBEFORE + DYNAMICTREESTRAVERSE + DYNAMICTREESTRAVERSE_VER
//            + NEXT
//            + OPTBEFORE + DYNAMICTREESHNC + DYNAMICTREESHNC_VER
//            + NEXT
//            + OPTAFTER + RECURRENT_COMPLEX
//            ;
//

    public enum EnumAxeDamage {
		VANILLA,
		THICKNESS,
		VOLUME
	}

	public enum VanillaWoodTypes {
        oak,
        spruce,
        birch,
        jungle,
        darkoak,
        acacia
    }

    public enum EnumDestroyMode {
        SLOPPY,
        SETRADIUS,
        HARVEST,
        ROT,
        OVERFLOW
    }

    public DynamicTrees() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, DTConfigs.SERVER_CONFIG);
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, DTConfigs.CLIENT_CONFIG);

        DTRegistries.setupBlocks();
        DTRegistries.setupItems();
        DTRegistries.setupLeavesProperties();
        DTRegistries.setupEntities();

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) { //PREINIT
    }

    private void doClientStuff(final FMLClientSetupEvent event) { //CLIENT INIT
        DTClient.setup();
    }

    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) { //SERVER INIT
    }
}
