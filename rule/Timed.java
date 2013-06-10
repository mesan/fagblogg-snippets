package no.mesan.regler;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** @author Torbjørn S.Knutsen */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Timed {
    /**
     * Maksimalt antall millisekunder testen kan ta og fortsatt gå grønt.
     *
     * @return se over.
     */
    public int threshold();
}
