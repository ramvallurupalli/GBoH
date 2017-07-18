#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )

package ${package}.bigoh.${company-name};

import com.ifactorconsulting.bigoh.core.batch.OutageFileDispatcher;
import com.ifactorconsulting.bigoh.core.extraction.AreaSummaryExtractor;
import com.ifactorconsulting.bigoh.core.extraction.DefaultArticleLoadingExtractor;
import com.ifactorconsulting.bigoh.core.extraction.DefaultDeliverNodeExtractor;
import com.ifactorconsulting.bigoh.core.persistence.entity.FileType;
import com.ifactorconsulting.bigoh.core.service.FullOutageVersionCache;
import com.ifactorconsulting.bigoh.core.service.OutageVersionCache;
import com.ifactorconsulting.bigoh.core.service.outage.ArticleOutageVoter;
import com.ifactorconsulting.zest.batch.BatchFile;
import com.ifactorconsulting.zest.batch.TimestampFileNameFormatter;
import com.ifactorconsulting.zest.batch.file.LocalFileService;
import com.ifactorconsulting.zest.batch.predicate.FileExtensionPredicate;
import com.ifactorconsulting.zest.batch.predicate.FilePredicate;
import com.ifactorconsulting.zest.batch.predicate.MaxAgePredicate;
import com.ifactorconsulting.zest.batch.predicate.StatusPredicate;
import com.ifactorconsulting.bigoh.core.batch.GenericFileDispatcher;

import org.joda.time.DateTimeZone;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.ArrayList;
import com.ifactorconsulting.bigoh.core.batch.FileHandler;

import ${package}.bigoh.ext.RetryingFileUnmarshaller;
import ${package}.bigoh.${company-name}.OutageFileHandler;
import ${package}.bigoh.${company-name}.PremiseFileHandler;
import ${package}.bigoh.ext.ExternalIdVoterImpl;
import ${package}.bigoh.ext.DistantEtrVoterImpl;
import ${package}.bigoh.ext.DefaultCoordinateExtractor;



@Configuration
public class ImplConfig {


	@Bean
	public RetryingFileUnmarshaller unmarshaller() {
		return new RetryingFileUnmarshaller();
	}

	@Bean
	public OutageFileHandler outageFileHandler() {
		return new OutageFileHandler();
	}

	@Bean
	public PremiseFileHandler premiseFileHandler() {
		return new PremiseFileHandler();
	}

	@Bean
	public OutageVersionCache outageVersionCache(){
		return new FullOutageVersionCache();
	}

	@Bean
	public ArticleOutageVoter etrVoter() {
		return new DistantEtrVoterImpl();
	}

	@Bean
	public ArticleOutageVoter externalIdVoter() {
		return new ExternalIdVoterImpl();
	}


	@Bean
	public DefaultArticleLoadingExtractor defaultArticleLoadingExtractor() {
		return new DefaultArticleLoadingExtractor();
	}


	@Bean
	public DefaultDeliverNodeExtractor defaultDeliveryNodeExtractor() {
		return new DefaultDeliverNodeExtractor();
	}

	@Bean
	public DefaultCoordinateExtractor coordinateExtractor() {
		DefaultCoordinateExtractor extractor = new DefaultCoordinateExtractor();
		extractor.setOrder(10);
		return extractor;
	}

	@Bean
	public AreaSummaryExtractor areaSummaryExtractor() {
		AreaSummaryExtractor extractor = new AreaSummaryExtractor();
		extractor.setOrder(20);
		return extractor;
	}

	@Bean
	public OutageFileDispatcher outageFileDispatcher(@Value("${batch.outage.directory}") String outageDirectory,
													 OutageFileHandler outageFileHandler){
		OutageFileDispatcher outageFileDispatcher = new OutageFileDispatcher();
		outageFileDispatcher.setFileHandler(outageFileHandler);
		outageFileDispatcher.setFileNameFormatter(timestampFileNameFormatter());
		outageFileDispatcher.setFileService(localFileService());
		outageFileDispatcher.setDirectory(outageDirectory);
		outageFileDispatcher.setFileType(FileType.OUTAGE);
		outageFileDispatcher.setOrPredicates(orFilePredicates());
		outageFileDispatcher.setAndPredicates(andFilePredicates());
		return outageFileDispatcher;
	}

	@Bean
	public GenericFileDispatcher premiseFileDispatcher(@Value("${batch.premise.directory}") String premiseDirectory,
													   PremiseFileHandler premiseFileHandler){
		GenericFileDispatcher premiseFileDispatcher = new GenericFileDispatcher();
		premiseFileDispatcher.setFileHandler(premiseFileHandler);
		premiseFileDispatcher.setFileNameFormatter(timestampFileNameFormatter());
		premiseFileDispatcher.setFileService(localFileService());
		premiseFileDispatcher.setDirectory(premiseDirectory);
		premiseFileDispatcher.setFileType(FileType.OUTAGE);
		premiseFileDispatcher.setOrPredicates(orFilePredicates());
		premiseFileDispatcher.setAndPredicates(andFilePredicates());
		return premiseFileDispatcher;
	}

	@Bean
	public LocalFileService localFileService(){
		return new LocalFileService();
	}

	@Bean
	public ArrayList<FilePredicate> orFilePredicates(){
		ArrayList<FilePredicate> filePredicates = new ArrayList<FilePredicate>();
		StatusPredicate statusPredicate = new StatusPredicate();
		statusPredicate.setStatus(BatchFile.Status.NONE);
		filePredicates.add(statusPredicate);
		statusPredicate = new StatusPredicate();
		statusPredicate.setStatus(BatchFile.Status.NEW);
		filePredicates.add(statusPredicate);
		statusPredicate = new StatusPredicate();
		statusPredicate.setStatus(BatchFile.Status.PENDING);
		filePredicates.add(statusPredicate);
		return filePredicates;
	}

	@Bean
	public ArrayList<FilePredicate> andFilePredicates(){
		ArrayList<FilePredicate> filePredicates = new ArrayList<FilePredicate>();
		MaxAgePredicate maxAgePredicate = new MaxAgePredicate();
		maxAgePredicate.setFileNameFormatter(timestampFileNameFormatter());
		filePredicates.add(maxAgePredicate);
		return filePredicates;
	}

	@Bean
	public TimestampFileNameFormatter timestampFileNameFormatter(){
		TimestampFileNameFormatter timestampFileNameFormatter = new TimestampFileNameFormatter();
		timestampFileNameFormatter.setExtension(".*");
		//TODO if the file in different timezone change this accordingly
		timestampFileNameFormatter.setTimeZone(DateTimeZone.UTC);
		return timestampFileNameFormatter;
	}

}
