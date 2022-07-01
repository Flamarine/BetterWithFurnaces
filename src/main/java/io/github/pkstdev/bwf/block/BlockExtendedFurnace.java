package io.github.pkstdev.bwf.block;

import io.github.pkstdev.bwf.BetterWithFurnaces;
import net.minecraft.src.*;

import java.util.Random;

public class BlockExtendedFurnace extends BlockFurnace {
    private final int maxCookTime;
    private final int idDropped;

    public BlockExtendedFurnace(int i, boolean flag, int maxCookTime, int idDropped) {
        super(i, flag);
        this.maxCookTime = maxCookTime;
        this.idDropped = idDropped;
    }

    public static void updateFurnaceBlockState(boolean flag, World world, int i, int j, int k) {
        int l = world.getBlockMetadata(i, j, k);
        Block block = Block.getBlock(world.getBlockId(i, j, k));
        TileEntity tileEntity = world.getBlockTileEntity(i, j, k);
        keepFurnaceInventory = true;
        if (block == BetterWithFurnaces.IRON_FURNACE || block == BetterWithFurnaces.IRON_FURNACE_ACTIVE) {
            if (flag) {
                world.setBlockWithNotify(i, j, k, BetterWithFurnaces.IRON_FURNACE_ACTIVE.blockID);
            } else {
                world.setBlockWithNotify(i, j, k, BetterWithFurnaces.IRON_FURNACE.blockID);
            }
        }
        if (block == BetterWithFurnaces.GOLDEN_FURNACE || block == BetterWithFurnaces.GOLDEN_FURNACE_ACTIVE) {
            if (flag) {
                world.setBlockWithNotify(i, j, k, BetterWithFurnaces.GOLDEN_FURNACE_ACTIVE.blockID);
            } else {
                world.setBlockWithNotify(i, j, k, BetterWithFurnaces.GOLDEN_FURNACE.blockID);
            }
        }
        if (block == BetterWithFurnaces.DIAMOND_FURNACE || block == BetterWithFurnaces.DIAMOND_FURNACE_ACTIVE) {
            if (flag) {
                world.setBlockWithNotify(i, j, k, BetterWithFurnaces.DIAMOND_FURNACE_ACTIVE.blockID);
            } else {
                world.setBlockWithNotify(i, j, k, BetterWithFurnaces.DIAMOND_FURNACE.blockID);
            }
        }
        keepFurnaceInventory = false;
        world.setBlockMetadataWithNotify(i, j, k, l);
        tileEntity.validate();
        world.setBlockTileEntity(i, j, k, tileEntity);
    }

    @Override
    public TileEntity getBlockEntity() {
        return new TileEntityExtendedFurnace(maxCookTime);
    }

    @Override
    public int idDropped(int i, Random random) {
        return idDropped;
    }

    @Override
    public Block disableNeighborNotifyOnMetadataChange() {
        return super.disableNeighborNotifyOnMetadataChange();
    }

    @Override
    public Block setStepSound(StepSound stepsound) {
        return super.setStepSound(stepsound);
    }

    @Override
    public Block setLightValue(float f) {
        return super.setLightValue(f);
    }

    @Override
    public Block setImmovable() {
        return super.setImmovable();
    }

    @Override
    public Block setHardness(float f) {
        return super.setHardness(f);
    }

    public boolean blockActivated(World world, int i, int j, int k, EntityPlayer player) {
        if (!world.isMultiplayerAndNotHost) {
            TileEntityExtendedFurnace tile = (TileEntityExtendedFurnace) world.getBlockTileEntity(i, j, k);
            player.displayGUIFurnace(tile);
        }
        return true;
    }
}
