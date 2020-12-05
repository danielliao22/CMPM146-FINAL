package com.thecoolerdaniel.livefaces.util;

import com.thecoolerdaniel.livefaces.LiveFaces;
import com.thecoolerdaniel.livefaces.armor.*;
import com.thecoolerdaniel.livefaces.items.ItemBase;
import net.minecraft.block.Block;
import net.minecraft.block.SkullBlock;
import net.minecraft.block.material.Material;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
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
//    public static final RegistryObject<SkullBlock> SKULL_BLOCK = BLOCKS.register("skull_block", () -> new SkullBlock(SkullBlock.Types.PLAYER, SkullBlock.Properties.create(Material.MISCELLANEOUS)));
//    public static final RegistryObject<SkullItem> SKULL_ITEM = ITEMS.register("skull_item", () -> new SkullItem(SKULL_BLOCK.get(), SKULL_BLOCK.get(), new Item.Properties().group(ItemGroup.COMBAT)));

    // Armor
    public static final RegistryObject<ArmorItem> FACE1 = ITEMS.register("face1", () -> new ArmorItem(ModArmorMaterial.FACE1, EquipmentSlotType.HEAD, new Item.Properties().group(ItemGroup.COMBAT)));



    public static final RegistryObject<ArmorItem> ANGRY = ITEMS.register("angry", () -> new ArmorItem(AngryArmorMaterial.ANGRY, EquipmentSlotType.HEAD, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<ArmorItem> DISGUST = ITEMS.register("disgust", () -> new ArmorItem(DisgustArmorMaterial.DISGUST, EquipmentSlotType.HEAD, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<ArmorItem> FEAR = ITEMS.register("fear", () -> new ArmorItem(FearArmorMaterial.FEAR, EquipmentSlotType.HEAD, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<ArmorItem> HAPPY = ITEMS.register("happy", () -> new ArmorItem(HappyArmorMaterial.HAPPY, EquipmentSlotType.HEAD, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<ArmorItem> SAD = ITEMS.register("sad", () -> new ArmorItem(SadArmorMaterial.SAD, EquipmentSlotType.HEAD, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<ArmorItem> NEUTRAL = ITEMS.register("neutral", () -> new ArmorItem(NeutralArmorMaterial.NEUTRAL, EquipmentSlotType.HEAD, new Item.Properties().group(ItemGroup.COMBAT)));
    public static final RegistryObject<ArmorItem> SURPRISE = ITEMS.register("surprise", () -> new ArmorItem(SurpriseArmorMaterial.SURPRISE, EquipmentSlotType.HEAD, new Item.Properties().group(ItemGroup.COMBAT)));
}

//SKULL BLOCK -> AbstractSkullBlock -> ContainerBlock -> Block -> AbstractBlock
//SkULL ITEM -> WallorFloorItem -> BlockItem -> Item