package ru.sondar.documentmodel.documentfactory;

import ru.sondar.documentmodel.SDDocument;
import ru.sondar.documentmodel.SDSequenceObject;
import ru.sondar.documentmodel.documentfactory.structure.*;
import ru.sondar.documentmodel.objectmodel.*;
import ru.sondar.documentmodel.dependencymodel.DependencyPart;
import static ru.sondar.documentmodel.documentfactory.structure.SDStructureFactory.addCompositeObjectList;
import static ru.sondar.documentmodel.documentfactory.structure.SDStructureFactory.enter;
import ru.sondar.core.filemodule.FileModuleWriteThreadInterface;
import ru.sondar.core.filemodule.pc.FileModuleWriteThread;

/**
 *
 * @author GlebZemnieks
 */
public class SpaceForYourCreativity {

    public static String pathToFileCreate = "E:\\test.txt";

    public static String documentUUID = "69f08553-bf07-405f-b849-0bea1423ed1c";

    public static void main(String... args) {
        SDDocument document = new SDDocument();
        SDHeadPart head = SDObjectFactory.getHeadPart(documentUUID);
        document.setHeadPart(head);
        SDWordsBasePart baseList = getWordsBase();
        document.setWordsBasePart(baseList);
        SDSequenceObject sequenceObject = getSequence(baseList);
        document.setSequence(sequenceObject);

        DependencyPart dependency = getDependencyPart(sequenceObject);
        document.setDependencyPart(dependency);

        SDLogPart log = new SDLogPart();
        document.setLogPart(log);
        FileModuleWriteThreadInterface fileModule = new FileModuleWriteThread(pathToFileCreate, false);
        document.saveDocument(fileModule);
        fileModule.close();
    }

    public static SDWordsBasePart getWordsBase() {
        SDWordsBasePart baseList = new SDWordsBasePart();
        WordBase base1 = new WordBase();
        base1.add("test");
        base1.add("test", "test");
        baseList.addNewBase("test", base1);
        return baseList;
    }

    public static DependencyPart getDependencyPart(SDSequenceObject sequenceObject) {
        DependencyPart dependency = new DependencyPart();
        dependency.addDependencyItemWithValidation(sequenceObject, "test", 0);
        dependency.addDependencyItemWithValidation(sequenceObject, "test2", 1);
        return dependency;
    }

