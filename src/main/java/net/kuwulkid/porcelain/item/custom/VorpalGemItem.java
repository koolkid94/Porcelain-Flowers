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
import net.minecraft.world.item.component.ItemAttributeModifiers;

public class VorpalGemItem extends SwordItem {
    int numOne = 0, numTwo = 6;

    public VorpalGemItem(Tier tier, Properties properties) {
        super(tier, properties);
        createAttributes(Tiers.GOLD, 6, 4);
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
        itemStack.hurtAndBreak(1, attacker, EquipmentSlot.MAINHAND);

        EntityType.EXPERIENCE_BOTTLE.spawn((ServerLevel) target.level(), null, null,  attacker.blockPosition(), MobSpawnType.EVENT, true, true);



        target.level().addParticle(ParticleTypes.TOTEM_OF_UNDYING, (double)attacker.blockPosition().getX()+ Math.random(), (double)attacker.blockPosition().getY()+ 1, (double)attacker.blockPosition().getZ() + Math.random(), Math.random() * (1.001) , Math.random()  * (1.2), Math.random()  * (1.001));

        if(1 == (int) ((numTwo - numOne + 1) * Math.random() + numOne)) {
            BlockPos pos = target.blockPosition();
            target.spawnAtLocation(Items.AMETHYST_SHARD);
            itemStack.hurtAndBreak(1, attacker, EquipmentSlot.MAINHAND);
            attacker.playSound(SoundEvents.ITEM_BREAK, 3, 4);
        }
    }

}
