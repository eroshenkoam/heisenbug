package io.github.eroshenkoam.heisenbug.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.specification.RequestSpecification;
import org.apache.groovy.util.Maps;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class RestAssuredIT {

    @Test
    public void testTodoList() throws JsonProcessingException {
        final RequestSpecification base = given()
            .baseUri("http://localhost:8080")
            .contentType("application/json");

        final Map<String, Object> authData = Maps.of(
            "username", "admin",
            "password", "admin",
            "rememberMe", false
        );

        final ObjectMapper mapper = new ObjectMapper();
        final String authJson = mapper.writeValueAsString(authData);

        final String token = base
            .body(authJson)
            .post("/api/authenticate")
            .path("id_token")
            .toString();

        final RequestSpecification authorized = base
            .header("Authorization", "Bearer " + token);

        authorized
            .get("api/todo")
            .then()
            .statusCode(200);
    }

}
