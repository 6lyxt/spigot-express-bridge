package at.dietze.seb.bridge.http;

import org.jetbrains.annotations.Contract;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * <p>Manages http operations</p>
 * @see <a href="https://github.com/6lyxt/SpigotExpressBridge/#httphelper">Documentation</a>
 */
public class HTTPConnector {

    /**
     * <p>URL where the webhook send requests to</p>
     */
    private String url;

    /**
     * <p>80 for http, 443 for https, 8000 for express testing apps</p>
     */
    private int port;

    /**
     * @param url
     * @param port
     */
    @Contract(pure = true)
    public HTTPConnector(String url, int port) {
        this.url = url;
        this.port = port;
    }

    /**
     * @param payload
     * @return body of requested URL
     */
    public String get(Payload payload) throws IOException {

        String params = payload.getParams().size() > 0 ? this.getParamsString(payload.getParams()) : "";

        URL url = new URL(this.url + params);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        if(con.getResponseCode() != 200) {
            return con.getResponseMessage() + " : " + con.getResponseCode();
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        con.disconnect();

        return String.valueOf(content);
    }

    /**
     * @param params
     * @return human-readable string
     * @throws UnsupportedEncodingException
     */
    public String getParamsString(Map<String, String> params)
            throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();

        for (Map.Entry<String, String> entry : params.entrySet()) {
            result.append(URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8));
            result.append("&");
        }

        String resultString = result.toString();
        return resultString.length() > 0 ? resultString.substring(0, resultString.length() - 1) : resultString;
    }
}
