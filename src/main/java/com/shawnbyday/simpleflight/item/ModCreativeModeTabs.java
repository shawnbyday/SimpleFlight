package com.shawnbyday.simpleflight.item;

import com.shawnbyday.simpleflight.SimpleFlight;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, SimpleFlight.MODID);

    public static final Supplier<CreativeModeTab> SIMPLE_FLIGHT_TAB = CREATIVE_MODE_TAB.register("simple_flight_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.NETHERITE_CHESTPLATE.get()))
                    .title(Component.translatable("creativemodetab.simpleflight.simple_flight_tab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.DIAMOND_CHESTPLATE);
                        output.accept(ModItems.NETHERITE_CHESTPLATE);
                    }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
