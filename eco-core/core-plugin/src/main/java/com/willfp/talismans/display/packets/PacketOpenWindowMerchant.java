package com.willfp.talismans.display.packets;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;
import com.willfp.eco.util.ProxyUtils;
import com.willfp.eco.util.plugin.AbstractEcoPlugin;
import com.willfp.eco.util.protocollib.AbstractPacketAdapter;
import com.willfp.talismans.proxy.proxies.VillagerTradeProxy;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.MerchantRecipe;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.stream.Collectors;

public class PacketOpenWindowMerchant extends AbstractPacketAdapter {
    /**
     * Instantiate a new listener for {@link PacketType.Play.Server#OPEN_WINDOW_MERCHANT}.
     *
     * @param plugin The plugin to listen through.
     */
    public PacketOpenWindowMerchant(@NotNull final AbstractEcoPlugin plugin) {
        super(plugin, PacketType.Play.Server.OPEN_WINDOW_MERCHANT, false);
    }

    @Override
    public void onSend(@NotNull final PacketContainer packet,
                       @NotNull final Player player) {
        List<MerchantRecipe> recipes = packet.getMerchantRecipeLists().readSafely(0);

        recipes = recipes.stream().peek(merchantRecipe -> {
            if (merchantRecipe.getResult().getType() != Material.PLAYER_HEAD) {
                return;
            }

            ProxyUtils.getProxy(VillagerTradeProxy.class).displayTradeTalismans(merchantRecipe);
        }).collect(Collectors.toList());

        packet.getMerchantRecipeLists().writeSafely(0, recipes);
    }
}
