package com.bedrocklegends.woodenutilities.api.resin;

import net.minecraft.block.Block;

import java.util.Objects;

//Modders don't need to implement this is any way or form
//If you want to add your own resin provider add it to the Resin Provider registry
public class ResinProvider {

    private final int amount;
    private final Block resinProvider;

    public ResinProvider(int amount, Block resinProvider) {
        this.amount = amount;
        this.resinProvider = resinProvider;
    }

    public Block getResinProvider() {
        return this.resinProvider;
    }

    public int getAmount() {
        return this.amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ResinProvider)) return false;
        ResinProvider that = (ResinProvider) o;
        return amount == that.amount && Objects.equals(resinProvider, that.resinProvider);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, resinProvider);
    }

    @Override
    public String toString() {
        return "ResinProvider{" +
                "amount=" + amount +
                ", resinProvider=" + resinProvider +
                '}';
    }
}
