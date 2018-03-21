package iea.iearthia.humans.entity;

import iea.iearthia.humans.Humans;
import iea.iearthia.humans.handlers.SoundsHandler;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class EntityHuman extends EntityMob {

    private static final DataParameter<Integer> HUMAN_VARIANT = EntityDataManager.<Integer>createKey(EntityHuman.class, DataSerializers.VARINT);
    private static final DataParameter<Boolean> HUMAN_IS_LEGACY = EntityDataManager.<Boolean>createKey(EntityHuman.class, DataSerializers.BOOLEAN);


    public EntityHuman(World worldIn) {
        super(worldIn);
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataManager.register(HUMAN_VARIANT, 0);
        this.dataManager.register(HUMAN_IS_LEGACY, false);
    }

    @Override
    @Nullable
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata) {
        livingdata = super.onInitialSpawn(difficulty, livingdata);
        int v;
        boolean isLegacy;
        int r = this.rand.nextInt(20);
        if (livingdata instanceof EntityHuman.GroupData) {
            v = ((GroupData)livingdata).variant;
            isLegacy = (((GroupData) livingdata).isLegacy);
        } else {
            v = this.rand.nextInt(5);
            isLegacy = (v == 4 || r == 5);
            livingdata = new GroupData(v, isLegacy);
        }

        this.setHumanVariant(v);
        this.setHumanIsLegacy(isLegacy);
        if (this.getHumanVariant() > 1) {
            switch (this.getHumanVariant()) {
                case 3:
                    this.setCustomNameTag("JadeIEA");
                    break;
                default:
                    break;
            }
        }
        this.setSize(0.6F, 1.6F);
        return livingdata;
    }
    public static class GroupData implements IEntityLivingData {
        public int variant;
        public boolean isLegacy;

        public GroupData(int variantIn, boolean isLegacyIn) {
            this.variant = variantIn;
            this.isLegacy = isLegacyIn;
        }
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);
        this.setHumanVariant(compound.getInteger("Variant"));
        this.setHumanIsLegacy(compound.getBoolean("IsLegacy"));
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound compound) {
        super.writeEntityToNBT(compound);
        compound.setInteger("Variant", this.getHumanVariant());
        compound.setBoolean("IsLegacy", this.getHumanIsLegacy());
    }

    public void setHumanVariant(int variant) {
        this.dataManager.set(HUMAN_VARIANT, Integer.valueOf(variant));
    }

    public int getHumanVariant() {
        return ((Integer)this.dataManager.get(HUMAN_VARIANT).intValue());
    }

    public void setHumanIsLegacy(boolean isLegacy) {
        this.dataManager.set(HUMAN_IS_LEGACY, isLegacy);
    }

    public boolean getHumanIsLegacy() {
        return ((Boolean)this.dataManager.get(HUMAN_IS_LEGACY).booleanValue());
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(1.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.10000000149011612D);
    }

    @Override
    protected void initEntityAI() {
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(7, new EntityAIWander(this, 2.0D));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
    }
    @Override
    public boolean isChild() {
        return false;
    }

    @Override
    protected SoundEvent getDeathSound() {
        if (this.getHumanIsLegacy() || this.getHumanVariant() == 4) {
            return SoundsHandler.ENTITY_HUMAN_HURT;
        } else {
            return super.getDeathSound();
        }
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        if (this.getHumanIsLegacy() || this.getHumanVariant() == 4) {
            return SoundsHandler.ENTITY_HUMAN_HURT;
        } else {
            return super.getHurtSound(damageSourceIn);
        }
    }
}
