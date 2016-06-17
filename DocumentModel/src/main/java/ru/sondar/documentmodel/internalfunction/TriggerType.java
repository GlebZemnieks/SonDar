package ru.sondar.documentmodel.internalfunction;

/**
 * Type of trigger
 *
 * @author GlebZemnieks
 */
public enum TriggerType {
    /**
     * Function active AllTime
     */
    allAction,
    /**
     * Function active when target field was changed by user
     */
    isChange,
    /**
     * Function active when target field was changed by another function
     */
    isUpdate,
    /**
     * Function active when object preparing to View on screen
     */
    onView
}
