package no.mesan.regler;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.apache.commons.lang.time.StopWatch;
import org.junit.rules.MethodRule;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;

/** @author Torbjørn S.Knutsen */
public class TimedTest implements MethodRule {

    @Override
    public Statement apply(final Statement base, final FrameworkMethod method,
                           final Object target) {
        return new TimedTestStatement(base, method.getMethod());
    }

    private class TimedTestStatement extends Statement {

        private final Statement testStatement;
        private final Method metode;

        public TimedTestStatement(final Statement base, final Method method) {
            testStatement = base;
            metode = method;
        }

        @Override
        public void evaluate() throws Throwable {
            final StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            testStatement.evaluate();

            final Long tidIMillisekunder = stopWatch.getTime();
            final Timed annotasjon = metode.getAnnotation(Timed.class);

            if (annotasjon != null) {
                if (tidIMillisekunder > annotasjon.threshold()) {
                    throw new AssertionError("Metoden har en tidsbegrensning på"
                            + annotasjon.threshold() + " ms, " +
                            "men brukte " + tidIMillisekunder + " ms!");
                }

                System.out.println("Kjøring av testen tok " +
                                   tidIMillisekunder + " ms.");
            }
        }
    }
}
