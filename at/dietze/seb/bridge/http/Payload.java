package at.dietze.seb.bridge.http;

import org.jetbrains.annotations.Contract;
import java.util.Map;

public class Payload {

    private Map params;

    /**
     * @param params
     */
    @Contract(pure = true)
    public Payload(Map params) {
        this.params = params;
    }

    /**
     * @return params value of this class
     */
    public Map getParams() {
        return params;
    }
}
