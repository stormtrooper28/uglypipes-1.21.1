package com.stormtrooper28.uglypipes.events;

import com.stormtrooper28.uglypipes.blocks.ConnectingPipeBlock;
import com.stormtrooper28.uglypipes.blocks.UglyFunctionalBlock;
import com.stormtrooper28.uglypipes.items.tools.UglyWrenchItem;
import net.fabricmc.fabric.api.event.player.AttackBlockCallback;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;

public class UglyEventHandler {
    public static void registerEvents() {
        uglyWrenchEvents();
    }

    private static void uglyWrenchEvents() {
        // Ugly Wrench Starts to break blocks
        AttackBlockCallback.EVENT.register(((playerEntity, world, hand, blockPos, direction) -> {
            // Makes sure that the player isn't sneaking (@@@plans as this will have a special interaction later)
            if(world instanceof ServerWorld serverWorld && !playerEntity.isSneaking()) {
                if(playerEntity.getStackInHand(hand).getItem() instanceof UglyWrenchItem) {
                    if(serverWorld.getBlockState(blockPos).getBlock() instanceof UglyFunctionalBlock) {
                        serverWorld.breakBlock(blockPos, true);
                        serverWorld.playSound(null, blockPos, SoundEvents.BLOCK_AMETHYST_BLOCK_RESONATE, SoundCategory.PLAYERS, 1f, 1f);
                        return ActionResult.SUCCESS;
                    }
                }
            }
            return ActionResult.PASS;
        }));
    }
}
