package com.epam.builder;

/**
 * Created by Olga Kramska on 24-Apr-16.
 */
public class Project {
    private String name;
    private String key;
    private String lead;
    private String logo;

    private Project() {
    }

    public String getName() {
        return name;
    }

    public String getKey() {
        return key;
    }

    public String getLead() {
        return lead;
    }

    public String getLogo() {
        return logo;
    }

    public static ProjectBuilder builder() {
        return new ProjectBuilder();
    }

    public static class ProjectBuilder {
        private Project project = new Project();

        public ProjectBuilder setName(final String name) {
            this.project.name = name;
            return this;
        }

        public ProjectBuilder setKey(final String key) {
            this.project.key = key;
            return this;
        }

        public ProjectBuilder setLead(final String lead) {
            this.project.lead = lead;
            return this;
        }

        public ProjectBuilder setLogo(final String logo) {
            this.project.logo = logo;
            return this;
        }

        public Project build() {
            return this.project;
        }
    }
}

