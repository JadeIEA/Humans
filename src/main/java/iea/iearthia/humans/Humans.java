package iea.iearthia.humans;

import iea.iearthia.humans.handlers.RegistryHandler;
import iea.iearthia.humans.proxy.CommonProxy;
import iea.iearthia.humans.util.Constants;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import scala.collection.immutable.Stream;

@Mod(modid = Constants.MODID, name = Constants.NAME, version = Constants.VERSION)
public class Humans {
    @Mod.Instance
    public static Humans INSTANCE;

    @SidedProxy(clientSide = Constants.CLIENT, serverSide = Constants.SERVER)
    public static CommonProxy proxy;

    @Mod.EventHandler
    public static void preInit(FMLPreInitializationEvent e) {
        RegistryHandler.preInitRegistries();
        proxy.handlePreInitEvent(e);
    }

    @Mod.EventHandler
    public static void init(FMLInitializationEvent e) {
        proxy.handleInitEvent(e);
    }

    @Mod.EventHandler
    public static void postInit(FMLPostInitializationEvent e) {RegistryHandler.postInitRegistries(); }

}
