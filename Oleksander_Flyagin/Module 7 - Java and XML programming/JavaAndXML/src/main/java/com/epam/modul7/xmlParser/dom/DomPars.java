package com.epam.modul7.xmlParser.dom;

import com.epam.modul7.xmlParser.person.Person;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.logging.Logger;

public class DomPars
    {

      private   Logger myLog =  Logger.getLogger("Loger");
        private Document parsFile(File file)
            {


                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                Document document = null;
                try
                    {
                        DocumentBuilder db = dbf.newDocumentBuilder();

                        document = db.parse(file);
                    }

                catch (ParserConfigurationException | SAXException | IOException e)
                    {

                        myLog.info("can't get document" + e);
                        document = null;

                    }


                return document;
            }


        private  List<Person>  addPerson(Document document)
            {
                List<Person> persons = new ArrayList<>();
                NodeList personNodeList = document.getElementsByTagName("SPEECH");
                Element personElements = (Element) personNodeList.item(0);
Person person = getPerson(personElements);
                persons.add(person);

                for (int stap = 1; stap < personNodeList.getLength(); stap++)
                    {
                        Element  personElement = (Element) personNodeList.item(stap);
                        person = getPerson(personElement);
                        boolean res = true;

                        for (Person persona : persons)
                            {
                                if (persona.getName().equals(person.getName()))
                                    {
                                        persona.setSumLines(persona.getSumLines() +1);
                                        persona.setWordsSize(persona.getWordsSize() + getLine(personElement));
                                        res = false;
                                        break;
                                    }
                            }
                        if (res)
                            {
                                persons.add(person);
                            }
                    }

            return  persons;
            }

        private  int getLine(Element personElement)
            {
                String line;
                int sum = 0;
                if(personElement.getElementsByTagName("LINE").item(0).getChildNodes().item(0).getNodeValue()!=null)
                    {
                        line = personElement.getElementsByTagName("LINE").item(0).getChildNodes().item(0).getNodeValue();

                        if (personElement.getElementsByTagName("LINE").getLength() > 1)
                            {
                                for (int stap = 1; stap < personElement.getElementsByTagName("LINE").getLength(); stap++)
                                    {
                                        line += " " + personElement.getElementsByTagName("LINE").item(stap).getChildNodes().item(0).getNodeValue();
                                    }
                            }
                        sum += line.split(" ").length;
                    }
                return  sum;
            }
    private  Person getPerson(Element personElement)
        {
            Person person = new Person();
            person.setName(personElement.getElementsByTagName("SPEAKER").item(0).getChildNodes().item(0).getNodeValue());
            person.setSumLines(1);
            person.setWordsSize(person.getWordsSize() + getLine(personElement));
            return person;
        }

        public void showPerson(File file)
            {
                for (Person persone : addPerson(parsFile(file)))
                    {
                        System.out.println(persone);

                    }
            }


    }


