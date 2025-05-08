package modulos.produto;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

@DisplayName("Teste de API Rest do modulo de Produto")
public class ProdutoTest {
    @Test
    @DisplayName("Validar os limites proibidos do valor do produto")
    public void testValidarLimitesProibidosValorProduto() {
        // Configurando os dados da API Rest da Lojinha
        baseURI = "http://165.227.93.41";
        // port = 8080;
        basePath = "/lojinha";
        // Obter o token do usuário admin
        String token = given()
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"usuarioLogin\": \"admin\",\n" +
                        "  \"usuarioSenha\": \"admin\"\n" +
                        "}")
            .when()
                .post("/v2/login")
            .then()
                .extract()
                .path("data.token");


        //Tentar inserir um produto com valor 0.00 e validar que a mensagem de erro foi apresentada
        // e o status code foi 422
        given()
                        .header("token", token)
                        .contentType(ContentType.JSON)
                        .body("{\n" +
                                "  \"produtoNome\": \"PlayStation 5\",\n" +
                                "  \"produtoValor\": 0.00,\n" +
                                "  \"produtoCores\": [\n" +
                                "    \"Branco\"\n" +
                                "  ],\n" +
                                "  \"produtoUrlMock\": \"N/A\",\n" +
                                "  \"componentes\": [\n" +
                                "    {\n" +
                                "      \"componenteNome\": \"Controle\",\n" +
                                "      \"componenteQuantidade\": 1\n" +
                                "    },\n" +
                                "    {\n" +
                                "      \"componenteNome\": \"Cabo HDMI\",\n" +
                                "      \"componenteQuantidade\": 2\n" +
                                "    }\n" +
                                "  ]\n" +
                                "}")
                .when()
                        .post("/v2/produtos")
                .then()
                            .assertThat()
                                    .body("error", equalTo("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00"))
                                    .statusCode(422);
    }
}
