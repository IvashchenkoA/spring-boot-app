package com.imba.gymmemore.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;

@Service
public class GoogleMapsService {

    @Value("${google.api.key}")
    private String apiKey;

    private static final String DIRECTIONS_API_URL = "https://maps.googleapis.com/maps/api/directions/json";
    private static final String AUTOCOMPLETE_API_URL = "https://maps.googleapis.com/maps/api/place/autocomplete/json";
    private static final String STATIC_MAPS_API_URL = "https://maps.googleapis.com/maps/api/staticmap";
    private static final String GEOCODE_API_URL = "https://maps.googleapis.com/maps/api/geocode/json";


    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper;

    public GoogleMapsService( ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public JsonNode getDirections(String origin, String destination) throws IOException {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(DIRECTIONS_API_URL)
                .queryParam("origin", origin)
                .queryParam("destination", destination)
                .queryParam("key", apiKey);

        String jsonResponse = restTemplate.getForObject(builder.toUriString(), String.class);
        return objectMapper.readTree(jsonResponse);
    }


    public String getRoute(String origin, String destination) throws IOException {
        JsonNode directions = getDirections(origin, destination);

        if (!directions.has("routes") || !directions.path("routes").isArray() || directions.path("routes").size() == 0) {
            throw new IOException("No routes found in the response from Google Maps API");
        }

        JsonNode route = directions.path("routes").get(0);

        if (!route.has("legs") || !route.path("legs").isArray() || route.path("legs").size() == 0) {
            throw new IOException("No legs found in the route from Google Maps API");
        }

        JsonNode legs = route.path("legs").get(0);

        StringBuilder formattedRoute = new StringBuilder();
        formattedRoute.append("Distance: ").append(legs.path("distance").path("text").asText()).append("\n");
        formattedRoute.append("Duration: ").append(legs.path("duration").path("text").asText()).append("\n\n");

        formattedRoute.append("Steps:\n");
        for (JsonNode step : legs.path("steps")) {
            formattedRoute.append(step.path("html_instructions").asText().replaceAll("<[^>]*>", "")).append(" - ");
            formattedRoute.append(step.path("distance").path("text").asText()).append("\n");
        }

        return formattedRoute.toString();
    }


    public JsonNode autocompleteAddress(String input) throws IOException {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(AUTOCOMPLETE_API_URL)
                .queryParam("input", input)
                .queryParam("key", apiKey);

        String jsonResponse = restTemplate.getForObject(builder.toUriString(), String.class);
        return objectMapper.readTree(jsonResponse);
    }

    public boolean isValidAddress(String address) throws IOException {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(GEOCODE_API_URL)
                .queryParam("address", address)
                .queryParam("key", apiKey);

        String jsonResponse = restTemplate.getForObject(builder.toUriString(), String.class);
        JsonNode response = objectMapper.readTree(jsonResponse);

        return response.has("results") && response.path("results").size() > 0;
    }
    public String generateStaticMapWithRoute(String origin, String destination) throws IOException {
        JsonNode directions = getDirections(origin, destination);

        if (!directions.has("routes") || !directions.path("routes").isArray() || directions.path("routes").size() == 0) {
            throw new IOException("No routes found in the response from Google Maps API");
        }

        JsonNode route = directions.path("routes").get(0);
        String overviewPolyline = route.path("overview_polyline").path("points").asText();

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(STATIC_MAPS_API_URL)
                .queryParam("size", "600x300")
                .queryParam("path", "enc:" + overviewPolyline)
                .queryParam("key", apiKey);

        return builder.toUriString();
    }

}
