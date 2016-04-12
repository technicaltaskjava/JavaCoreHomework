package com.epam.modul7.xmlParser.stax;

import com.epam.modul7.xmlParser.person.Person;
import com.epam.modul7.xmlParser.person.PersonTegName;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;


public class StAXParserDemo
    {
        Person  person = new Person();
        private List<Person> persons = new ArrayList<>();
        private boolean bName = false;
        private boolean bLine = false;
        private boolean bSpeech = false;
        private String name = "";
        private String line = "";
        PersonTegName personTegName;
        XMLEvent event;



private List<Person> ListPerson(String fileName)
    {
        try
            {
                XMLInputFactory factory = XMLInputFactory.newInstance();
                XMLEventReader eventReader =
                        factory.createXMLEventReader(
                                new FileReader(fileName));

                while (eventReader.hasNext())
                    {
                        event = eventReader.nextEvent();
                        switch (event.getEventType())
                            {
                                case XMLStreamConstants.START_ELEMENT:
                                    serchStart();
                                    break;

                                case XMLStreamConstants.CHARACTERS:
                                    characters();
                                    break;

                                case XMLStreamConstants.END_ELEMENT:
                                    serchEnad();
                                    break;
                                   default:
                            }
                    }
            }
        catch (FileNotFoundException | XMLStreamException e)
            {
                e.printStackTrace();
            }
        return persons;
    }



        private void characters()
            {
                Characters characters = event.asCharacters();

                if (bSpeech)
                    {
                        person = new Person();
                        bSpeech = false;

                    }


                if (bName)
                    {
                        name = characters.getData();
                        bName = false;

                    }
                if (bLine)
                    {
                        line = characters.getData() + "\r\n";
                        bLine = false;
                    }
            }

        private void serchStart()
            {
                StartElement startElement = event.asStartElement();
                String qName = startElement.getName().getLocalPart();
                    personTegName = PersonTegName.getElementTagName(qName);
                    switch (personTegName)
                        {
                            case SPEECH:
                                bSpeech = true;
                                break;

                            case SPEAKER:
                                bName = true;
                                break;

                            case LINE:
                                bLine = true;
                                break;
                            default:
                        }
            }

        private void serchEnad()
            {
                EndElement endElement = event.asEndElement();
                personTegName = PersonTegName.getElementTagName(endElement.getName().getLocalPart());
                switch (personTegName)
                    {
                        case SPEECH:
                            addPerson();
                            break;
                        case SPEAKER:
                            person.setName(name);
                            break;
                        case LINE:
                            person.setWordsSize(person.getWordsSize() + line.split(" ").length);
                            break;
                        default:
                    }
            }

        private void addPerson()
            {
                boolean resoult = true;
                if (!persons.isEmpty())
                    {
                        for (Person persona : persons)

                            if (persona.getName().equals(person.getName()))
                                {
                                    persona.setSumLines(persona.getSumLines() +1);
                                    persona.setWordsSize(persona.getWordsSize() + person.getWordsSize());
                                    resoult = false;
                                    break;
                                }
                    }
                if (resoult)
                    {
                        person.setSumLines(1);
                        persons.add(person);

                    }
            }



        public void shoPersons(String nameFile)
            {
                for (Person persona : ListPerson(nameFile))
                    {
                        System.out.println(persona);
                    }
            }
    }

