package net.teamtf.launcher.configuration;

import java.io.File;
import java.util.List;

public interface IConfig {
    public Object getObject(String key);
    public String getString(String key);
    public Boolean getBoolean(String key);
    public Integer getInteger(String key);
    public Double getDouble(String key);
    public List getList(String key);
    public void saveToFile();
    public void setConfig(String key, Object obj);
}
