package iea.iearthia.humans.proxy;

import com.google.common.collect.Maps;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import com.mojang.authlib.minecraft.MinecraftSessionService;
import iea.iearthia.humans.handlers.RenderHandler;
import iea.iearthia.humans.handlers.SoundsHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.client.resources.SkinManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.util.Map;
import java.util.UUID;

public class ClientProxy extends CommonProxy {

    private static Map<String, ResourceLocation> skinCache = Maps.newHashMap();

    @Override
    public ResourceLocation getSkinFromCache(String username) {
        return skinCache.get(username);

    }

    @Override
    public void handleInitEvent(FMLInitializationEvent e) {
        super.handleInitEvent(e);
        skinCache.put("JadeIEA", getSkinFromUUID("1e25868f-6372-492d-8319-3a4627f0cc18"));
        SoundsHandler.registerSounds();

    }

    @Override
    public void handlePreInitEvent(FMLPreInitializationEvent e) {
        super.handlePreInitEvent(e);
        RenderHandler.registerEntityRenders();
    }

    public ResourceLocation getSkinFromUUID(String uuid) {
        ResourceLocation location = null;
        SkinManager skinManager = Minecraft.getMinecraft().getSkinManager();
        MinecraftSessionService sessionService = Minecraft.getMinecraft().getSessionService();

        GameProfile profile = new GameProfile(UUID.fromString(uuid), null);
        profile = sessionService.fillProfileProperties(profile, false);
        Map<MinecraftProfileTexture.Type, MinecraftProfileTexture> map = skinManager.loadSkinFromCache(profile);

        if(!map.isEmpty()) {
            MinecraftProfileTexture texture = map.get(MinecraftProfileTexture.Type.SKIN);
            location = skinManager.loadSkin(texture, MinecraftProfileTexture.Type.SKIN);
        } else {
            location = DefaultPlayerSkin.getDefaultSkinLegacy();
        }

        return location;
    }
}
