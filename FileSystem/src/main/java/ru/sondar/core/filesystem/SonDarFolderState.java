package ru.sondar.core.filesystem;

/**
 * State of sondar folder object. All positive operation work only with
 * <code>SonDarFolderState.None</code>.
 *
 * @author GlebZemnieks
 * @since SonDar-1.0
 */
public enum SonDarFolderState {
    /**
     * Main code for work. Every thing good with folder
     */
    None,
    /**
     * Need rebuild. <code>ReduildPending</code> - status which can be fixed by
     * program. Need call rebuild method.
     */
    ReduildPending,
    /**
     * Need rebuild. <code>RepairPending</code> - status which can't be fixed by
     * program. Need user's action.
     */
    RepairPending,
    /**
     * Starting status for folder.
     */
    Initialization;
}
