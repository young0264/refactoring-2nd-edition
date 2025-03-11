package young.refactoring.ch8.moveStatementsIntoFunction;

import java.io.OutputStream;

public class Camera {

    public String renderPerson(OutputStream outputStream, Person person) {
        StringBuilder result = new StringBuilder();
        result.append(String.format("<p> %s </p>", person.name));
        result.append(renderPhoto(person.photo));
        result.append(String.format("<p>제목: %s </p>", person.photo.title));
        result.append(emitPhotoData(person.photo));
        return result.toString();
    }

    private String emitPhotoData(Photo photo) {
        StringBuilder result = new StringBuilder();
        result.append(String.format("<p>위치: %s", photo.location));
        result.append(String.format("<p>날짜: %s", photo.date));
        return result.toString();
    }

    private String renderPhoto(Photo photo) {
        return null;
    }

}
