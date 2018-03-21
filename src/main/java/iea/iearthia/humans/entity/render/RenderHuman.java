package iea.iearthia.humans.entity.render;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import com.mojang.authlib.minecraft.MinecraftSessionService;
import iea.iearthia.humans.Humans;
import iea.iearthia.humans.entity.EntityHuman;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelCreeper;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;
import java.util.UUID;

public class RenderHuman extends RenderLiving<EntityHuman> {

    protected int variant = 0;

    public static final ResourceLocation STEVE = DefaultPlayerSkin.getDefaultSkinLegacy();
    public static final ResourceLocation ALEX = new ResourceLocation("minecraft:textures/entity/alex.png");
    public static final ResourceLocation LEGASTEVE = new ResourceLocation("humans:textures/entity/legasteve.png");
    public static final ResourceLocation THINSTEVE = new ResourceLocation("humans:textures/entity/thinsteve.png");

    private static final ModelPlayer defaultModel = new ModelPlayer(0.0F, false);

    public ResourceLocation skin = STEVE;

    public RenderHuman(RenderManager manager) {
        super(manager, defaultModel, 0.5F);
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
                this.skin = THINSTEVE;
                this.mainModel = new ModelPlayer(0.0F, true);
                break;
            case 3:
                this.skin = Humans.proxy.getSkinFromCache("JadeIEA");
                this.mainModel = new ModelPlayer(0.0F, false);
                break;
            case 4:
                this.skin = LEGASTEVE;
                this.mainModel = new ModelPlayer(0.0F, false);
                break;
            default:
                this.skin = STEVE;
                this.mainModel = new ModelPlayer(0.0F, false);
                break;
        }
        this.mainModel.isChild = entity.isChild();
        GlStateManager.scale(0.94F, 0.94F, 0.9F);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityHuman entity) {
        return skin;
    }

}
