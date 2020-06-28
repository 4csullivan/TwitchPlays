package com.ravensdot.twitchplaysmod.network.packets;

import net.minecraft.network.PacketBuffer;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Collection;
import java.util.Random;
import java.util.function.Supplier;

public class EffectPacket
{
    //private final int effect;
    private final int duration;
    private final int strength;

    public EffectPacket(PacketBuffer buf)
    {
            this.duration = buf.readInt();
            this.strength = buf.readInt();
    }

    public EffectPacket(int duration, int strength)
    {
        this.duration = duration;
        this.strength = strength;
    }

    public void encode(PacketBuffer buf)
    {
        buf.writeInt(this.duration);
        buf.writeInt(this.strength);
    }

    public EffectPacket decode(PacketBuffer buf)
    {
        return new EffectPacket(buf.readInt(), buf.readInt());
    }

    public void handle(Supplier<NetworkEvent.Context> ctx)
    {
        ctx.get().enqueueWork(() -> {
            World world = ctx.get().getSender().world;
            if (!world.isRemote) {
                Collection<Effect> collection = ForgeRegistries.POTIONS.getValues();
                Object[] arr = collection.toArray();
                Random rand = new Random();
                Effect effect = Effects.HASTE;
                if (arr[rand.nextInt(arr.length)] instanceof Effect){
                    effect = (Effect)arr[rand.nextInt(arr.length)];
                }
                ctx.get().getSender().sendMessage(new StringTextComponent(String.format("Applied effect - %s %d for %d seconds", effect.getDisplayName().getUnformattedComponentText(), this.strength + 1, this.duration)));
                ctx.get().getSender().addPotionEffect(new EffectInstance(effect, 20 * this.duration, this.strength, false, false));
            }
        });
    }
}
