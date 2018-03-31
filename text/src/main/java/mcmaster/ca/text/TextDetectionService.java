package mcmaster.ca.text;

import android.net.Uri;
import android.support.annotation.NonNull;

import com.google.gson.reflect.TypeToken;

import mcmaster.ca.appcore.network.BaseNetworkingClient;
import mcmaster.ca.appcore.network.HttpCallback;
import mcmaster.ca.appcore.network.RestEndpoints;
import mcmaster.ca.appcore.network.ServiceHttpCallback;
import mcmaster.ca.text.models.GetCreditsRs;
import mcmaster.ca.text.models.SearchMovieRs;
import okhttp3.Request;

import java.lang.reflect.Type;

public final class TextDetectionService {
    // Keys
    private static final String API_KEY = "api_key";
    private static final String LANGUAGE = "language";
    private static final String QUERY = "query";

    // Values
    private static final String MOVIE_DB_API_KEY = "6a527439d35b5f40b5e32e67288aeadc";
    private static final String LANGUAGE_VALUE = "en-US";

    private final BaseNetworkingClient client;

    /**
     * Constructor to create a service to handle networking requests for retrieve information from a text.
     */
    public TextDetectionService() {
        this.client = new BaseNetworkingClient();
    }

    /**
     * Creates a request to get a list of movies matching the query.
     *
     * @param query
     *     The movie to search.
     * @param callback
     *     The success and failure callback.
     */
    public void retrieveResultsFromText(@NonNull String query,
        @NonNull HttpCallback<SearchMovieRs> callback) {
        // Build the URL.
        Uri uri = Uri.parse(RestEndpoints.THE_MOVIE_DB_BASE_URL + RestEndpoints.SEARCH_MOVIE_PATH)
            .buildUpon()
            .appendQueryParameter(API_KEY, MOVIE_DB_API_KEY)
            .appendQueryParameter(LANGUAGE, LANGUAGE_VALUE)
            .appendQueryParameter(QUERY, query)
            .build();

        // Build the Request
        Request.Builder requestBuilder = client.createRequestBuilder(uri.toString());

        // Send the Request.
        Type type = new TypeToken<SearchMovieRs>() {}.getType();
        client.enqueueRequest(requestBuilder.build(),
            new ServiceHttpCallback<>(type, callback));
    }

    /**
     * Retrieves the credits for a particular movie. The credits will contain the cast of the movie.
     *
     * @param movieId
     *     The movie id obtained from retrieveResultsFromText call.
     * @param callback
     *     The success and failure callback.
     */
    public void retrieveCreditsForMovie(@NonNull String movieId,
        @NonNull HttpCallback<GetCreditsRs> callback) {
        Uri uri = Uri.parse(RestEndpoints.THE_MOVIE_DB_BASE_URL + RestEndpoints.getMoviePath(movieId))
            .buildUpon()
            .appendQueryParameter(API_KEY, MOVIE_DB_API_KEY)
            .build();

        // Build the Request
        Request.Builder requestBuilder = client.createRequestBuilder(uri.toString());

        // Send the Request.
        Type type = new TypeToken<GetCreditsRs>() {}.getType();
        client.enqueueRequest(requestBuilder.build(),
            new ServiceHttpCallback<>(type, callback));
    }
}
