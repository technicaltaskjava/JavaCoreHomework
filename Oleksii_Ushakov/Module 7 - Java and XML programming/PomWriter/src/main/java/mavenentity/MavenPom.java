package mavenentity;

import mavenentity.build.MavenBuild;
import mavenentity.dependency.MavenDependency;

import javax.xml.bind.annotation.*;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Alexey Ushakov
 */

@XmlRootElement(name = "project")
@XmlType(propOrder = { "modelVersion", "groupId", "artifactId", "version", "packaging", "name", "dependencyList", "build" })
public class MavenPom extends AbstractMavenEntity {

    private String modelVersion;
    private String packaging;
    private String name;
    private List<MavenDependency> dependencyList = new LinkedList<>();
    private MavenBuild build = new MavenBuild();

    @XmlAttribute(name = "xmlns")
    private String xmlns = "http://maven.apache.org/POM/4.0.0";

    public MavenPom() {
        this.modelVersion = "4.0.0";
        this.groupId = "TestPomModule7";
        this.artifactId = "Homework";
        this.version = "1.0";
        this.packaging = "pom";
        this.name = "Validate";
    }

    @XmlElement(name = "modelVersion")
    public String getModelVersion() {
        return modelVersion;
    }

    @XmlElement(name = "packaging")
    public String getPackaging() {
        return packaging;
    }

    @XmlElement(name = "name")
    public String getName() {
        return name;
    }

    public void addDependency(MavenDependency dependency) {
        dependencyList.add(dependency);
    }

    @XmlElementWrapper(name = "dependencies")
    @XmlElement(name = "dependency")
    public List<MavenDependency> getDependencyList() {
        return dependencyList;
    }

    @XmlElement(name = "build")
    public MavenBuild getBuild() {
        return build;
    }
}
