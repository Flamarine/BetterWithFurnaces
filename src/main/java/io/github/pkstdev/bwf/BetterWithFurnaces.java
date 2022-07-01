package io.github.pkstdev.bwf;

import bta.Mod;
import io.github.pkstdev.bwf.block.BlockExtendedFurnace;
import io.github.pkstdev.bwf.block.TileEntityExtendedFurnace;
import io.github.pkstdev.bwf.util.RecipeHelper;
import io.github.pkstdev.bwf.util.TileEntityHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.src.Block;
import net.minecraft.src.Item;
import net.minecraft.src.ItemBlock;
import net.minecraft.src.ItemStack;

public class BetterWithFurnaces implements Mod {
    public static Block IRON_FURNACE;
    public static Block IRON_FURNACE_ACTIVE;
    public static Item IRON_FURNACE_ITEM;
    public static Item IRON_FURNACE_ACTIVE_ITEM;
    public static Block GOLDEN_FURNACE;
    public static Block GOLDEN_FURNACE_ACTIVE;
    public static Item GOLDEN_FURNACE_ITEM;
    public static Item GOLDEN_FURNACE_ACTIVE_ITEM;
    public static Block DIAMOND_FURNACE;
    public static Block DIAMOND_FURNACE_ACTIVE;
    public static Item DIAMOND_FURNACE_ITEM;
    public static Item DIAMOND_FURNACE_ACTIVE_ITEM;

    @Override
    public void init(Minecraft minecraft) {
        IRON_FURNACE = ((BlockExtendedFurnace) ((BlockExtendedFurnace) ((BlockExtendedFurnace) ((BlockExtendedFurnace) (new BlockExtendedFurnace(1001, false, 100, 1001))
                .setBlockName("furnace.iron.idle")
                .setTexCoords(22, 0, 22, 2, 22, 1, 22, 3, 22, 3, 22, 3))
                .setHardness(4.0f))
                .setStepSound(Block.soundMetalFootstep))
                .disableNeighborNotifyOnMetadataChange())
                .setImmovable();
        IRON_FURNACE_ACTIVE = ((BlockExtendedFurnace) ((BlockExtendedFurnace) ((BlockExtendedFurnace) ((BlockExtendedFurnace) ((BlockExtendedFurnace) (new BlockExtendedFurnace(1002, true, 100, 1001))
                .setBlockName("furnace.iron.active")
                .setTexCoords(22, 0, 22, 2, 22, 4, 22, 3, 22, 3, 22, 3))
                .setHardness(4.0f))
                .setStepSound(Block.soundMetalFootstep))
                .disableNeighborNotifyOnMetadataChange())
                .setImmovable())
                .setLightValue(0.875f)
                .setNotInCreativeMenu();
        IRON_FURNACE_ITEM = createItemForBlock(IRON_FURNACE);
        IRON_FURNACE_ACTIVE_ITEM = createItemForBlock(IRON_FURNACE_ACTIVE).setNotInCreativeMenu();
        GOLDEN_FURNACE = ((BlockExtendedFurnace) ((BlockExtendedFurnace) ((BlockExtendedFurnace) ((BlockExtendedFurnace) (new BlockExtendedFurnace(1003, false, 50, 1003))
                .setBlockName("furnace.golden.idle")
                .setTexCoords(23, 0, 23, 2, 23, 1, 23, 3, 23, 3, 23, 3))
                .setHardness(4.0f))
                .setStepSound(Block.soundMetalFootstep))
                .disableNeighborNotifyOnMetadataChange())
                .setImmovable();
        GOLDEN_FURNACE_ACTIVE = ((BlockExtendedFurnace) ((BlockExtendedFurnace) ((BlockExtendedFurnace) ((BlockExtendedFurnace) ((BlockExtendedFurnace) (new BlockExtendedFurnace(1004, true, 50, 1003))
                .setBlockName("furnace.golden.active")
                .setTexCoords(23, 0, 23, 2, 23, 4, 23, 3, 23, 3, 23, 3))
                .setHardness(4.0f))
                .setStepSound(Block.soundMetalFootstep))
                .disableNeighborNotifyOnMetadataChange())
                .setImmovable())
                .setLightValue(0.875f)
                .setNotInCreativeMenu();
        GOLDEN_FURNACE_ITEM = createItemForBlock(GOLDEN_FURNACE);
        GOLDEN_FURNACE_ACTIVE_ITEM = createItemForBlock(GOLDEN_FURNACE_ACTIVE).setNotInCreativeMenu();
        DIAMOND_FURNACE = ((BlockExtendedFurnace) ((BlockExtendedFurnace) ((BlockExtendedFurnace) ((BlockExtendedFurnace) (new BlockExtendedFurnace(1005, false, 25, 1005))
                .setBlockName("furnace.diamond.idle")
                .setTexCoords(24, 0, 24, 2, 24, 1, 24, 3, 24, 3, 24, 3))
                .setHardness(4.0f))
                .setStepSound(Block.soundMetalFootstep))
                .disableNeighborNotifyOnMetadataChange())
                .setImmovable();
        DIAMOND_FURNACE_ACTIVE = ((BlockExtendedFurnace) ((BlockExtendedFurnace) ((BlockExtendedFurnace) ((BlockExtendedFurnace) ((BlockExtendedFurnace) (new BlockExtendedFurnace(1006, true, 25, 1005))
                .setBlockName("furnace.diamond.active")
                .setTexCoords(24, 0, 24, 2, 24, 4, 24, 3, 24, 3, 24, 3))
                .setHardness(4.0f))
                .setStepSound(Block.soundMetalFootstep))
                .disableNeighborNotifyOnMetadataChange())
                .setImmovable())
                .setLightValue(0.875f)
                .setNotInCreativeMenu();
        DIAMOND_FURNACE_ITEM = createItemForBlock(DIAMOND_FURNACE);
        DIAMOND_FURNACE_ACTIVE_ITEM = createItemForBlock(DIAMOND_FURNACE_ACTIVE).setNotInCreativeMenu();
        TileEntityHelper.registerTileEntityClass(TileEntityExtendedFurnace.class, "ExtendedFurnace");
        RecipeHelper.addRecipe(new ItemStack(IRON_FURNACE), "BBB", "BDB", "BBB", 'D', Block.furnaceStoneIdle, 'B', Item.ingotIron);
        RecipeHelper.addRecipe(new ItemStack(GOLDEN_FURNACE), "BBB", "BDB", "BBB", 'D', IRON_FURNACE, 'B', Item.ingotGold);
        RecipeHelper.addRecipe(new ItemStack(DIAMOND_FURNACE), "BBB", "BDB", "BBB", 'D', GOLDEN_FURNACE, 'B', Item.diamond);
        System.out.println("[Better With Furnaces] Initialized.");
    }

    @Override
    public void update() {

    }

    @Override
    public void tick() {

    }

    public static ItemBlock createItemForBlock(Block block) {
        return new ItemBlock(block.blockID - Block.blocksList.length);
    }
}
