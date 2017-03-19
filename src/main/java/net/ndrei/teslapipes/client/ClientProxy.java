package net.ndrei.teslapipes.client;

import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.ndrei.teslapipes.TeslaPipesMod;
import net.ndrei.teslapipes.common.CommonProxy;
import net.ndrei.teslapipes.common.Pipes;
import net.ndrei.teslapipes.entities.PipeEntity;

/**
 * Created by CF on 2017-03-12.
 */
public class ClientProxy extends CommonProxy {
    @Override
    public void init(FMLInitializationEvent e) {
        super.init(e);

        for(Pipes pipe: Pipes.values()) {
            pipe.block.registerRenderer();
        }

        GameRegistry.registerTileEntity(PipeEntity.class, new ResourceLocation(TeslaPipesMod.MODID, "pipe_entity").toString());
        ClientRegistry.bindTileEntitySpecialRenderer(PipeEntity.class, new PipeRenderer());
    }

//    @SubscribeEvent
//    public void onBakeModel(ModelBakeEvent e) {
//        e.getModelRegistry().putObject(new ModelResourceLocation(TeslaPipesMod.MODID + ":tesla"), new PipeModel());
//    }
//
    public static TextureAtlasSprite MIDDLE_CABLE_SPRITE;

    @SubscribeEvent
    public void onStitch(TextureStitchEvent.Pre event) {
        // PipeModel.loadTextures(event.getMap());
        MIDDLE_CABLE_SPRITE = event.getMap().registerSprite(new ResourceLocation(TeslaPipesMod.MODID+":blocks/pipe_tesla_core"));
    }
}
