package iea.iearthia.humans;

import com.mojang.authlib.GameProfile;
import iea.iearthia.humans.handlers.RegistryHandler;
import iea.iearthia.humans.helpers.ConfigHelper;
import iea.iearthia.humans.proxy.CommonProxy;
import iea.iearthia.humans.util.Constants;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;
import scala.collection.immutable.Stream;

import java.io.File;
import java.util.UUID;

@Mod(modid = Constants.MODID, name = Constants.NAME, version = Constants.VERSION)
public class Humans {
    @Mod.Instance
    public static Humans INSTANCE;

    @SidedProxy(clientSide = Constants.CLIENT, serverSide = Constants.SERVER)
    public static CommonProxy proxy;

    public static ConfigHelper.HumansConfig CONFIG;

    public static Logger logger;

    @Mod.EventHandler
    public static void preInit(FMLPreInitializationEvent e) {
        File configFolder = new File(e.getModConfigurationDirectory().getAbsolutePath() + "/Humans/");
        if (!configFolder.exists()) configFolder.mkdirs();
        CONFIG = ConfigHelper.getConfig(configFolder.getAbsolutePath() + "/config.json");
        RegistryHandler.preInitRegistries();
        logger = e.getModLog();
        proxy.handlePreInitEvent(e);
    }

    @Mod.EventHandler
    public static void init(FMLInitializationEvent e) {
        RegistryHandler.initRegistries();
        proxy.handleInitEvent(e);
    }

    @Mod.EventHandler
    public static void postInit(FMLPostInitializationEvent e) {RegistryHandler.postInitRegistries(); }

}
