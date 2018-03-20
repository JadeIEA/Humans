package iea.iearthia.humans.handlers;

import iea.iearthia.humans.init.EntityInit;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class RegistryHandler {

    @SubscribeEvent
    public static void onModelRegister(ModelRegistryEvent e) {

    }

    public static void preInitRegistries() {
        EntityInit.registerEntities();
    }

    public static void initRegistries() {

    }

    public static void postInitRegistries() {

    }

}
