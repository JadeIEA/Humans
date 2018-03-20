package iea.iearthia.humans.init;

import iea.iearthia.humans.Humans;
import iea.iearthia.humans.entity.EntityHuman;
import iea.iearthia.humans.util.Constants;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class EntityInit {

    public static void registerEntities() {
        registerEntity("human", EntityHuman.class, Constants.ENTITY_HUMAN, 59, 0xFFFFFF, 0x000000);
    }

    private static void registerEntity(String name, Class<? extends Entity> entityIn, int id, int range, int color1, int color2) {
        EntityRegistry.registerModEntity(new ResourceLocation(Constants.MODID + ":" + name), entityIn, name, id, Humans.INSTANCE, range, 1, true, color1, color2);
    }
}
