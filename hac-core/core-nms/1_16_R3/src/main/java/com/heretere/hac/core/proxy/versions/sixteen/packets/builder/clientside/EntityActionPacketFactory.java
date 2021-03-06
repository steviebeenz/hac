package com.heretere.hac.core.proxy.versions.sixteen.packets.builder.clientside;

import com.heretere.hac.api.events.packets.factory.PacketFactory;
import com.heretere.hac.api.events.packets.wrapper.clientside.EntityActionPacket;
import com.heretere.hac.api.player.HACPlayer;
import net.minecraft.server.v1_16_R3.PacketPlayInEntityAction;
import org.jetbrains.annotations.NotNull;

/**
 * The type Entity action packet factory.
 */
public final class EntityActionPacketFactory extends PacketFactory<EntityActionPacket> {
    /**
     * Instantiates a new Entity action packet factory.
     */
    public EntityActionPacketFactory() {
        super(PacketPlayInEntityAction.class);
    }

    @Override
    public @NotNull EntityActionPacket create(
        final @NotNull HACPlayer player,
        final @NotNull Object packet
    ) {
        PacketPlayInEntityAction entityAction = (PacketPlayInEntityAction) packet;

        EntityActionPacket.Action action;

        switch (entityAction.c()) {
            case PRESS_SHIFT_KEY:
                action = EntityActionPacket.Action.START_SNEAKING;
                break;
            case RELEASE_SHIFT_KEY:
                action = EntityActionPacket.Action.STOP_SNEAKING;
                break;
            case START_SPRINTING:
                action = EntityActionPacket.Action.START_SPRINTING;
                break;
            case STOP_SPRINTING:
                action = EntityActionPacket.Action.STOP_SPRINTING;
                break;
            default:
                action = EntityActionPacket.Action.INVALID;
        }

        return new EntityActionPacket(action);
    }

    @Override
    public @NotNull Class<EntityActionPacket> getWrappedClass() {
        return EntityActionPacket.class;
    }
}
