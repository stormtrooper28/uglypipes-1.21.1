package com.stormtrooper28.uglypipes.blocks;

import com.stormtrooper28.uglypipes.UglyPipes;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.EntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class BlockInitializer {
    public static final Block ITEM_PIPE = registerBlock("item_pipe", new UglyItemPipeBlock(AbstractBlock.Settings.create().strength(1f).nonOpaque().sounds(BlockSoundGroup.CHAIN).pistonBehavior(PistonBehavior.DESTROY).allowsSpawning(Blocks::never)));

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(UglyPipes.MOD_ID, name), new BlockItem(block, new Item.Settings()));
    }
    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(UglyPipes.MOD_ID, name), block);
    }

    public static void initBlocks() {
        UglyPipes.LOGGER.info("Adding Placable ugly stuff");

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(entries -> {
            entries.add(ITEM_PIPE);
        });
    }
}
