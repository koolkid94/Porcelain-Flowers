package net.kuwulkid.porcelain.mixin;

import net.kuwulkid.porcelain.item.ModItems;
import net.kuwulkid.porcelain.item.custom.HungeringScytheItem;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Objects;

@Mixin(Player.class)
public abstract class PlayerEnttyMixin extends LivingEntity {
    @Shadow @Final private Inventory inventory;

    protected PlayerEnttyMixin(EntityType<? extends LivingEntity> entityType, Level level) {
        super(entityType, level);
    }

    @Inject(at = @At("HEAD"), method = "drop*")
    private void init(CallbackInfoReturnable<Boolean> cir) {
        if (this.getAttributeValue(Attributes.SCALE) > 1) {
            double scale = this.getAttributeValue(Attributes.SCALE);
            if ( Objects.requireNonNull(this.getAttribute(Attributes.SCALE)).getBaseValue() > 1) {
                while(scale > 1){
                    this.playSound(SoundEvents.COPPER_HIT, 3, (float) (scale - 0.1));
                    this.playSound(SoundEvents.COPPER_HIT, 3, (float) (4 - 0.1));
                    Objects.requireNonNull(this.getAttribute(Attributes.SCALE)).setBaseValue(scale - 0.02);
                    Objects.requireNonNull(this.getAttribute(Attributes.ATTACK_DAMAGE)).setBaseValue(scale - 0.02);
                    scale = this.getAttributeValue(Attributes.SCALE);
                }
            }
        }


    }
}

