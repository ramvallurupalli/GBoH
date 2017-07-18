package ${package}.bigoh.ext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.guava.GuavaModule;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.ifactorconsulting.zest.spring.web.config.IFactorMvcConfig;
import ${package}.bigoh.${company-name}.core.batch.json.JsonOutageBatch;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotNull;


public class FileUnmarshallerTest {

    FileUnmarshaller unmarshaller;

    @Before
    public void setup() {
        /** copied from {@link IFactorMvcConfig#objectMapper()} -- same configuration used by big-oh core */
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapper.registerModule(new JodaModule());
        objectMapper.registerModule(new GuavaModule());

        unmarshaller = new FileUnmarshaller();
        unmarshaller.setJsonMapper(objectMapper);
    }


    @Test
    public void testUnmarshallJson() throws Exception {
        File file = new File("src/test/resources/outages.json");
        JsonOutageBatch batchOutage = unmarshaller.unmarshallJson(new FileInputStream(file), JsonOutageBatch.class);

        assertNotNull(batchOutage);
        assertNotNull(batchOutage.getOutages());
        assertEquals(2, batchOutage.getOutages().size());

        assertEquals("1",  batchOutage.getOutages().get(0).getId());
        assertEquals(2017, batchOutage.getOutages().get(0).getEtr().getYear());

        assertEquals("2",  batchOutage.getOutages().get(1).getId());
        assertEquals(2018, batchOutage.getOutages().get(1).getEtr().getYear());
    }

    @Test
    public void testUnmarshallXml() throws Exception {
        /* TODO */
    }
}