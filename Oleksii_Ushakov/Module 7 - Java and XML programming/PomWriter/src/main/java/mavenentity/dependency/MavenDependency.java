package mavenentity.dependency;

import mavenentity.AbstractMavenEntity;

import javax.xml.bind.annotation.XmlElement;

/**
 * @author Alexey Ushakov
 */

public class MavenDependency extends AbstractMavenEntity {
    protected String type;
    protected String classifier;
    protected String scope;
    protected String systemPath;
    protected String optional;

    @XmlElement(name = "type")
    public String getType() {
        return type;
    }

    @XmlElement(name = "classifier")
    public String getClassifier() {
        return classifier;
    }

    @XmlElement(name = "scope")
    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    @XmlElement(name = "systemPath")
    public String getSystemPath() {
        return systemPath;
    }


    @XmlElement(name = "optional")
    public String getOptional() {
        return optional;
    }

}
