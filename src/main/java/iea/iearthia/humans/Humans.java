package iea.iearthia.humans;

import iea.iearthia.humans.handlers.RegistryHandler;
import iea.iearthia.humans.proxy.CommonProxy;
import iea.iearthia.humans.util.Constants;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;
import scala.collection.immutable.Stream;

@Mod(modid = Constants.MODID, name = Constants.NAME, version = Constants.VERSION)
public class Humans {
    @Mod.Instance
    public static Humans INSTANCE;

    @SidedProxy(clientSide = Constants.CLIENT, serverSide = Constants.SERVER)
    public static CommonProxy proxy;

    public static Logger logger;

    @Mod.EventHandler
    public static void preInit(FMLPreInitializationEvent e) {
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
