package com.willfp.talismans.integrations.mcmmo.plugins;

import com.gmail.nossr50.events.fake.FakeEvent;
import com.willfp.talismans.integrations.mcmmo.McmmoIntegration;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

public class McmmoIntegrationImpl implements McmmoIntegration {
    @Override
    public boolean isFake(@NotNull final Event event) {
        return event instanceof FakeEvent;
    }

    @Override
    public String getPluginName() {
        return "mcMMO";
    }
}
