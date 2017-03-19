package net.ndrei.teslapipes.entities;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.ndrei.teslapipes.blocks.BasePipeBlock;

/**
 * Created by CF on 2017-03-12.
 */
public class PipeEntity extends TileEntity implements ITickable {
    private boolean needsUpdate = true;

    public PipeEntity() {
        super();
    }

    public void neighborUpdated(BlockPos neighbor) {
        if (this.hasWorld() && this.getWorld().isBlockLoaded(this.getPos())) {
            Block t = this.getBlockType();
            if (this.getWorld().isBlockLoaded(neighbor)) {
                Block n = this.getWorld().getBlockState(neighbor).getBlock();
                if (t instanceof BasePipeBlock) {
                    BlockPos pos = this.getPos();
                    EnumFacing facing = EnumFacing.UP;

                    if ((pos.getX() == neighbor.getX()) && (pos.getY() == neighbor.getY())) {
                        facing = EnumFacing.getFacingFromAxis(
                                (pos.getZ() > neighbor.getZ()) ? EnumFacing.AxisDirection.NEGATIVE : EnumFacing.AxisDirection.POSITIVE,
                                EnumFacing.Axis.Z);
                    }
                    else if ((pos.getZ() == neighbor.getZ()) && (pos.getY() == neighbor.getY())) {
                        facing = EnumFacing.getFacingFromAxis(
                                (pos.getX() > neighbor.getX()) ? EnumFacing.AxisDirection.NEGATIVE : EnumFacing.AxisDirection.POSITIVE,
                                EnumFacing.Axis.X);
                    }
                    else if ((pos.getX() == neighbor.getX()) && (pos.getZ() == neighbor.getZ())) {
                        facing = EnumFacing.getFacingFromAxis(
                                (pos.getY() > neighbor.getY()) ? EnumFacing.AxisDirection.NEGATIVE : EnumFacing.AxisDirection.POSITIVE,
                                EnumFacing.Axis.Y);
                    }

                    // TeslaPipesMod.logger.info(pos + " updated: " + facing + " from " + neighbor);

                    if (((BasePipeBlock) t).setSideConnected(this.getWorld(), pos, facing, (n instanceof BasePipeBlock))) {
                        this.markDirty();
                    }
                }
            }
        }
    }

    @Override
    public void update() {
        if (this.needsUpdate && this.hasWorld() /*&& !this.getWorld().isRemote*/) {
            this.needsUpdate = false;
            for (EnumFacing f : EnumFacing.values()) {
                this.neighborUpdated(this.getPos().offset(f));
            }
        }
    }

    @Override
    public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newState) {
        return super.shouldRefresh(world, pos, oldState, newState) && (oldState.getBlock() != newState.getBlock());
    }

    @Override
    public void onLoad() {
        super.onLoad();
        this.needsUpdate = true;
    }
}
