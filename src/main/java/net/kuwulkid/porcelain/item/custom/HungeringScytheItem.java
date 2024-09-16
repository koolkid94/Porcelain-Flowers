package net.kuwulkid.porcelain.item.custom;

import net.minecraft.client.resources.sounds.Sound;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import org.w3c.dom.Attr;

import java.util.Objects;


public class HungeringScytheItem extends SwordItem {
    public HungeringScytheItem(Tier tier, Properties properties) {
        super(tier, properties);
    }

    @Override
    public void postHurtEnemy(ItemStack itemStack, LivingEntity target, LivingEntity attacker) {
        itemStack.hurtAndBreak(1, attacker, EquipmentSlot.MAINHAND);
        double scale = attacker.getAttributeValue(Attributes.SCALE);
        BlockPos pos = target.getOnPos();
        double d =  0.6;
        double f =   0.6;
        target.level().addParticle(ParticleTypes.SCRAPE, (double)pos.getX() + d, (double)pos.getY() + 2, (double)pos.getZ() + f, 0.0, 0.0, 0.0);
        if(attacker instanceof Player player){
            player.causeFoodExhaustion(20);
            attacker.playSound(SoundEvents.PLAYER_BURP, 6, 4);
        }

        if(scale < 1.5)
        {
            Objects.requireNonNull(attacker.getAttribute(Attributes.SCALE)).setBaseValue(scale + 0.02);
            Objects.requireNonNull(attacker.getAttribute(Attributes.ATTACK_DAMAGE)).setBaseValue(scale + 0.02);
        }
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        if (entity instanceof LivingEntity thug && !isSelected  ) {
            double scale = thug.getAttributeValue(Attributes.SCALE);
            if ( Objects.requireNonNull(thug.getAttribute(Attributes.SCALE)).getBaseValue() > 1) {
                thug.playSound(SoundEvents.COPPER_HIT, 3, (float) (scale - 0.1));
                Objects.requireNonNull(thug.getAttribute(Attributes.SCALE)).setBaseValue(scale - 0.02);
                Objects.requireNonNull(thug.getAttribute(Attributes.ATTACK_DAMAGE)).setBaseValue(scale - 0.02);
                System.out.println("JUST 1 ATTCK DAMAGdE");

            }
        }
    }
}
