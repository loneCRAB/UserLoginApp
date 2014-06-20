package core.model.Util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Клас-валідатор дат, забеспечуе корректність
 * для об'єктів типу {@link java.util.Date}
 */
public class DateValidator {
    public boolean dateValid(String d) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMМM yyyy");

        try {
            Date dx = sdf.parse(d);
            return d.equals(sdf.format(dx));
        } catch (ParseException e) {
            return false;
        }
    }
}