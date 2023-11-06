package ru.stroy.testcontainer;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.stroy.controller.AuthenticationController;
import ru.stroy.dto.enumeration.AdvertTypeEnum;
import ru.stroy.dto.enumeration.CurrencyTypeEnum;
import ru.stroy.dto.request.*;
import ru.stroy.entity.datasource.Login;
import ru.stroy.repositories.AdvertRepository;
import ru.stroy.repositories.security.LoginRepository;

import java.time.LocalDate;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
public class ControllersTest {
    @LocalServerPort
    private Integer port;

    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15");

    @Autowired
    AuthenticationController authenticationController;
    @Autowired
    LoginRepository loginRepository;
    @Autowired
    AdvertRepository advertRepository;

    @BeforeAll
    static void beforeAll() {
        postgres.start();
    }

    @AfterAll
    static void afterAll() {
        postgres.stop();
    }

    private final static String contextUsername = "mike";
    private final static String contextPassword = "admin";
    private final static String contextURL = "http://best-url.com";
    private final static String contextTitle = "test title";
    private final static String contextDescription = "test description";
    private final static Long contextPrice = 750L;
    private final static AdvertCreateDto contextAdvert = new AdvertCreateDto(
            AdvertTypeEnum.ResumeEmployee,
            null,
            contextTitle,
            contextDescription,
            contextPrice,
            CurrencyTypeEnum.Rub);
    private final static CompanyCreateDto contextCompany = new CompanyCreateDto(contextUsername, contextURL);
    private static Login actualLogin = null;

    @BeforeEach
    void setUp() {
        RestAssured.baseURI = "http://localhost:" + port;
        authenticationController.registerUser(new RegisterRequestDto(contextUsername, contextPassword));
        advertRepository.deleteAll();
    }

    private RequestSpecification whenAuth() {
        return
                given()
                        .contentType(ContentType.JSON)
                .when()
                        .auth()
                                .preemptive()
                                .oauth2(
                given()
                        .contentType(ContentType.JSON)
                .when()
                        .auth()
                                .preemptive()
                                .basic(contextUsername, contextPassword)
                        .post("/login")
                        .then()
                                .statusCode(200)
                        .extract()
                                .response()
                                .getBody()
                                .asString());
    }

    private ValidatableResponse basicGet(String url) {
        return whenAuth()
                .get(url)
                .then()
                    .statusCode(200)
                    .time(lessThan(1500L));
    }

    private ValidatableResponse basicPost(String url, Object obj) {
        return whenAuth()
                .body(obj)
                .post(url)
                .then()
                .statusCode(200)
                .time(lessThan(1500L));
    }

    private ValidatableResponse basicPut(String url, Object obj) {
        return whenAuth()
                .body(obj)
                .put(url)
                .then()
                .statusCode(200)
                .time(lessThan(1500L));
    }

    private Login getLogin() {
        if (actualLogin == null)
            actualLogin = loginRepository.findByUsername(contextUsername).get();
        return actualLogin;
    }


    @Test
    void getLoginAuth() {
        whenAuth();
    }

    @Test
    void getAccountInfo() {
        basicGet("/account")
            .body("id", equalTo(getLogin().getAccount().getId().intValue()));
    }

    @Test
    void getAccountInfoByOtherHand() {
        basicGet("/account/" + getLogin().getAccount().getId())
                .body("id", equalTo(getLogin().getAccount().getId().intValue()));
    }

    @Test
    void getAccountInfoAdmin() {
        basicGet("/account/0")
                .body("id", equalTo(0))
                .body("name", equalTo("admin"));
    }

    @Test
    void changeAccountFields() {
        AccountUpdateDto accountUpdateDto = new AccountUpdateDto(contextUsername, contextURL, LocalDate.now());
        basicPost("/account", accountUpdateDto);
        basicGet("/account")
                .body("id", equalTo(getLogin().getAccount().getId().intValue()))
                .body("name", equalTo(contextUsername))
                .body("avatarUrl", equalTo(contextURL));
    }

    @Test
    void putSimpleAdvert() {
        basicPut("/advert", contextAdvert);
        basicGet("/advert")
                .body("type.code", hasItem(contextAdvert.getAdvertType().code.intValue()))
                .body("title", hasItem(contextAdvert.getTitle()))
                .body("description", hasItem(contextAdvert.getDescription()))
                .body("price", hasItem(contextAdvert.getPrice().intValue()))
                .body("currency.code", hasItem(contextAdvert.getCurrency().getCode().intValue()));
    }

    @Test
    void createCompany() {
        basicPut("/company", contextCompany);
    }
}