package core.test;

import core.model.Util.EmailValidator;
import org.junit.Assert;
import org.junit.Test;


/**
 * Created by aleck_000 on 03.05.14.
 */
public class EmailValidatorTest {

    @Test
    public void ValidEmailTest() {
        EmailValidator emailValidator = new EmailValidator();

        Assert.assertEquals(emailValidator.validate("ssa@mail.ru"), true);
        Assert.assertEquals(emailValidator.validate("ss_a@gmail.com"), true);

        Assert.assertEquals(emailValidator.validate("ssa@@mail.ru"), false);
        Assert.assertEquals(emailValidator.validate("ssa.@mail.ru"), false);
        Assert.assertEquals(emailValidator.validate("ssamail.ru"), false);
    }

}