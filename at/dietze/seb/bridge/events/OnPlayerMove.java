package at.dietze.seb.bridge.events;

import at.dietze.seb.bridge.Controller;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.io.IOException;

public class OnPlayerMove implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        // TODO: implement region check
        try {
            Controller.updateWebHook(e.getPlayer());
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
