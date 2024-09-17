package net.kuwulkid.porcelain.item.custom;

import net.kuwulkid.porcelain.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.component.ItemAttributeModifiers;


public class QuartzSwordItem extends SwordItem {
    int numOne = 0, numTwo = 3;

    public QuartzSwordItem(Tier tier, Properties properties) {
        super(tier, properties);
        createAttributes(Tiers.GOLD, 12, 8);
    }

    public static ItemAttributeModifiers createAttributes(Tier tier, int i, float f) {
        return ItemAttributeModifiers.builder()
                .add(
                        Attributes.ATTACK_DAMAGE,
                        new AttributeModifier(BASE_ATTACK_DAMAGE_ID, (double)((float)i + tier.getAttackDamageBonus()), AttributeModifier.Operation.ADD_VALUE),
                        EquipmentSlotGroup.MAINHAND
                )
                .add(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_ID, (double)f, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND)
                .build();
    }




    @Override
    public void postHurtEnemy(ItemStack itemStack, LivingEntity target, LivingEntity attacker) {
        int rand = (int) ((5 - numOne + 1) * Math.random() + numOne);
        itemStack.hurtAndBreak( 2 , attacker, EquipmentSlot.MAINHAND);
        if(1 == (int) ((numTwo - numOne + 1) * Math.random() + numOne)) {
            BlockPos pos = target.blockPosition();
            target.spawnAtLocation(Items.QUARTZ);
            attacker.level().levelEvent(2002, attacker.blockPosition(), PotionContents.getColor(Potions.INVISIBILITY));
            attacker.playSound(SoundEvents.ITEM_BREAK, 3, 4);
            itemStack.hurtAndBreak(128, attacker, EquipmentSlot.MAINHAND);
        }

        if(attacker.getUseItem().getDamageValue() <= 0)
        {
            attacker.level().levelEvent(2002, attacker.blockPosition(), PotionContents.getColor(Potions.INVISIBILITY));
            target.spawnAtLocation(Items.QUARTZ);
            attacker.playSound(SoundEvents.ITEM_BREAK, 3, 4);
        }

        if(attacker instanceof Player){
            Player player = (Player) attacker;
            if( !player.getAbilities().instabuild){
                attacker.setItemInHand(InteractionHand.MAIN_HAND, ModItems.HILT.getDefaultInstance());
            }
        }
    }

}
