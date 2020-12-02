package com.thecoolerdaniel.livefaces.util;

import com.thecoolerdaniel.livefaces.LiveFaces;
import com.thecoolerdaniel.livefaces.items.ItemBase;
import net.minecraft.block.Block;
import net.minecraft.block.SkullBlock;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SkullItem;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RegistryHandler {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, LiveFaces.MOD_ID);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, LiveFaces.MOD_ID);

    public static void init() {
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    // Items
    public static final RegistryObject<Item> HAPPY_FACE = ITEMS.register("happy_face", ItemBase::new);

    // Skull Blocks
    public static final RegistryObject<SkullBlock> SKULL_BLOCK = BLOCKS.register("skull_block", () -> new SkullBlock(SkullBlock.Types.PLAYER, SkullBlock.Properties.create(Material.MISCELLANEOUS)));
    public static final RegistryObject<SkullItem> SKULL_ITEM = ITEMS.register("skull_item", () -> new SkullItem(SKULL_BLOCK.get(), SKULL_BLOCK.get(), new Item.Properties().group(ItemGroup.COMBAT)));

}

//SKULL BLOCK -> AbstractSkullBlock -> ContainerBlock -> Block -> AbstractBlock
//SkULL ITEM -> WallorFloorItem -> BlockItem -> Item