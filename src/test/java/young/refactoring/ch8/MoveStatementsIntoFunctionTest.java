package young.refactoring.ch8;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import young.refactoring.ch8.moveStatementsIntoFunction.Camera;
import young.refactoring.ch8.moveStatementsIntoFunction.Person;
import young.refactoring.ch8.moveStatementsIntoFunction.Photo;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoveStatementsIntoFunctionTest {

    private Camera camera;
    private Photo photo;
    private Person person;

    @BeforeEach
    void setUp() {
        camera = new Camera();
        photo = new Photo("Test Title", "Test Location", LocalDateTime.of(2024, 3, 13, 0, 0, 0));
        person = new Person("John Doe", photo);
    }

    @Test
    @DisplayName("사진 데이터가 올바르게 출력되는지")
    void testEmitPhotoData() {
        String expected = "<p> 제목: Test Title </p>" +
                "<p> 위치: Test Location </p>" +
                "<p> 날짜: 2024-03-13T00:00 </p>";
        assertEquals(expected, camera.emitPhotoData(photo));
    }

    @Test
    @DisplayName("사진 데이터를 div로 감싸서 출력이 되는지")
    void testPhotoDiv() {
        String expected = "<div>" +
                "<p> 제목: Test Title </p>" +
                "<p> 위치: Test Location </p>" +
                "<p> 날짜: 2024-03-13T00:00 </p>" +
                "</div>";
        assertEquals(expected, camera.photoDiv(photo));
    }

    @Test
    @DisplayName("사용자 정보를 렌더링하고 사진 데이터가 함께 출력되는지")
    void testRenderPerson() {
        String expected =
                "<p> John Doe </p>" +
                "null" +                        // renderPhoto()가 아직 null을 반환
                "<p> 제목: Test Title </p>" +
                "<p> 위치: Test Location </p>" +
                "<p> 날짜: 2024-03-13T00:00 </p>";
        assertEquals(expected, camera.renderPerson(null, person));
    }

}
