package iea.iearthia.humans.handlers;

import iea.iearthia.humans.entity.EntityHuman;
import iea.iearthia.humans.entity.render.RenderHuman;
import net.minecraft.client.model.ModelPlayer;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class RenderHandler {

    public static void registerEntityRenders() {
        RenderingRegistry.registerEntityRenderingHandler(EntityHuman.class, (manager) -> new RenderHuman(manager, new ModelPlayer(0.0F, false), 0.5F));
    }
}
