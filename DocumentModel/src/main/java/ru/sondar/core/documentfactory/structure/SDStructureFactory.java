package ru.sondar.core.documentfactory.structure;

import ru.sondar.core.documentfactory.SDObjectFactory;
import ru.sondar.core.documentmodel.SDSequenceObject;

/**
 *
 * @author GlebZemnieks
 */
public class SDStructureFactory {

    public static void enter(SDSequenceObject sequenceObject) {
        sequenceObject.AddXMLObject(SDObjectFactory.getEndln());
    }

    /**
     * @param sequenceObject
     * @param obj - CompositeObject's child list
     */
    public static void addCompositeObjectList(SDSequenceObject sequenceObject, CompositeObject[] obj) {
        for (CompositeObject object : obj) {
            object.addObjectListToSequence(sequenceObject);
        }
    }
}
