package at.dietze.seb;

import at.dietze.seb.bridge.events.OnPlayerMove;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public static Plugin plugin;

    @Override
    public void onEnable() {
        plugin = this;
        getServer().getPluginManager().registerEvents(new OnPlayerMove(), this);
    }


}
