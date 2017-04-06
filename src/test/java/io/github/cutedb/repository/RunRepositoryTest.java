package io.github.cutedb.repository;

import io.github.cutedb.Application;
import io.github.cutedb.model.BuildStatus;
import io.github.cutedb.model.Run;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by barmi83 on 10/06/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ContextConfiguration(classes=Application.class)
public class RunRepositoryTest {

    @Autowired
    private RunRepository repository;

    @Before
    public void init() {
        repository.deleteAll();
    }

    @After
    public void tearDown() {
        repository.deleteAll();
    }

    @Test
    public void testSave() {
        Run run = new Run();
        run.setUser("toto");
        run.setStatus(BuildStatus.PENDING);
        run.setDatabaseProductName("postgres");
        run.setStarted(new Date());
        run.setEnded(run.getStarted());
        run = repository.save(run);
        assertNotNull(run.getId());
        assertTrue(repository.findAll().iterator().hasNext());
    }

    @Test
    public void testFindAll() {
        Iterable results=repository.findAll();
        assertFalse(results.iterator().hasNext());
    }

    @Test
    public void testDeleteRunId() {
        Run run = new Run();
        run.setUser("toto");
        run.setStatus(BuildStatus.PENDING);
        run = repository.save(run);
        assertTrue(repository.findAll().iterator().hasNext());
        repository.delete(run.getId());
        assertFalse(repository.findAll().iterator().hasNext());
    }
}
