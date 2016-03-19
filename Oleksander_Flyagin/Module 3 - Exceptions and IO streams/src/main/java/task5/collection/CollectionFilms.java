package task5.collection;


import task5.films.ActorInFilms;
import task5.myExeption.NotFoundFilmsInCollection;

import java.io.Serializable;
import java.util.Iterator;


public class CollectionFilms implements Iterable<ActorInFilms>, Serializable
    {
        private ActorInFilms[] films;
        private int top = 0;


        public CollectionFilms()
            {
            }

        public int size()
            {
                return top;
            }

        public ActorInFilms getFilmForName(String nameFilm)
            {
                int pos = 0;
                for (int stap = 0; stap < size(); stap++)
                    {
                        if (films[stap].getNameFilm().equals(nameFilm))
                            {
                                pos = stap;
                                break;
                            }
                    }
                return  films[pos];
            }

        public ActorInFilms getFilm(int pos)
            {
                return  films[pos];
            }



        public void addFilm(ActorInFilms film)
            {

                ActorInFilms[] temp = new ActorInFilms[top +1];
                for (int stap = 0; stap < top; stap++)
                    {
                        temp[stap] = films[stap];
                    }
                temp[temp.length -1] = film;
                films = temp;
                top++;
            }




        public void dellFilm(String nameFilm)
            {
                int pos = 0;
                for (int stap = 0; stap < size(); stap++)
                    {
                        if (films[stap].getNameFilm().equals(nameFilm))
                            {
                                pos = stap;
                                break;
                            }
                    }

                ActorInFilms[] temp = new ActorInFilms[top - 1];

                for (int stap = 0; stap < pos; stap++)
                    {
                        temp[stap] = films[stap];
                    }
                for (int stap = pos + 1; stap < top; stap++)
                    {
                        temp[stap - 1] = films[stap];
                    }
                films = temp;
                top--;


            }
        public void showInfoFilms() throws NotFoundFilmsInCollection
            {
               if (films == null)
                   {
                       throw new NotFoundFilmsInCollection("\n collection is empty");
                   }
                else
                   {

                       for (ActorInFilms film : films)
                           {
                               System.out.println("Film : " + film.getNameFilm());
                               film.showInfoActors();
                               System.out.println();

                           }
                   }


            }


        public Iterator<ActorInFilms> iterator()
            {
                return new MIterator();
            }



        class MIterator implements Iterator<ActorInFilms>
            {
                int index = 0;

                @Override
                public boolean hasNext()
                    {
                        return index < size();
                    }

                @Override
                public ActorInFilms next()
                    {

                        return  films[index++];
                    }
            }

    }
