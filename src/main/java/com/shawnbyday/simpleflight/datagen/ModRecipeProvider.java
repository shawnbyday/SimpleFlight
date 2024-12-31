package com.shawnbyday.simpleflight.datagen;

import com.shawnbyday.simpleflight.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(@NotNull RecipeOutput recipeOutput) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.DIAMOND_CHESTPLATE.get())
                .pattern("SDS")
                .pattern("SES")
                .pattern("SYS")
                .define('D', Items.DIAMOND_CHESTPLATE)
                .define('E', Items.ELYTRA)
                .define('S', Items.STRING)
                .define('Y', Items.ENDER_EYE)
                .unlockedBy("has_diamond_chestplate", has(Items.DIAMOND_CHESTPLATE))
                .unlockedBy("has_elytra", has(Items.ELYTRA))
                .unlockedBy("has_string", has(Items.STRING))
                .unlockedBy("has_ender_eye", has(Items.ENDER_EYE))
                .save(recipeOutput);
        SmithingTransformRecipeBuilder.smithing(Ingredient.of(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE),
                Ingredient.of(ModItems.DIAMOND_CHESTPLATE), Ingredient.of(Items.NETHERITE_INGOT), RecipeCategory.COMBAT,
                ModItems.NETHERITE_CHESTPLATE.get())
                .unlocks("has_template", has(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE))
                .unlocks("has_diamond_chestplate", has(ModItems.DIAMOND_CHESTPLATE))
                .unlocks("has_netherite_ingot", has(Items.NETHERITE_INGOT))
                .save(recipeOutput, "netherite_chestplate");
    }
}
