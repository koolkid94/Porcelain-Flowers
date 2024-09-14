package net.kuwulkid.porcelain.entity.custom.unused;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TraceableEntity;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.UUID;

public class SpikeEntity extends Entity implements GeoEntity, TraceableEntity {
    public static final int ATTACK_DURATION = 20;
    public static final int LIFE_OFFSET = 2;
    public static final int ATTACK_TRIGGER_TICKS = 14;
    private int warmupDelayTicks;
    private boolean sentSpikeEvent;
    private int lifeTicks = 22;
    private boolean clientSideAttackStarted;
    @Nullable
    private LivingEntity owner;
    @Nullable
    private UUID ownerUUID;
    private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);

    public SpikeEntity(EntityType<Entity> entityType, Level level) {
        super(entityType, level);
    }

    public SpikeEntity(Level level, double x, double y, double z, float yRot, int warmupDelay, LivingEntity owner) {
        super(EntityType.EVOKER_FANGS, level);
        //this(ModEntities.SPIKE, level);
        this.warmupDelayTicks = warmupDelay;
        this.setOwner(owner);
        this.setYRot(yRot * (180.0F / (float)Math.PI));
        this.setPos(x, y, z);
    }




    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag compound) {
        this.warmupDelayTicks = compound.getInt("Warmup");
        if (compound.hasUUID("Owner")) {
            this.ownerUUID = compound.getUUID("Owner");
        }
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag compound) {
        compound.putInt("Warmup", this.warmupDelayTicks);
        if (this.ownerUUID != null) {
            compound.putUUID("Owner", this.ownerUUID);
        }
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {

    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.geoCache;
    }

    public void setOwner(@Nullable LivingEntity owner) {
        this.owner = owner;
        this.ownerUUID = owner == null ? null : owner.getUUID();
    }

    @Override
    public void tick() {
        super.tick();
        if (this.level().isClientSide) {
            if (this.clientSideAttackStarted) {
                this.lifeTicks--;
                if (this.lifeTicks == 14) {
                    for (int i = 0; i < 12; i++) {
                        double d = this.getX() + (this.random.nextDouble() * 2.0 - 1.0) * (double)this.getBbWidth() * 0.5;
                        double e = this.getY() + 0.05 + this.random.nextDouble();
                        double f = this.getZ() + (this.random.nextDouble() * 2.0 - 1.0) * (double)this.getBbWidth() * 0.5;
                        double g = (this.random.nextDouble() * 2.0 - 1.0) * 0.3;
                        double h = 0.3 + this.random.nextDouble() * 0.3;
                        double j = (this.random.nextDouble() * 2.0 - 1.0) * 0.3;
                        this.level().addParticle(ParticleTypes.CRIT, d, e + 1.0, f, g, h, j);
                    }
                }
            }
        } else if (--this.warmupDelayTicks < 0) {
            if (this.warmupDelayTicks == -8) {
                for (LivingEntity livingEntity : this.level().getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(0.2, 0.0, 0.2))) {
                    this.dealDamageTo(livingEntity);
                }
            }

            if (!this.sentSpikeEvent) {
                this.level().broadcastEntityEvent(this, (byte)4);
                this.sentSpikeEvent = true;
            }

            if (--this.lifeTicks < 0) {
                this.discard();
            }
        }
    }

    private void dealDamageTo(LivingEntity target) {
        LivingEntity livingEntity = (LivingEntity) this.getOwner();
        if (target.isAlive() && !target.isInvulnerable() && target != livingEntity) {
            if (livingEntity == null) {
                target.hurt(this.damageSources().magic(), 6.0F);
            } else {
                if (livingEntity.isAlliedTo(target)) {
                    return;
                }

                DamageSource damageSource = this.damageSources().indirectMagic(this, livingEntity);
                if (target.hurt(damageSource, 6.0F) && this.level() instanceof ServerLevel serverLevel) {
                    EnchantmentHelper.doPostAttackEffects(serverLevel, target, damageSource);
                }
            }
        }
    }

    @Override
    public void handleEntityEvent(byte id) {
        super.handleEntityEvent(id);
        if (id == 4) {
            this.clientSideAttackStarted = true;
            if (!this.isSilent()) {
                this.level()
                        .playLocalSound(
                                this.getX(), this.getY(), this.getZ(), SoundEvents.DRIPSTONE_BLOCK_FALL, this.getSoundSource(), 1.0F, this.random.nextFloat() * 0.2F + 0.85F, false
                        );
            }
        }
    }

    @Nullable
    public LivingEntity getOwner() {
        if (this.owner == null && this.ownerUUID != null && this.level() instanceof ServerLevel) {
            Entity entity = ((ServerLevel)this.level()).getEntity(this.ownerUUID);
            if (entity instanceof LivingEntity) {
                this.owner = (LivingEntity)entity;
            }
        }

        return this.owner;
    }
}
