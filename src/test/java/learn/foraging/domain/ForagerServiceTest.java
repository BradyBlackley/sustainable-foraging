package learn.foraging.domain;

import learn.foraging.data.DataException;
import learn.foraging.data.ForagerRepositoryDouble;
import learn.foraging.models.Forager;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ForagerServiceTest {

    ForagerService service = new ForagerService(new ForagerRepositoryDouble());

    @Test
    void shouldAdd() throws DataException {
        Forager forager = new Forager();
        forager.setFirstName("John");
        forager.setLastName("Doe");
        forager.setState("WI");

        Result<Forager> result = service.add(forager);
        assertTrue(result.isSuccess());
        assertNotNull(result.getPayload());
        assertEquals(36, result.getPayload().getId().length());
    }

    @Test
    void shouldNotAddDuplicateForager() throws DataException {

        Forager forager = new Forager();
        //forager.setId("0e4707f4-407e-4ec9-9665-baca0aabe88c");
        forager.setFirstName("Jilly");
        forager.setLastName("Sisse");
        forager.setState("GA");
        Result<Forager> result = service.add(forager);
        assertFalse(result.isSuccess());

    }

    @Test
    void shouldNotAddEmptyFirstNameForager() throws DataException {

        Forager forager = new Forager();
        forager.setFirstName("");
        forager.setLastName("Sisse");
        forager.setState("GA");
        Result<Forager> result = service.add(forager);
        assertFalse(result.isSuccess());

    }

    @Test
    void shouldNotAddEmptyLastNameForager() throws DataException {

        Forager forager = new Forager();
        forager.setFirstName("Jilly");
        forager.setLastName("");
        forager.setState("GA");
        Result<Forager> result = service.add(forager);
        assertFalse(result.isSuccess());

    }

    @Test
    void shouldNotAddEmptyStateForager() throws DataException {

        Forager forager = new Forager();
        forager.setFirstName("Jilly");
        forager.setLastName("Sisse");
        forager.setState("");
        Result<Forager> result = service.add(forager);
        assertFalse(result.isSuccess());

    }

    @Test
    void shouldNotAddBadFormatStateForager() throws DataException {

        Forager forager = new Forager();
        forager.setFirstName("Jilly");
        forager.setLastName("Sisse");
        forager.setState("Georgia");
        Result<Forager> result = service.add(forager);
        assertFalse(result.isSuccess());

        Forager forager1 = new Forager();
        forager1.setFirstName("Jilly");
        forager1.setLastName("Sisse");
        forager1.setState("G");
        Result<Forager> result1 = service.add(forager1);
        assertFalse(result1.isSuccess());

    }
}