package task5.films;

import task5.actors.Actor;
import java.io.Serializable;
import java.util.Iterator;


public class ActorInFilms  implements Iterable<Actor>, Serializable
    {
        private Actor[] actors = new  Actor[20];
        private int top = 0;
        private String nameFilm;

        public ActorInFilms(String nameFilm)
            {
                this.nameFilm = nameFilm;

            }
        public ActorInFilms()
            {
            }
        public String getNameFilm()
            {
                return nameFilm;
            }



        public int size()
            {
                return top;
            }

        public void  getActorName(String fNameActors)
            {
                int pos = 0;
                for (int stap = 0; stap < size(); stap++)
                    {
                        if (actors[stap].getfName().equals(fNameActors))
                            {
                                pos = stap;
                                break;
                            }
                    }
                System.out.println("First name " + actors[pos].getfName()+ ", " + "Last name " + actors[pos].getlName());

            }


        public void dellActors(String fNameActors)
            {
                int pos = 0;
                for (int stap = 0; stap < size(); stap++)
                    {
                        if (actors[stap].getfName().equals(fNameActors))
                            {
                                pos = stap;
                                break;
                            }
                    }
                Actor[] temp = new Actor[top  -1];

                for (int stap = 0; stap < pos; stap++)
                    {
                        temp[stap] = actors[stap];
                    }
                for (int stap = pos + 1; stap < top; stap++)
                    {
                        temp[stap - 1] = actors[stap];
                    }
                actors = temp;
                top--;


            }



        public Actor getActor(int pos)
            {
                return actors[pos];
            }



        public void addActor(Actor actor)
            {

                        Actor[] temp = new Actor[top + 1];
                        for (int stap = 0; stap < top; stap++)
                            {
                                temp[stap] = actors[stap];
                            }
                        temp[temp.length -1] = actor;
                        actors = temp;
                        top++;

            }



        public void showInfoActors()
            {
                for (Actor actor:actors)

                    {
                        System.out.println("First name :" + actor.getfName() + ", Last name :" + actor.getlName() );

                    }
            }

        public Iterator<Actor> iterator()
            {
                return new MIterator();
            }


        class MIterator implements Iterator<Actor>
            {
                int index = 0;

                @Override
                public boolean hasNext()
                    {
                        return index < size();
                    }

                @Override
                public Actor next()
                    {

                        return actors[index++];
                    }
            }
    }
