package net.kuwulkid.porcelain.mixin;

import net.minecraft.core.Holder;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.damagesource.CombatTracker;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Objects;

@Mixin(LivingEntity.class)
//yes its misspellt
public abstract class LivingEnttyMixin {
    @Shadow public abstract float getScale();

    @Shadow
    public abstract @Nullable AttributeInstance getAttribute(Holder<Attribute> holder);

    @Shadow public abstract float getSpeed();

    @Shadow public abstract double getAttributeBaseValue(Holder<Attribute> attribute);

    @Shadow public abstract AttributeMap getAttributes();

    @Shadow public abstract double getAttributeValue(Holder<Attribute> attribute);

    @Shadow public abstract CombatTracker getCombatTracker();

    @Shadow public abstract float getHealth();

    @Inject(at = @At("HEAD"), method = "hurt")
    private void init(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        double scale = this.getAttributeValue(Attributes.SCALE);
        //10          4
            if(this.getAttributes().getValue(Attributes.SCALE) > 1){
                double value = amount * 0.02;
                if(scale - value > 1){
                    Objects.requireNonNull(this.getAttribute(Attributes.SCALE)).setBaseValue(scale - value);
                }
                else{
                    Objects.requireNonNull(this.getAttribute(Attributes.SCALE)).setBaseValue(1);
                }
            }
            else if(this.getAttributes().getValue(Attributes.SCALE) < 1){
                Objects.requireNonNull(this.getAttribute(Attributes.SCALE)).setBaseValue(1);
            }
            else{
                Objects.requireNonNull(this.getAttribute(Attributes.SCALE)).setBaseValue(1);
            }

        if(this.getAttribute(Attributes.ATTACK_DAMAGE) != null){
            double strength = this.getAttributeValue(Attributes.ATTACK_DAMAGE);
            if(this.getAttributes().getValue(Attributes.ATTACK_DAMAGE) > 1){
                double value = amount * 0.02;
                if(strength - value > 1){
                    Objects.requireNonNull(this.getAttribute(Attributes.ATTACK_DAMAGE)).setBaseValue(strength - value);
                }
                else{
                    Objects.requireNonNull(this.getAttribute(Attributes.ATTACK_DAMAGE)).setBaseValue(1);
                }
            }
            else if(this.getAttributes().getValue(Attributes.ATTACK_DAMAGE) < 1){
                Objects.requireNonNull(this.getAttribute(Attributes.ATTACK_DAMAGE)).setBaseValue(1);
            }
            else{
                Objects.requireNonNull(this.getAttribute(Attributes.ATTACK_DAMAGE)).setBaseValue(1);
            }
        }

    }

    @Inject(at = @At("HEAD"), method = "baseTick")
    private void init(CallbackInfo ci) {
            if(this.getHealth() <= 0){
                Objects.requireNonNull(this.getAttribute(Attributes.SCALE)).setBaseValue(1);
                if(this.getAttribute(Attributes.ATTACK_DAMAGE) != null){
                    Objects.requireNonNull(this.getAttribute(Attributes.ATTACK_DAMAGE)).setBaseValue(1);
                }
            }
    }
}
