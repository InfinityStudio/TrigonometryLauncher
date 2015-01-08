package net.teamtf.launcher.auth;

import java.awt.Image;

/**
 *
 * @author Decker
 */
public interface AuthClient {

    public Boolean isAuthorized();

    public String getUserName();

    public Image getUserAvatar();

    public String getUUID();
    
    public Boolean auth(String userName,String password);
}
