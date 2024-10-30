import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.example.PetBody;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PetPut {

    @BeforeMethod
    public void setup(){
        RestAssured.baseURI = "https://petstore.swagger.io/v2";

    }

    @Test
    public void validateBodyUpdatingPet() throws JsonProcessingException {

        PetBody petBody = new PetBody();
        petBody.setId(88001122);
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
                .put("/pet")
                .then()
                .log().all()
                .statusCode(200)
                .body("id", equalTo(88001122))
                .body("name", equalTo("Yami2"))
                .body("status", equalTo("sold"));
    }

}
