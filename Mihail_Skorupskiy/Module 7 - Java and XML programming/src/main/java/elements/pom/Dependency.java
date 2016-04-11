package elements.pom;

public class Dependency {
    String groupId;
    String artifactId;
    String version;
    String scope;

    public Dependency(String groupId, String artifactId, String version){
        this.groupId = groupId;
        this.artifactId = artifactId;
        this.version = version;
        this.scope = null;
    }

    public Dependency(String groupId, String artifactId, String version, String scope) {
        this.groupId = groupId;
        this.artifactId = artifactId;
        this.version = version;
        this.scope = scope;
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

    public String getScope() {
        return scope;
    }

    public boolean hasScope() {
        return scope != null;
    }
}
