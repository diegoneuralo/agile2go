package br.com.scrum.infrastructure.dao.exception;

import org.jboss.solder.exception.control.CaughtException;
import org.jboss.solder.exception.control.Handles;
import org.jboss.solder.exception.control.HandlesExceptions;
import org.jboss.solder.logging.Logger;

@HandlesExceptions
public class GeneralExceptionHandler {

    public void printExceptionMessage(@Handles CaughtException<Throwable> event, Logger log) {
        log.info("Exception logged by seam-catch catcher: " + event.getException().getMessage());
        event.rethrow();
    }
}
