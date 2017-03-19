package net.ndrei.teslapipes;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.ndrei.teslapipes.common.CommonProxy;
import net.ndrei.teslapipes.common.Pipes;
import org.apache.logging.log4j.Logger;

/**
 * Created by CF on 2017-03-12.
 */
@Mod(modid = TeslaPipesMod.MODID, version = TeslaPipesMod.VERSION, name = "Tesla Pipes", dependencies = "after:teslacorelib", useMetadata = true)
public class TeslaPipesMod
{
    public static final String MODID = "teslapipes";
    public static final String VERSION = "0.0.1";

    @SidedProxy(clientSide = "net.ndrei.teslapipes.client.ClientProxy", serverSide = "net.ndrei.teslapipes.common.CommonProxy")
    private static CommonProxy proxy;

    @Mod.Instance
    public static TeslaPipesMod instance;

    public static Logger logger;

    public static CreativeTabs creativeTab = new CreativeTabs("tabTeslaPipes") {
        @Override
        public ItemStack getIconItemStack()
        {
            return new ItemStack(Item.getItemFromBlock(Pipes.TESLA.block), 1);
        }

        @Override
        public ItemStack getTabIconItem() { return this.getIconItemStack(); }
    };

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent e){
        TeslaPipesMod.logger = e.getModLog();

        MinecraftForge.EVENT_BUS.register(TeslaPipesMod.proxy);

        proxy.preInit(e);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent e) {
        proxy.init(e);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e) {
        proxy.postInit(e);
    }
}
