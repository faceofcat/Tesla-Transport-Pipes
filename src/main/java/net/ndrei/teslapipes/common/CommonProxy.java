package net.ndrei.teslapipes.common;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.ndrei.teslapipes.TeslaPipesMod;
import net.ndrei.teslapipes.entities.PipeEntity;

/**
 * Created by CF on 2017-03-12.
 */
public class CommonProxy {
    public void preInit(FMLPreInitializationEvent e) {
        for(Pipes pipe : Pipes.values()) {
            pipe.block.register();
        }

        GameRegistry.registerTileEntity(PipeEntity.class, new ResourceLocation(TeslaPipesMod.MODID, "pipe_entity").toString());
    }

    public void init(FMLInitializationEvent e) {
    }

    public void postInit(FMLPostInitializationEvent e) {
    }
}
