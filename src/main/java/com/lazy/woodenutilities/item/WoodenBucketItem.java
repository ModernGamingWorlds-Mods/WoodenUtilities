package com.lazy.woodenutilities.item;

import com.lazy.woodenutilities.WoodenUtilities;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BucketItem;

public class WoodenBucketItem extends BucketItem {

    public WoodenBucketItem() {
        super(Fluids.EMPTY, new Properties().group(WoodenUtilities.WOODEN_UTILITIES));
    }
}
