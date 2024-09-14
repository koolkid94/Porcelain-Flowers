package net.kuwulkid.porcelain.item.custom.unused;

import net.kuwulkid.porcelain.client.renderer.item.DeleterCubeRenderer;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.SingletonGeoAnimatable;
import software.bernie.geckolib.animatable.client.GeoRenderProvider;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.function.Consumer;

public final class DeleterCubeItem extends Item implements GeoItem {
    public int getMaxUseTime(ItemStack stack, LivingEntity user) {
        return 1048576;
    }
    public boolean alreadyStartedUsing = false;

    private static final RawAnimation IDLE = RawAnimation.begin().thenPlay("animation.deleter_cube.idle");
    private static final RawAnimation ATTACK_START = RawAnimation.begin().thenPlay("animation.deleter_cube.attack_start");
    private static final RawAnimation BEAM_LOOP = RawAnimation.begin().thenPlay("animation.deleter_cube.beam_loop");
    private static final RawAnimation ATTACK_END = RawAnimation.begin().thenPlay("animation.deleter_cube.attack_end");
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public DeleterCubeItem(Properties settings) {
        super(settings);

        SingletonGeoAnimatable.registerSyncedAnimatable(this);
    }

    @Override
    public void createGeoRenderer(Consumer<GeoRenderProvider> consumer) {
        consumer.accept(new GeoRenderProvider() {
            private DeleterCubeRenderer renderer;

            @Override
            public @Nullable BlockEntityWithoutLevelRenderer getGeoItemRenderer() {
                if (this.renderer == null)
                    this.renderer = new DeleterCubeRenderer();
                // Defer creation of our renderer then cache it so that it doesn't get instantiated too early

                return this.renderer;
            }
        });
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "on_attack_controller", 0, state -> PlayState.STOP)
                .triggerableAnim("attack_start", ATTACK_START));
        // We've marked the "attack_start" animation as being triggerable from the server

        controllers.add(new AnimationController<>(this, "beam_loop_controller", 0, state -> PlayState.STOP)
                .triggerableAnim("beam_loop", BEAM_LOOP)                .setSoundKeyframeHandler(state -> {
                    // Use helper method to avoid client-code in common class
                    //PlayerEntity player = ClientUtil.getClientPlayer();

                    //if (player != null)
                    //player.playSound(SoundRegistry.JACK_MUSIC, 1, 1);
                }));
        controllers.add(new AnimationController<>(this, "attack_end_controller", 0, state -> PlayState.STOP)
                .triggerableAnim("attack_end", ATTACK_END));
        controllers.add(new AnimationController<>(this, "idle_controller", 0, this::idlePredicate));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        if (world instanceof ServerLevel serverWorld) {
            if (!alreadyStartedUsing) {
                triggerAnim(player, GeoItem.getOrAssignId(player.getUseItem(), serverWorld), "on_attack_controller", "attack_start");
                alreadyStartedUsing = true;
            }
            triggerAnim(player, GeoItem.getOrAssignId(player.getUseItem(), serverWorld), "beam_loop_controller", "beam_loop");
        }
        return super.use(world, player, hand);
    }


    public void onStoppedUsing(ItemStack stack, Level world, LivingEntity user, int remainingUseTicks) {
        if (world instanceof ServerLevel serverWorld) {
            triggerAnim(user, GeoItem.getOrAssignId(user.getUseItem(), serverWorld), "attack_end_controller", "attack_end");
            alreadyStartedUsing = false;
        }
    }

    private PlayState idlePredicate(AnimationState<DeleterCubeItem> deleterCubeItemAnimationState) {
        if (alreadyStartedUsing) {
            deleterCubeItemAnimationState.getController().stop();
            return PlayState.STOP;
        }
        deleterCubeItemAnimationState.getController().setAnimation(IDLE);
        return PlayState.CONTINUE;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }
}
