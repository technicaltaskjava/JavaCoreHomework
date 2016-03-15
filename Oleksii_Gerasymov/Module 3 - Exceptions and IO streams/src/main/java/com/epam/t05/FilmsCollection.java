package main.java.com.epam.t05;

import java.io.*;

public class FilmsCollection implements Serializable {
    private Film [] films = new Film [0];
    private int actualCountOfFilms = 0;
    private static final String saveFile = "saved2films.txt";

    public Film[] getFilms() {
        return films;
    }

    public int getActualCountOfFilms() {
        return actualCountOfFilms;
    }

    public static void saveFilms (Object films) throws IOException {

        ByteArrayOutputStream newByteStream = new ByteArrayOutputStream();
        ObjectOutputStream newOutputStream = new ObjectOutputStream(newByteStream);
        OutputStream currentWritter = new FileOutputStream(saveFile);
        newOutputStream.writeObject(films);
        newByteStream.writeTo(currentWritter);
        currentWritter.close();
        newByteStream.close();
        newOutputStream.close();
    }

    public static FilmsCollection restoreFilms () throws IOException, ClassNotFoundException {
        FileInputStream currentReader = new FileInputStream(saveFile);
        ObjectInputStream newInputStream = new ObjectInputStream(currentReader);
        FilmsCollection films = (FilmsCollection) newInputStream.readObject();
        currentReader.close();
        newInputStream.close();
        return films;
    }

    public void addFilm (String filmName, String[] actorsList) {
        Film newFilm = new Film(filmName, actorsList);
        Film[] tempFilms = new Film[films.length + 1];
        System.arraycopy(films, 0, tempFilms, 0, films.length);
        tempFilms[films.length] = newFilm;
        films = tempFilms;
        actualCountOfFilms++;
    }

    private int filmIndexByName(String filmName) {
        for (int filmIndex = 0; filmIndex < films.length; filmIndex++) {
                if (films[filmIndex].getFilmName().equals(filmName)) {
                return filmIndex;
            }
        }
        return -1;
    }

    public void deleteFilm (String filmName) {
        int deleteIndex = this.filmIndexByName(filmName);
        if (deleteIndex != -1) {
                Film[] tempFilms = new Film[films.length -1];
                System.arraycopy(films, 0, tempFilms, 0, deleteIndex);
                System.arraycopy(films, deleteIndex + 1, tempFilms, deleteIndex, films.length - deleteIndex - 1);
                films = tempFilms;
                actualCountOfFilms--;
        }
    }
}
