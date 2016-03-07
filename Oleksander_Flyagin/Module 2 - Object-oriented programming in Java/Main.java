import cd.CD;

import player.Player;
import record.*;

public class Main
    {
        public static void main(String[] args)
            {
                CD jazzCD = new CD();
                CD rockCD = new CD();
                PlayLists playLists = new PlayLists();
                Record record = new Record();
                Player play = new Player();


                record.recordCD(rockCD, playLists.getRockPlayList() );
                record.recordCD(jazzCD, playLists.getJazzPlayList() );
                play.sortCD(jazzCD);
              //play.playCD(rockCD);
                play.playEQ(jazzCD);
                play.searchSong(jazzCD, "Sammer Time");

            }
    }
