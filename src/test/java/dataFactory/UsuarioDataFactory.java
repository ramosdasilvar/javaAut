package dataFactory;

import io.restassured.http.ContentType;
import pojo.UsuarioPojo;

import static io.restassured.RestAssured.given;

public class UsuarioDataFactory {
    public static String criarUsuarioAdmin(){
        UsuarioPojo usuario = new UsuarioPojo();

        usuario.setUsuarioLogin("admin");
        usuario.setUsuarioSenha("admin");

        String tokenUsuario = given()
                .contentType(ContentType.JSON)
                .body(usuario)
                .when()
                .post("/v2/login")
                .then()
                .extract()
                .path("data.token");
        return tokenUsuario;
    }
}
