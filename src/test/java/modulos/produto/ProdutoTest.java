package modulos.produto;

import dataFactory.ProdutoDataFactory;
import dataFactory.UsuarioDataFactory;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@DisplayName("Teste de API Rest do modulo de Produto")
public class ProdutoTest {
    private String token;

    @BeforeEach
    public void beforeEach(){
        // Configurando os dados da API Rest da Lojinha
        baseURI = "http://165.227.93.41";
        // port = 8080;
        basePath = "/lojinha";
        // Obter o token do usuário admin

        this.token = UsuarioDataFactory.criarUsuarioAdmin();
    }

    @Test
    @DisplayName("Validar que o valor do produto igual a 0.00 não é permitido")
    public void testValidarLimitesZeradoProibidoValorProduto() {


        //Tentar inserir um produto com valor 0.00 e validar que a mensagem de erro foi apresentada
        // e o status code foi 422
        given()
                        .header("token", this.token)
                        .contentType(ContentType.JSON)
                        .body(ProdutoDataFactory.criaProdutoComumComOValorIGualA(0.00))
                .when()
                        .post("/v2/produtos")
                .then()
                            .assertThat()
                                    .body("error", equalTo("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00"))
                                    .statusCode(422);
    }

    @Test
    @DisplayName("Validar que o valor do produto maior que 7000.00 não é permitido")
    public void testValidarLimitesMaiorQueSeteMil() {

        //Tentar inserir um produto com valor 0.00 e validar que a mensagem de erro foi apresentada
        // e o status code foi 422
        given()
                .header("token", this.token)
                .contentType(ContentType.JSON)
                .body(ProdutoDataFactory.criaProdutoComumComOValorIGualA(7000.01))
            .when()
                .post("/v2/produtos")
            .then()
                .assertThat()
                .body("error", equalTo("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00"))
                .statusCode(422);
    }
}

