package com.traumere.bedrocraft.util;

import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;

/**
 * 一个用于简化物品栏实现的默认接口。
 * 只要你的类实现了这个接口，并提供一个 DefaultedList，就能自动获得所有物品栏的基础功能。
 */
public interface ImplementedInventory extends Inventory {

    // 获取物品列表，你的 BlockEntity 需要提供这个列表
    DefaultedList<ItemStack> getItems();

    @Override
    default int size() { return getItems().size(); }

    @Override
    default boolean isEmpty() {
        for (int i = 0; i < size(); i++) {
            ItemStack stack = getStack(i);
            if (!stack.isEmpty()) return false;
        }
        return true;
    }

    @Override
    default ItemStack getStack(int slot) { return getItems().get(slot); }

    @Override
    default ItemStack removeStack(int slot, int count) {
        ItemStack result = Inventories.splitStack(getItems(), slot, count);
        if (!result.isEmpty()) markDirty();
        return result;
    }

    @Override
    default ItemStack removeStack(int slot) {
        return Inventories.removeStack(getItems(), slot);
    }

    @Override
    default void setStack(int slot, ItemStack stack) {
        getItems().set(slot, stack);
        if (stack.getCount() > getMaxCountPerStack()) {
            stack.setCount(getMaxCountPerStack());
        }
        markDirty();
    }

    @Override
    default void clear() { getItems().clear(); }

    @Override
    default void markDirty() {}

    @Override
    default boolean canPlayerUse(net.minecraft.entity.player.PlayerEntity player) { return true; }
}
