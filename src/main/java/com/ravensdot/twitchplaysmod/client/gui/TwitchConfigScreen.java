package com.ravensdot.twitchplaysmod.client.gui;

import com.ravensdot.twitchplaysmod.TwitchPlays;
import com.ravensdot.twitchplaysmod.config.TwitchConfig;
import com.ravensdot.twitchplaysmod.twitch.TwitchHandler;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.fml.client.gui.widget.ExtendedButton;

public class TwitchConfigScreen extends Screen {

    public static final int BTN_WIDTH = 150;
    public static final int BTN_HEIGHT = 20;

    private TextFieldWidget channelNameField;
    private Button connectButton, saveButton;

    private TwitchHandler twitch;
    private boolean connectSwitch = false;

    public TwitchConfigScreen(ITextComponent titleIn) {
        super(new TranslationTextComponent("gui." + TwitchPlays.MOD_ID + ".title"));
    }

    @Override
    protected void init() {
        final int width2 = this.width / 2;
        final int height2 = this.height / 2;

        channelNameField = this.addButton(new TextFieldWidget(font, width2 - BTN_WIDTH /2, height2 - BTN_HEIGHT*2, BTN_WIDTH, BTN_HEIGHT, I18n.format("gui." + TwitchPlays.MOD_ID + ".channel_add")));
        channelNameField.setText(TwitchConfig.CHANNEL);
        saveButton = this.addButton(new ExtendedButton(width2, height2 - BTN_HEIGHT, BTN_WIDTH, BTN_HEIGHT, I18n.format("gui." + TwitchPlays.MOD_ID + ".button_save"),
                $ -> saveInput()
        ));
        connectButton = this.addButton(new ExtendedButton(width2 - BTN_WIDTH, height2, BTN_WIDTH, BTN_HEIGHT, getConnectFormat(),
                $ -> setConnectDisconnect()
        ));
        this.addButton(new ExtendedButton(width2, height2, BTN_WIDTH, BTN_HEIGHT, I18n.format("gui." + TwitchPlays.MOD_ID + ".button_done"),
                $ -> this.minecraft.displayGuiScreen(null)
                ));
        super.init();
    }

    @Override
    public void tick() {
        super.tick();
        channelNameField.tick();
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        this.renderBackground();
        this.drawCenteredString(this.font, this.title.getFormattedText(), this.width / 2, 20, 16777215);
        super.render(mouseX, mouseY, partialTicks);
    }

    private void saveInput() {
        TwitchConfig.GENERAL.Channel.set(channelNameField.getText());
        TwitchConfig.bakeConfig();
    }

    private String getConnectFormat() {
        return connectSwitch ?
                I18n.format("gui." + TwitchPlays.MOD_ID + ".button_disconnect")
                : I18n.format("gui." + TwitchPlays.MOD_ID + ".button_connect");
    }

    private void setConnectDisconnect()
    {
        if (connectSwitch) {
            disconnect();
        } else {
            connect();
        }
    }

    private void connect()
    {
        if (twitch != null) {
            twitch.stop();
        }
        minecraft.player.sendMessage(new StringTextComponent("Connecting..."));
        try {
            twitch = new TwitchHandler();
            twitch.register();
            twitch.start();
            minecraft.player.sendMessage(new StringTextComponent(String.format("Connected to channel %s!", TwitchConfig.CHANNEL)));
            connectSwitch = true;
            connectButton.setMessage(I18n.format("gui." + TwitchPlays.MOD_ID + ".button_disconnect"));
        } catch (Exception e) {
            e.printStackTrace();
            minecraft.player.sendMessage(new StringTextComponent("Error: incorrect configuration :/"));
        }
    }

    private void disconnect()
    {
        if (twitch != null) {
            minecraft.player.sendMessage(new StringTextComponent("Disconnecting..."));
            twitch.stop();
            minecraft.player.sendMessage(new StringTextComponent(String.format("Disconnected from channel %s.", TwitchConfig.CHANNEL)));
        }
        connectSwitch = false;
        connectButton.setMessage(I18n.format("gui." + TwitchPlays.MOD_ID + ".button_connect"));
    }
}
