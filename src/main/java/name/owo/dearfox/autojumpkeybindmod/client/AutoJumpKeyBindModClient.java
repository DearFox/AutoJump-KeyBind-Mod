package name.owo.dearfox.autojumpkeybindmod.client;

import net.minecraft.text.KeybindText;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import org.lwjgl.glfw.GLFW;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import net.minecraft.client.option.KeyBinding;
<<<<<<< Updated upstream
=======
import net.minecraft.text.Text;

>>>>>>> Stashed changes
import net.minecraft.client.util.InputUtil;
import net.minecraft.client.MinecraftClient;

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

import java.util.logging.Logger;

@Environment(EnvType.CLIENT)
public class AutoJumpKeyBindModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        KeyBinding auto_jump_key_bind = KeyBindingHelper.registerKeyBinding(new KeyBinding("option.auto-jump-key-bind.key", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_R, "option.auto-jump-key-bind.category"));
        KeyBinding auto_jump_key_bind_on = KeyBindingHelper.registerKeyBinding(new KeyBinding("option.auto-jump-key-bind.key.on", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, "option.auto-jump-key-bind.category"));
        KeyBinding auto_jump_key_bind_off = KeyBindingHelper.registerKeyBinding(new KeyBinding("option.auto-jump-key-bind.key.off", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, "option.auto-jump-key-bind.category"));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (auto_jump_key_bind.wasPressed()) {
<<<<<<< Updated upstream
                MinecraftClient.getInstance().options.autoJump = !MinecraftClient.getInstance().options.autoJump;
                assert client.player != null;
                client.player.sendMessage(new LiteralText(new TranslatableText("options.autoJump").getString() + ": " + new TranslatableText((MinecraftClient.getInstance().options.autoJump)? "options.on":"options.off").getString()), true);
                    }
                    while (auto_jump_key_bind_on.wasPressed()) {
                        MinecraftClient.getInstance().options.autoJump = true;
                        assert client.player != null;
                        client.player.sendMessage(new LiteralText(new TranslatableText("options.autoJump").getString() + ": " + new TranslatableText("options.on").getString()), true);
                    }
                    while (auto_jump_key_bind_off.wasPressed()) {
                        MinecraftClient.getInstance().options.autoJump = false;
                        assert client.player != null;
                        client.player.sendMessage(new LiteralText(new TranslatableText("options.autoJump").getString() + ": " + new TranslatableText("options.off").getString()), true);
                    }
                });
            }
        }
=======
                //MinecraftClient.getInstance().options.autoJump= !MinecraftClient.getInstance().options.autoJump; //Старая реализация переключения авто прыжка. Не работает на снапшотах.
                if (MinecraftClient.getInstance().options.getAutoJump().getValue()) {
                    MinecraftClient.getInstance().options.getAutoJump().setValue(false);
                    assert client.player != null;
                    client.player.sendMessage(Text.of(Text.literal(Text.translatable("options.autoJump").getString() + ": " + Text.translatable("options.off").getString())), true);
                } else {
                    MinecraftClient.getInstance().options.getAutoJump().setValue(true);
                    assert client.player != null;
                    client.player.sendMessage(Text.of(Text.literal(Text.translatable("options.autoJump").getString() + ": " + Text.translatable("options.on").getString())), true);
                }
            }
            while (auto_jump_key_bind_on.wasPressed()) {
                //MinecraftClient.getInstance().options.autoJump=true;
                MinecraftClient.getInstance().options.getAutoJump().setValue(true);
                assert client.player != null;
                client.player.sendMessage(Text.of(Text.literal(Text.translatable("options.autoJump").getString() + ": " + Text.translatable("options.on").getString())), true);
            }
            while (auto_jump_key_bind_off.wasPressed()) {
                //MinecraftClient.getInstance().options.autoJump=false;
                MinecraftClient.getInstance().options.getAutoJump().setValue(false);
                assert client.player != null;
                client.player.sendMessage(Text.of(Text.literal(Text.translatable("options.autoJump").getString() + ": " + Text.translatable("options.off").getString())), true);
            }
        });
    }
}
>>>>>>> Stashed changes
