#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )

package ${package}.bigoh.ext;


import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.io.InputStream;

/**
 * If incomplete files are expected, use this to retry unmarshalling.
 *
 * By default, this unmarshaller will attempt a maximum of 3 times,
 * sleeping 5 seconds between attempts.
 */
public class RetryingFileUnmarshaller extends FileUnmarshaller {

    protected final int maxAttempts;
    protected final long sleepDuration;

    public RetryingFileUnmarshaller() {
        this(3, 5000L);
    }

    public RetryingFileUnmarshaller(int numberOfTries, long sleepDurationMillis) {
        this.maxAttempts = numberOfTries;
        this.sleepDuration = sleepDurationMillis;
    }

    @Override
    public <T> T unmarshallJson(InputStream in, Class<T> clazz) throws IOException {
        IOException lastException = null;

        for (int attempt = 1; attempt <= maxAttempts; attempt++) {
            try {
                return super.unmarshallJson(in, clazz);
            }
            catch (IOException e) {
                lastException = e;
                try { Thread.sleep(sleepDuration); }
                catch (InterruptedException e1) { throw new RuntimeException(e1); }
            }
        }

        throw lastException;
    }

    @Override
    public <T> T unmarshallXml(InputStream in, Class<T> clazz) throws JAXBException {
        JAXBException lastException = null;

        for (int attempt = 1; attempt <= maxAttempts; attempt++) {
            try {
                return super.unmarshallXml(in, clazz);
            }
            catch (JAXBException e) {
                lastException = e;
                try { Thread.sleep(sleepDuration); }
                catch (InterruptedException e1) { throw new RuntimeException(e1); }
            }
        }

        throw lastException;
    }

}
