package young.refactoring.ch8;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import young.refactoring.ch8.moveStatementsToCallers.Camera;
import young.refactoring.ch8.moveStatementsToCallers.Person;
import young.refactoring.ch8.moveStatementsToCallers.Photo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoveStatementsToCallersTest {
    private Camera camera;
    private Person person;
    private Photo photo;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    void setUp() {
        camera = new Camera();
        outputStream = new ByteArrayOutputStream();
        photo = new Photo("Test Title", "Test Location", LocalDateTime.of(2024, 3, 13, 0, 0,0));
        person = new Person("John Doe", photo);
    }

    @Test
    @DisplayName("renderPeron()은 사용자의 정보를 출력한다")
    void testRenderPerson() throws IOException {
        camera.renderPeron(outputStream, person);
        String expected =
                "<p> John Doe </p>" +
                "<p> 제목: Test Title </p>" +
                "<p> 위치: Test Location </p>" +
                "<p> 날짜: 2024-03-13T00:00 </p>";
        assertEquals(expected, outputStream.toString().trim());
    }

//    @Test
//    @DisplayName("listRecentPhotos()는 최근 날짜 이후의 사진을 출력한다")
//    void testListRecentPhotos() {
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        List<Photo> photos = List.of(
//                new Photo("Old Photo", "Old Location", LocalDateTime.of(2023, 12, 1, 0, 0)),
//                new Photo("New Photo", "New Location", LocalDateTime.of(2024, 3, 14, 0, 0))
//        );
//
//        camera.listRecentPhotos(outputStream, photos);
//
//        String expected =
//                "<div> \n" +
//                "<p> 제목: New Photo </p>" +
//                "<p> 위치: New Location </p>" +
//                "<p> 날짜: 2024-03-14T00:00 </p>" +
//                "</div>";
//        System.out.println("outputStream.toString().trim() "+ outputStream);
//        System.out.println("expected "+ expected);
//        assertEquals(expected.replaceAll("\\s+", ""), outputStream.toString().trim());
////        assertEquals(expected.trim(), outputStream.toString().trim());
//    }
}
