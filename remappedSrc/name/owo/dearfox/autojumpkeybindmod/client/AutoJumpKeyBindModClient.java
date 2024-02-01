package name.owo.dearfox.autojumpkeybindmod.client;

import org.lwjgl.glfw.GLFW;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import net.minecraft.client.option.KeyBinding;
import net.minecraft.text.LiteralTextContent;
import net.minecraft.text.Text;

import net.minecraft.client.util.InputUtil;
import net.minecraft.client.MinecraftClient;

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

@Environment(EnvType.CLIENT)
public class AutoJumpKeyBindModClient implements ClientModInitializer {
    //public static final Logger LOGGER = LoggerFactory.getLogger("ModId");
    @Override
    public void onInitializeClient() {
        KeyBinding auto_jump_key_bind = KeyBindingHelper.registerKeyBinding(new KeyBinding("option.auto-jump-key-bind.key", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_R, "option.auto-jump-key-bind.category"));
        KeyBinding auto_jump_key_bind_on = KeyBindingHelper.registerKeyBinding(new KeyBinding("option.auto-jump-key-bind.key.on", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, "option.auto-jump-key-bind.category"));
        KeyBinding auto_jump_key_bind_off = KeyBindingHelper.registerKeyBinding(new KeyBinding("option.auto-jump-key-bind.key.off", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, "option.auto-jump-key-bind.category"));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (auto_jump_key_bind.wasPressed()) {
                //MinecraftClient.getInstance().options.autoJump= !MinecraftClient.getInstance().options.autoJump; //Старая реализация переключения авто прыжка. Не работает на снапшотах.
                if (MinecraftClient.getInstance().options.getAutoJump().getValue()) {
                    MinecraftClient.getInstance().options.getAutoJump().setValue(false);
                    assert client.player != null;
                    client.player.sendMessage(Text.of(new LiteralTextContent(Text.translatable("options.autoJump").getString() + ": " + Text.translatable("options.off").getString()).string()), true);
                } else {
                    MinecraftClient.getInstance().options.getAutoJump().setValue(true);
                    assert client.player != null;
                    client.player.sendMessage(Text.of(new LiteralTextContent(Text.translatable("options.autoJump").getString() + ": " + Text.translatable("options.on").getString()).string()), true);
                }
            }
            while (auto_jump_key_bind_on.wasPressed()) {
                //MinecraftClient.getInstance().options.autoJump=true;
                MinecraftClient.getInstance().options.getAutoJump().setValue(true);
                assert client.player != null;
                client.player.sendMessage(Text.of(new LiteralTextContent(Text.translatable("options.autoJump").getString() + ": " + Text.translatable("options.on").getString()).string()), true);
            }
            while (auto_jump_key_bind_off.wasPressed()) {
                //MinecraftClient.getInstance().options.autoJump=false;
                MinecraftClient.getInstance().options.getAutoJump().setValue(false);
                assert client.player != null;
                client.player.sendMessage(Text.of(new LiteralTextContent(Text.translatable("options.autoJump").getString() + ": " + Text.translatable("options.off").getString()).string()), true);
            }
        });
    }
}
