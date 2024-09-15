package net.kuwulkid.porcelain.item.custom;

import net.minecraft.client.resources.sounds.Sound;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import org.w3c.dom.Attr;


public class HungeringScytheItem extends SwordItem {
    public HungeringScytheItem(Tier tier, Properties properties) {
        super(tier, properties);
    }

    @Override
    public void postHurtEnemy(ItemStack itemStack, LivingEntity target, LivingEntity attacker) {
        itemStack.hurtAndBreak(1, attacker, EquipmentSlot.MAINHAND);
        attacker.level().playSound(null, attacker.getOnPos(), SoundEvents.EVOKER_FANGS_ATTACK, SoundSource.MASTER);
        double scale = attacker.getAttributeValue(Attributes.SCALE);
        double speed = attacker.getAttributeValue(Attributes.MOVEMENT_SPEED);
        double jump = attacker.getAttributeValue(Attributes.JUMP_STRENGTH);
        double armor = attacker.getAttributeValue(Attributes.ARMOR);
            if(scale < 1.5)
            {

            }
    }
}
