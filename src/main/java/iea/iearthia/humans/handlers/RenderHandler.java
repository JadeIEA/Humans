package iea.iearthia.humans.handlers;

import iea.iearthia.humans.entity.EntityHuman;
import iea.iearthia.humans.entity.render.RenderHuman;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class RenderHandler {

    @SideOnly(Side.CLIENT)
    public static void registerEntityRenders() {
        RenderingRegistry.registerEntityRenderingHandler(EntityHuman.class, new IRenderFactory<EntityHuman>() {
            @Override
            public Render<? super EntityHuman> createRenderFor(RenderManager manager) {
                return new RenderHuman(manager);
            }
        });
    }
}
