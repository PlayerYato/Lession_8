import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.example.PetBody;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class PetDelete {

    private static final int PET_ID = 881122;

    @BeforeMethod
    public void setup(){
        RestAssured.baseURI = "https://petstore.swagger.io/v2";

    }

    @Test
    public void validateBodyDeletingPet() throws JsonProcessingException {

        PetBody petBody = new PetBody();
        petBody.setId(881122);
        petBody.setName("Yami2");
        petBody.setStatus("sold");

        ObjectMapper mapper = new ObjectMapper();
        String bodyForPet = mapper.writeValueAsString(petBody);


        given()
                .baseUri("https://petstore.swagger.io/v2")
                .contentType(ContentType.JSON)
                .body(bodyForPet)
                .log().all()
                .when()
                .delete("/pet/" + PET_ID)
                .then()
                .log().all()
                .statusCode(200);

        given()
                .baseUri("https://petstore.swagger.io/v2")
                .log().all()
                .when()
                .get("/pet/" + PET_ID)
                .then()
                .log().all()
                .statusCode(404);
    }
}