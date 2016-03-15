package player;

import cd.CD;
import traks.Traks;

public class Player
    {


        private int[] equalize = new int[3];

        public void playCD(CD cd)
            {

                System.out.println("Songs on CD : " + cd.size() + "; \r\n" + "Duration CD : " + cd.durationCD());

                System.out.println("CD starts to play with standard  equalizer settings");

                for (Traks traks : cd.toArray())
                    {
                        System.out.println(traks.toString());
                    }
            }

        public void playEQ(CD cd)
            {


                System.out.println("Songs on CD : " + cd.size() + "; \r\n" + "Duration CD : " + cd.durationCD());

                System.out.println("CD starts to play with special equalizer settings\n");


                for (int indax = 0; indax < cd.size(); indax++)
                    {
                        equalize = cd.getWithCD(indax).getSattingsEqualize();
                        System.out.print("EQ Sattings : ");
                        for (int eq = 0; eq < equalize.length; eq++)
                            {
                                System.out.print(equalize[eq] + "  ");
                            }

                        System.out.println("\r\n" + cd.getWithCD(indax).toString());


                        if (indax < cd.size() - 1)
                            {
                                System.out.println("Next trak ");

                                if (!cd.getWithCD(indax).equals(cd.getWithCD(indax + 1)))
                                    {
                                        System.out.println("EQ setting changes");

                                    }
                            }
                    }
            }

        public void searchSong(CD cd, String name)
            { boolean resoultSearch = false;
                for (Traks traks : cd.toArray())
                    {
                        if (traks.getNameTrak().equals(name))
                            {
                                System.out.println("You search this song : \r\n" + traks.toString());
                                resoultSearch =true;
                                break;
                            }

                    }
                if (!resoultSearch)
                    {
                        System.out.println("Your request didn't give result!\n");
                    }


            }

        public void sortCD(CD cd)
            {
                for (int idax = (cd.size() - 1); idax > 0; idax--)
                    {
                        for (int stap = 0; stap < idax; stap++)
                            {
                                if (cd.getWithCD(stap).compareTo(cd.getWithCD(stap + 1)) == 1)
                                    {
                                        Traks tmp = cd.getWithCD(stap);
                                        cd.setOnCD(stap, cd.getWithCD(stap + 1));
                                        cd.setOnCD(stap + 1, tmp);

                                    }
                            }
                    }
            }


    }
