package ru.sondar.documentmodel.documentfactory;

import ru.sondar.documentmodel.internalfunction.Action;
import ru.sondar.documentmodel.internalfunction.Function;
import ru.sondar.documentmodel.internalfunction.InternalFunction;
import ru.sondar.documentmodel.internalfunction.action.CheckAction;
import ru.sondar.documentmodel.internalfunction.action.SetAction;
import ru.sondar.documentmodel.internalfunction.function.CustomFunction;
import ru.sondar.documentmodel.internalfunction.interfaces.NavigatorInterface;

/**
 *
 * @author GlebZemnieks
 */
public class InternalFunctionFactory {

    /**
     * Return aggregator for function
     *
     * @return
     */
    public static InternalFunction getInternalFunction() {
        return new InternalFunction();
    }

    /**
     * Return function object with empty action list
     *
     * @return
     */
    public static Function getCustomFunction() {
        return new CustomFunction();
    }

    /**
     * Return checker action object with empty name
     *
     * @param navigator
     * @param targetId
     * @param value
     * @return
     */
    public static Action getCheckerAction(NavigatorInterface navigator, Object targetId, Object value) {
        return getCheckerAction(navigator, targetId, value, "");
    }

    /**
     * Return checker object action
     *
     * @param navigator
     * @param targetId
     * @param value
     * @param actionName
     * @return
     */
    public static Action getCheckerAction(NavigatorInterface navigator, Object targetId, Object value, String actionName) {
        CheckAction action = new CheckAction(navigator);
        action.setTargetId(targetId);
        action.setValue(value);
        action.setActionName(actionName);
        return action;
    }

    /**
     * Return setter action object with empty name and without condition
     *
     * @param navigator
     * @param targetId
     * @param value
     * @return
     */
    public static Action getSetterAction(NavigatorInterface navigator, Object targetId, Object value) {
        return getSetterAction(navigator, targetId, value, null, "");
    }

    /**
     * Return setter action object with empty name
     *
     * @param navigator
     * @param targetId
     * @param value
     * @param condition
     * @return
     */
    public static Action getSetterAction(NavigatorInterface navigator, Object targetId, Object value, Action condition) {
        return getSetterAction(navigator, targetId, value, condition, "");
    }

    /**
     * Return setter action object
     *
     * @param navigator
     * @param targetId
     * @param value
     * @param condition
     * @param actionName
     * @return
     */
    public static Action getSetterAction(NavigatorInterface navigator, Object targetId, Object value, Action condition, String actionName) {
        SetAction action = new SetAction(navigator);
        action.setTargetId(targetId);
        action.setValue(value);
        action.setCondition(condition);
        action.setActionName(actionName);
        return action;
    }

}
