package net.kuwulkid.porcelain.mixin;

import net.kuwulkid.porcelain.item.ModItems;
import net.minecraft.core.Holder;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.CombatTracker;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
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

    @Shadow public abstract ItemStack getItemInHand(InteractionHand hand);

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

        if(this.getAttribute(Attributes.MAX_ABSORPTION) != null && this.getItemInHand(InteractionHand.MAIN_HAND).equals(ModItems.HUNGERING_SCYTHE)){
            double strength = this.getAttributeValue(Attributes.MAX_ABSORPTION);
            System.out.println("I JUST GOT HURT HERE: " + amount + " AND MY STRENGTH IS " + strength);
            if(this.getAttributeValue(Attributes.MAX_ABSORPTION) > 1){
                double value2 = amount * 0.02;
                System.out.println("THE VALUE IS " + value2);
                if(strength - value2 > 1){

                    System.out.println("README BEFORE " + (strength));
                    Objects.requireNonNull(this.getAttribute(Attributes.MAX_ABSORPTION)).setBaseValue(strength - value2);
                    System.out.println("README AFTER " + (strength - value2));
                }
                else{
                    Objects.requireNonNull(this.getAttribute(Attributes.MAX_ABSORPTION)).setBaseValue(1);
                    System.out.println("JUST 1 ATTCK DAMAGE");
                }
            }
            else if(this.getAttributes().getValue(Attributes.MAX_ABSORPTION) < 1){
                Objects.requireNonNull(this.getAttribute(Attributes.MAX_ABSORPTION)).setBaseValue(1);
                System.out.println("JUST 1 ATTCKK DAMAGE");
            }
            else{
                Objects.requireNonNull(this.getAttribute(Attributes.MAX_ABSORPTION)).setBaseValue(1);
                System.out.println("JUST 1 ATTCKKK DAMAGE");
            }
        }

    }

    @Inject(at = @At("HEAD"), method = "baseTick")
    private void init(CallbackInfo ci) {
        if(this.getHealth() <= 0){
            Objects.requireNonNull(this.getAttribute(Attributes.SCALE)).setBaseValue(1);
            if(this.getAttribute(Attributes.MAX_ABSORPTION) != null){
                Objects.requireNonNull(this.getAttribute(Attributes.MAX_ABSORPTION)).setBaseValue(1);
            }
        }
    }
}
