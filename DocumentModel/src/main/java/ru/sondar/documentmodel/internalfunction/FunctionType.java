package ru.sondar.documentmodel.internalfunction;

/**
 * Type of internal functions
 *
 * @author GlebZemnieks
 */
public enum FunctionType {
    /**
     * Custom function configuration
     */
    CustomFunction,
    /**
     * See the same value and set value to another object
     */
    AutoFill,
    /**
     * Filter to make choose value from list
     */
    SearchFilter
}
