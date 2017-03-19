package net.ndrei.teslapipes.client;

import com.google.common.collect.Lists;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.*;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.ndrei.teslapipes.TeslaPipesMod;

import java.util.List;

/**
 * Created by CF on 2017-03-12.
 */
public class PipeModel implements IBakedModel {
    private static TextureAtlasSprite MIDDLE_SPRITE;

    private final ItemOverrideList ITEM_OVERRIDE = new OverrideHandler(this);
    private ItemStack currentStack = null;
    private FaceBakery bakery = new FaceBakery();

    //#region standard boring stuff

    @Override
    public boolean isAmbientOcclusion() {
        return true;
    }

    @Override
    public boolean isGui3d() {
        return true;
    }

    @Override
    public boolean isBuiltInRenderer() {
        return false;
    }

    @Override
    public TextureAtlasSprite getParticleTexture() {
        return null;
    }

    @Override
    public ItemCameraTransforms getItemCameraTransforms() {
        return ItemCameraTransforms.DEFAULT;
    }

    @Override
    public ItemOverrideList getOverrides() {
        return ITEM_OVERRIDE;
    }

    private static class OverrideHandler extends ItemOverrideList
    {
        public PipeModel model;

        public OverrideHandler(PipeModel model)
        {
            super(Lists.newArrayList());
            this.model = model;
        }

        public IBakedModel handleItemState(IBakedModel originalModel, ItemStack stack, World world, EntityLivingBase entity)
        {
            this.model.currentStack = stack;
            return this.model;
        }
    }

    //#endregion

    public static void loadTextures(TextureMap map) {
        MIDDLE_SPRITE = map.registerSprite(new ResourceLocation(TeslaPipesMod.MODID+":blocks/pipe_tesla_core"));
    }

    @Override
    public List<BakedQuad> getQuads(IBlockState state, EnumFacing side, long rand) {
        List<BakedQuad> list = Lists.newArrayList();


        return list;
    }
}
