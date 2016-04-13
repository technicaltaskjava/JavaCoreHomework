package elements.pom;

public class Plugin {
    String groupId;
    String artifactId;
    String version;
    Configuration configuration;

    public Plugin (String groupId, String artifactId, String version){
        this.groupId = groupId;
        this.artifactId = artifactId;
        this.version = version;
        configuration = null;
    }

    public Plugin(String groupId, String artifactId, String version, String source, String target) {
        this.groupId = groupId;
        this.artifactId = artifactId;
        this.version = version;
        this.configuration = new Configuration();
        this.configuration.setSource(source);
        this.configuration.setTarget(target);
    }

    public String getGroupId() {
        return groupId;
    }

    public String getArtifactId() {
        return artifactId;
    }

    public String getVersion() {
        return version;
    }

    public String getSource() {
        return configuration.getSource();
    }

    public String getTarget(){
        return configuration.getTarget();
    }

    public boolean isConfigurable(){
        return configuration != null;
    }
}

class Configuration {
    String source;
    String target;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }
}
