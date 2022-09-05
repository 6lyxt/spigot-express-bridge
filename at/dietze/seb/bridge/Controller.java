package at.dietze.seb.bridge;

import at.dietze.seb.bridge.http.HTTPConnector;
import at.dietze.seb.bridge.http.Payload;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.HashMap;

/**
 * <p> Controller class to manage webhook operations </p>
 */
public class Controller {

    /**
     * @param p
     * @throws IOException
     * @deprecated not finished yet
     */
    public static void updateWebHook(Player p) throws IOException {
        HTTPConnector httpConnector = new HTTPConnector("https://url.com/", 8080);
        Payload payload = new Payload(getPositionMap());

        // TODO: Finish this
    }

    /**
     * @return
     * @deprecated not finished yet
     */
    @Contract(pure = true)
    public static @NotNull HashMap getPositionMap() {
        String posX;
        String posY;
        String posZ;

        // TODO: Finish this

        return new HashMap();
    }


}
