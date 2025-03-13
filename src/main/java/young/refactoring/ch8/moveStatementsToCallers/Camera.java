package young.refactoring.ch8.moveStatementsToCallers;

import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.util.List;

public class Camera {

    public void renderPeron(OutputStream outputStream, Person person) throws IOException {
        outputStream.write(String.format("<p> %s </p>", person.name).getBytes());
        renderPhoto(outputStream, person.photo);
        zzNew(outputStream, person.photo);
        outputStream.write(String.format("<p> 날짜: %s </p>", person.photo.date.toString()).getBytes());
    }

    public void listRecentPhotos(OutputStream outputStream, List<Photo> photos) {
        photos.stream()
                .filter(p -> p.date.isAfter(recentDateCutOff()))
                .forEach(p -> {
                    try {
                        outputStream.write("<div> \n".getBytes());
                        zzNew(outputStream, p);
                        outputStream.write(String.format("<p> 날짜: %s </p>", p.date.toString()).getBytes());
                        outputStream.write("</div>\n".getBytes());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
    }

    private void emitPhotoData(OutputStream outputStream, Photo photo) throws IOException {
        zzNew(outputStream, photo);
        outputStream.write(String.format("<p> 날짜: %s </p>", photo.date.toString()).getBytes());
    }

    public String renderPhoto(OutputStream outputStream, Photo photo) {
        return null;
    }

    private LocalDateTime recentDateCutOff() {
        return null;
    }

    private static void zzNew(OutputStream outputStream, Photo photo) throws IOException {
        outputStream.write(String.format("<p> 제목: %s </p>", photo.title).getBytes());
        outputStream.write(String.format("<p> 위치: %s </p>", photo.location).getBytes());
    }

}
