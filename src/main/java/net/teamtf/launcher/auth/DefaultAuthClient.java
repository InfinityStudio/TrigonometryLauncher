/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.teamtf.launcher.auth;

import java.awt.Image;
import java.util.UUID;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Decker
 */
public class DefaultAuthClient implements AuthClient {

    protected String userName;
    protected Image userAvatar;
    protected String UUID;

    @Override
    public Boolean isAuthorized() {
        return StringUtils.isEmpty(this.userName);
    }

    @Override
    public String getUserName() {
        return this.userName;
    }

    @Override
    public Image getUserAvatar() {
        return this.userAvatar;
    }

    @Override
    public String getUUID() {
        return this.UUID;
    }

    @Override
    public Boolean auth(String userName, String password) {
        //TODO:Implement auth function.
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
