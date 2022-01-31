package movietheatres;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalTime;
import java.util.*;

public class MovieTheatreService {

    private Map<String, List<Movie>> shows = new HashMap<>();

    public Map<String, List<Movie>> getShows() {
        return new TreeMap<>(shows);
    }

    public void readFromFile(Path path) {

        try (BufferedReader br = Files.newBufferedReader(path)) {
            String line;
            while ((line = br.readLine()) != null) {
                fillMap(line);
            }
        } catch (IOException ioe) {
            throw new IllegalStateException("Can't read file", ioe);
        }
    }

    public List<String> findMovie(String title) {
        return shows.entrySet()
                .stream()
                .filter(stringListEntry -> stringListEntry.getValue().contains(title))
                .map(stringListEntry -> stringListEntry.getKey())
                .toList();
    }

    public LocalTime findLatestShow(String title) {
        Optional<LocalTime> latest = Optional.empty();
        for (Map.Entry<String, List<Movie>> entry: shows.entrySet()) {
            for (Movie actual: entry.getValue()) {
                if (title.equals(actual.getTitle())) {
                    if (latest.isEmpty()) {
                        latest = Optional.of(actual.getStartTime());
                    }
                    if (latest.isPresent() && latest.get().isBefore(actual.getStartTime())) {
                        latest = Optional.of(actual.getStartTime());
                    }
                }
            }
        }
        return latest.orElseThrow(() -> new IllegalArgumentException("No movie"));
    }

    private void fillMap(String line) {
        String[] temp = line.split("-");
        String key = temp[0];
        Movie movie = createMovie(temp[1]);
        if (!shows.containsKey(key)) {
            shows.put(key, new ArrayList<Movie>());
        }
        shows.get(key).add(movie);
    }

    private Movie createMovie(String part) {
        String[] temp = part.split(";");
        String title = temp[0];
        LocalTime startTime = LocalTime.parse(temp[1]);
        return new Movie(title, startTime);
    }
}
