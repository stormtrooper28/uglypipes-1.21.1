package com.stormtrooper28.uglypipes.blocks;

import net.minecraft.block.Block;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;

import java.util.List;

public class UglyItemPipeBlock extends ConnectingPipeBlock /*ConnectingBlock*/ {
    public UglyItemPipeBlock(Settings settings) {
        super(settings);
    }

    @Override
    public float getSlipperiness() {
        return 1.1f;
    }

    @Override
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType options) {
        if(Screen.hasShiftDown()) {

        }
        else
            tooltip.add(Text.translatable("tooltip.uglypipes.ugly_item_block_shift_msg"));
        super.appendTooltip(stack, context, tooltip, options);
    }
}
