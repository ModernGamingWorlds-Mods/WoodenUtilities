package com.bedrocklegends.woodenutilities.item;

import com.bedrocklegends.woodenutilities.setup.WoodenItems;
import com.lazy.baubles.api.bauble.BaubleType;
import com.lazy.baubles.api.bauble.IBauble;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class WoodenRingItem extends Item implements IBauble {

    public WoodenRingItem(Properties properties) {
        super(properties);
    }

    public WoodenRingItem() {
        this(new Properties().maxStackSize(1).group(WoodenItems.WOODEN_UTILITIES_TAB));
    }

    @Override
    public BaubleType getBaubleType(ItemStack stack) {
        return BaubleType.RING;
    }
}
