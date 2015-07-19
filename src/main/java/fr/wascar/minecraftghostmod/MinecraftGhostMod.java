package fr.wascar.minecraftghostmod;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;

@Mod(modid = MinecraftGhostMod.MODID, version = MinecraftGhostMod.VERSION)
public class MinecraftGhostMod
{
    public static final String MODID = "minecraftghostmod";
    public static final String VERSION = "1.0";

    public static KeyBinding switchGamemode;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        FMLCommonHandler.instance().bus().register(this);
        switchGamemode = new KeyBinding("key.switchGamemode", Keyboard.KEY_H, "key.categories.gameplay");
        ClientRegistry.registerKeyBinding(switchGamemode);
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onRenderLabel(RenderLivingEvent.Specials.Pre event)
    {
        if(event.entity instanceof EntityPlayer)
            event.setCanceled(true);
    }

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        if(switchGamemode.isPressed())
        {
            if(Minecraft.getMinecraft().playerController.getCurrentGameType().isCreative())
                Minecraft.getMinecraft().thePlayer.sendChatMessage("/gamemode 3");
            else
                Minecraft.getMinecraft().thePlayer.sendChatMessage("/gamemode 1");
        }
    }
}
