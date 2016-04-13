package model.task2.entity;

import exception.ParameterIsNullException;
import utility.Validator;

public class DependElement {
	private final String groupId;
	private final String artifactId;
	private final String version;

	public DependElement(final String groupId, final String artifactId, final String version) throws ParameterIsNullException {
		Validator.isNull(groupId);
		Validator.isNull(artifactId);
		Validator.isNull(version);
		this.groupId = groupId;
		this.artifactId = artifactId;
		this.version = version;
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

	@Override
	public int hashCode() {
		int result = groupId.hashCode();
		result = 31 * result + artifactId.hashCode();
		result = 31 * result + version.hashCode();
		return result;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		DependElement dependElement = (DependElement) o;
		return groupId.equals(dependElement.groupId) && artifactId.equals(dependElement.artifactId) && version.equals(dependElement.version);

	}

	@Override
	public java.lang.String toString() {
		return "DependElement{" +
				"groupId=" + groupId +
				", artifactId=" + artifactId +
				", version=" + version +
				'}';
	}
}
