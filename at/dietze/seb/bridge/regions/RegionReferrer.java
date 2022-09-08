package at.dietze.seb.bridge.regions;

import org.bukkit.Chunk;

import java.util.Map;

/**
 * <p>Used to build a reference between a chunk and a region</p>
 */
public class RegionReferrer {

    /**
     * <p> Chunk object for reference</p>
     */
    private Chunk chunk;

    /**
     * @param chunk
     */
    public RegionReferrer(Chunk chunk) {
        this.chunk = chunk;
    }

    /**
     * <p> Method to link a chunk to a region</p>
     * @param region
     */
    private void link(Region region) {
        Map<Integer, Chunk> merge = region.getChunks();
        merge.put(region.getChunks().size() + 1, this.chunk);
        region.setChunks(merge);
    }
}
