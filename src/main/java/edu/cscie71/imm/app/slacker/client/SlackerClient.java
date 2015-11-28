package edu.cscie71.imm.app.slacker.client;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Request;
import org.apache.http.NameValuePair;
import java.util.List;
import java.io.IOException;

public class SlackerClient implements ISlackerClient {

    private static final String MESSAGE_URL = "https://slack.com/api/chat.postMessage";

    public String postMessage(String token, MessagePost message) {
        message.setToken(token);
        List<NameValuePair> input = message.toForm();
        return postToURL(MESSAGE_URL, input);
    }

    private String postToURL(String url, List<NameValuePair> formData) {
        Request toSend = Request.Post(url).bodyForm(formData);
        try {
            return toSend.execute().returnContent().asString();
        }
        catch (ClientProtocolException e) {
            return "{\"ok\":false,\"error\":\"client_protocol_exception: " + e.getMessage() + "\"}";
        }
        catch (IOException e) {
            return "{\"ok\":false,\"error\":\"io_exception: " + e.getMessage() + "\"}";
        }
    }
}
