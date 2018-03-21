package iea.iearthia.humans.proxy;

import com.google.common.collect.Maps;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import com.mojang.authlib.minecraft.MinecraftSessionService;
import iea.iearthia.humans.Humans;
import iea.iearthia.humans.handlers.RenderHandler;
import iea.iearthia.humans.handlers.SoundsHandler;
import iea.iearthia.humans.helpers.ConfigHelper;
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
    private static Map<String, Boolean> isThin = Maps.newHashMap();

    @Override
    public ResourceLocation getSkinFromCache(String username) {
        if (skinCache.containsKey(username)) {
            return skinCache.get(username);
        } else {
            return skinCache.get("Steve");
        }
    }

    @Override
    public int getCacheSize() {
        return skinCache.size();
    }

    @Override
    public String[] getCacheUsernames() {
        return skinCache.keySet().toArray(new String[]{});
    }

    @Override
    public void handleInitEvent(FMLInitializationEvent e) {
        super.handleInitEvent(e);
        skinCache.put("Steve", DefaultPlayerSkin.getDefaultSkinLegacy());
        for (ConfigHelper.HumansSkin skin : Humans.CONFIG.skins) {
            skinCache.put(skin.username, getSkinFromUUID(skin.uuid));
            isThin.put(skin.username, skin.useSlim);
        }
        SoundsHandler.registerSounds();
    }

    @Override
    public void handlePreInitEvent(FMLPreInitializationEvent e) {
        super.handlePreInitEvent(e);
        RenderHandler.registerEntityRenders();
    }

    @Override
    public boolean getIsSlim(String username) {
        return isThin.getOrDefault(username, false);
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
