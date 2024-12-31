package com.shawnbyday.simpleflight.item;

import com.shawnbyday.simpleflight.SimpleFlight;
import com.shawnbyday.simpleflight.item.custom.ModArmorItem;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(SimpleFlight.MODID);

    public static final DeferredItem<ArmorItem> DIAMOND_CHESTPLATE = ITEMS.register("diamond_chestplate",
            () -> new ModArmorItem(ModArmorMaterials.DIAMOND_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(33))));
    public static final DeferredItem<ArmorItem> NETHERITE_CHESTPLATE = ITEMS.register("netherite_chestplate",
            () -> new ModArmorItem(ModArmorMaterials.NETHERITE_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE,
                    new Item.Properties().fireResistant().durability(ArmorItem.Type.CHESTPLATE.getDurability(37))));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
