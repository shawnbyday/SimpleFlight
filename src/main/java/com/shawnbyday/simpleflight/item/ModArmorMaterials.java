package com.shawnbyday.simpleflight.item;

import com.shawnbyday.simpleflight.SimpleFlight;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.EnumMap;
import java.util.List;
import java.util.function.Supplier;

public class ModArmorMaterials {
    public static final Holder<ArmorMaterial> DIAMOND_ARMOR_MATERIAL = register("diamond_chestplate",
            Util.make(new EnumMap<>(ArmorItem.Type.class), attribute -> {
                attribute.put(ArmorItem.Type.CHESTPLATE, 8);
            }), 10, 2.0F, 0.0F, SoundEvents.ARMOR_EQUIP_DIAMOND, () -> Items.DIAMOND);
    public static final Holder<ArmorMaterial> NETHERITE_ARMOR_MATERIAL = register("netherite_chestplate",
            Util.make(new EnumMap<>(ArmorItem.Type.class), attribute -> {
                attribute.put(ArmorItem.Type.CHESTPLATE, 8);
            }), 15, 3.0F, 0.1F, SoundEvents.ARMOR_EQUIP_NETHERITE, () -> Items.NETHERITE_INGOT);

    private static Holder<ArmorMaterial> register(String name, EnumMap<ArmorItem.Type, Integer> typeProtection,
                                                  int enchantability, float toughness, float knockbackResistance,
                                                  Holder<SoundEvent> equipSound, Supplier<Item> ingredientItem) {
        ResourceLocation location = ResourceLocation.fromNamespaceAndPath(SimpleFlight.MODID, name);
        Supplier<Ingredient> repairIngredient = () -> Ingredient.of(ingredientItem.get());
        List<ArmorMaterial.Layer> layers = List.of(new ArmorMaterial.Layer(location));

        EnumMap<ArmorItem.Type, Integer> typeMap = new EnumMap<>(ArmorItem.Type.class);

        for(ArmorItem.Type type : ArmorItem.Type.values()) {
            typeMap.put(type, typeProtection.get(type));
        }

        return Registry.registerForHolder(BuiltInRegistries.ARMOR_MATERIAL, location,
                new ArmorMaterial(typeProtection, enchantability, equipSound, repairIngredient, layers,
                        toughness, knockbackResistance));
    }
}
