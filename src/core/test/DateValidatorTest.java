package core.test;

import core.model.Util.DateValidator;
import org.junit.Assert;
import org.junit.Test;


/**
 * Created by aleck_000 on 03.05.14.
 */
public class DateValidatorTest {

    @Test
    public void ValidDateTest() {
        DateValidator dateValidator = new DateValidator();
        Assert.assertEquals(true, dateValidator.dateValid("07 марта 1995"));
        Assert.assertEquals(false, dateValidator.dateValid("07 13 1995"));
        Assert.assertEquals(false, dateValidator.dateValid("288 12 1995"));
    }

}