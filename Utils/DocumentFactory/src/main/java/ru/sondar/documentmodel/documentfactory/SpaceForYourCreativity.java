package ru.sondar.documentmodel.documentfactory;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import ru.sondar.core.filemodule.FileModuleWriteThreadInterface;
import ru.sondar.core.filemodule.pc.FileModuleWriteThread;
import ru.sondar.core.logger.Logger;
import ru.sondar.core.logger.pc.PCLogging;
import ru.sondar.core.parser.exception.ObjectStructureException;
import ru.sondar.documentmodel.SDDocument;
import ru.sondar.documentmodel.SDSequenceObject;
import ru.sondar.documentmodel.dependencymodel.DependencyPart;
import ru.sondar.documentmodel.documentfactory.structure.CompositeObject;
import ru.sondar.documentmodel.documentfactory.structure.Enter;
import static ru.sondar.documentmodel.documentfactory.structure.SDStructureFactory.addCompositeObjectList;
import static ru.sondar.documentmodel.documentfactory.structure.SDStructureFactory.enter;
import ru.sondar.documentmodel.documentfactory.structure.Text;
import ru.sondar.documentmodel.documentfactory.structure.TextAndCheckBoxtList;
import ru.sondar.documentmodel.documentfactory.structure.TextAndEditTextList;
import ru.sondar.documentmodel.documentfactory.structure.TextAndSpinnerList;
import ru.sondar.documentmodel.objectmodel.SDHeadPart;
import ru.sondar.documentmodel.objectmodel.SDLogPart;
import ru.sondar.documentmodel.objectmodel.SDMainObject;
import ru.sondar.documentmodel.objectmodel.SDWordsBasePart;

/**
 *
 * @author GlebZemnieks
 */
public class SpaceForYourCreativity {

    public static String pathToFileCreate = "E:\\test.txt";

    public static String documentUUID = "69f08553-bf07-405f-b849-0bea1423ed1c";