    public static SDSequenceObject getSequence(SDWordsBasePart baseList) {
        SDSequenceObject sequence = new SDSequenceObject();
        String listValue = "test";
        sequence.AddXMLObject(SDObjectFactory.getDate()); //TODO 
        sequence.AddXMLObject(SDObjectFactory.getText("произведена фискация технического состояния"));
        sequence.AddXMLObject(SDObjectFactory.getText("многоквартирного дома по адресу:"));
        sequence.AddXMLObject(SDObjectFactory.getText("Калужская область,"));
        sequence.AddXMLObject(SDObjectFactory.getEditText("Октябрьского", 20));
        sequence.AddXMLObject(SDObjectFactory.getText("района, гор., пос., дер."));
        sequence.AddXMLObject(SDObjectFactory.getEditText("Калуга", 20));
        sequence.AddXMLObject(SDObjectFactory.getText("ул."));
        sequence.AddXMLObject(SDObjectFactory.getEditText("Чижевского", 20));
        sequence.AddXMLObject(SDObjectFactory.getText("дом №"));
        sequence.AddXMLObject(SDObjectFactory.getEditText("5", 20));
        sequence.AddXMLObject(SDObjectFactory.getText("кор."));
        sequence.AddXMLObject(SDObjectFactory.getEditText("50", 20));
        enter(sequence);
        /*
        Part.2
         */
        sequence.AddXMLObject(SDObjectFactory.getText("Общие данные:"));
        enter(sequence);
        //TODO Вынести словарные базы в конфигурацию.
        addCompositeObjectList(sequence, new CompositeObject[]{
            new TextAndSpinnerList("Категория жилищного фонда", baseList, "test", 1, "")
        });
        addCompositeObjectList(sequence, new CompositeObject[]{
            new TextAndEditTextList("принадлежность", "123123123123", 30, "")
        });
        addCompositeObjectList(sequence, new CompositeObject[]{
            new TextAndEditTextList("серия", "123123123", 10, "")
        });
        enter(sequence);
        addCompositeObjectList(sequence, new CompositeObject[]{
            new TextAndEditTextList("1.Год постройки", "1998", 10, "")
        });
        enter(sequence);
        //TODO Переделать в числовое поле с ограничениями 
        addCompositeObjectList(sequence, new CompositeObject[]{
            new TextAndSpinnerList("2.Этажность", baseList, listValue, 0, "")
        });
        enter(sequence);
        //TODO Переделать в числовое поле с ограничениями. 
        //TODO Может быть не целое.
        addCompositeObjectList(sequence, new CompositeObject[]{
            new TextAndEditTextList("3.Площать МКД, всего", "", 5, "")
        });
        enter(sequence);
        //TODO Переделать в числовое поле с ограничениями. 
        //TODO Может быть не целое.
        addCompositeObjectList(sequence, new CompositeObject[]{
            new TextAndEditTextList("4.Прощадь помещений МКД", "", 5, "")
        });
        enter(sequence);
        //TODO Переделать в числовое поле с ограничениями. 
        //TODO Может быть не целое.
        addCompositeObjectList(sequence, new CompositeObject[]{
            new TextAndEditTextList("5. В т.ч. жилых", "", 5, "")
        });
        enter(sequence);
        //TODO Переделать в числовое поле с ограничениями. 
        //TODO Может быть не целое.
        addCompositeObjectList(sequence, new CompositeObject[]{
            new TextAndEditTextList("6.Площать мест общего назначения", "", 5, "")
        });
        enter(sequence);
        //TODO Переделать в числовое поле с ограничениями. 
        addCompositeObjectList(sequence, new CompositeObject[]{
            new TextAndEditTextList("Количетво жилых помещений", "", 5, "")
        });
        //TODO Переделать в числовое поле с ограничениями. 
        //TODO Может быть не целое.
        addCompositeObjectList(sequence, new CompositeObject[]{
            new TextAndEditTextList("Площать нежилых помещений", "", 5, "")
        });

        //TODO разобраться еще как это поле сделать        
        addCompositeObjectList(sequence, new CompositeObject[]{
            new TextAndEditTextList("Принадлежность нежелых помещений", "", 20, "")
        });
        enter(sequence);
        addCompositeObjectList(sequence, new CompositeObject[]{
            new TextAndEditTextList("7.Кол-во подъездов:", "", 5, "")
        });
        addCompositeObjectList(sequence, new CompositeObject[]{
            new TextAndEditTextList("Кол-во квартир", "", 5, "")
        });
        addCompositeObjectList(sequence, new CompositeObject[]{
            new TextAndEditTextList("в т.ч. коммунальных", "", 5, "")
        });
        addProsentWithText(sequence, "");
        enter(sequence);

        //TODO Вынести словарные базы в конфигурацию.
        addCompositeObjectList(sequence, new CompositeObject[]{
            new TextAndSpinnerList("8. Конструкция/Материал фундамента", baseList,
            listValue, 0, "",
            listValue, 0, "")
        });
        enter(sequence);
        addProsentWithText(sequence, "");
        enter(sequence);

        //TODO Вынести словарные базы в конфигурацию.
        addCompositeObjectList(sequence, new CompositeObject[]{
            new TextAndSpinnerList("9. Конструкция/Материал стен", baseList,
            listValue, 0, "",
            listValue, 0, "")
        });
        enter(sequence);
        addProsentWithText(sequence, "фасада");
        enter(sequence);

        //TODO Вынести словарные базы в конфигурацию.
        addCompositeObjectList(sequence, new CompositeObject[]{
            new TextAndSpinnerList("10. Конструкция/Материал кровли", baseList,
            listValue, 0, "",
            listValue, 0, "")
        });
        enter(sequence);
        addProsentWithText(sequence, "кровли");
        enter(sequence);

        //TODO Вынести словарные базы в конфигурацию.
        addCompositeObjectList(sequence, new CompositeObject[]{
            new TextAndSpinnerList("11. Конструкция/Материал водостока", baseList,
            listValue, 0, "")
        });
        enter(sequence);

        //TODO Вынести словарные базы в конфигурацию.
        addCompositeObjectList(sequence, new CompositeObject[]{
            new TextAndSpinnerList("12. Конструкция/Материал цокольного перекрытия", baseList,
            listValue, 0, "")
        });
        enter(sequence);

        //TODO Вынести словарные базы в конфигурацию.
        addCompositeObjectList(sequence, new CompositeObject[]{
            new TextAndSpinnerList("13. Конструкция/Материал межэтажного перекрытия", baseList,
            listValue, 0, "")
        });
        enter(sequence);

        //TODO Вынести словарные базы в конфигурацию.
        addCompositeObjectList(sequence, new CompositeObject[]{
            new TextAndSpinnerList("14. Конструкция/Материал чердачного перекрытия", baseList,
            listValue, 0, "")
        });
        enter(sequence);

        //TODO Вынести словарные базы в конфигурацию.
        addCompositeObjectList(sequence, new CompositeObject[]{
            new TextAndSpinnerList("15. Материал утепляющего слоя", baseList,
            listValue, 0, "")
        });
        enter(sequence);

        //TODO Вынести словарные базы в конфигурацию.
        addCompositeObjectList(sequence, new CompositeObject[]{
            new TextAndSpinnerList("16. Наличие/Характеристия чердака(тех.этажа)", baseList,
            listValue, 0, "", listValue, 0, "")
        });
        enter(sequence);

        //TODO Вынести словарные базы в конфигурацию.
        addCompositeObjectList(sequence, new CompositeObject[]{
            new TextAndSpinnerList("17. Наличие/Характеристия цокольный(подвальных) помещений", baseList,
            listValue, 0, "", listValue, 0, "")
        });
        enter(sequence);
        addProsentWithText(sequence, "подвала");
        enter(sequence);

        //TODO Вынести словарные базы в конфигурацию.
        addCompositeObjectList(sequence, new CompositeObject[]{
            new TextAndSpinnerList("18. Наличи дренажной системы", baseList,
            listValue, 0, "")
        });
        enter(sequence);

        //TODO Вынести словарные базы в конфигурацию.
        addCompositeObjectList(sequence, new CompositeObject[]{
            new TextAndSpinnerList("19. Наличие дренажных насосов", baseList,
            listValue, 0, "")
        });
        enter(sequence);

        //TODO Вынести словарные базы в конфигурацию.
        addCompositeObjectList(sequence, new CompositeObject[]{
            new TextAndSpinnerList("20. Материал/Конструкция полов мест общего пользования первого этажа", baseList,
            listValue, 0, "", listValue, 0, "")
        });
        enter(sequence);

        //TODO Вынести словарные базы в конфигурацию.
        addCompositeObjectList(sequence, new CompositeObject[]{
            new TextAndSpinnerList("21. Материал/Конструкция полов мест общего пользования других этажей", baseList,
            listValue, 0, "", listValue, 0, "")
        });
        enter(sequence);

        //TODO Вынести словарные базы в конфигурацию.
        addCompositeObjectList(sequence, new CompositeObject[]{
            new TextAndSpinnerList("22. Материал/Конструкция лестниц мест общего пользования", baseList,
            listValue, 0, "", listValue, 0, "")
        });
        enter(sequence);

        //TODO Вынести словарные базы в конфигурацию.
        //TODO Refactoring using internalFunction.OpenNewDocument()
        addCompositeObjectList(sequence, new CompositeObject[]{
            new TextAndSpinnerList("23. Балконы(Лоджии)", baseList,
            listValue, 0, ""),
            new TextAndSpinnerList("конструкция оснований", baseList,
            listValue, 0, ""),
            new TextAndSpinnerList("ограждение", baseList,
            listValue, 0, ""),
            new TextAndSpinnerList("остекление", baseList,
            listValue, 0, ""),
            new TextAndSpinnerList("козырьки", baseList,
            listValue, 0, "")
        });
        enter(sequence);

        //TODO Вынести словарные базы в конфигурацию.
        addCompositeObjectList(sequence, new CompositeObject[]{
            new TextAndSpinnerList("24.Входные площадки", baseList,
            listValue, 0, ""),
            new TextAndSpinnerList("Надподъездные козырьки", baseList,
            listValue, 0, "")
        });
        enter(sequence);

        //TODO Вынести словарные базы в конфигурацию.
        addCompositeObjectList(sequence, new CompositeObject[]{
            new TextAndSpinnerList("25.Материал/Конструкция окон мест общего пользования", baseList,
            listValue, 0, "", listValue, 0, "")
        });
        enter(sequence);

        //TODO Вынести словарные базы в конфигурацию.
        addCompositeObjectList(sequence, new CompositeObject[]{
            new TextAndSpinnerList("26.Материал/Конструкция входных дверей мест общего пользования", baseList,
            listValue, 0, "", listValue, 0, "")
        });
        enter(sequence);

        //TODO Вынести словарные базы в конфигурацию.
        addCompositeObjectList(sequence, new CompositeObject[]{
            new TextAndSpinnerList("27. Наличие входного тамбура", baseList, listValue, 0, "")
        });
        enter(sequence);

        //TODO Вынести словарные базы в конфигурацию.
        addCompositeObjectList(sequence, new CompositeObject[]{
            new TextAndSpinnerList("28. Оборудованность для доступа инвалидов", baseList, listValue, 0, "")
        });
        enter(sequence);

        //TODO Вынести словарные базы в конфигурацию.
        addCompositeObjectList(sequence, new CompositeObject[]{
            new TextAndSpinnerList("29. Отделка наружняя", baseList,
            listValue, 0, ""),
            new TextAndSpinnerList("цокольная", baseList,
            listValue, 0, ""),
            new TextAndSpinnerList("карнизов", baseList,
            listValue, 0, ""),
            new TextAndSpinnerList("подъездов", baseList,
            listValue, 0, "")
        });
        enter(sequence);

        //TODO Вынести словарные базы в конфигурацию.
        addCompositeObjectList(sequence, new CompositeObject[]{
            new TextAndSpinnerList("30.Наличие архитектурных элементов: наружных", baseList,
            listValue, 0, ""),
            new TextAndSpinnerList("подъездных", baseList,
            listValue, 0, "")
        });
        enter(sequence);

        //TODO Вынести словарные базы в конфигурацию.
        addCompositeObjectList(sequence, new CompositeObject[]{
            new TextAndSpinnerList("31.Наличие/материал окрытия: парапетов", baseList, listValue, 0, "", listValue, 0, "")
        });
        //TODO Вынести словарные базы в конфигурацию.
        addCompositeObjectList(sequence, new CompositeObject[]{
            new TextAndSpinnerList("поясков(сандриков)", baseList, listValue, 0, "", listValue, 0, "")
        });

        //TODO Уточнить формат входных данных
        addCompositeObjectList(sequence, new CompositeObject[]{
            new TextAndEditTextList("32. Наличие статуса памятника", "", 20, "")
        });

        sequence.AddXMLObject(SDObjectFactory.getText("33.Оборудованность МКД инженерными системами"));

        //TODO переделать во внутренние функции с открытием нового экрана
        addCompositeObjectList(sequence, new CompositeObject[]{
            new TextAndSpinnerList("a)Отопление", baseList, listValue, 0, ""),
            new TextAndSpinnerList("вид топлива", baseList, listValue, 0, ""),
            new TextAndSpinnerList("Кол-во вводов", baseList, listValue, 0, ""),
            new TextAndSpinnerList("объем посребления", baseList, listValue, 0, ""),
            new TextAndSpinnerList("расположение запорных устройств", baseList, listValue, 0, ""),
            new TextAndSpinnerList("схема теплоснабжения", baseList, listValue, 0, ""),
            new TextAndSpinnerList("Наличие циркулярного насоса", baseList, listValue, 0, ""),
            new TextAndSpinnerList("Наличие/вид учета узла", baseList, listValue, 0, "", listValue, 0, ""),
            new TextAndSpinnerList("расположение стояков", baseList, listValue, 0, ""),
            new TextAndSpinnerList("наличие элеваторов", baseList, listValue, 0, ""),
            new TextAndSpinnerList("Вид отопительных приборов", baseList, listValue, 0, ""),
            new TextAndSpinnerList("Вид розлива", baseList, listValue, 0, ""),
            new TextAndSpinnerList("Материал трубопроводов: розлива", baseList, listValue, 0, ""),
            new TextAndSpinnerList("стояков", baseList, listValue, 0, ""),
            new TextAndSpinnerList("Отопление подъедов", baseList, listValue, 0, ""),
            new TextAndSpinnerList("Подача теплоносителей в полотенцесушители", baseList, listValue, 0, "")
        });
        addProsentWithText(sequence, "");
        enter(sequence);

        //TODO переделать во внутренние функции с открытием нового экрана
        addCompositeObjectList(sequence, new CompositeObject[]{
            new TextAndSpinnerList("б)Водоснабжение", baseList, listValue, 0, ""),
            new TextAndSpinnerList("ХВС", baseList, listValue, 0, ""),
            new TextAndSpinnerList("кол-во вводов", baseList, listValue, 0, ""),
            new TextAndSpinnerList("расположение запорных устройств", baseList, listValue, 0, ""),
            new TextAndSpinnerList("расположение стояков", baseList, listValue, 0, ""),
            new TextAndSpinnerList("Наличие/Вид узла учета", baseList, listValue, 0, "", listValue, 0, ""),
            new TextAndSpinnerList("Материал трубопроводов: розлива ХВС", baseList, listValue, 0, ""),
            new TextAndSpinnerList("стояков ХВС", baseList, listValue, 0, ""),
            new TextAndSpinnerList("Наличие повысительного насоса", baseList, listValue, 0, ""),
            new TextAndSpinnerList("Наличие поквартирного учета", baseList, listValue, 0, "")
        });
        addProsentWithText(sequence, "");
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
        addProsentWithText(sequence, "");
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
        addProsentWithText(sequence, "");
        enter(sequence);

        addCompositeObjectList(sequence, new CompositeObject[]{
            new TextAndSpinnerList("г)Газоснабжение", baseList, listValue, 0, ""),
            new TextAndSpinnerList("Кол-во вводов", baseList, listValue, 0, ""),
            new TextAndSpinnerList("Наличие узла учета", baseList, listValue, 0, ""),
            new TextAndSpinnerList("расположение стояков", baseList, listValue, 0, ""),
            new TextAndSpinnerList("Вид газового оборудования (плиты/колонки(котлы))", baseList, listValue, 0, "", listValue, 0, ""),
            new TextAndSpinnerList("Наличие поквартирного учета", baseList, listValue, 0, "")
        });
        addProsentWithText(sequence, "");
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
        addProsentWithText(sequence, "");
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
        addProsentWithText(sequence, "");
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

    private static void addProsentWithText(SDSequenceObject sequenceObject, String text) {
        sequenceObject.AddXMLObject(SDObjectFactory.getText("% износа " + text + " по тех паспарту"));
        sequenceObject.AddXMLObject(SDObjectFactory.getEditText(3));
        sequenceObject.AddXMLObject(SDObjectFactory.getText("изменения внесены"));
        //sequenceObject.AddXMLObject(SDObjectFactory.getDate());
        sequenceObject.AddXMLObject(SDObjectFactory.getText("**Здесь могла быть ваша дата**"));
    }
}
