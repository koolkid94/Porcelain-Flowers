package net.kuwulkid.porcelain.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.component.ItemAttributeModifiers;

public class VorpalGemItem extends SwordItem {
    int numOne = 0, numTwo = 3;

    public VorpalGemItem(Tier tier, Properties properties) {
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
        target.level().addParticle(ParticleTypes.TOTEM_OF_UNDYING, (double)attacker.blockPosition().getX()+ Math.random(), (double)attacker.blockPosition().getY()+ 1, (double)attacker.blockPosition().getZ() + Math.random(), Math.random() * (1.001) , Math.random()  * (1.2), Math.random()  * (1.001));

        if(1 == (int) ((numTwo - numOne + 1) * Math.random() + numOne)) {
            BlockPos pos = target.blockPosition();
            target.spawnAtLocation(Items.AMETHYST_SHARD);
            attacker.level().levelEvent(2002, attacker.blockPosition(), PotionContents.getColor(Potions.TURTLE_MASTER));
            attacker.playSound(SoundEvents.ITEM_BREAK, 3, 4);
            itemStack.hurtAndBreak(128, attacker, EquipmentSlot.MAINHAND);
        }

        if(attacker.getUseItem().getDamageValue() <= 0)
        {
            attacker.level().levelEvent(2002, attacker.blockPosition(), PotionContents.getColor(Potions.TURTLE_MASTER));
            target.spawnAtLocation(Items.AMETHYST_SHARD);
            attacker.playSound(SoundEvents.ITEM_BREAK, 3, 4);
        }
    }

}
