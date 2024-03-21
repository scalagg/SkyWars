package net.mccade.skywars;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
@Setter
public class LimboPlugin extends JavaPlugin {

    @Getter
    private static LimboPlugin instance;
    public boolean debug = true;

    @Override
    public void onEnable() {
        instance = this;

        new Limbo(this);
    }

    @Override
    public void onDisable() {
        Limbo.getInstance().stop();
    }

}
