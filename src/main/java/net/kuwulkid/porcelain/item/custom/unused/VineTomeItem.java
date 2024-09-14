package net.kuwulkid.porcelain.item.custom.unused;

import net.kuwulkid.porcelain.client.renderer.item.VineTomeRenderer;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.commands.Commands;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.EvokerFangs;
import net.minecraft.world.item.BookItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.SingletonGeoAnimatable;
import software.bernie.geckolib.animatable.client.GeoRenderProvider;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.function.Consumer;


public final class VineTomeItem extends BookItem implements GeoItem {


    private static final RawAnimation IDLE = RawAnimation.begin().thenPlay("animation.vinetomeitem.idle");
    private static final RawAnimation OPEN = RawAnimation.begin().thenPlay("animation.vinetomeitem.open");
    private static final RawAnimation OPEN2 = RawAnimation.begin().thenPlay("animation.vinetomeitem.open2");

    public boolean inuse = false;

    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public VineTomeItem(Properties properties) {
        super(properties);

        SingletonGeoAnimatable.registerSyncedAnimatable(this);
    }

    @Override
    public void createGeoRenderer(Consumer<GeoRenderProvider> consumer) {
        consumer.accept(new GeoRenderProvider() {
            private VineTomeRenderer renderer;

            @Override
            public @Nullable BlockEntityWithoutLevelRenderer getGeoItemRenderer() {
                if (this.renderer == null)
                    this.renderer = new VineTomeRenderer();
                // Defer creation of our renderer then cache it so that it doesn't get instantiated too early

                return this.renderer;
            }
        });
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {

        controllers.add(new AnimationController<>(this, "on_attack_controller", 0, state -> PlayState.STOP)
                .triggerableAnim("open", OPEN));

        controllers.add(new AnimationController<>(this, "on_attack_controller2", 0, state -> PlayState.STOP)
                .triggerableAnim("open2", OPEN2));

        controllers.add(new AnimationController<>(this, "idle_controller", 0, this::idlePredicate));

    }

    private PlayState idlePredicate(AnimationState<VineTomeItem> ItemAnimationState) {
        ItemAnimationState.getController().setAnimation(IDLE);
        return PlayState.CONTINUE;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        int cooldown = 120;
        player.getCooldowns().addCooldown(this, cooldown);
        if (level instanceof ServerLevel serverWorld) {
            Commands commandManager = player.getServer().getCommands();
            commandManager.performCommand(player.createCommandSourceStack().dispatcher().parse("gamerule sendCommandFeedback false",player.createCommandSourceStack() ), "this does nothing i think");
            commandManager.performCommand(player.createCommandSourceStack().dispatcher().parse("execute at @s run particle minecraft:enchant ~ ~1.7 ~ .1 .1 .2 0.055 10 force",player.createCommandSourceStack() ), "this does nothing i think");

            if(player.getMainHandItem().toString().equals("1 porcelainflowers:vinetomeitem")){
                triggerAnim(player, GeoItem.getOrAssignId(player.getMainHandItem(), serverWorld ), "on_attack_controller", "open");
            }
            else{
                triggerAnim(player, GeoItem.getOrAssignId(player.getOffhandItem(), serverWorld ), "on_attack_controller2", "open2");
            }
        }

        //import net.minecraft.world.phys.HitResult;
        HitResult target = player.pick(20, 0, true);

        Vec3 pos = target.getLocation();
        double x = pos.x();
        double y = pos.y();
        double z = pos.z();

        //this summons the spike
        level.addFreshEntity(new EvokerFangs(level, x, y, z, (float) y, 20, player));

        Vec3 lookAngle = player.getLookAngle();
        Vec3 adjustedAngle = lookAngle.add(player.getX(), player.getY(), player.getZ());

        player.playSound(SoundEvents.ENCHANTMENT_TABLE_USE, 1, 1);
        player.awardStat(Stats.ITEM_USED.get(this));
        return super.use(level, player, hand);
    }


    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

}
