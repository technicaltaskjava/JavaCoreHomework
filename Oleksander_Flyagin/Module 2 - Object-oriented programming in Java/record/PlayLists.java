package record;

import traks.Traks;
import traks.jazz.BeBop;
import traks.jazz.Bluse;
import traks.rock.AlternativeRock;
import traks.rock.PostRock;

public class PlayLists
    {




        private Traks []jazzPlayList = new Traks []
        {
             new Bluse("Strory of the bluse",  "Garry Moor",        3.5),
             new Bluse("Sammer Time",          "Jorg Gershvin",     4.7),
             new BeBop("Bebop",                "Dizzy Gillespie",   4.16),
             new BeBop("A Night in Tunisia",   "Clifford Brown",    5.34),
             new BeBop("Sandu",                "Clifford Brown",    4.57),
             new BeBop("Sonny Stitt",          "There'll Never Be", 4.37),
             new Bluse("La vie en rose",       "Louis Armstrong",   3.24),
             new Bluse("Georgia On My Mind",   "Ray Charles",       3.52),
             new Bluse("I got a woman",        "Ray Charles",       2.5),
             new Bluse("You Know I'm No Good", "Amy Winehouse",     3.5),
        };

        private Traks []rockPlayList = new Traks []
                {
                        new PostRock       ("Lowercase Noises",   "Rushes",              8.5),
                        new AlternativeRock("The Snowflakes",     "Seven Saturdays",     4.5),
                        new PostRock       ("Circadian Eyes ",    "Fog",                 3.16),
                        new AlternativeRock("Beautiful Thieves ", "AFI ",                5.34),
                        new PostRock       ("Sandu",              "Ancient Mariner",     4.57),
                        new AlternativeRock("Sing ",              "My Chemical Romance", 4.27),
                        new PostRock       ("Nine",               "Louis Armstrong",     3.24),
                        new AlternativeRock("Desperate",          "Fireflight",          3.52),
                        new PostRock       ("Bandicoots",         "The Dead Sea",        7.5),
                        new AlternativeRock("Memories",           "Weezer",              3.5),

                };


        public Traks[] getJazzPlayList()
            {
                return jazzPlayList;
            }

        public Traks[] getRockPlayList()
            {
                return rockPlayList;
            }

    }
