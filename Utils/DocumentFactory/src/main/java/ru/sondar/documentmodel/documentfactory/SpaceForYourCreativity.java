package ru.sondar.documentmodel.documentfactory;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import ru.sondar.core.filemodule.FileModuleWriteThreadInterface;
import ru.sondar.core.filemodule.pc.FileModuleWriteThread;
import ru.sondar.core.logger.Logger;
import ru.sondar.core.parser.exception.ObjectStructureException;
import ru.sondar.documentmodel.SDDocument;
import ru.sondar.documentmodel.SDSequenceObject;
import ru.sondar.documentmodel.documentfactory.structure.*;
import ru.sondar.documentmodel.objectmodel.*;
import ru.sondar.documentmodel.dependencymodel.DependencyPart;
import static ru.sondar.documentmodel.documentfactory.structure.SDStructureFactory.addCompositeObjectList;
import static ru.sondar.documentmodel.documentfactory.structure.SDStructureFactory.enter;

/**
 *
 * @author GlebZemnieks
 */
public class SpaceForYourCreativity {

    public static String pathToFileCreate = "E:\\test.txt";

    public static String documentUUID = "69f08553-bf07-405f-b849-0bea1423ed1c";

    public static void main(String... args) throws SAXException, IOException, ParserConfigurationException, ObjectStructureException {
        SDDocument document = new SDDocument();
        SDHeadPart head = SDObjectFactory.getHeadPart(documentUUID);
        document.setHeadPart(head);

        //The sequence of function calls faithful to the following 4 lines
        SDWordsBasePart baseList = new SDWordsBasePart();
        SDSequenceObject sequenceObject = getSequence(baseList);
        document.setWordsBasePart(baseList);
        document.setSequence(sequenceObject);

        DependencyPart dependency = getDependencyPart(sequenceObject);
        document.setDependencyPart(dependency);

        SDLogPart log = new SDLogPart();
        document.setLogPart(log);
        FileModuleWriteThreadInterface fileModule = new FileModuleWriteThread(pathToFileCreate, false);
        document.saveDocument(fileModule);
        fileModule.close();
        //For check document is valid - loading it!
        SDDocument testDocument = new SDDocument();
        testDocument.loadDocument(pathToFileCreate);
    }

    public static DependencyPart getDependencyPart(SDSequenceObject sequenceObject) {
        DependencyPart dependency = new DependencyPart();
        // 0-3 for head part
        int startI = 4;
        for (SDMainObject object : sequenceObject.sequenceArray) {
            if ((object.getObjectName() != null) && (object.getObjectName() != "")) {
                dependency.addDependencyItemWithValidation(sequenceObject, object.getObjectName(), startI++);
            }
        }
        return dependency;
    }

