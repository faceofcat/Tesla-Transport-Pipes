package net.ndrei.teslapipes.common;

import net.ndrei.teslapipes.blocks.BasePipeBlock;
import net.ndrei.teslapipes.blocks.TeslaPipe;

/**
 * Created by CF on 2017-03-12.
 */

public enum Pipes {
    TESLA(new TeslaPipe()); //,
    // LIQUID(new LiquidPipe());

    public final BasePipeBlock block;

    Pipes(BasePipeBlock block) {
        this.block = block;
    }
}

//public final class BlocksRegistry {
//    private static final LiquidPipe liquidPipeBlock = new LiquidPipe();
//    private static final TeslaPipe teslaPipeBlock = new TeslaPipe();
//
//    public static void registerBlocks() {
//        BlocksRegistry.liquidPipeBlock.register();
//        BlocksRegistry.teslaPipeBlock.register();
//    }
//
//    @SideOnly(Side.CLIENT)
//    public static void registerBlockRenderers() {
//        BlocksRegistry.liquidPipeBlock.registerRenderer();
//        BlocksRegistry.teslaPipeBlock.registerRenderer();
//    }
//}
