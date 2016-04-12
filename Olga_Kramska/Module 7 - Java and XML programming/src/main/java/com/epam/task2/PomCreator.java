package com.epam.task2;

import org.apache.maven.pom._4_0.Build;
import org.apache.maven.pom._4_0.Dependency;
import org.apache.maven.pom._4_0.Model;
import org.apache.maven.pom._4_0.ObjectFactory;
import org.apache.maven.pom._4_0.Plugin;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.OutputStream;
import java.util.List;

/**
 * Created by Olga Kramska on 11-Apr-16.
 */
public class PomCreator {
    private JAXBElement<Model> project;
    private ObjectFactory objectFactory;
    private Model model;
    private Model.Dependencies dependencies;
    private List<Dependency> dependencyList;
    private Build build;
    private Build.Plugins buildPlugins;
    private List<Plugin> pluginList;

    public PomCreator() {
        objectFactory = new ObjectFactory();
        model = objectFactory.createModel();
        project = objectFactory.createProject(model);
        dependencies = objectFactory.createModelDependencies();
        dependencyList = dependencies.getDependency();
        build = objectFactory.createBuild();
        buildPlugins = objectFactory.createBuildPlugins();
        pluginList = buildPlugins.getPlugin();
    }

    public void addDependency(String groupId, String artifactId, String version) {
        Dependency dependency = objectFactory.createDependency();
        dependency.setGroupId(groupId);
        dependency.setArtifactId(artifactId);
        dependency.setVersion(version);
        dependencyList.add(dependency);
        model.setDependencies(dependencies);
    }

    public void addPlugin(String groupId, String artifactId, String version) {
        Plugin plugin = objectFactory.createPlugin();
        plugin.setGroupId(groupId);
        plugin.setArtifactId(artifactId);
        plugin.setVersion(version);
        pluginList.add(plugin);
        build.setPlugins(buildPlugins);
        model.setBuild(build);
    }

    public void create(OutputStream outputStream) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Model.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        jaxbMarshaller.marshal(project, outputStream);
    }
}
