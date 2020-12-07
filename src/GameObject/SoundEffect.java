package GameObject;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class SoundEffect {
    private Media media;
    private MediaPlayer mediaPlayer;

    private boolean isPlaying = false;

    public void playWithFlag(String string) {
        if (!isPlaying) {
            media = new Media(new File(string).toURI().toString());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setAutoPlay(false);
            isPlaying = true;
            mediaPlayer.setOnEndOfMedia(new Runnable() {
                @Override
                public void run() {
                    isPlaying = false;
                }
            });
            mediaPlayer.play();
        }
    }

    public void playWithoutFlag(String string) {
        media = new Media(new File(string).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(false);
        mediaPlayer.play();
    }
}
