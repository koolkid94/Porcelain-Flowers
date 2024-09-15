package net.kuwulkid.porcelain.mixin;

import net.minecraft.core.Holder;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Objects;

@Mixin(LivingEntity.class)
//actuallyHurt
public abstract class LivingEnttyMixin {
    @Shadow public abstract float getScale();

    @Shadow
    public abstract @Nullable AttributeInstance getAttribute(Holder<Attribute> holder);

    @Shadow public abstract float getSpeed();

    @Shadow public abstract double getAttributeBaseValue(Holder<Attribute> attribute);

    @Inject(at = @At("HEAD"), method = "hurt")
    private void init(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {

    }
}
