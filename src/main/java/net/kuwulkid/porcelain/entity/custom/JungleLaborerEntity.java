package net.kuwulkid.porcelain.entity.custom;

//import net.minecraft.server.level.ServerLevel;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Arm;
import net.minecraft.world.World;
import net.tslat.smartbrainlib.api.SmartBrainOwner;
import net.tslat.smartbrainlib.api.core.SmartBrainProvider;
import net.tslat.smartbrainlib.api.core.sensor.ExtendedSensor;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.List;

public class JungleLaborerEntity extends AnimalEntity implements GeoEntity{
    int output, numOne = 0, numTwo = 4;
    protected static final RawAnimation WALK_ANIM = RawAnimation.begin().thenLoop("animation.villager.walk");
    protected static final RawAnimation IDLE_ANIM = RawAnimation.begin().thenLoop("animation.villager.idle");
    protected static final RawAnimation IDLE_ANIM2 = RawAnimation.begin().thenLoop("animation.villager.fishing");
    protected static final RawAnimation SWIM_ANIM = RawAnimation.begin().thenLoop("animation.villager.swim");
    private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);
   public JungleLaborerEntity(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }

    public static DefaultAttributeContainer.Builder createJungleLaborerAttributes(){
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 20)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED,0.21);

    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return false;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "Walking", 5, this::walkAnimController));
    }


    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.geoCache;
    }



    protected <E extends JungleLaborerEntity> PlayState walkAnimController(final AnimationState<E> event) {

        if (event.isMoving() && !isTouchingWater()) {
            return event.setAndContinue(WALK_ANIM);
        }
        else if(isTouchingWater())
        {
            return event.setAndContinue(SWIM_ANIM);
        }
        else
            return event.setAndContinue(IDLE_ANIM);

    }

    @Nullable
    protected SoundEvent getAmbientSound() {
            return SoundEvents.ENTITY_VILLAGER_AMBIENT;

    }

    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_VILLAGER_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_VILLAGER_DEATH;
    }

    protected void initGoals() {
        this.goalSelector.add(1, new SwimGoal(this));
        this.goalSelector.add(2, new WanderAroundFarGoal(this, 1.0));
        this.goalSelector.add(3, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.add(4, new LookAroundGoal(this));
        this.goalSelector.add(5, new LookAtEntityGoal(this, MobEntity.class, 8.0F));
    }


    @Nullable
    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return null;
    }
}
