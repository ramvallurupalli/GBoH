#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )

package ${package}.bigoh.ext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Optional;
import org.springframework.beans.factory.annotation.Autowired;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.InputStream;

/**
 * Supports unmarshalling XML and JSON from file. CSV currently not supported.
 *
 * Use {@link RetryingFileUnmarshaller} if incomplete files are expected.
 */
public class FileUnmarshaller {

    protected ObjectMapper jsonMapper;

    protected Optional<JAXBContext> jaxbContext;

    @Autowired
    public void setJsonMapper(ObjectMapper jsonMapper) {
        this.jsonMapper = jsonMapper;
    }

    @Autowired(required = false)
    public void setJaxbContext(JAXBContext jaxbContext) {
        this.jaxbContext = Optional.fromNullable(jaxbContext);
    }

    public <T> T unmarshallJson(InputStream in, Class<T> clazz) throws IOException {
        return jsonMapper.readValue(in, clazz);
    }

    public <T> T unmarshallXml(InputStream in, Class<T> clazz) throws JAXBException {
        JAXBContext ctx = this.jaxbContext.isPresent()
                ? this.jaxbContext.get()
                : JAXBContext.newInstance(clazz);

        Unmarshaller unmarshaller = ctx.createUnmarshaller();
        Object result = unmarshaller.unmarshal(in);
        return clazz.cast(result);
    }


}
