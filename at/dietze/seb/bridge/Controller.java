package at.dietze.seb.bridge;

import at.dietze.seb.bridge.http.HTTPConnector;
import at.dietze.seb.bridge.http.Payload;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * <p> Controller class to manage webhook operations </p>
 */
public class Controller {

    /**
     * @param p Player object
     * @throws IOException
     * @deprecated not finished yet
     */
    public static void updateWebHook(Player p) throws IOException {
        HTTPConnector httpConnector = new HTTPConnector("https://url.com/", 8080);
        Payload payload = new Payload(getPositionMap(p));
        httpConnector.post(payload);
    }

    /**
     * @return Map of player Positions
     * @deprecated not finished yet
     */
    @Contract(pure = true)
    public static @NotNull Map<Integer, Integer> getPositionMap(Player p) {
        HashMap<Integer, Integer> curr = new HashMap<>();
        curr.put(0, p.getLocation().getBlockX());
        curr.put(1, p.getLocation().getBlockY());
        curr.put(2, p.getLocation().getBlockZ());

        return curr;
    }


}
