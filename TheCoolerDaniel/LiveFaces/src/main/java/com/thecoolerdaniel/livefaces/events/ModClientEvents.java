package com.thecoolerdaniel.livefaces.events;

import com.thecoolerdaniel.livefaces.LiveFaces;
import com.thecoolerdaniel.livefaces.util.RegistryHandler;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
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
            File myObj = new File("C:\\Users\\very cool dood\\Desktop\\MCmod\\Live Faces Git\\CMPM146-FINAL\\TheCoolerDaniel\\LiveFaces\\src\\main\\java\\com\\thecoolerdaniel\\livefaces\\events\\face.txt");
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
        if (data.equals("happy")) {
            LiveFaces.LOGGER.info("Player is happy");
            player.replaceItemInInventory(103, new ItemStack(RegistryHandler.FACE1.get()));
        } else if (data.equals("sad")) {
            LiveFaces.LOGGER.info("Player is sad");
            player.replaceItemInInventory(103, new ItemStack(Items.CARVED_PUMPKIN));
        }
    }


}
