package com.stormtrooper28.uglypipes;

import com.stormtrooper28.uglypipes.blocks.BlockInitializer;
import com.stormtrooper28.uglypipes.events.UglyEventHandler;
import com.stormtrooper28.uglypipes.items.ItemInitializer;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UglyPipes implements ModInitializer {
	public static final String MOD_ID = "uglypipes";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	/**
	 * Registers the UglyPipes Creative Tab ("Item Group")
	 */
	public static final ItemGroup UglyPipesItemGroup = Registry.register(Registries.ITEM_GROUP, Identifier.of(MOD_ID, "ugly_pipes_item_group"),
			FabricItemGroup.builder().icon(() -> new ItemStack(ItemInitializer.UGLY_WRENCH))
					.displayName(Text.translatable("item_group.ugly_pipes.all"))
					.entries((displayContext, entries) -> {
						entries.add(ItemInitializer.UGLY_WRENCH);
						entries.add(BlockInitializer.ITEM_PIPE);
					}).build());

	@Override
	public void onInitialize() {
		LOGGER.info("Pipes so ugly your machines break :)");
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		ItemInitializer.initItems();
		BlockInitializer.initBlocks();
		UglyEventHandler.registerEvents();

		LOGGER.info("Done making your world uglier by adding stuff!");
	}
}