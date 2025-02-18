package com.stormtrooper28.uglypipes;

import com.stormtrooper28.uglypipes.blocks.BlockInitializer;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;


/**
 * Build Drops for UglyPipes
 * Currently includes: UglyItemPipeBlock
 * */
public class UglyPipesLootProvider extends FabricBlockLootTableProvider {
    public UglyPipesLootProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        addDrop(BlockInitializer.ITEM_PIPE);
    }
}
