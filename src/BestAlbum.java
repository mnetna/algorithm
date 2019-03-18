import java.util.*;
import java.util.stream.Collectors;

public class BestAlbum {
    public static void main(String[] args) {
        String[] genres = new String[]{"classic", "pop", "classic", "classic", "pop"};
        int[] plays = {500, 600, 150, 800, 2500};
        int[] answer = answer = solution(genres, plays);

        for (int i=0; i<answer.length; i++) { System.out.println(answer[i]); }
    }

    public static int[] solution(String[] genres, int[] plays) {
        List<Song> songList = new ArrayList<>();
        Set<String> keySet = new HashSet();

        for (int i = 0; i < genres.length; i++) {
            songList.add(new Song(i, genres[i], plays[i]));
            keySet.add(genres[i]);
        }

        ///////////////////////////////////////////////////////////////////////////

        LinkedHashMap<String, Integer> hashMap = new LinkedHashMap<>();
        for (String key : keySet) {
            int sumByPlay = songList.stream().filter(x -> x.getGenre().equals(key)).mapToInt(Song::getPlay).sum();
            hashMap.put(key, sumByPlay);
        }

        List<String> sortedByGenres = new ArrayList<>(hashMap.keySet());
        Collections.sort(sortedByGenres, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return hashMap.get(o2).compareTo(hashMap.get(o1));
            }
        });

        ///////////////////////////////////////////////////////////////////////////

        List<Song> playsList = new ArrayList<>();
        for (String genre : sortedByGenres) {
            List<Song> selectSong = songList.stream().filter(song -> genre.equals(song.getGenre())).collect(Collectors.toList());
            Collections.sort(selectSong, new Comparator<Song>() {
                @Override
                public int compare(Song o1, Song o2) {
                    return o2.getPlay() - o1.getPlay();
                }
            });
            while(selectSong.size() > 2) { selectSong.remove(selectSong.size() - 1); }
            playsList.addAll(selectSong);
        }

        return playsList.stream().mapToInt(Song::getId).toArray();
    }
}

class Song {
    int id;
    String genre;
    int play;

    Song(int id, String genre, int play) {
        this.id = id;
        this.genre = genre;
        this.play = play;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getPlay() {
        return play;
    }

    public void setPlay(int play) {
        this.play = play;
    }
}