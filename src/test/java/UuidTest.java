import kr.sprouts.validation.constraints.annotation.Uuid;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Collection;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UuidTest {
    Logger log = Logger.getLogger(this.getClass().getSimpleName());

    @Test
    void invalid() {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        TestClass test = new TestClass("asd");
        Collection<ConstraintViolation<TestClass>> constraintViolations = validator.validate(test);

        assertEquals(1, constraintViolations.size());
        assertEquals("Does not match the UUID format.", constraintViolations.iterator().next().getMessage());
    }

    @Test
    void valid() {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        TestClass test = new TestClass("98c73526-7b15-4e0c-aacd-a47816efaedc");
        Collection<ConstraintViolation<TestClass>> constraintViolations = validator.validate(test);

        assertEquals(0, constraintViolations.size());
    }

    static class TestClass {
        @Uuid
        public String uuid;

        public TestClass(String uuid) {
            this.uuid = uuid;
        }
    }
}
