package com.araujo.araujofood;

import com.araujo.araujofood.domain.model.Cozinha;
import com.araujo.araujofood.domain.model.Restaurante;
import com.araujo.araujofood.domain.repository.CozinhaRepository;
import com.araujo.araujofood.domain.repository.RestauranteRepository;
import com.araujo.araujofood.util.DatabaseCleaner;
import com.araujo.araujofood.util.ResourceUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;

import java.math.BigDecimal;

import static io.restassured.RestAssured.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
public class CadastroRestauranteIT {

    @LocalServerPort
    private int port;

    @Autowired
    private DatabaseCleaner databaseCleaner;

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private CozinhaRepository cozinhaRepository;

    private static final int RESTAURANTE_ID_INEXISTENTE = 100;

    private Restaurante restauranteAmericano;
    private Cozinha cozinhaJaponesa;
    private String jsonRestauranteJapones;
    private String jsonRestauranteComFreteInvalido;
    private String jsonRestauranteCozinhaInexistente;

    @BeforeEach
    public void setUp() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.port = port;
        RestAssured.basePath = "/restaurantes";

        databaseCleaner.clearTables();
        prepararDados();

        jsonRestauranteJapones = ResourceUtils.getContentFromResource("/json/correto/restaurante-japones.json");
        jsonRestauranteComFreteInvalido = ResourceUtils.getContentFromResource("/json/correto/restaurante-com-frete-invalido.json");
        jsonRestauranteCozinhaInexistente = ResourceUtils.getContentFromResource("/json/correto/restaurante-cozinha-inexistente.json");
    }

    @Test
    public void deveRetornarStatus200_QuandoConsultarCozinhas() {
        given()
                .accept(ContentType.JSON)
            .when()
                .get()
            .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void deveRetornarStatus404_QuandoConsultarCozinhaInexistente() {
        given()
                .pathParam("restauranteId", RESTAURANTE_ID_INEXISTENTE)
                .accept(ContentType.JSON)
            .when()
                .get("/{restauranteId}")
            .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test
    public void deveRetornar201_QuandoCriarRestaurante() {
        given()
                .body(jsonRestauranteJapones)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
            .when()
                .post()
            .then()
                .statusCode(HttpStatus.CREATED.value());
    }

    @Test
    public void deveRetornar400_QuandoCriarRestauranteComCozinhaInexistente() {
        given()
                .body(jsonRestauranteCozinhaInexistente)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
            .when()
                .post()
            .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void deveRetornarErro_QuandoCriarRestauranteComFreteInvalido() {
        given()
                .body(jsonRestauranteComFreteInvalido)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
            .when()
                .post()
            .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    private void prepararDados() {
        Cozinha cozinha = new Cozinha();
        cozinha.setNome("Americana");
        cozinhaRepository.save(cozinha);

        cozinhaJaponesa = new Cozinha();
        cozinhaJaponesa.setNome("Japonesa");
        cozinhaRepository.save(cozinhaJaponesa);

        restauranteAmericano = new Restaurante();
        restauranteAmericano.setNome("Restaurante Americano");
        restauranteAmericano.setTaxaFrete(new BigDecimal("5.35"));
        restauranteAmericano.setCozinha(cozinha);
        restauranteRepository.save(restauranteAmericano);
    }
}
