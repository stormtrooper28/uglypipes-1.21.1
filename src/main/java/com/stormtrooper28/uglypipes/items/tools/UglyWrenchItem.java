package com.stormtrooper28.uglypipes.items.tools;

import com.stormtrooper28.uglypipes.blocks.UglyItemPipeBlock;
import net.minecraft.block.Block;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;

import java.util.List;

public class UglyWrenchItem extends Item {
    public UglyWrenchItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        return ActionResult.PASS;
    }



    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        entity.bodyYaw = safeRotate(entity.bodyYaw, user.isSneaking());
        entity.headYaw = safeRotate(entity.headYaw, user.isSneaking());

        if(user.getWorld() instanceof ServerWorld serverWorld)
            serverWorld.playSound(null, user.getBlockPos(), SoundEvents.BLOCK_AMETHYST_BLOCK_CHIME, SoundCategory.PLAYERS, 3f, 1f);

        return ActionResult.SUCCESS;
    }

    /**
     * Rotates the given yaw by ±90 degrees (taking into account Minecraft's range from -180 to 180)
     * @param yaw entity yaw to rotate
     * @param reverse true: rotate in positive yaw, false: rotate in negative yaw
     * @return rotated yaw
     */
    private float safeRotate(float yaw, boolean reverse) {
        if(reverse) {
            if (yaw >= 90f)
                return yaw - 270f;
            else
                return yaw + 90f;
        }
        else {
            if (yaw <= -90f)
                return yaw + 270f;
            else
                return yaw - 90f;
        }
    }

    @Override
    public int getMaxCount() {
        return 1;
    }

    @Override
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType options) {
        tooltip.add(Text.translatable("tooltip.uglypipes.ugly_wrench_0"));
        tooltip.add(Text.translatable("tooltip.uglypipes.ugly_wrench_1"));
        tooltip.add(Text.translatable("tooltip.uglypipes.ugly_wrench_2"));
        tooltip.add(Text.translatable("tooltip.uglypipes.ugly_wrench_3"));
        super.appendTooltip(stack, context, tooltip, options);
    }
}
