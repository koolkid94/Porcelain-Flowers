package net.kuwulkid.porcelain.item.custom;

import net.kuwulkid.porcelain.client.renderer.item.VineTomeRenderer;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoAnimatable;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.SingletonGeoAnimatable;
import software.bernie.geckolib.animatable.client.GeoRenderProvider;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.util.GeckoLibUtil;
import net.minecraft.commands.Commands;

import net.minecraft.core.Registry;

import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;

import java.util.function.Consumer;


public final class VineTomeItem extends Item implements GeoItem {


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
            public BlockEntityWithoutLevelRenderer getGeoItemRenderer() {
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
        return ItemAnimationState.setAndContinue(IDLE);
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }



    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if (level instanceof ServerLevel serverWorld) {
            Commands commandManager = player.getServer().getCommands();
            commandManager.performCommand(player.createCommandSourceStack().dispatcher().parse("say hello",player.createCommandSourceStack() ), "this does nothing i think");
            if(player.getMainHandItem().equals(this.getDefaultInstance())){
                triggerAnim(player, GeoItem.getOrAssignId(player.getMainHandItem(), serverWorld ), "on_attack_controller", "open");
            }
            else{
                triggerAnim(player, GeoItem.getOrAssignId(player.getOffhandItem(), serverWorld ), "on_attack_controller2", "open2");
            }
        }
        player.awardStat(Stats.ITEM_USED.get(this));
        finishUsingItem(this.getDefaultInstance(), level, player);
        return ItemUtils.startUsingInstantly(level, player, hand);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity user) {

        return performAttack(stack, level, user);
    }

    public ItemStack performAttack(ItemStack stack, Level level, LivingEntity user) {
        if (user instanceof Player player) {
            player.playSound(SoundEvents.VILLAGER_TRADE, 1, 1);
            return stack;
        }
        return stack;
    }
}
