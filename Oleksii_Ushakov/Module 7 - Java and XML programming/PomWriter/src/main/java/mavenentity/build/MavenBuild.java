package mavenentity.build;

import mavenentity.build.plugin.MavenPlugin;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Alexey Ushakov
 */
public class MavenBuild {
    private List<MavenPlugin> pluginList = new LinkedList<>();

    public void addPlugin(MavenPlugin plugin) {
        pluginList.add(plugin);
    }

    @XmlElementWrapper(name = "plugins")
    @XmlElement(name = "plugin")
    public List<MavenPlugin> getPluginList() {
        return pluginList;
    }
}
