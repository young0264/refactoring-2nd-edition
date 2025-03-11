package young.refactoring.ch8.moveStatementsIntoFunction;

import java.io.OutputStream;

// 사진 관련 데이터를 HTML로 내보내는 코드.
public class Camera {

    public String renderPerson(OutputStream outputStream, Person person) {
        StringBuilder result = new StringBuilder();
        result.append(String.format("<p> %s </p>", person.name));
        result.append(renderPhoto(person.photo));
        result.append(emitPhotoData(person.photo));

        // OutputStream에 문자열을 바이트로 변환하여 출력
//        outputStream.write(result.toString().getBytes(StandardCharsets.UTF_8));
//        outputStream.flush(); // 버퍼를 비우고 즉시 출력

        return result.toString();
    }

    private String renderPhoto(Photo photo) {
        return null;
    }

    public String photoDiv(Photo photo) {
        StringBuilder result = new StringBuilder();
        result.append("<div>");
        result.append(emitPhotoData(photo));
        result.append("</div>");
        return result.toString();
    }

    public String emitPhotoData(Photo photo) {
        StringBuilder result = new StringBuilder();
        result.append(String.format("<p> 제목: %s </p>", photo.title));
        result.append(String.format("<p> 위치: %s </p>", photo.location));
        result.append(String.format("<p> 날짜: %s </p>", photo.date.toString()));
        return result.toString();
    }

}