    public static SDSequenceObject getSequence(SDWordsBasePart baseList) {
        SDSequenceObject sequence = new SDSequenceObject();
        String listValue = "test";

        sequence.AddXMLObject(SDObjectFactory.getDate()); //TODO 
        sequence.AddXMLObject(SDObjectFactory.getText("произведена фискация технического состояния"));
        sequence.AddXMLObject(SDObjectFactory.getText("многоквартирного дома по адресу:"));
        sequence.AddXMLObject(SDObjectFactory.getText("Калужская область,"));
        sequence.AddXMLObject(SDObjectFactory.getEditText("Октябрьского", 20).setObjectName("Район"));
        sequence.AddXMLObject(SDObjectFactory.getText("района, гор., пос., дер."));
        sequence.AddXMLObject(SDObjectFactory.getEditText("Калуга", 20).setObjectName("населенныйПункт"));
        sequence.AddXMLObject(SDObjectFactory.getText("ул."));
        sequence.AddXMLObject(SDObjectFactory.getEditText("Чижевского", 20).setObjectName("улица"));
        sequence.AddXMLObject(SDObjectFactory.getText("дом №"));
        sequence.AddXMLObject(SDObjectFactory.getEditText("5", 5).setObjectName("дом"));
        sequence.AddXMLObject(SDObjectFactory.getText("кор."));
        sequence.AddXMLObject(SDObjectFactory.getEditText("50", 3).setObjectName("корпус"));
        enter(sequence);
        /*
        Part.2
         */
        sequence.AddXMLObject(SDObjectFactory.getText("Общие данные:"));
        enter(sequence);
        //TODO Вынести словарные базы в конфигурацию.
        addCompositeObjectList(sequence, new CompositeObject[]{
            new TextAndSpinnerList("Категория жилищного фонда", baseList,
            "категория фонда", 0, "категория фонда"),
            new TextAndEditTextList("принадлежность", "", 30, "собственник"),
            new TextAndEditTextList("серия", "", 10, "серия"),
            new TextAndEditTextList("1.Год постройки", "1900", 5, "год постройки"),
            new Enter(),
            new TextAndEditTextList("2.Этажность", "1", 6, "этажность"),
            new Enter(),
            new TextAndEditTextList("3.Общая площать МКД, всего", "", 8, "общая площадь"),
            new Enter(),
            new TextAndEditTextList("4.Прощадь помещений МКД", "", 8, "прощадь помещений"),
            new Enter(),
            new TextAndEditTextList("5. В т.ч. жилых", "", 8, "площадь жилых помещений"),
            new Enter(),
            new TextAndEditTextList("6.Площать мест общего назначения", "", 8, "площадь общего назначения"),
            new Enter(),
            new TextAndEditTextList("Количество жилых помещений", "", 3, "Количество жилых"),
            new TextAndEditTextList("Количество нежилых помещений", "", 3, "Количество нежилых"),
            new Enter(),
            new TextAndEditTextList("Площать жилых помещений", "", 8, "Площадь жилых"),
            new TextAndEditTextList("Площать нежилых помещений", "", 8, "Площадь нежилых"),
            new Enter(),
            new TextAndEditTextList("Принадлежность нежелых помещений", "", 20, "Собственник нежилых"),
            new Enter(),
            new TextAndEditTextList("7.Кол-во подъездов:", "", 2, "количество подъездов"),
            new TextAndEditTextList("Кол-во квартир", "", 3, "количество квартир"),
            new TextAndEditTextList("в т.ч. коммунальных", "", 3, "комунальных квартир"),
            new ProsentWithText("дома", "износ дома", "определен для дома"),
            new Enter(),
            new TextAndSpinnerList("8. Конструкция/Материал фундамента", baseList,
            "Конструкция фундамента", 0, "Конструкция фундамента",
            "Материал фундамента", 0, "Материал фундамента"),
            new ProsentWithText("дома", "износ дома", "определен для дома"),
            new Enter(),
            new TextAndSpinnerList("9. Конструкция/Материал стен", baseList,
            "Конструкция стен", 0, "Конструкция стен",
            "Материал стен", 0, "Материал стен"),
            new ProsentWithText("стен", "износ стен", "определен для стен"),
            new Enter(),
            new TextAndSpinnerList("10. Конструкция/Материал кровли", baseList,
            "Конструкция кровли", 0, "Конструкция кровли",
            "Материал кровли", 0, "Материал кровли"),
            new ProsentWithText("кровли", "износ кровли", "определен для кровли"),
            new Enter(),
            new TextAndSpinnerList("11. Конструкция/Материал водостока", baseList,
            "Конструкция водостока", 0, "Конструкция водостока",
            "Материал водостока", 0, "Материал водостока"),
            new Enter(),
            new TextAndSpinnerList("12. Конструкция/Материал цокольного перекрытия", baseList,
            "Конструкция цокольного перекрытия", 0, "Конструкция цокольного перекрытия",
            "Материал цокольного перекрытия", 0, "Материал цокольного перекрытия"),
            new Enter(),
            new TextAndSpinnerList("13. Конструкция/Материал межэтажного перекрытия", baseList,
            "Конструкция межэтажного перекрытия", 0, "Конструкция межэтажного перекрытия",
            "Материал межэтажного перекрытия", 0, "Материал межэтажного перекрытия"),
            new Enter(),
            new TextAndSpinnerList("14. Конструкция/Материал чердачного перекрытия", baseList,
            "Конструкция чердачного перекрытия", 0, "Конструкция чердачного перекрытия",
            "Материал чердачного перекрытия", 0, "Материал чердачного перекрытия"),
            new Enter(),
            new TextAndSpinnerList("15. Конструкция/Материал утепляющего слоя", baseList,
            "Конструкция утепляющего слоя", 0, "Конструкция утепляющего слоя",
            "Материал утепляющего слоя", 0, "Материал утепляющего слоя"),
            new Enter(),
            new TextAndCheckBoxtList("16. Наличие/Характеристика чердака(тех.этажа)", "Наличие", false, "Наличие чердака"),
            new TextAndSpinnerList("", baseList,
            "Характеристика чердака", 0, "Характеристика чердака"),
            new Enter(),
            new TextAndSpinnerList("17. Наличие/Характеристика цокольных(подвальных) помещений", baseList,
            "Конструкция цокольных помещений", 0, "Конструкция цокольных помещений",
            "Материал цокольных помещений", 0, "Материал цокольных помещений"),
            new ProsentWithText("цокольных помещений", "износ цокольных помещений", "определен для цокольных помещений"),
            new Enter(),
            // TODO CheckBox ? 
            new TextAndSpinnerList("18. Наличиe дренажной системы", baseList,
            "Наличиe дренажной системы", 0, "Наличиe дренажной системы"),
            new Enter(),
            // TODO CheckBox ? 
            new TextAndSpinnerList("19. Наличиe дренажных насосов", baseList,
            "Наличиe дренажных насосов", 0, "Наличиe дренажных насосов"),
            new Enter(),
            // TODO Long string
            new TextAndSpinnerList("20. Материал/Конструкция полов мест общего пользования первого этажа", baseList,
            "Материал полов мест общего пользования первого этажа", 0, "Наличиe полов мест общего пользования первого этажа",
            "Конструкция полов мест общего пользования первого этажа", 0, "Конструкция полов мест общего пользования первого этажа"),
            new Enter(),
            new TextAndSpinnerList("21. Материал/Конструкция полов мест общего пользования других этажей", baseList,
            "Материал полов мест общего пользования других этажей", 0, "Наличиe полов мест общего пользования других этажей",
            "Конструкция полов мест общего пользования других этажей", 0, "Конструкция полов мест общего пользования других этажей"),
            new Enter(),
            new TextAndSpinnerList("22. Материал/Конструкция лестниц мест общего пользования", baseList,
            "Материал лестниц", 0, "Наличиe лестниц",
            "Конструкция лестниц", 0, "Конструкция лестниц"),
            new Enter(),
            // TODO CheckBox ? 
            new TextAndSpinnerList("23. Балконы(Лоджии)", baseList,
            "Балконы(Лоджии)", 0, "Балконы(Лоджии)"),
            new TextAndSpinnerList("конструкция оснований", baseList,
            "Балконы(Лоджии) - конструкция оснований", 0, "Балконы(Лоджии) - конструкция оснований"),
            new TextAndSpinnerList("ограждение", baseList,
            "Балконы(Лоджии) - ограждение", 0, "Балконы(Лоджии) - ограждение"),
            new TextAndSpinnerList("остекление", baseList,
            "Балконы(Лоджии) - остекление", 0, "Балконы(Лоджии) - остекление"),
            new TextAndSpinnerList("козырьки", baseList,
            "Балконы(Лоджии) - козырьки", 0, "Балконы(Лоджии) - козырьки"),
            new Enter(),
            new TextAndSpinnerList("24.Входные площадки", baseList,
            "Входные площадки", 0, "Входные площадки"),
            new TextAndSpinnerList("Надподъездные козырьки", baseList,
            "Надподъездные козырьки", 0, "Надподъездные козырьки"),
            new Enter(),
            new TextAndSpinnerList("25.Материал/Конструкция окон мест общего пользования", baseList,
            "Материал окон мест общего пользования", 0, "Материал окон мест общего пользования",
            "Конструкция окон мест общего пользования", 0, "Конструкция окон мест общего пользования"),
            new Enter(),
            new TextAndSpinnerList("26.Материал/Конструкция входных дверей мест общего пользования", baseList,
            "Материал входных дверей мест общего пользования", 0, "Материал входных дверей мест общего пользования",
            "Конструкция входных дверей мест общего пользования", 0, "Конструкция входных дверей мест общего пользования"),
            new Enter(),
            // TODO CheckBox ? 
            new TextAndSpinnerList("27. Наличие входного тамбура", baseList,
            "Наличие входного тамбура", 0, "Наличие входного тамбура"),
            new Enter(),
            // TODO CheckBox ? 
            new TextAndSpinnerList("28. Оборудованность для доступа инвалидов", baseList,
            "Оборудованность для доступа инвалидов", 0, "Оборудованность для доступа инвалидов"),
            new Enter(),
            new TextAndSpinnerList("29. Отделка наружняя", baseList,
            "Отделка наружняя", 0, "Отделка наружняя"),
            new TextAndSpinnerList("цокольная", baseList,
            "Отделка наружняя - цокольная", 0, "Отделка наружняя - цокольная"),
            new TextAndSpinnerList("карнизов", baseList,
            "Отделка наружняя - карнизов", 0, "Отделка наружняя - карнизов"),
            new TextAndSpinnerList("подъездов", baseList,
            "Отделка наружняя - подъездов", 0, "Отделка наружняя - подъездов"),
            new Enter(),
            new TextAndSpinnerList("30.Наличие архитектурных элементов: наружных", baseList,
            "Наличие архитектурных элементов - наружных", 0, "Наличие архитектурных элементов - наружных"),
            new TextAndSpinnerList("подъездных", baseList,
            "Наличие архитектурных элементов - подъездных", 0, "Наличие архитектурных элементов - подъездных"),
            new Enter(),
            // TODO CheckBox ? 
            new TextAndSpinnerList("31.Наличие/Материал окрытия: парапетов", baseList,
            "Наличие окрытия - парапетов", 0, "Наличие окрытия - парапетов",
            "Материал окрытия - парапетов", 0, "Материал окрытия - парапетов"),
            // TODO CheckBox ? 
            new TextAndSpinnerList("поясков(сандриков)", baseList,
            "Наличие окрытия - поясков(сандриков)", 0, "Наличие окрытия - поясков(сандриков)",
            "Материал окрытия - поясков(сандриков)", 0, "Материал окрытия - поясков(сандриков)"),
            // TODO ???
            new TextAndEditTextList("32. Наличие статуса памятника", "", 20, ""),
            new Enter()
        });

        sequence.AddXMLObject(SDObjectFactory.getText("33.Оборудованность МКД инженерными системами"));

        //TODO переделать во внутренние функции с открытием нового экрана
        addCompositeObjectList(sequence, new CompositeObject[]{
            // TODO CheckBox ? 
            new TextAndSpinnerList("a)Отопление", baseList,
            "Отопление", 0, "Отопление"),
            new TextAndSpinnerList("вид топлива", baseList,
            "Отопление - вид топлива", 0, "Отопление - вид топлива"),
            // TODO EditText(number) ? 
            new TextAndSpinnerList("Кол-во вводов", baseList,
            "Отопление - Кол-во вводов", 0, "Отопление - Кол-во вводов"),
            // TODO EditText(number) ? 
            new TextAndSpinnerList("объем посребления", baseList,
            "Отопление - объем посребления", 0, "Отопление - объем посребления"),
            new TextAndSpinnerList("расположение запорных устройств", baseList,
            "Отопление - расположение запорных устройств", 0, "Отопление - расположение запорных устройств"),
            new TextAndSpinnerList("схема теплоснабжения", baseList,
            "Отопление - схема теплоснабжения", 0, "Отопление - схема теплоснабжения"),
            // TODO CheckBox ? 
            new TextAndSpinnerList("Наличие циркулярного насоса", baseList,
            "Отопление - Наличие циркулярного насоса", 0, "Отопление - Наличие циркулярного насоса"),
            new TextAndSpinnerList("Наличие/вид учета узла", baseList,
            "Отопление - Наличие учета узла", 0, "Отопление - Наличие учета узла",
            "Отопление - Вид учета узла", 0, "Отопление - Вид учета узла"),
            new TextAndSpinnerList("расположение стояков", baseList,
            "Отопление - расположение стояков", 0, "Отопление - расположение стояков"),
            new TextAndSpinnerList("наличие элеваторов", baseList,
            "Отопление - наличие элеваторов", 0, "Отопление - наличие элеваторов"),
            new TextAndSpinnerList("Вид отопительных приборов", baseList,
            "Отопление - Вид отопительных приборов", 0, "Отопление - Вид отопительных приборов"),
            new TextAndSpinnerList("Вид розлива", baseList,
            "Отопление - Вид розлива", 0, "Отопление - Вид розлива"),
            new TextAndSpinnerList("Материал трубопроводов: розлива", baseList,
            "Отопление - Материал трубопроводов - розлива", 0, "Отопление - Материал трубопроводов - розлива"),
            new TextAndSpinnerList("стояков", baseList,
            "Отопление - Материал трубопроводов - стояков", 0, "Отопление - Материал трубопроводов - стояков"),
            // TODO CheckBox ? 
            new TextAndSpinnerList("Отопление подъедов", baseList,
            "Отопление - подъедов", 0, "Отопление - подъедов"),
            // TODO CheckBox ? 
            new TextAndSpinnerList("Подача теплоносителей в полотенцесушители", baseList,
            "Отопление - Подача теплоносителей в полотенцесушители", 0, "Отопление - Подача теплоносителей в полотенцесушители"),
            new ProsentWithText("Отопления", "износ Отопления", "определен для Отопления"),
            new Enter()
        });

        //TODO переделать во внутренние функции с открытием нового экрана
        addCompositeObjectList(sequence, new CompositeObject[]{
            new TextAndSpinnerList("б)Водоснабжение", baseList,
            "", 0, ""),
            new TextAndSpinnerList("ХВС", baseList,
            "", 0, ""),
            new TextAndSpinnerList("кол-во вводов", baseList,
            "", 0, ""),
            new TextAndSpinnerList("расположение запорных устройств", baseList,
            "", 0, ""),
            new TextAndSpinnerList("расположение стояков", baseList,
            "", 0, ""),
            new TextAndSpinnerList("Наличие/Вид узла учета", baseList,
            "", 0, "",
            "", 0, ""),
            new TextAndSpinnerList("Материал трубопроводов: розлива ХВС", baseList,
            "", 0, ""),
            new TextAndSpinnerList("стояков ХВС", baseList,
            "", 0, ""),
            new TextAndSpinnerList("Наличие повысительного насоса", baseList,
            "", 0, ""),
            new TextAndSpinnerList("Наличие поквартирного учета", baseList,
            "", 0, "")
        });
        //addProsentWithText(sequence, "", "", "");
        enter(sequence);

        addCompositeObjectList(sequence, new CompositeObject[]{
            new TextAndSpinnerList("ГВС", baseList, listValue, 0, ""),
            new TextAndSpinnerList(" Кол-во вводов", baseList, listValue, 0, ""),
            new TextAndSpinnerList("Схема ГВС", baseList, listValue, 0, ""),
            new TextAndSpinnerList("Наличие/Вид узла учета", baseList, listValue, 0, "", listValue, 0, ""),
            new TextAndSpinnerList("расположение запорных устройств", baseList, listValue, 0, ""),
            new TextAndSpinnerList("расположение стояков", baseList, listValue, 0, ""),
            new TextAndSpinnerList("Наличие бойлеров", baseList, listValue, 0, ""),
            new TextAndSpinnerList("Наличие/вид элеваторов", baseList, listValue, 0, "", listValue, 0, ""),
            new TextAndSpinnerList("Материал трубопроводов: розлива ГВС", baseList, listValue, 0, ""),
            new TextAndSpinnerList("стояков ГВС", baseList, listValue, 0, ""),
            new TextAndSpinnerList("Наличие повысительного насоса", baseList, listValue, 0, ""),
            new TextAndSpinnerList("Наличие поквартирного учета", baseList, listValue, 0, ""),
            new TextAndSpinnerList("Наличие пожарного водопровода", baseList, listValue, 0, "")
        });
        //addProsentWithText(sequence, "", "", "");
        enter(sequence);

        addCompositeObjectList(sequence, new CompositeObject[]{
            new TextAndSpinnerList("в)Водоотведение", baseList, listValue, 0, ""),
            new TextAndSpinnerList("кол-во выпусков", baseList, listValue, 0, ""),
            new TextAndSpinnerList("расположение стояков", baseList, listValue, 0, ""),
            new TextAndSpinnerList("наличие фекальных стояков", baseList, listValue, 0, ""),
            new TextAndSpinnerList("разделение кухонных и фекальных стояков", baseList, listValue, 0, ""),
            new TextAndSpinnerList("материал трубопроводов водоотведение: стояков", baseList, listValue, 0, ""),
            new TextAndSpinnerList("лежаков", baseList, listValue, 0, "")
        });
        //addProsentWithText(sequence, "", "", "");
        enter(sequence);

        addCompositeObjectList(sequence, new CompositeObject[]{
            new TextAndSpinnerList("г)Газоснабжение", baseList, listValue, 0, ""),
            new TextAndSpinnerList("Кол-во вводов", baseList, listValue, 0, ""),
            new TextAndSpinnerList("Наличие узла учета", baseList, listValue, 0, ""),
            new TextAndSpinnerList("расположение стояков", baseList, listValue, 0, ""),
            new TextAndSpinnerList("Вид газового оборудования (плиты/колонки(котлы))", baseList, listValue, 0, "", listValue, 0, ""),
            new TextAndSpinnerList("Наличие поквартирного учета", baseList, listValue, 0, "")
        });
        //addProsentWithText(sequence, "", "", "");
        enter(sequence);

        addCompositeObjectList(sequence, new CompositeObject[]{
            new TextAndSpinnerList("д)вентиляция", baseList, listValue, 0, ""),
            new TextAndSpinnerList("расположение/материал вентканалов", baseList, listValue, 0, "", listValue, 0, "")
        });
        enter(sequence);

        addCompositeObjectList(sequence, new CompositeObject[]{
            new TextAndSpinnerList("е)Наличие мусоропроводов/число стволов", baseList, listValue, 0, "", listValue, 0, "")
        });
        enter(sequence);

        addCompositeObjectList(sequence, new CompositeObject[]{
            new TextAndSpinnerList("ж)Наличие ливтов пасажирских/грузовых", baseList, listValue, 0, "", listValue, 0, "")
        });
        //addProsentWithText(sequence, "", "", "");
        enter(sequence);

        addCompositeObjectList(sequence, new CompositeObject[]{
            new TextAndSpinnerList("з)Электроснабжение", baseList, listValue, 0, ""),
            new TextAndSpinnerList("Входное напряжение/кол-ва фаз", baseList, listValue, 0, "", listValue, 0, ""),
            new TextAndSpinnerList("Кол-во / Вид вводов", baseList, listValue, 0, "", listValue, 0, ""),
            new TextAndSpinnerList("Наличие/расположение ВРУ", baseList, listValue, 0, "", listValue, 0, ""),
            new TextAndSpinnerList("Наличие прибора учета", baseList, listValue, 0, ""),
            new TextAndSpinnerList("Прокладка/кол-во фаз/расположение стояков", baseList, listValue, 0, "", listValue, 0, "", listValue, 0, ""),
            new TextAndSpinnerList("Наличие заземлений", baseList, listValue, 0, ""),
            new TextAndSpinnerList("Наличие СУП", baseList, listValue, 0, ""),
            new TextAndSpinnerList("Наличие подвального освещения", baseList, listValue, 0, "", listValue, 0, ""),
            new TextAndSpinnerList("Наличие чердачного освежения", baseList, listValue, 0, "", listValue, 0, ""),
            new TextAndSpinnerList("Наличие придомового освещения", baseList, listValue, 0, ""),
            new TextAndSpinnerList("Наличие автоматики на освещении", baseList, listValue, 0, ""),
            new TextAndSpinnerList("Вид применяемых ламп", baseList, listValue, 0, ""),
            new TextAndSpinnerList("Наличие/расположение поквартирного учета", baseList, listValue, 0, "", listValue, 0, ""),
            new TextAndSpinnerList("Наличие автоматики на силовой проводке", baseList, listValue, 0, ""),
            new TextAndSpinnerList("Наличие системы дымоудаления", baseList, listValue, 0, ""),
            new TextAndSpinnerList("Электроснабжение насоса/приводов задвижек пожарного водопровода", baseList,
            listValue, 0, "", listValue, 0, ""),
            new TextAndSpinnerList("Наличие/способ электроснабжения электрических плит", baseList, listValue, 0, "", listValue, 0, "")
        });
        //addProsentWithText(sequence, "", "", "");
        enter(sequence);

        addCompositeObjectList(sequence, new CompositeObject[]{
            new TextAndSpinnerList("и)Наличие коммуникационных сетей и сигнализаций", baseList, listValue, 0, ""),
            new TextAndSpinnerList("ОДС", baseList, listValue, 0, ""),
            new TextAndSpinnerList("Пожарная сигнализация", baseList, listValue, 0, ""),
            new TextAndSpinnerList("радио", baseList, listValue, 0, ""),
            new TextAndSpinnerList("телефон", baseList, listValue, 0, ""),
            new TextAndSpinnerList("ТВ_антенна", baseList, listValue, 0, ""),
            new TextAndSpinnerList("Кабельное ТВ", baseList, listValue, 0, "", listValue, 0, "", listValue, 0, ""),
            new TextAndSpinnerList("Домофон", baseList, listValue, 0, ""),
            new TextAndSpinnerList("КИП", baseList, listValue, 0, ""),
            new TextAndSpinnerList("Интернет провайдеров", baseList, listValue, 0, "", listValue, 0, "", listValue, 0, "", listValue, 0, "")
        });
        SDStructureFactory.enter(sequence);

        addCompositeObjectList(sequence, new CompositeObject[]{
            new TextAndSpinnerList("к)Элементы блгоустройства", baseList, listValue, 0, ""),
            new TextAndSpinnerList("Наличие сформированного земельного участка", baseList, listValue, 0, ""),
            new TextAndSpinnerList("Кадастровый номер", baseList, listValue, 0, ""),
            new TextAndSpinnerList("Площать привомового жилищного участка", baseList, listValue, 0, ""),
            new TextAndSpinnerList("Наличие надворных построек: туалеты", baseList, listValue, 0, ""),
            new TextAndSpinnerList("помойницы", baseList, listValue, 0, ""),
            new TextAndSpinnerList("сараи", baseList, listValue, 0, ""),
            new TextAndSpinnerList("площадки для ТБО", baseList, listValue, 0, ""),
            new TextAndSpinnerList("Строения: для инженерного оборудования", baseList, listValue, 0, ""),
            new TextAndSpinnerList("для хранения топлива", baseList, listValue, 0, ""),
            new TextAndSpinnerList("Наличие привомового проезда/парковки", baseList, listValue, 0, "", listValue, 0, ""),
            new TextAndSpinnerList("пешеходных дорожек", baseList, listValue, 0, ""),
            new TextAndSpinnerList("Уборочная часть", baseList, listValue, 0, ""),
            new TextAndSpinnerList("Наличие площадок:хозяйственной", baseList, listValue, 0, ""),
            new TextAndSpinnerList("спортивной", baseList, listValue, 0, ""),
            new TextAndSpinnerList("детской", baseList, listValue, 0, ""),
            new TextAndSpinnerList("для отдыха", baseList, listValue, 0, ""),
            new TextAndSpinnerList("Число малых форм", baseList, listValue, 0, "")
        });
        enter(sequence);

        /*
        addCompositeObjectList(sequence, true, false, new CompositeObject[]{
            new TextAndSpinnerList("", listValue, 0),
            new TextAndSpinnerList("", listValue, 0),
            new TextAndSpinnerList("", listValue, 0),
            new TextAndSpinnerList("", listValue, 0),
            new TextAndSpinnerList("", listValue, 0),
            new TextAndSpinnerList("", listValue, 0)
        });
        addProsentWithText(sequence, "");
        enter(sequence);
         */
        enter(sequence);
        sequence.enumirateSequence(0);
        return sequence;
    }

    static class ProsentWithText extends CompositeObject {

        public ProsentWithText(String text, String name1, String name2) {
            super();
            this.addObject(SDObjectFactory.getText("% износа " + text + " по тех паспарту"));
            this.addObject(SDObjectFactory.getEditText(3).setObjectName(name1));
            this.addObject(SDObjectFactory.getText("изменения внесены "));
            this.addObject(SDObjectFactory.getDate().setObjectName(name2));
        }
    }
}
