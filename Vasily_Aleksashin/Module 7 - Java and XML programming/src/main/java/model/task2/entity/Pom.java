package model.task2.entity;

import exception.ParameterIsNullException;
import utility.Constant;
import utility.Validator;

import java.util.*;

public class Pom {

	private final Map<String, String> subElement = new HashMap<>();
	private final Map<String, String> attributes = new HashMap<>();
	private final Set<DependElement> dependElements = new HashSet<>();
	private final Set<DependElement> pluginElements = new HashSet<>();
	private DependElement rootDependElement;

	public Pom() {
		subElement.put(Constant.DEPENDENCIES, Constant.DEPENDENCY);
		subElement.put(Constant.BUILD, Constant.PLUGINS);
		subElement.put(Constant.PLUGINS, Constant.PLUGIN);
		attributes.put("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
		attributes.put("xmlns", "http://maven.apache.org/POM/4.0.0");
		attributes.put("xsi:schemaLocation", "http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd");
	}

	public DependElement getRootDependElement() {
		return rootDependElement;
	}

	public void setRootDependElement(final DependElement rootDependElement) throws ParameterIsNullException {
		Validator.isNull(rootDependElement);
		this.rootDependElement = rootDependElement;
	}

	public Map<String, String> getSubElement() {
		return Collections.unmodifiableMap(subElement);
	}

	public Map<String, String> getAttributes() {
		return Collections.unmodifiableMap(attributes);
	}

	public void addDependElement(final String groupId, final String artifactId, final String version) throws ParameterIsNullException {
		dependElements.add(new DependElement(groupId, artifactId, version));
	}

	public void addPluginElement(final String groupId, final String artifactId, final String version) throws ParameterIsNullException {
		pluginElements.add(new DependElement(groupId, artifactId, version));
	}

	public Set<DependElement> getDependElements() {
		return Collections.unmodifiableSet(dependElements);
	}

	public Set<DependElement> getPluginElements() {
		return Collections.unmodifiableSet(pluginElements);
	}

	public DependElement getDependElement(final String groupId, final String artifactId, final String version) {
		for (DependElement dependElement : dependElements) {
			final boolean flag = isFlag(groupId, artifactId, version, dependElement);
			if (flag) {
				return dependElement;
			}
		}
		return null;
	}

	public DependElement getPluginElement(final String groupId, final String artifactId, final String version) {
		for (DependElement dependElement : pluginElements) {
			final boolean flag = isFlag(groupId, artifactId, version, dependElement);
			if (flag) {
				return dependElement;
			}
		}
		return null;
	}

	private boolean isFlag(final String groupId, final String artifactId, final String version, final DependElement dependElement) {
		boolean isFind = true;
		Boolean[] flags = new Boolean[]{null, null, null};
		if (groupId != null) {
			flags[0] = dependElement.getGroupId().equals(groupId);
		}
		if (artifactId != null) {
			flags[1] = dependElement.getArtifactId().equals(artifactId);
		}
		if (version != null) {
			flags[2] = dependElement.getVersion().equals(version);
		}
		for (Boolean elements : flags) {
			if (elements != null && !elements) {
				isFind = false;
				break;
			}
		}
		return isFind;
	}

	public void addDependElement(final DependElement dependElement) throws ParameterIsNullException {
		final String groupId = dependElement.getGroupId();
		final String artifactId = dependElement.getArtifactId();
		final String version = dependElement.getVersion();
		addDependElement(groupId, artifactId, version);
	}

	public void addPluginElement(final DependElement dependElement) throws ParameterIsNullException {
		final String groupId = dependElement.getGroupId();
		final String artifactId = dependElement.getArtifactId();
		final String version = dependElement.getVersion();
		addPluginElement(groupId, artifactId, version);
	}
}
