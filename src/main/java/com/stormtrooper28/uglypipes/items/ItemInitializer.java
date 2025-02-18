package com.stormtrooper28.uglypipes.items;

import com.stormtrooper28.uglypipes.UglyPipes;
import com.stormtrooper28.uglypipes.items.tools.UglyWrenchItem;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ItemInitializer {
    public static final Item UGLY_WRENCH = registerItem("ugly_wrench", new UglyWrenchItem(new Item.Settings()));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(UglyPipes.MOD_ID, name), item);
    }

    public static void initItems() {
        UglyPipes.LOGGER.info("Adding holdable ugly stuff");
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(entries -> {
            entries.add(UGLY_WRENCH);
        });
    }
}
