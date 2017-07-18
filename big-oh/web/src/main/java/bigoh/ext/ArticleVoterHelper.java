#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )

package ${package}.bigoh.ext;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

public class ArticleVoterHelper {

    public String formatOutageDetail(long outageDetailId, long outageId, String externalId) {
        return String.format(
                "[outage detail id = '%s', outage id = '%s', external id = '%s']",
                outageDetailId, outageId, externalId
        );
    }

    public String formatOutageDetail(long outageDetailId, long outageId, String externalId, DateTime etr) {
        final String etrToString;
        if (etr == null) {
            etrToString = "NULL!";
        }
        else {
            etrToString = etr.withZone(DateTimeZone.UTC).toString();
        }

        return String.format(
                "[outage detail id = '%s', outage id = '%s', external id = '%s', ETR = '%s']",
                outageDetailId, outageId, externalId, etrToString
        );
    }

    public String formatResult(int result) {
        if (result == 0) {
            return "no winner; result = '0'";
        }
        else if (result > 0) {
            return String.format("'A' wins; result = '%s'", result);
        }
        else {
            return String.format("'B' wins; result = '%s'", result);
        }
    }



}