package no.mesan.regler;

import org.junit.Rule;
import org.junit.Test;

/** @author Torbjørn S.Knutsen */
public class TimedTestTest {

    @Rule
    public TimedTest timedTest = new TimedTest();

    @Test
    public void enLitenTest() {
        System.out.println("Jeg er en test!");
    }

    @Timed(threshold = 100)
    @Test
    public void bittelittTid() throws InterruptedException {
        Thread.sleep(42);
    }

    @Timed(threshold = 1000)
    @Test
    public void kjempelangTid() throws InterruptedException {
        Thread.sleep(31337);
    }
}
