package com.epam.builder;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Olga Kramska on 26-Apr-16.
 */
public class BuilderTest {
    @Test
    public void testBuilder() {
        final String name = "MediaPlayer";
        final String key = "MP";
        final String lead = "admin";

        Project project = Project.builder().setName(name).setKey(key).setLead(lead).build();

        Assert.assertNotNull(project);
        Assert.assertEquals(name, project.getName());
        Assert.assertEquals(key, project.getKey());
        Assert.assertEquals(lead, project.getLead());
        Assert.assertNull(project.getLogo());
    }
}
