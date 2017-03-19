package net.ndrei.teslapipes.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.ndrei.teslapipes.TeslaPipesMod;
import net.ndrei.teslapipes.entities.PipeEntity;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by CF on 2017-03-12.
 */
public class BasePipeBlock extends Block implements ITileEntityProvider {
    private static Map<EnumFacing, PropertyBool> CONNECTIONS;

    static {
        CONNECTIONS = new HashMap<>();
        for(EnumFacing f : EnumFacing.values()) {
            CONNECTIONS.put(f, PropertyBool.create(f.name().toLowerCase()));
        }
    }

    public BasePipeBlock(String registryName) {
        super(Material.GLASS);

        this.setRegistryName(TeslaPipesMod.MODID, registryName);
        this.setUnlocalizedName(TeslaPipesMod.MODID + "_" + registryName);
        this.setCreativeTab(TeslaPipesMod.creativeTab);
        this.setHarvestLevel("pickaxe", 0);
        this.setHardness(3.0f);
        this.setLightOpacity(0);
    }

    @SuppressWarnings("unused")
    public void register() {
        GameRegistry.register(this);
        GameRegistry.register(new ItemBlock(this), this.getRegistryName());

        IRecipe recipe = this.getRecipe();
        if (recipe != null) {
            CraftingManager.getInstance().addRecipe(recipe);
        }
    }

    @SuppressWarnings("WeakerAccess")
    protected IRecipe getRecipe() {
        return null;
    }

    @SideOnly(Side.CLIENT)
    @SuppressWarnings("unused")
    public void registerRenderer() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this)
                , 0
                , new ModelResourceLocation(this.getRegistryName(), "inventory")
        );
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new PipeEntity();
    }

    @Override
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.TRANSLUCENT;
    }

    @Override
    public boolean doesSideBlockRendering(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing face) {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return new AxisAlignedBB(.3, .3, .3, .7, .7, .7);
    }

    @Override
    public void onNeighborChange(IBlockAccess world, BlockPos pos, BlockPos neighbor) {
        super.onNeighborChange(world, pos, neighbor);

        if ((world instanceof World) && !((World)world).isRemote) {
            TileEntity te = world.getTileEntity(pos);
            if ((te != null) && (te instanceof PipeEntity)) {
                ((PipeEntity) te).neighborUpdated(neighbor);
            }
        }
    }

    public boolean setSideConnected(World world, BlockPos pos, EnumFacing side, boolean value)
    {
        IBlockState state = world.getBlockState(pos);
        if ((state.getBlock() == this) && (state.getValue(CONNECTIONS.get(side)) != value)) {
            IBlockState newState = state.withProperty(CONNECTIONS.get(side), value);

            world.setBlockState(pos, newState);

            //world.getBlockState(pos.offset(side)).getBlock().onNeighborChange(world, pos.offset(side), pos);

            world.scheduleBlockUpdate(pos, this, 0, 0);
            return true;
        }
        return false;
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, CONNECTIONS.values().toArray(new IProperty[0]));
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return 0;
    }

    @SuppressWarnings("deprecation")
    @Override
    public IBlockState getStateFromMeta(int meta) {
        IBlockState state = this.getDefaultState();
        for(IProperty<Boolean> p : CONNECTIONS.values()) {
            state = state.withProperty(p, false);
        }
        return state;
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        playerIn.sendMessage(new TextComponentString("------"));
        for(Map.Entry<IProperty<?>, Comparable<?>> e : state.getProperties().entrySet()) {
            playerIn.sendMessage(new TextComponentString(worldIn.isRemote + " :: " + e.getKey().getName() + " : " + e.getValue().toString()));
        }

        return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
    }
}
