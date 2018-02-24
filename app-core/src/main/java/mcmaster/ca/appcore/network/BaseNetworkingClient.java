package mcmaster.ca.appcore.network;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Wrapper class for Random User Generator.
 */
class BaseNetworkingClient {
    private OkHttpClient client;

    /**
     * Constructor.
     */
    BaseNetworkingClient() {
        client = new OkHttpClient();
    }

    /**
     * Creates a new request builder with default base URL.
     *
     * @param path
     *     path extension from Base URL.
     *
     * @return new Request builder.
     */
    Request.Builder createRequestBuilder(String path) {
        return new Request.Builder().url(path);
    }

    /**
     * Enqueues a request in the okHttp library.
     *
     * @param request
     *     new request.
     * @param callback
     *     new callback.
     */
    void enqueueRequest(Request request, Callback callback) {
        client.newCall(request).enqueue(callback);
    }
}