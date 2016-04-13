package writer;

import mavenentity.MavenPom;
import mavenentity.build.plugin.MavenPlugin;
import mavenentity.dependency.MavenDependency;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author Alexey Ushakov
 */
public class PomWriter {
    private PomWriter() {
    }

    public void writePom(MavenPom pom) throws JAXBException {
        try {
            JAXBContext context = JAXBContext.newInstance(MavenPom.class);
            File pomNewFile = new File("./Validate/pom.xml");
            FileOutputStream pomFile = new FileOutputStream(pomNewFile);

            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, "http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd");

            marshaller.marshal(pom, pomFile);

        } catch (IOException e) {
            throw new JAXBException(e.getMessage(), e);
        }


    }

    public static MavenDependency firstDependency() {
        MavenDependency dependency = new MavenDependency();
        dependency.setGroupId("junit");
        dependency.setArtifactId("junit");
        dependency.setVersion("4.8.2");
        dependency.setScope("test");
        return dependency;
    }

    public static MavenDependency secondDependency() {
        MavenDependency dependency = new MavenDependency();
        dependency.setGroupId("log4j");
        dependency.setArtifactId("log4j");
        dependency.setVersion("1.2.9");
        return dependency;
    }

    public static MavenDependency thirdDependency() {
        MavenDependency dependency = new MavenDependency();
        dependency.setGroupId("com.google.guava");
        dependency.setArtifactId("guava");
        dependency.setVersion("19.0");
        return dependency;
    }

    public static MavenPlugin firstPlugin() {
        MavenPlugin plugin = new MavenPlugin();
        plugin.setArtifactId("maven-compiler-plugin");
        plugin.setVersion("3.5");
        return plugin;
    }

    public static void main(String[] args) throws JAXBException {
        PomWriter writer = new PomWriter();

        MavenPom pom = new MavenPom();
        pom.addDependency(firstDependency());
        pom.addDependency(secondDependency());
        pom.addDependency(thirdDependency());
        pom.getBuild().addPlugin(firstPlugin());

        writer.writePom(pom);
    }
}
