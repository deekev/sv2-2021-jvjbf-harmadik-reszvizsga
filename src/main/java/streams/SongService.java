package streams;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class SongService {

    private List<Song> songs = new ArrayList<>();

    public void addSong(Song song) {
        validateSong(song);
        songs.add(song);
    }

    public Optional<Song> shortestSong() {
        return songs
                .stream()
                .min(Comparator.comparingInt(Song::getLength));
    }

    public List<Song> findSongByTitle(String title) {
        validateString(title);
        return songs
                .stream()
                .filter(song -> title.equals(song.getTitle()))
                .toList();
    }

    public boolean isPerformerInSong(Song song, String performer) {
        validateSong(song);
        validateString(performer);
        return song.getPerformers()
                .stream()
                .anyMatch(p -> performer.equals(p));
    }

    public List<String> titlesBeforeDate(LocalDate date) {
        validateDate(date);
        return songs
                .stream()
                .filter(song -> song.getRelease().isBefore(date))
                .map(Song::getTitle)
                .toList();
    }

    private void validateSong(Song song) {
        if (song == null) {
            throw new IllegalArgumentException("Song is null.");
        }
    }

    private void validateString(String s) {
        if (s == null || s.isBlank()) {
            throw new IllegalArgumentException("String parameter is empty or null.");
        }
    }

    private void validateDate(LocalDate date) {
        if (date == null) {
            throw new IllegalArgumentException("Date is null.");
        }
    }
}
