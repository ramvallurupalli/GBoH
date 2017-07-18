#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )

package ${package}.bigoh.${company-name};

import com.ifactorconsulting.bigoh.core.batch.FileHandler;
import com.ifactorconsulting.bigoh.core.outage.OutageService;
import com.ifactorconsulting.bigoh.core.persistence.entity.version.Version;
import com.ifactorconsulting.bigoh.core.revision.RevisionContext;
import com.ifactorconsulting.zest.batch.BatchFile;
import com.ifactorconsulting.zest.mapper.Mapper;
import ${package}.bigoh.ext.RetryingFileUnmarshaller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.InputStream;

public class OutageFileHandler implements FileHandler<Version> {

    private final Logger logger = LoggerFactory.getLogger(OutageFileHandler.class);

    private RetryingFileUnmarshaller unmarshaller;
    private OutageService outageService;
    private Mapper mapper;

    @Autowired
    public void setUnmarshaller(RetryingFileUnmarshaller unmarshaller) {
        this.unmarshaller = unmarshaller;
    }

    @Autowired
    public void setOutageService(OutageService outageService) {
        this.outageService = outageService;
    }

    @Autowired
    public void setMapper(Mapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public Version handle(BatchFile batchFile) throws Exception {
        logger.info("Loading outage file {}", batchFile.getFullName());

        try {

            InputStream fileInputStream = batchFile.getContents();

            /** TODO unmarshall from JSON/XML file using the unmarshaller */


            RevisionContext snapshot = outageService.createSnapshotChange();

             /** TODO for each active outage (required), call `snapshot.addActiveOutage` */

             /** TODO if restorations are explicit (common), call `snapshot.addRestoration`, otherwise skip this step */

             /** TODO if cancellations are explicit (rare), call `snapshot.addCancellation`, otherwise skip this step. */

            return outageService.record(snapshot, batchFile.getTimestamp());
        }
        catch (Exception e) {
            logger.error("Error while loading outage file {}", batchFile.getFullName(), e);
            throw e;
        }
        finally {
            batchFile.close();
        }

    }
}
