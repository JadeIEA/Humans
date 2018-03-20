package iea.iearthia.humans.entity;

import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class EntityHuman extends EntityMob {

    private static final DataParameter<Integer> HUMAN_VARIANT = EntityDataManager.<Integer>createKey(EntityHuman.class, DataSerializers.VARINT);

    public EntityHuman(World worldIn) {
        super(worldIn);
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataManager.register(HUMAN_VARIANT, 0);
    }

    @Override
    @Nullable
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata) {
        livingdata = super.onInitialSpawn(difficulty, livingdata);
        int v;

        if (livingdata instanceof EntityHuman.GroupData) {
            v = ((GroupData)livingdata).variant;
        } else {
            v = this.rand.nextInt(4);
            livingdata = new GroupData(v);
        }

        this.setHumanVariant(v);
        this.setSize(0.6F, 1.8F);
        return livingdata;
    }
    public static class GroupData implements IEntityLivingData {
        public int variant;

        public GroupData(int variantIn) {
            this.variant = variantIn;
        }
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);
        this.setHumanVariant(compound.getInteger("Variant"));
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound compound) {
        super.writeEntityToNBT(compound);
        compound.setInteger("Variant", this.getHumanVariant());
    }

    public void setHumanVariant(int variant) {
        this.dataManager.set(HUMAN_VARIANT, Integer.valueOf(variant));
    }

    public int getHumanVariant() {
        return ((Integer)this.dataManager.get(HUMAN_VARIANT).intValue());
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
}
