package at.dietze.seb.bridge.http;

import org.jetbrains.annotations.Contract;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * <p>Manages http operations</p>
 * @see <a href="https://github.com/6lyxt/SpigotExpressBridge/#httphelper">Documentation</a>
 * TODO: This needs some cleanup!
 */
public class HTTPConnector {

    /**
     * <p>URL where the webhook send requests to</p>
     */
    private String url;

    /**
     * <p>80 for http, 443 for https, 8000 for express testing apps</p>
     * <h1> Unused ATM! </h1>
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
     * @throws IOException
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
     * @param payload
     * @return response of api
     * @throws IOException
     */
    public String post(Payload payload) throws IOException {
        URL url = new URL(this.url);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setDoOutput(true);

        String sj = getParamsString(payload.getParams());

        byte[] out = sj.getBytes(StandardCharsets.UTF_8);
        int length = out.length;

        // TODO: Migrate to JSON

        con.setFixedLengthStreamingMode(length);
        con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        con.connect();
        try(OutputStream os = con.getOutputStream()) {
            os.write(out);
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
