package net.teamtf.launcher.addon;

/**
 *
 * @author Decker
 */
public interface Addon {

    /**
     * Provide name for this addon. In addintion, Loader using this name to
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
     * We recommend addons load files for their self in this pahse. Launcher
     * files should alredy loeaded after this phase. Moreover, all addons file
     * have already loaded before this phase. Thus, you may would like to
     * detecte other addons for dependency intention.
     */
    public void preLoad();

    /**
     * Interactive with launcher core. In this phase, GUI should already fully
     * initiallized. We recommend addons add their elements to main window at
     * this phase.
     */
    public void load();

    /**
     * Interactive with other addones. In this pase, all externed component
     * added by addons should already done. We recommend addons interactive with
     * other addons in this phase.
     */
    public void postLoad();
}
