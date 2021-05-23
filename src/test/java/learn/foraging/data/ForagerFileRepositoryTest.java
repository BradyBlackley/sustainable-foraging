package learn.foraging.data;

import learn.foraging.models.Forager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ForagerFileRepositoryTest {

    static final String SEED_PATH = "./data/foragers-seed.csv";
    static final String TEST_PATH = "./data/foragers-test.csv";

    ForagerFileRepository repository = new ForagerFileRepository(TEST_PATH);

    @BeforeEach
    void setup() throws IOException {
        Path seedPath = Paths.get(SEED_PATH);
        Path testPath = Paths.get(TEST_PATH);
        Files.copy(seedPath, testPath, StandardCopyOption.REPLACE_EXISTING);
    }

    @Test
    void shouldFindAll() {
        //ForagerFileRepository repo = new ForagerFileRepository("./data/foragers.csv");
        List<Forager> all = repository.findAll();
        assertEquals(3, all.size());
    }

    @Test
    void shouldAddForagers() throws DataException {

        Forager forager = new Forager();

        assertEquals(3, repository.findAll().size());

        forager.setFirstName("Test");
        forager.setLastName("Testing");
        forager.setState("WI");
        repository.add(forager);
        assertEquals(4, repository.findAll().size());

        Forager forager2 = new Forager();

        forager2.setFirstName("Test2");
        forager2.setLastName("Testing2");
        forager2.setState("WI");
        repository.add(forager2);
        assertEquals(5, repository.findAll().size());
    }
}