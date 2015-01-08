package net.teamtf.launcher.auth;

/**
 *
 * @author Decker
 */
public class UnauthorizedException extends Exception {

    private UnauthorizedException() {
    }

    public UnauthorizedException(String message) {
        super(message);
    }

}
