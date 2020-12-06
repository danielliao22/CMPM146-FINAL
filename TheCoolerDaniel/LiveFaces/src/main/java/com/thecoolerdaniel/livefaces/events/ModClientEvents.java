package com.thecoolerdaniel.livefaces.events;

import com.thecoolerdaniel.livefaces.LiveFaces;
import com.thecoolerdaniel.livefaces.util.RegistryHandler;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.DoorBlock;
import net.minecraft.client.audio.Sound;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

// Find all events at 4:30 in tut vid 15

@Mod.EventBusSubscriber(modid = LiveFaces.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ModClientEvents {

    @SubscribeEvent
    public static void onJumpWithStick(LivingEvent.LivingJumpEvent event) {
        LivingEntity player = event.getEntityLiving();
        if (player.getHeldItemMainhand().getItem() == Items.STICK) {
            LiveFaces.LOGGER.info("Player jumped with a stick");
            World world = player.getEntityWorld();
            world.setBlockState(player.getPosition().add(0, -1, 0), Blocks.DIAMOND_BLOCK.getDefaultState());
            // trying to change item to a thing now
            player.replaceItemInInventory(103, new ItemStack(RegistryHandler.FACE1.get()));
        }
    }

    @SubscribeEvent
    public static void onGameTick(TickEvent.PlayerTickEvent event) {
        LivingEntity player = event.player;

        // gonna try to read a file
        String data = null;
        try {
            // File myObj = new File("C:\\Users\\very cool dood\\Desktop\\MCmod\\Live Faces Git\\CMPM146-FINAL\\TheCoolerDaniel\\LiveFaces\\src\\main\\java\\com\\thecoolerdaniel\\livefaces\\events\\face.txt");
            File myObj = new File("C:\\Users\\very cool dood\\Desktop\\MCmod\\facial recognition git\\ML-Facial-Recognition-and-Replacement-in-Minecraft\\emotionfile.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                data = myReader.nextLine();
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            data = "Error";
        }

        // data now stores the content of face.txt (assuming it could be found)
        if (data.equals("Happy")) {
            LiveFaces.LOGGER.info("Player is happy");
            player.replaceItemInInventory(103, new ItemStack(RegistryHandler.FACE1.get()));
        } else if (data.equals("Sad")) {
            LiveFaces.LOGGER.info("Player is sad");
            player.replaceItemInInventory(103, new ItemStack(Items.CARVED_PUMPKIN));
        } else if (data.equals("Neutral")) {
            LiveFaces.LOGGER.info("Player is neutral");
            player.replaceItemInInventory(103, new ItemStack(Items.STICK));
        } else if (data.equals("Surprised")) {
            LiveFaces.LOGGER.info("Player is surprised");
            player.replaceItemInInventory(103, new ItemStack(Items.DIAMOND_HELMET));
        }

        // Now going to try and search the area around the player for doors
        BlockPos loc = player.getPosition();
        World world = player.getEntityWorld();
        LiveFaces.LOGGER.info("\n\n\n\n\n==================Start of loop==================");
        for(int x = -2; x < 3; x++) {
            for (int z = -2; z < 3; z++) {
                BlockPos pos = loc.add(x, 0, z);
                BlockState current = world.getBlockState(pos);
                LiveFaces.LOGGER.info("\nState of location:");
                LiveFaces.LOGGER.info(x);
                LiveFaces.LOGGER.info(z);
                LiveFaces.LOGGER.info("is");
                LiveFaces.LOGGER.info(current.getBlock());

                if (current.getBlock() == Blocks.IRON_DOOR.getBlock()) {
                    LiveFaces.LOGGER.info("\nWOOHOO! found an iron door my guy");

                    // Now we need to determine what the correct expression is based off of the block below
                    // can't put a BlockState in a switch statement, so we'll do it the good ol fashioned way
                    BlockState below = world.getBlockState(pos.add(0, -1, 0));
                    String expression = null;
                    if (below.getBlock() == Blocks.DIAMOND_BLOCK.getBlock()){
                        expression = "Happy";
                    } else if (below.getBlock() == Blocks.COAL_BLOCK.getBlock()) {
                        expression = "Fear";
                    } else if (below.getBlock() == Blocks.REDSTONE_BLOCK.getBlock()) {
                        expression = "Angry";
                    } else if (below.getBlock() == Blocks.LAPIS_BLOCK.getBlock()) {
                        expression = "Disgust";
                    } else if (below.getBlock() == Blocks.EMERALD_BLOCK.getBlock()) {
                        expression = "Sad";
                    } else if (below.getBlock() == Blocks.GOLD_BLOCK.getBlock()) {
                        expression = "Surprise";
                    } else if (below.getBlock() == Blocks.IRON_BLOCK.getBlock()) {
                        expression = "Neutral";
                    }

                    if (!current.get(BlockStateProperties.OPEN)) {
                        world.setBlockState(pos, current.with(BlockStateProperties.OPEN, true), 10);
                        world.playSound(null, pos, SoundEvents.BLOCK_IRON_DOOR_OPEN, SoundCategory.BLOCKS, 1, 1);
                    }
                }

            }
        }

    }


}


























