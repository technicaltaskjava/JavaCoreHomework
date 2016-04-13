package logic;

import elements.pom.Dependency;
import elements.pom.Plugin;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.TransformerException;

public class Main {

    private Main(){}

    public static void main(String[] args) throws SAXException, XMLStreamException,
            ParserConfigurationException, TransformerException{

        //Task 1.
        //Works with any play.dtd-compliant XML file from src/logic/resources.
        //You can tinker around with the constructors below to check results of different methods for the same file.
        //Or you can just invoke "startSAX, startStAX, startDOM" on the same XMLAnalyzer.

        XMLAnalyzer sax = new XMLAnalyzer("coriolan.xml");
        sax.startSAX();
        sax.showResults();

        XMLAnalyzer stax = new XMLAnalyzer("hamlet.xml");
        stax.startStAX();
        stax.showResults();

        XMLAnalyzer dom = new XMLAnalyzer("r_and_j.xml");
        dom.startDOM();
        dom.showResults();

        //Task 2.
        //New pom.xml will appear in src/main/resources/pom after running this.
        //The writer will accept any number of plugins or dependencies, but it is very specific.
        //Plugins will only have <groupId>, <artifactId> and <version>, <configuration> is optional.
        //<configuration> only supports <source> and <target>.
        //Dependencies will only have <groupId>, <artifactId> and <version>, <scope> is optional.

        POMWriter writer = new POMWriter();
        writer.createPom("TestGroup", "Test");
        Plugin compiler = new Plugin("org.apache.maven.plugins", "maven-compiler-plugin", "3.3", "1.7", "1.7");
        Plugin javadoc = new Plugin("org.apache.maven.plugins", "maven-javadoc-plugin", "2.10.3");
        writer.createBuild(compiler, javadoc);
        Dependency junit = new Dependency("junit", "junit", "4.8.2", "test");
        Dependency log4jcore = new Dependency("org.apache.logging.log4j", "log4j-core", "2.5");
        Dependency log4japi = new Dependency("org.apache.logging.log4j", "log4j-api", "2.5");
        writer.createDependencies(junit, log4jcore, log4japi);
        writer.write();
    }
}
