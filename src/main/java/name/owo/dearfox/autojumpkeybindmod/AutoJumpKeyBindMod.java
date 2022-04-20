package name.owo.dearfox.autojumpkeybindmod;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;

import net.minecraftforge.client.ClientRegistry;

import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.common.MinecraftForge;

import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import net.minecraftforge.fml.common.Mod;

import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.glfw.GLFW;

import javax.swing.text.JTextComponent;
import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("autojumpkeybindmod")
public class AutoJumpKeyBindMod {

    private static final KeyMapping auto_jump_key_bind = new KeyMapping("option.auto-jump-key-bind.key", KeyConflictContext.UNIVERSAL, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_R, "option.auto-jump-key-bind.category");
    private static final KeyMapping auto_jump_key_bind_on = new KeyMapping("option.auto-jump-key-bind.key.on", KeyConflictContext.UNIVERSAL, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, "option.auto-jump-key-bind.category");
    private static final KeyMapping auto_jump_key_bind_off = new KeyMapping("option.auto-jump-key-bind.key.off", KeyConflictContext.UNIVERSAL, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, "option.auto-jump-key-bind.category");

    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    public AutoJumpKeyBindMod() {
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
        ClientRegistry.registerKeyBinding(auto_jump_key_bind);
        ClientRegistry.registerKeyBinding(auto_jump_key_bind_on);
        ClientRegistry.registerKeyBinding(auto_jump_key_bind_off);
    }

    @SubscribeEvent
    public void clientTick (TickEvent.ClientTickEvent event) {
        if (auto_jump_key_bind.consumeClick()) {
            LOGGER.info("AutoJump");
            Minecraft.getInstance().options.autoJump= !Minecraft.getInstance().options.autoJump;
        }
        if (auto_jump_key_bind_on.consumeClick()) {
            LOGGER.info("AutoJump");
            Minecraft.getInstance().options.autoJump=true;
        }
        if (auto_jump_key_bind_off.consumeClick()) {
            LOGGER.info("AutoJump");
            Minecraft.getInstance().options.autoJump=false;
        }
    }
}
