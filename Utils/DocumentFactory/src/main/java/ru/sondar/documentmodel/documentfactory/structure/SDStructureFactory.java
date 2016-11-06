package ru.sondar.documentmodel.documentfactory.structure;

import ru.sondar.documentmodel.documentfactory.SDObjectFactory;
import ru.sondar.documentmodel.SDSequenceObject;

/**
 *
 * @author GlebZemnieks
 */
public class SDStructureFactory {

    public static void enter(SDSequenceObject sequenceObject) {
        sequenceObject.addXMLObject(SDObjectFactory.getEndln());
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
