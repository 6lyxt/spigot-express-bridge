package at.dietze.seb.bridge.regions;

import org.bukkit.Chunk;

import java.util.Map;

/**
 * <p>Used to split the rendered world into regions where the webhook will emit an update</p>
 * <p>The server will reproduce Regions every time a player moves, to assure smooth gameplay</p>
 */
public class Region {
    private Map<Integer, Chunk> chunks;

    /**
     * @param chunks
     */
    public Region(Map<Integer, Chunk> chunks) {
        this.chunks = chunks;
    }

    /**
     * @return chunks
     */
    public Map<Integer, Chunk> getChunks() {
        return chunks;
    }

    /**
     * @param chunks
     */
    public void setChunks(Map<Integer, Chunk> chunks) {
        this.chunks = chunks;
    }
}
