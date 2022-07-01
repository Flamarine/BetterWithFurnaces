package io.github.pkstdev.bwf.block;

import io.github.pkstdev.bwf.BetterWithFurnaces;
import net.minecraft.src.*;

public class TileEntityExtendedFurnace extends TileEntityFurnace {
    private int maxCookTime;

    public TileEntityExtendedFurnace() {
        super();
        this.maxCookTime = 100;
    }

    public TileEntityExtendedFurnace(int maxCookTime) {
        super();
        this.maxCookTime = maxCookTime;
    }

    @Override
    public String getInvName() {
        return "ExtendedFurnace";
    }

    @Override
    protected void updateFurnace() {
        BlockExtendedFurnace.updateFurnaceBlockState(this.currentBurnTime > 0, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
    }

    @Override
    public void updateEntity() {
        boolean isBurnTimeHigherThan0 = this.currentBurnTime > 0;
        boolean furnaceUpdated = false;
        if (this.currentBurnTime > 0) {
            --this.currentBurnTime;
        }
        if (!this.worldObj.isMultiplayerAndNotHost) {
            if (this.worldObj.getBlockId(this.xCoord, this.yCoord, this.zCoord) == (BetterWithFurnaces.IRON_FURNACE.blockID | BetterWithFurnaces.GOLDEN_FURNACE.blockID | BetterWithFurnaces.DIAMOND_FURNACE.blockID) && this.currentBurnTime == 0 && this.furnaceItemStacks[0] == null && this.furnaceItemStacks[1] != null && this.furnaceItemStacks[1].itemID == Block.netherrack.blockID) {
                --this.furnaceItemStacks[1].stackSize;
                if (this.furnaceItemStacks[1].stackSize == 0) {
                    this.furnaceItemStacks[1] = null;
                }
                BlockExtendedFurnace.updateFurnaceBlockState(true, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
                furnaceUpdated = true;
            }
            if (this.currentBurnTime == 0 && this.canSmelt()) {
                this.maxBurnTime = this.currentBurnTime = this.getBurnTimeFromItem(this.furnaceItemStacks[1]);
                if (this.currentBurnTime > 0) {
                    furnaceUpdated = true;
                    if (this.furnaceItemStacks[1] != null) {
                        if (this.furnaceItemStacks[1].getItem() == Item.bucketLava) {
                            this.furnaceItemStacks[1] = new ItemStack(Item.bucket);
                        } else {
                            --this.furnaceItemStacks[1].stackSize;
                            if (this.furnaceItemStacks[1].stackSize == 0) {
                                this.furnaceItemStacks[1] = null;
                            }
                        }
                    }
                }
            }
            if (this.isBurning() && this.canSmelt()) {
                ++this.currentCookTime;
                if (this.currentCookTime == this.maxCookTime) {
                    this.currentCookTime = 0;
                    this.smeltItem();
                    furnaceUpdated = true;
                }
            } else {
                this.currentCookTime = 0;
            }
            if (isBurnTimeHigherThan0 != this.currentBurnTime > 0) {
                furnaceUpdated = true;
                this.updateFurnace();
            }
        }
        if (furnaceUpdated) {
            this.onInventoryChanged();
        }
    }

    private boolean canSmelt() {
        if (this.furnaceItemStacks[0] == null) {
            return false;
        } else {
            ItemStack itemstack = RecipesFurnace.smelting().getSmeltingResult(this.furnaceItemStacks[0].getItem().itemID);
            if (itemstack == null) {
                return false;
            } else if (this.furnaceItemStacks[2] == null) {
                return true;
            } else if (!this.furnaceItemStacks[2].isItemEqual(itemstack)) {
                return false;
            } else if (this.furnaceItemStacks[2].stackSize < this.getInventoryStackLimit() && this.furnaceItemStacks[2].stackSize < this.furnaceItemStacks[2].getMaxStackSize()) {
                return true;
            } else {
                return this.furnaceItemStacks[2].stackSize < itemstack.getMaxStackSize();
            }
        }
    }

    private int getBurnTimeFromItem(ItemStack itemStack) {
        return itemStack == null ? 0 : LookupFuelFurnace.fuelFurnace().getFuelYield(itemStack.getItem().itemID);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        this.maxCookTime = nbt.getShort("MaxCookTime");
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        nbt.setShort("MaxCookTime", (short) this.maxCookTime);
    }

    @Override
    public int getCookProgressScaled(int i) {
        return super.getCookProgressScaled(i);
    }
}
