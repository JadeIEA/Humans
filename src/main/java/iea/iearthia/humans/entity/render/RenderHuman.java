package iea.iearthia.humans.entity.render;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import com.mojang.authlib.minecraft.MinecraftSessionService;
import iea.iearthia.humans.Humans;
import iea.iearthia.humans.entity.EntityHuman;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.client.resources.SkinManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.UUID;

public class RenderHuman extends RenderLiving<EntityHuman> {

    protected ModelPlayer modelEntity;
    protected int variant = 0;

    public static final ResourceLocation STEVE = DefaultPlayerSkin.getDefaultSkinLegacy();
    public static final ResourceLocation ALEX = DefaultPlayerSkin.getDefaultSkin(null);


    public ResourceLocation skin = STEVE;

    public RenderHuman(RenderManager manager, ModelBase modelBase, float shadowSize) {
        super(manager, modelBase, shadowSize);
        modelEntity = new ModelPlayer(0.0F, false);
    }
    @Override
    protected void preRenderCallback(EntityHuman entity, float f) {
        this.variant = entity.getHumanVariant();
        switch (this.variant) {
            case 0:
                this.skin = STEVE;
                this.mainModel = new ModelPlayer(0.0F, false);
                break;
            case 1:
                this.skin = ALEX;
                this.mainModel = new ModelPlayer(0.0F, true);
                break;
            case 2:
                break;
            case 3:
                break;
            default:
                break;
        }
        this.mainModel.isChild = entity.isChild();
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityHuman entity) {
        return Humans.proxy.getSkinFromCache("JadeIEA");
    }

    @Override
    public void doRender(EntityHuman entity, double x, double y, double z, float entityYaw, float partialTicks) {
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240F, 240F);
        super.doRender(entity, x, y, z, entityYaw, partialTicks);

    }


}
