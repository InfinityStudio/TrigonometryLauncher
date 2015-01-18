package net.teamtf.launcher.addon;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.jar.JarFile;
import net.teamtf.launcher.core.Engine;
import org.apache.commons.lang3.StringUtils;

/**
 * @author Decker
 */
public final class AddonLoader {

    private final File addonsFolder;

    private HashMap<String, Addon> entireAddons;

    private LinkedList<Addon> lowLevelAddons;
    private LinkedList<Addon> midLevelAddons;
    private LinkedList<Addon> highLevelAddons;

    public AddonLoader(String addonsFolderPath) {
        this.addonsFolder = new File(addonsFolderPath);
        if (!addonsFolder.exists() || !addonsFolder.isDirectory()) {
            throw new IllegalArgumentException("path not exists or not a folder");
        }
        this.entireAddons = new HashMap<>();
        this.lowLevelAddons = new LinkedList<>();
        this.midLevelAddons = new LinkedList<>();
        this.highLevelAddons = new LinkedList<>();
    }

    /**
     * Load all addons from specified folder path. Add them to particular query
     * through splitting their priority.
     *
     * @throws ClassNotFoundException if there no class implement Addon
     * interface
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws MalformedURLException
     */
    public void loadFilesFromFolder() throws ClassNotFoundException, IllegalAccessException, InstantiationException, MalformedURLException {

        for (File listFile : addonsFolder.listFiles()) {
            Addon addon;
            try {
                JarFile addonFile = new JarFile(listFile);
                String mainClass = addonFile.getManifest().getMainAttributes().getValue("Addon-Class");
                if (StringUtils.isEmpty(mainClass)) {
                    Engine.getEngine().getLogger().error(String.format("No declared Addon-Class found in MANIFEST.MF in \'%s\'. Load gave up.", listFile.getPath()));
                }
                URLClassLoader loader = new URLClassLoader(new URL[]{listFile.toURI().toURL()});
                Class addonClass = loader.loadClass(mainClass);
                Object instance = addonClass.newInstance();
                if (!(instance instanceof Addon)) {
                    throw new ClassCastException("Specified class is not a instance of addon.");
                }
                addon = (Addon) instance;
            } catch (Exception e) {
                Engine.getEngine().getLogger().error(String.format("Can not load addon file%s", listFile.getPath()), e);
                continue;

            }

            //Check if the add which has same name had already loaded.
            if (entireAddons.containsKey(addon.getAddonName())) {
                if (addon.getAddonName().equals(entireAddons.get(addon.getAddonName()).getAddonName())
                        && (addon.getVersion() == entireAddons.get(addon.getAddonName()).getVersion())) {
                    continue;
                }
                //Thus get the latest version
                addon = this.selectProprietyAddon(addon, entireAddons.get(addon.getAddonName()));
            }

            this.entireAddons.put(addon.getAddonName(), addon);
            switch (addon.getPriority()) {
                case LOW: {
                    this.lowLevelAddons.add(addon);
                    break;
                }
                case MEDIUM: {
                    this.midLevelAddons.add(addon);
                    break;
                }
                case HIGH: {
                    this.highLevelAddons.add(addon);
                    break;
                }
            }
        }

    }

    /**
     * Sort addons through its priority.
     *
     * @return all addons sorted
     */
    private Addon[] getAddonsSortedByPriority() {
        LinkedList<Addon> sortedList = new LinkedList<>();
        sortedList.addAll(this.highLevelAddons);
        sortedList.addAll(this.midLevelAddons);
        sortedList.addAll(this.lowLevelAddons);
        return sortedList.toArray(new Addon[]{});
    }

    public void perLoadAllAddons() {
        for (Addon addon : this.getAddonsSortedByPriority()) {
            addon.preLoad();
        }
    }

    public void loadAllAddons() {
        for (Addon addon : this.getAddonsSortedByPriority()) {
            addon.load();
        }
    }

    public void postLoadAllAddons() {
        for (Addon addon : this.getAddonsSortedByPriority()) {
            addon.postLoad();
        }
    }

    /**
     * Receive particular addon by addon name.
     *
     * @param name Addon name specified
     * @return corresponding addon
     */
    public Addon getAddonByName(String name) {
        return this.entireAddons.get(name);
    }

    private Addon selectProprietyAddon(Addon addon1, Addon addon2) {
        if (!addon1.getAddonName().equals(addon2.getAddonName())) {
            throw new IllegalArgumentException("Addons name not same. This should not happen as normal, but it is a deadly error.");
        }

        if (addon1.getVersion() >= addon2.getVersion()) {
            return addon1;
        } else {
            return addon2;
        }
    }

}
