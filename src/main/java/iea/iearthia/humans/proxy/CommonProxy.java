package iea.iearthia.humans.proxy;

import iea.iearthia.humans.handlers.RegistryHandler;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {
    public ResourceLocation getSkinFromCache(String username) {
        return null;
    }

    public void handleInitEvent(FMLInitializationEvent e) {

    }
    public void handlePreInitEvent(FMLPreInitializationEvent e) {
        RegistryHandler.initRegistries();
    }

}
