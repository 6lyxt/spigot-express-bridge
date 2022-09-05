package at.dietze.seb.bridge.http;

import org.jetbrains.annotations.Contract;
import java.util.HashMap;

public class Payload {

    private HashMap params;

    /**
     * @param params
     */
    @Contract(pure = true)
    public Payload(HashMap params) {
        this.params = params;
    }

    /**
     * @return params value of this class
     */
    public HashMap getParams() {
        return params;
    }
}
