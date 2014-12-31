package net.teamtf.launcher.addon;

/**
 *
 * @author Decker
 */
public interface Addon {

    /**
     * Provide name for this addon. In addition, Loader using this name to
     * identify addon , therefore, be sure this name is unique according other
     * addons.
     *
     * @return The name of this addon
     */
    public String getAddonName();

    /**
     * Provide version number for this addon. If more than one same addon names
     * encountered but different version, no exception would be throw, only
     * latest version would be used.
     *
     * @return The version number of this addon
     */
    public int getVersion();

    /**
     * Declare priority for this addon. High priority means load earlier than
     * rest.
     *
     * @return The priority of this addon
     */
    public AddonPriority getPriority();

    /**
     * Load all prerequisites.
     *
     * We recommend addons load files for their self in this phase. Launcher
     * files should already loaded after this phase. Moreover, all addons file
     * have already loaded before this phase. Thus, you may would like to
     * detect other addons for dependency intention.
     */
    public void preLoad();

    /**
     * Interactive with launcher core. In this phase, GUI should already fully
     * initialized. We recommend addons add their elements to main window at
     * this phase.
     */
    public void load();

    /**
     * Interactive with other addons. In this phase, all extended component
     * added by addons should already done. We recommend addons interactive with
     * other addons in this phase.
     */
    public void postLoad();
}
