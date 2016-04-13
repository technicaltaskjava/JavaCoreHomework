package mavenentity.build.plugin;

import mavenentity.AbstractMavenEntity;

import javax.xml.bind.annotation.XmlElement;

/**
 * @author Alexey Ushakov
 */

public class MavenPlugin extends AbstractMavenEntity {
    protected String extensions;
    protected String inherited;

    @XmlElement(name = "extensions")
    public String getExtensions() {
        return extensions;
    }


    @XmlElement(name = "inherited")
    public String getInherited() {
        return inherited;
    }
}
