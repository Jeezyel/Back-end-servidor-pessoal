package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

import static org.hamcrest.Matchers.*;

@QuarkusTest
public class GreetingResourceTest {

    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/hello")
          .then()
             .statusCode(200)
             .body(is("Hello from RESTEasy Reactive"));
    }

    @Test
    public void testGetAllClientes() {
        given()
                .when().get("/cliente/getAll")
                .then()
                .statusCode(200)
                .body("$", not(empty()))
                .body("id", everyItem(notNullValue()))
                .body("nome", everyItem(notNullValue()))
                .body("login", everyItem(notNullValue()))
                .body("senha", everyItem(notNullValue()))
                .body("cpf", everyItem(notNullValue()))
                .body("telefone", everyItem(notNullValue()))
                .body("perfil", everyItem(notNullValue()));
    }

}