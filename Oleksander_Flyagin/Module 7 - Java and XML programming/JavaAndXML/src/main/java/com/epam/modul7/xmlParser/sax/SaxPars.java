package com.epam.modul7.xmlParser.sax;


import com.epam.modul7.xmlParser.person.Person;
import com.epam.modul7.xmlParser.person.PersonTegName;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;


public class SaxPars extends DefaultHandler
    {
        private List<Person> persons = new ArrayList<>();
        PersonTegName personTegName;
        private String text;
        Person person;

        @Override
        public void startDocument()throws SAXException
            {
                // start

            }


        @Override
        public void endDocument()throws SAXException
            {
                // end
            }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException
            {
                if("SPEECH".equals(qName))
                    {
                         person = new Person();
                    }
            }

        @Override
        public void endElement(String uri, String localName, String qName)throws SAXException
            {
                personTegName = PersonTegName.getElementTagName(qName);
                switch (personTegName)
                    {
                        case SPEAKER:
                              person.setName(text);
                              break;

                        case LINE:
                            String lineSize = text;
                            person.setWordsSize(person.getWordsSize() + lineSize.split(" ").length);
                            break;

                        case SPEECH:
                            addPerson();
                            break;
                        default:
                    }
            }


        private void addPerson()
            {
                boolean resoult = true;
                if(!persons.isEmpty())
                    {
                        for (Person persona:persons)
                            {
                                if (persona.getName().equals(person.getName()))
                                    {
                                        persona.setSumLines(persona.getSumLines() +1);
                                        persona.setWordsSize(persona.getWordsSize() + person.getWordsSize());
                                        resoult = false;
                                        break;
                                    }
                            }
                    }
                if(resoult)
                    {   person.setSumLines(1);
                        persons.add(person);
                        person = null;
                    }
            }



        @Override
        public void characters(char[] buffer, int start, int end)
        {
            text = new String(buffer, start, end);

        }

        public void showElement()
            {
                for (Person persona:persons)
                    {
                        System.out.println(persona);
                    }
            }
    }
