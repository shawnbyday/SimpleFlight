package com.shawnbyday.simpleflight.item.custom;

import com.google.common.collect.ImmutableMap;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import com.shawnbyday.simpleflight.item.ModArmorMaterials;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ModArmorItem extends ArmorItem {
    private static final Map<Holder<ArmorMaterial>, List<MobEffectInstance>> MATERIAL_TO_EFFECT_MAP =
            (new ImmutableMap.Builder<Holder<ArmorMaterial>, List<MobEffectInstance>>())
                    .put(ModArmorMaterials.DIAMOND_ARMOR_MATERIAL,
                            List.of(new MobEffectInstance(MobEffects.NIGHT_VISION, 300, 1, false, false)))
                    .put(ModArmorMaterials.NETHERITE_ARMOR_MATERIAL,
                            List.of(new MobEffectInstance(MobEffects.NIGHT_VISION, 300, 1, false, false)))
                    .build();

    public ModArmorItem(Holder<ArmorMaterial> material, Type type, Properties properties) {
        super(material, type, properties);
    }

    @Override
    public void inventoryTick(@NotNull ItemStack stack, @NotNull Level level, @NotNull Entity entity, int slotId, boolean isSelected) {
        if(entity instanceof Player player && !level.isClientSide() && hasChestplateOn(player)) {
            evaluateArmorEffects(player);
            player.getAbilities().mayfly = true;
            player.onUpdateAbilities();
        }
        if(entity instanceof Player player && !level.isClientSide() && !hasChestplateOn(player)) {
            player.getAbilities().flying = false;
            player.getAbilities().mayfly = false;
            player.onUpdateAbilities();
        }
    }

    private void evaluateArmorEffects(Player player) {
        for(Map.Entry<Holder<ArmorMaterial>, List<MobEffectInstance>> entry : MATERIAL_TO_EFFECT_MAP.entrySet()) {
            Holder<ArmorMaterial> mapArmorMaterial = entry.getKey();
            List<MobEffectInstance> mapEffect = entry.getValue();

            if(hasValidChestplateOn(mapArmorMaterial, player)) {
                addEffectToPlayer(player, mapEffect);
            }
        }
    }

    private void addEffectToPlayer(Player player, List<MobEffectInstance> mapEffect) {
        boolean hasPlayerEffect = mapEffect.stream().allMatch(effect -> player.hasEffect(effect.getEffect()));

        if(!hasPlayerEffect) {
            for(MobEffectInstance effect : mapEffect) {
                player.addEffect(new MobEffectInstance(effect.getEffect(), effect.getDuration(), effect.getAmplifier(),
                        effect.isAmbient(), effect.isVisible()));
            }
        }
        if(hasPlayerEffect) {
            int duration = Objects.requireNonNull(player.getEffect(MobEffects.NIGHT_VISION)).getDuration();
            if(duration < 250) {
                for (MobEffectInstance effect : mapEffect) {
                    player.addEffect(new MobEffectInstance(effect.getEffect(), effect.getDuration(), effect.getAmplifier(),
                            effect.isAmbient(), effect.isVisible()));
                }
            }
        }
    }

    private boolean hasValidChestplateOn(Holder<ArmorMaterial> material, Player player) {
        ArmorItem chestplate = ((ArmorItem) player.getInventory().getArmor(2).getItem());

        return chestplate.getMaterial() == material ||
               chestplate.getMaterial() == material;
    }

    private boolean hasChestplateOn(Player player) {
        ItemStack chestplate = player.getInventory().getArmor(2);

        return !chestplate.isEmpty();
    }
}
