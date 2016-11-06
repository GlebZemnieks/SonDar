package ru.sondar.documentmodel.documentfactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;
import ru.sondar.documentmodel.dependencymodel.DependencyPart;
import ru.sondar.documentmodel.objectmodel.*;

/**
 *
 * @author GlebZemnieks
 */
public class SDObjectFactory {

    private static SDMainObject returnObject(SDMainObject object) {
        return object;
    }

    public static SDHeadPart getHeadPart() {
        return getHeadPart("00000000-0000-0000-0000-000000000000", "00000000-0000-0000-0000-000000000000");
    }

    public static SDHeadPart getHeadPart(UUID PluginUuid) {
        return getHeadPart(PluginUuid.toString());
    }

    public static SDHeadPart getHeadPart(String PluginUuid) {
        SDHeadPart head = new SDHeadPart();
        Date date = new Date();
        head.setCreationTime(date);
        head.setLastModificationTime(date);
        head.setUUID(UUID.fromString("00000000-0000-0000-0000-000000000000"));
        head.setPluginUUID(UUID.fromString(PluginUuid));
        return head;
    }

    public static SDHeadPart getHeadPart(UUID PluginUuid, UUID Uuid) {
        return getHeadPart(PluginUuid.toString(), Uuid.toString());
    }

    public static SDHeadPart getHeadPart(String PluginUuid, String Uuid) {
        SDHeadPart head = new SDHeadPart();
        Date date = new Date();
        head.setCreationTime(date);
        head.setLastModificationTime(date);
        head.setUUID(UUID.fromString(Uuid));
        head.setPluginUUID(UUID.fromString(PluginUuid));
        return head;
    }

    public static SDHeadPart getHeadPart(UUID PluginUuid, UUID Uuid, Date date) {
        return getHeadPart(PluginUuid.toString(), Uuid.toString(), date);
    }

    public static SDHeadPart getHeadPart(String PluginUuid, String Uuid, Date date) {
        SDHeadPart head = new SDHeadPart();
        head.setCreationTime(date);
        head.setLastModificationTime(date);
        head.setUUID(UUID.fromString(Uuid));
        head.setPluginUUID(UUID.fromString(PluginUuid));
        return head;
    }

    public static SDCheckBox getCheckBox() {
        return getCheckBox("checkBox", true);
    }

    public static SDCheckBox getCheckBox(String text) {
        return getCheckBox(text, true);
    }

    public static SDCheckBox getCheckBox(String text, boolean isChecked) {
        SDCheckBox checkBox = new SDCheckBox();
        checkBox.setText(text);
        checkBox.setChecked(isChecked);
        return (SDCheckBox) returnObject(checkBox);
    }

    public static SDDate getDate() {
        return getDate(new Date());
    }

    public static SDDate getDate(Date date) {
        SDDate dateObject = new SDDate();
        dateObject.setCalendar(date);
        return (SDDate) returnObject(dateObject);
    }

    public static SDEditText getEditText() {
        return getEditText("default text", 0);
    }

    public static SDEditText getEditText(String text) {
        return getEditText(text, 0);
    }

    public static SDEditText getEditText(int count) {
        return getEditText("", count);
    }

    public static SDEditText getEditText(String text, int count) {
        SDEditText temp = new SDEditText();
        temp.setText(text);
        temp.setTextLength(count);
        return (SDEditText) returnObject(temp);
    }

    public static SDEndln getEndln() {
        SDEndln end = new SDEndln();
        return (SDEndln) returnObject(end);
    }

    public static SDLogPart getLogPart() {
        return getLogPart(null);
    }

    public static SDLogPart getLogPart(ArrayList<String> logList) {
        SDLogPart log = new SDLogPart();
        log.setLogList(logList);
        return log;
    }

    public static SDSpinner getSpinner(SDWordsBasePart baseList, String baseName) {
        return getSpinner(baseList, baseName, 0);
    }

    public static SDSpinner getSpinner(SDWordsBasePart baseList, String baseName, int itemSelected) {
        SDSpinner spinner = new SDSpinner();
        spinner.setWordsBaseName(baseName);
        spinner.setList(baseList.getList(baseName));
        spinner.setDefaultItemSelected(itemSelected);
        return (SDSpinner) returnObject(spinner);
    }

    public static SDText getText(String newText) {
        SDText text = new SDText();
        text.setText(newText);
        return (SDText) returnObject(text);
    }

    public static DependencyPart getDependency() {
        return new DependencyPart();
    }
}