    public static void main(String... args) throws SAXException, IOException, ParserConfigurationException, ObjectStructureException {
        Logger.setLogger(new PCLogging());
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

        sequence.addXMLObject(SDObjectFactory.getDate().setObjectName("DocumentCreationName")); //TODO 
        sequence.addXMLObject(SDObjectFactory.getText("произведена фискация технического состояния"));
        sequence.addXMLObject(SDObjectFactory.getText("многоквартирного дома по адресу:"));
        sequence.addXMLObject(SDObjectFactory.getText("Калужская область,"));
        sequence.addXMLObject(SDObjectFactory.getEditText("Октябрьского", 20).setObjectName("Район"));
        sequence.addXMLObject(SDObjectFactory.getText("района, гор., пос., дер."));
        sequence.addXMLObject(SDObjectFactory.getEditText("Калуга", 20).setObjectName("населенныйПункт"));
        sequence.addXMLObject(SDObjectFactory.getText("ул."));
        sequence.addXMLObject(SDObjectFactory.getEditText("Чижевского", 20).setObjectName("улица"));
        sequence.addXMLObject(SDObjectFactory.getText("дом №"));
        sequence.addXMLObject(SDObjectFactory.getEditText("5", 5).setObjectName("дом"));
        sequence.addXMLObject(SDObjectFactory.getText("кор."));
        sequence.addXMLObject(SDObjectFactory.getEditText("50", 3).setObjectName("longPrimer"));
        enter(sequence);
        /*
        Part.2
         */
        sequence.addXMLObject(SDObjectFactory.getText("Общие данные:"));
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
            new TextAndEditTextList("Площать жилых помещений", "", 8, "Площадь жилых"),
            new Enter(),
            new TextAndEditTextList("Количество нежилых помещений", "", 3, "Количество нежилых"),
            new TextAndEditTextList("Площать нежилых помещений", "", 8, "Площадь нежилых"),
            new Enter(),
            new TextAndEditTextList("Принадлежность нежелых помещений", "", 20, "Собственник нежилых"),
            new Enter(),
            new TextAndEditTextList("7.Кол-во подъездов:", "", 2, "количество подъездов"),
            new TextAndEditTextList("Кол-во квартир", "", 3, "количество квартир"),
            new TextAndEditTextList("в т.ч. коммунальных", "", 3, "коммунальных квартир"),
            new TextAndEditTextList("в т.ч. коммунальных", "", 3, "число проживающих"),
            new ProsentWithText("дома", "износ дома", "определен для дома"),
            new Enter(),
            new TextAndSpinnerList("8. Конструкция/Материал фундамента", baseList,
            "Конструкция фундамента", 0, "Конструкция фундамента",
            "Материал фундамента", 0, "Материал фундамента"),
            new ProsentWithText("дома", "износ фундамента", "определен для фундамента"),
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
            "Материал лестниц", 0, "Материал лестниц",
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
        }
        );

        sequence.addXMLObject(SDObjectFactory.getText("33.Оборудованность МКД инженерными системами"));

        //TODO переделать во внутренние функции с открытием нового экрана
        addCompositeObjectList(sequence,
                new CompositeObject[]{
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
                }
        );

        addCompositeObjectList(sequence,
                new CompositeObject[]{
                    // TODO CheckBox ? 
                    new Text("б)Водоснабжение"),
                    new Enter(),
                    new TextAndSpinnerList("ХВС", baseList,
                            "ХВС", 0, "ХВС"),
                    // TODO EditText(number) ? 
                    new TextAndSpinnerList("Кол-во вводов", baseList,
                            "Водоснабжение ХВС - Кол-во вводов", 0, "Водоснабжение ХВС: - Кол-во вводов"),
                    new Enter(),
                    new TextAndSpinnerList("расположение запорных устройств", baseList,
                            "Водоснабжение ХВС - расположение запорных устройств", 0, "Водоснабжение ХВС - расположение запорных устройств"),
                    new TextAndSpinnerList("расположение стояков", baseList,
                            "Водоснабжение ХВС - расположение стояков", 0, "Водоснабжение ХВС - расположение стояков"),
                    new TextAndSpinnerList("Наличие/вид узла учета", baseList,
                            "Водоснабжение ХВС - Наличие узла учета", 0, "Водоснабжение ХВС - Наличие узла учета",
                            "Водоснабжение ХВС - Вид узла учета", 0, "Водоснабжение ХВС - Вид узла учета"),
                    new Enter(),
                    new TextAndSpinnerList("Материал трубопроводов: розлива ХВС", baseList,
                            "Водоснабжение ХВС - Материал розлива ХВС", 0, "Водоснабжение ХВС - Материал розлива ХВС"),
                    new TextAndSpinnerList("стояков ХВС", baseList,
                            "Водоснабжение ХВС - Материал стояков ХВС", 0, "Водоснабжение ХВС - Материал стояков ХВС"),
                    new TextAndSpinnerList("Наличие повысительного насоса", baseList,
                            "Водоснабжение ХВС - Наличие повысительного насоса", 0, "Водоснабжение ХВС - Наличие повысительного насоса"),
                    new TextAndSpinnerList("Наличие поквартирного учета", baseList,
                            "Водоснабжение ХВС - Наличие поквартирного учета", 0, "Водоснабжение ХВС - Наличие поквартирного учета"),
                    new ProsentWithText("водоснабжение", "износ Водоснабжение ХВС", "определен для Водоснабжение ХВС"),
                    new Enter(),
                    new TextAndSpinnerList("ГВС", baseList,
                            "ГВС", 0, "ГВС"),
                    // TODO EditText(number) ? 
                    new TextAndSpinnerList("Кол-во вводов", baseList,
                            "Водоснабжение ГВС - Кол-во вводов", 0, "Водоснабжение ГВС - Кол-во вводов"),
                    new TextAndSpinnerList("схема ГВС", baseList,
                            "Водоснабжение ГВС - схема ГВС", 0, "Водоснабжение ГВС - схема ГВС"),
                    new TextAndSpinnerList("Наличие/вид узла учета", baseList,
                            "Водоснабжение ГВС - Наличие узла учета", 0, "Водоснабжение ГВС - Наличие узла учета",
                            "Водоснабжение ГВС - Вид узла учета", 0, "Водоснабжение ГВС - Вид узла учета"),
                    new TextAndSpinnerList("расположение запорных устройств", baseList,
                            "Водоснабжение ГВС - расположение запорных устройств", 0, "Водоснабжение ГВС - расположение запорных устройств"),
                    new TextAndSpinnerList("расположение стояков", baseList,
                            "Водоснабжение ГВС - расположение стояков", 0, "Водоснабжение ГВС - расположение стояков"),
                    new TextAndSpinnerList("Материал трубопроводов: розлива ГВС", baseList,
                            "Водоснабжение ГВС - Материал розлива ХВС", 0, "Водоснабжение ГВС - Материал розлива ХВС"),
                    new TextAndSpinnerList("стояков ГВС", baseList,
                            "Водоснабжение ГВС - Материал стояков ХВС", 0, "Водоснабжение ГВС - Материал стояков ХВС"),
                    new TextAndSpinnerList("Наличие повысительного насоса", baseList,
                            "Водоснабжение ГВС - Наличие повысительного насоса", 0, "Водоснабжение ГВС - Наличие повысительного насоса"),
                    new TextAndSpinnerList("Наличие поквартирного учета", baseList,
                            "Водоснабжение ГВС - Наличие поквартирного учета", 0, "Водоснабжение ГВС - Наличие поквартирного учета"),
                    new ProsentWithText("водоснабжение", "износ Водоснабжение ГВС", "определен для Водоснабжение ГВС"),
                    new TextAndSpinnerList("наличие пожарного водопровода", baseList,
                            "Водоснабжение ГВС - наличие пожарного водопровода", 0, "Водоснабжение ГВС - наличие пожарного водопровода"),
                    new Enter(),}
        );

        addCompositeObjectList(sequence,
                new CompositeObject[]{
                    new TextAndSpinnerList("в) водоотведение:", baseList,
                            "Водоотведение", 0, "Водоотведение"),
                    new TextAndSpinnerList("кол-во выпусков", baseList,
                            "Водоотведение - кол-во выпусков", 0, "Водоотведение - кол-во выпусков"),
                    new TextAndSpinnerList("расположение стояков", baseList,
                            "Водоотведение - расположение стояков", 0, "Водоотведение - расположение стояков"),
                    new TextAndSpinnerList("наличие фекальных стояков", baseList,
                            "Водоотведение - наличие фекальных стояков", 0, "Водоотведение - наличие фекальных стояков"),
                    new TextAndSpinnerList("Наличие бойлеров", baseList,
                            "Водоотведение - Наличие бойлеров", 0, "Водоотведение - Наличие бойлеров"),
                    new TextAndSpinnerList("Наличие разделение кухонных и фекальных стояков", baseList,
                            "Водоотведение - разделение кухонных и фекальных стояков", 0, "Водоотведение - разделение кухонных и фекальных стояков"),
                    new TextAndSpinnerList("материал трубопроводов водоотведения: стояков", baseList,
                            "Водоотведение - материал трубопроводов водоотведения - стояков", 0, "Водоотведение - материал трубопроводов водоотведения - стояков"),
                    new TextAndSpinnerList("лежаков", baseList,
                            "Водоотведение - материал трубопроводов водоотведения - лежаков", 0, "Водоотведение - материал трубопроводов водоотведения - лежаков"),
                    new ProsentWithText("Водоотведение", "износ Водоотведения", "определен для Водоотведения"),
                    new Enter()
                }
        );
        addCompositeObjectList(sequence,
                new CompositeObject[]{
                    new TextAndSpinnerList("Г) Газоснабжение:", baseList,
                            "Газоснабжение", 0, "Газоснабжение"),
                    new TextAndSpinnerList("Кол-во вводов", baseList,
                            "Газоснабжение - Кол-во вводов", 0, "Газоснабжение - Кол-во вводов"),
                    new TextAndSpinnerList("Наличие узла учета", baseList,
                            "Газоснабжение - Наличие узла учета", 0, "Газоснабжение - Наличие узла учета"),
                    new TextAndSpinnerList("Наличие узла учета", baseList,
                            "Газоснабжение - расположение стояков", 0, "Газоснабжение - расположение стояков"),
                    new TextAndSpinnerList("Вид газового оборудования (плиты/колонки(котлы))", baseList,
                            "Газоснабжение - Вид газового оборудования 1", 0, "Газоснабжение - Вид газового оборудования 1",
                            "Газоснабжение - Вид газового оборудования 2", 0, "Газоснабжение - Вид газового оборудования 2"),
                    new TextAndSpinnerList("Наличие поквартирного учета", baseList,
                            "Газоснабжение - Наличие поквартирного учета", 0, "Газоснабжение - Наличие поквартирного учета"),
                    new ProsentWithText("Газоснабжение", "износ Газоснабжение", "определен для Газоснабжение"),
                    new Enter()
                }
        );
        
        
        addCompositeObjectList(sequence,
                new CompositeObject[]{
                    new TextAndSpinnerList("Д) Вентиляция:", baseList,
                            "Вентиляция", 0, "Вентиляция"),
                    new TextAndSpinnerList("расположение/материал вентканалов", baseList,
                            "Вентиляция - расположение вентканалов", 0, "Вентиляция - расположение вентканалов",
                            "Вентиляция - материал вентканалов", 0, "Вентиляция - материал вентканалов"),
                    new Enter()
                }
        );
        addCompositeObjectList(sequence,
                new CompositeObject[]{
                    new TextAndSpinnerList("Ж) Наличие лифтов пассажирских/грузовых ", baseList,
                            "Лифт - пассажирский", 0, "Лифт - пассажирский",
                            "Лифт - грузовой", 0, "Лифт - грузовой"),
                    new ProsentWithText("лифтов", "износ лифтов", "определен для лифтов"),
                    new Enter()
                }
        );
        addCompositeObjectList(sequence,
                new CompositeObject[]{
                    new Enter()
                }
        );
        
        
        addCompositeObjectList(sequence,
                new CompositeObject[]{
                    new Enter()
                }
        );

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
