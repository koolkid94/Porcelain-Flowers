package net.kuwulkid.porcelain.entity.custom;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.*;
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

public class JungleLaborerEntity extends VillagerEntity implements GeoEntity{
    int output, numOne = 0, numTwo = 4;
    protected static final RawAnimation WALK_ANIM = RawAnimation.begin().thenLoop("animation.villager.walk");
    protected static final RawAnimation IDLE_ANIM = RawAnimation.begin().thenLoop("animation.villager.idle");
    protected static final RawAnimation IDLE_ANIM2 = RawAnimation.begin().thenLoop("animation.villager.fishing");
    protected static final RawAnimation SWIM_ANIM = RawAnimation.begin().thenLoop("animation.villager.swim");
    private final AnimatableInstanceCache geoCache = GeckoLibUtil.createInstanceCache(this);
   public JungleLaborerEntity(EntityType<? extends VillagerEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "Walking", 5, this::walkAnimController));
    }


    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.geoCache;
    }

    public static DefaultAttributeContainer.Builder createJungleLaborerAttributes(){
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 20);

    }


    protected <E extends JungleLaborerEntity> PlayState walkAnimController(final AnimationState<E> event) {

        output = (int) ((numTwo - numOne + 1) * Math.random() + numOne);
        if (event.isMoving() && !isWet()) {
            return event.setAndContinue(WALK_ANIM);
        }
        else if(isTouchingWater())
        {
            return event.setAndContinue(SWIM_ANIM);
        }
        else if((output == 3) && !event.isMoving())
        {
            return event.setAndContinue(IDLE_ANIM2);
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

    @Override
    public Iterable<ItemStack> getArmorItems() {
        return null;
    }

    @Override
    public ItemStack getEquippedStack(EquipmentSlot slot) {
        return null;
    }

    @Override
    public void equipStack(EquipmentSlot slot, ItemStack stack) {

    }

    @Override
    public Arm getMainArm() {
        return null;
    }

    protected void initGoals() {
        this.goalSelector.add(0, new EscapeDangerGoal(this, 1.25));
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.add(3, new WanderAroundFarGoal(this, 1.0));
    }


}
