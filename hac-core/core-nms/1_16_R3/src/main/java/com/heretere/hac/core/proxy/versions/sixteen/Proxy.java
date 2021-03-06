package com.heretere.hac.core.proxy.versions.sixteen;

import com.heretere.hac.api.HACAPI;
import com.heretere.hac.api.events.packets.PacketReferences;
import com.heretere.hac.core.proxy.CoreVersionProxy;
import com.heretere.hac.core.proxy.packets.channel.ChannelInjector;
import com.heretere.hac.core.proxy.versions.sixteen.packets.builder.clientside.AbilitiesPacketFactory;
import com.heretere.hac.core.proxy.versions.sixteen.packets.builder.clientside.EntityActionPacketFactory;
import com.heretere.hac.core.proxy.versions.sixteen.packets.builder.clientside.FlyingPacketFactory;
import com.heretere.hac.core.proxy.versions.sixteen.packets.builder.serverside.EntityVelocityPacketFactory;
import com.heretere.hac.core.proxy.versions.sixteen.packets.channel.ChannelInjectorProxy;
import com.heretere.hac.util.plugin.HACPlugin;
import org.jetbrains.annotations.NotNull;

/**
 * The type Proxy.
 */
public final class Proxy extends CoreVersionProxy {
    /**
     * The parent plugin reference.
     */
    private final @NotNull HACPlugin parent;
    /**
     * The injector used to attach channel handlers to all players.
     */
    private final @NotNull ChannelInjectorProxy channelInjectorProxy;

    /**
     * Instantiates a new Proxy.
     *
     * @param parent the parent
     */
    public Proxy(final @NotNull HACPlugin parent) {
        this.parent = parent;
        this.channelInjectorProxy = new ChannelInjectorProxy(this.parent);
    }

    @Override
    protected void registerPackets() {
        PacketReferences packetReferences = HACAPI.getInstance().getPacketReferences();

        //clientside
        packetReferences.getClientSide().getAbilities().register(new AbilitiesPacketFactory());
        packetReferences.getClientSide().getEntityAction().register(new EntityActionPacketFactory());
        packetReferences.getClientSide().getFlying().register(new FlyingPacketFactory());

        //serverside
        packetReferences.getServerSide().getEntityVelocity().register(new EntityVelocityPacketFactory(this.parent));
    }

    @Override
    public @NotNull ChannelInjector getChannelInjector() {
        return this.channelInjectorProxy;
    }

    @Override
    protected void load() {
        //Nothing yet
    }

    @Override
    protected void unload() {
        this.channelInjectorProxy.shutdown();
    }
}
