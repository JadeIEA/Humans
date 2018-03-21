package iea.iearthia.humans.handlers;

import iea.iearthia.humans.util.Constants;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class SoundsHandler {
    public static SoundEvent ENTITY_HUMAN_HURT;

    public static void registerSounds() {
        ENTITY_HUMAN_HURT = registerSound("entity.human.hurt");
    }

    private static SoundEvent registerSound(String name) {
        ResourceLocation location = new ResourceLocation(Constants.MODID, name);
        SoundEvent event = new SoundEvent(location);
        event.setRegistryName(name);
        ForgeRegistries.SOUND_EVENTS.register(event);
        return event;
    }
}
