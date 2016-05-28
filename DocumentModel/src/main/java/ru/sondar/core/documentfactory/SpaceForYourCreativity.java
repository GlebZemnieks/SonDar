package ru.sondar.core.documentfactory;

import ru.sondar.core.documentfactory.structure.CompositeObject;
import ru.sondar.core.documentfactory.structure.SDStructureFactory;
import static ru.sondar.core.documentfactory.structure.SDStructureFactory.addCompositeObjectList;
import static ru.sondar.core.documentfactory.structure.SDStructureFactory.enter;
import ru.sondar.core.documentfactory.structure.TextAndEditTextList;
import ru.sondar.core.documentfactory.structure.TextAndSpinnerList;
import ru.sondar.core.documentmodel.*;
import ru.sondar.core.filemodule.FileModuleWriteThreadInterface;
import ru.sondar.core.filemodule.pc.FileModuleWriteThread;
import ru.sondar.core.objectmodel.*;

/**
 *
 * @author GlebZemnieks
 */
public class SpaceForYourCreativity {

    public static String pathToFileCreate = "E:\\test.txt";

    public static void main(String[] args) {
        SDDocument document = new SDDocument();
        //head
        SDHeadPart head = SDObjectFactory.getHeadPart("69f08553-bf07-405f-b849-0bea1423ed1c");
        document.setHeadPart(head);
        //sequence
        SDSequenceObject sequence = new SDSequenceObject();
        String[] listValue = new String[]{"1", "2", "3"};
        /*
         Part.1
         */
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
            new TextAndSpinnerList("Категория жилищного фонда",
            new String[]{"Частный", "Муниципальный", "Областной",
                "Федеральный", "Смешанный"}, 1, "")
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
            new TextAndSpinnerList("2.Этажность",
            new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9"}, 0, "")
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
            new TextAndSpinnerList("8. Конструкция/Материал фундамента",
            new String[]{"Ленточный", "Столбчатый", "Блочный"}, 0, "",
            new String[]{"Деревянный", "Бутовый", "Железо-бетонный"}, 0, "")
        });
        enter(sequence);
        addProsentWithText(sequence, "");
        enter(sequence);

        //TODO Вынести словарные базы в конфигурацию.
        addCompositeObjectList(sequence, new CompositeObject[]{
            new TextAndSpinnerList("9. Конструкция/Материал стен",
            new String[]{"Каркасная", "Панельная", "Обложенная кирпичем",
                "С вентилируемым фасадом"}, 0, "",
            new String[]{"Кирпич", "Дерево", "Бетон", "Шлако-бетон"}, 0, "")
        });
        enter(sequence);
        addProsentWithText(sequence, "фасада");
        enter(sequence);

        //TODO Вынести словарные базы в конфигурацию.
        addCompositeObjectList(sequence, new CompositeObject[]{
            new TextAndSpinnerList("10. Конструкция/Материал кровли",
            new String[]{"Двух-скатная", "Скатная-сложная", "Совмещенная"}, 0, "",
            new String[]{"Наплавляемый", "Осбесто-цементный волнистый",
                "Осбесто-цементный плоский", "Металочерепица", "Проф-настил"}, 0, "")
        });
        enter(sequence);
        addProsentWithText(sequence, "кровли");
        enter(sequence);

        //TODO Вынести словарные базы в конфигурацию.
        addCompositeObjectList(sequence, new CompositeObject[]{
            new TextAndSpinnerList("11. Конструкция/Материал водостока",
            listValue, 0, "")
        });
        enter(sequence);

        //TODO Вынести словарные базы в конфигурацию.
        addCompositeObjectList(sequence, new CompositeObject[]{
            new TextAndSpinnerList("12. Конструкция/Материал цокольного перекрытия",
            listValue, 0, "", listValue, 0, "")
        });
        enter(sequence);

        //TODO Вынести словарные базы в конфигурацию.
        addCompositeObjectList(sequence, new CompositeObject[]{
            new TextAndSpinnerList("13. Конструкция/Материал межэтажного перекрытия",
            listValue, 0, "", listValue, 0, "")
        });
        enter(sequence);

        //TODO Вынести словарные базы в конфигурацию.
        addCompositeObjectList(sequence, new CompositeObject[]{
            new TextAndSpinnerList("14. Конструкция/Материал чердачного перекрытия",
            listValue, 0, "", listValue, 0, "")
        });
        enter(sequence);

        //TODO Вынести словарные базы в конфигурацию.
        addCompositeObjectList(sequence, new CompositeObject[]{
            new TextAndSpinnerList("15. Материал утепляющего слоя",
            listValue, 0, "")
        });
        enter(sequence);

        //TODO Вынести словарные базы в конфигурацию.
        addCompositeObjectList(sequence, new CompositeObject[]{
            new TextAndSpinnerList("16. Наличие/Характеристия чердака(тех.этажа)",
            listValue, 0, "", listValue, 0, "")
        });
        enter(sequence);

        //TODO Вынести словарные базы в конфигурацию.
        addCompositeObjectList(sequence, new CompositeObject[]{
            new TextAndSpinnerList("17. Наличие/Характеристия цокольный(подвальных) помещений",
            listValue, 0, "", listValue, 0, "")
        });
        enter(sequence);
        addProsentWithText(sequence, "подвала");
        enter(sequence);

        //TODO Вынести словарные базы в конфигурацию.
        addCompositeObjectList(sequence, new CompositeObject[]{
            new TextAndSpinnerList("18. Наличи дренажной системы",
            listValue, 0, "")
        });
        enter(sequence);

        //TODO Вынести словарные базы в конфигурацию.
        addCompositeObjectList(sequence, new CompositeObject[]{
            new TextAndSpinnerList("19. Наличие дренажных насосов",
            listValue, 0, "")
        });
        enter(sequence);

        //TODO Вынести словарные базы в конфигурацию.
        addCompositeObjectList(sequence, new CompositeObject[]{
            new TextAndSpinnerList("20. Материал/Конструкция полов мест общего пользования первого этажа",
            listValue, 0, "", listValue, 0, "")
        });
        enter(sequence);

        //TODO Вынести словарные базы в конфигурацию.
        addCompositeObjectList(sequence, new CompositeObject[]{
            new TextAndSpinnerList("21. Материал/Конструкция полов мест общего пользования других этажей",
            listValue, 0, "", listValue, 0, "")
        });
        enter(sequence);

        //TODO Вынести словарные базы в конфигурацию.
        addCompositeObjectList(sequence, new CompositeObject[]{
            new TextAndSpinnerList("22. Материал/Конструкция лестниц мест общего пользования",
            listValue, 0, "", listValue, 0, "")
        });
        enter(sequence);

        //TODO Вынести словарные базы в конфигурацию.
        //TODO Refactoring using internalFunction.OpenNewDocument()
        addCompositeObjectList(sequence, new CompositeObject[]{
            new TextAndSpinnerList("23. Балконы(Лоджии)",
            listValue, 0, ""),
            new TextAndSpinnerList("конструкция оснований",
            listValue, 0, ""),
            new TextAndSpinnerList("ограждение",
            listValue, 0, ""),
            new TextAndSpinnerList("остекление",
            listValue, 0, ""),
            new TextAndSpinnerList("козырьки",
            listValue, 0, "")
        });
        enter(sequence);

        //TODO Вынести словарные базы в конфигурацию.
        addCompositeObjectList(sequence, new CompositeObject[]{
            new TextAndSpinnerList("24.Входные площадки",
            listValue, 0, ""),
            new TextAndSpinnerList("Надподъездные козырьки",
            listValue, 0, "")
        });
        enter(sequence);

        //TODO Вынести словарные базы в конфигурацию.
        addCompositeObjectList(sequence, new CompositeObject[]{
            new TextAndSpinnerList("25.Материал/Конструкция окон мест общего пользования",
            listValue, 0, "", listValue, 0, "")
        });
        enter(sequence);

        //TODO Вынести словарные базы в конфигурацию.
        addCompositeObjectList(sequence, new CompositeObject[]{
            new TextAndSpinnerList("26.Материал/Конструкция входных дверей мест общего пользования",
            listValue, 0, "", listValue, 0, "")
        });
        enter(sequence);

        //TODO Вынести словарные базы в конфигурацию.
        addCompositeObjectList(sequence, new CompositeObject[]{
            new TextAndSpinnerList("27. Наличие входного тамбура", listValue, 0, "")
        });
        enter(sequence);

        //TODO Вынести словарные базы в конфигурацию.
        addCompositeObjectList(sequence, new CompositeObject[]{
            new TextAndSpinnerList("28. Оборудованность для доступа инвалидов", listValue, 0, "")
        });
        enter(sequence);

        //TODO Вынести словарные базы в конфигурацию.
        addCompositeObjectList(sequence, new CompositeObject[]{
            new TextAndSpinnerList("29. Отделка наружняя",
            listValue, 0, ""),
            new TextAndSpinnerList("цокольная",
            listValue, 0, ""),
            new TextAndSpinnerList("карнизов",
            listValue, 0, ""),
            new TextAndSpinnerList("подъездов",
            listValue, 0, "")
        });
        enter(sequence);

        //TODO Вынести словарные базы в конфигурацию.
        addCompositeObjectList(sequence, new CompositeObject[]{
            new TextAndSpinnerList("30.Наличие архитектурных элементов: наружных",
            listValue, 0, ""),
            new TextAndSpinnerList("подъездных",
            listValue, 0, "")
        });
        enter(sequence);

        //TODO Вынести словарные базы в конфигурацию.
        addCompositeObjectList(sequence, new CompositeObject[]{
            new TextAndSpinnerList("31.Наличие/материал окрытия: парапетов", listValue, 0, "", listValue, 0, "")
        });
        //TODO Вынести словарные базы в конфигурацию.
        addCompositeObjectList(sequence, new CompositeObject[]{
            new TextAndSpinnerList("поясков(сандриков)", listValue, 0, "", listValue, 0, "")
        });

        //TODO Уточнить формат входных данных
        addCompositeObjectList(sequence, new CompositeObject[]{
            new TextAndEditTextList("32. Наличие статуса памятника", "", 20, "")
        });

        sequence.AddXMLObject(SDObjectFactory.getText("33.Оборудованность МКД инженерными системами"));

        //TODO переделать во внутренние функции с открытием нового экрана
        addCompositeObjectList(sequence, new CompositeObject[]{
            new TextAndSpinnerList("a)Отопление", listValue, 0, ""),
            new TextAndSpinnerList("вид топлива", listValue, 0, ""),
            new TextAndSpinnerList("Кол-во вводов", listValue, 0, ""),
            new TextAndSpinnerList("объем посребления", listValue, 0, ""),
            new TextAndSpinnerList("расположение запорных устройств", listValue, 0, ""),
            new TextAndSpinnerList("схема теплоснабжения", listValue, 0, ""),
            new TextAndSpinnerList("Наличие циркулярного насоса", listValue, 0, ""),
            new TextAndSpinnerList("Наличие/вид учета узла", listValue, 0, "", listValue, 0, ""),
            new TextAndSpinnerList("расположение стояков", listValue, 0, ""),
            new TextAndSpinnerList("наличие элеваторов", listValue, 0, ""),
            new TextAndSpinnerList("Вид отопительных приборов", listValue, 0, ""),
            new TextAndSpinnerList("Вид розлива", listValue, 0, ""),
            new TextAndSpinnerList("Материал трубопроводов: розлива", listValue, 0, ""),
            new TextAndSpinnerList("стояков", listValue, 0, ""),
            new TextAndSpinnerList("Отопление подъедов", listValue, 0, ""),
            new TextAndSpinnerList("Подача теплоносителей в полотенцесушители", listValue, 0, "")
        });
        addProsentWithText(sequence, "");
        enter(sequence);

        //TODO переделать во внутренние функции с открытием нового экрана
        addCompositeObjectList(sequence, new CompositeObject[]{
            new TextAndSpinnerList("б)Водоснабжение", listValue, 0, ""),
            new TextAndSpinnerList("ХВС", listValue, 0, ""),
            new TextAndSpinnerList("кол-во вводов", listValue, 0, ""),
            new TextAndSpinnerList("расположение запорных устройств", listValue, 0, ""),
            new TextAndSpinnerList("расположение стояков", listValue, 0, ""),
            new TextAndSpinnerList("Наличие/Вид узла учета", listValue, 0, "", listValue, 0, ""),
            new TextAndSpinnerList("Материал трубопроводов: розлива ХВС", listValue, 0, ""),
            new TextAndSpinnerList("стояков ХВС", listValue, 0, ""),
            new TextAndSpinnerList("Наличие повысительного насоса", listValue, 0, ""),
            new TextAndSpinnerList("Наличие поквартирного учета", listValue, 0, "")
        });
        addProsentWithText(sequence, "");
        enter(sequence);

        addCompositeObjectList(sequence, new CompositeObject[]{
            new TextAndSpinnerList("ГВС", listValue, 0, ""),
            new TextAndSpinnerList(" Кол-во вводов", listValue, 0, ""),
            new TextAndSpinnerList("Схема ГВС", listValue, 0, ""),
            new TextAndSpinnerList("Наличие/Вид узла учета", listValue, 0, "", listValue, 0, ""),
            new TextAndSpinnerList("расположение запорных устройств", listValue, 0, ""),
            new TextAndSpinnerList("расположение стояков", listValue, 0, ""),
            new TextAndSpinnerList("Наличие бойлеров", listValue, 0, ""),
            new TextAndSpinnerList("Наличие/вид элеваторов", listValue, 0, "", listValue, 0, ""),
            new TextAndSpinnerList("Материал трубопроводов: розлива ГВС", listValue, 0, ""),
            new TextAndSpinnerList("стояков ГВС", listValue, 0, ""),
            new TextAndSpinnerList("Наличие повысительного насоса", listValue, 0, ""),
            new TextAndSpinnerList("Наличие поквартирного учета", listValue, 0, ""),
            new TextAndSpinnerList("Наличие пожарного водопровода", listValue, 0, "")
        });
        addProsentWithText(sequence, "");
        enter(sequence);

        addCompositeObjectList(sequence, new CompositeObject[]{
            new TextAndSpinnerList("в)Водоотведение", listValue, 0, ""),
            new TextAndSpinnerList("кол-во выпусков", listValue, 0, ""),
            new TextAndSpinnerList("расположение стояков", listValue, 0, ""),
            new TextAndSpinnerList("наличие фекальных стояков", listValue, 0, ""),
            new TextAndSpinnerList("разделение кухонных и фекальных стояков", listValue, 0, ""),
            new TextAndSpinnerList("материал трубопроводов водоотведение: стояков", listValue, 0, ""),
            new TextAndSpinnerList("лежаков", listValue, 0, "")
        });
        addProsentWithText(sequence, "");
        enter(sequence);

        addCompositeObjectList(sequence, new CompositeObject[]{
            new TextAndSpinnerList("г)Газоснабжение", listValue, 0, ""),
            new TextAndSpinnerList("Кол-во вводов", listValue, 0, ""),
            new TextAndSpinnerList("Наличие узла учета", listValue, 0, ""),
            new TextAndSpinnerList("расположение стояков", listValue, 0, ""),
            new TextAndSpinnerList("Вид газового оборудования (плиты/колонки(котлы))", listValue, 0, "", listValue, 0, ""),
            new TextAndSpinnerList("Наличие поквартирного учета", listValue, 0, "")
        });
        addProsentWithText(sequence, "");
        enter(sequence);

        addCompositeObjectList(sequence, new CompositeObject[]{
            new TextAndSpinnerList("д)вентиляция", listValue, 0, ""),
            new TextAndSpinnerList("расположение/материал вентканалов", listValue, 0, "", listValue, 0, "")
        });
        enter(sequence);

        addCompositeObjectList(sequence, new CompositeObject[]{
            new TextAndSpinnerList("е)Наличие мусоропроводов/число стволов", listValue, 0, "", listValue, 0, "")
        });
        enter(sequence);

        addCompositeObjectList(sequence, new CompositeObject[]{
            new TextAndSpinnerList("ж)Наличие ливтов пасажирских/грузовых", listValue, 0, "", listValue, 0, "")
        });
        addProsentWithText(sequence, "");
        enter(sequence);

        addCompositeObjectList(sequence, new CompositeObject[]{
            new TextAndSpinnerList("з)Электроснабжение", listValue, 0, ""),
            new TextAndSpinnerList("Входное напряжение/кол-ва фаз", listValue, 0, "", listValue, 0, ""),
            new TextAndSpinnerList("Кол-во / Вид вводов", listValue, 0, "", listValue, 0, ""),
            new TextAndSpinnerList("Наличие/расположение ВРУ", listValue, 0, "", listValue, 0, ""),
            new TextAndSpinnerList("Наличие прибора учета", listValue, 0, ""),
            new TextAndSpinnerList("Прокладка/кол-во фаз/расположение стояков", listValue, 0, "", listValue, 0, "", listValue, 0, ""),
            new TextAndSpinnerList("Наличие заземлений", listValue, 0, ""),
            new TextAndSpinnerList("Наличие СУП", listValue, 0, ""),
            new TextAndSpinnerList("Наличие подвального освещения", listValue, 0, "", listValue, 0, ""),
            new TextAndSpinnerList("Наличие чердачного освежения", listValue, 0, "", listValue, 0, ""),
            new TextAndSpinnerList("Наличие придомового освещения", listValue, 0, ""),
            new TextAndSpinnerList("Наличие автоматики на освещении", listValue, 0, ""),
            new TextAndSpinnerList("Вид применяемых ламп", listValue, 0, ""),
            new TextAndSpinnerList("Наличие/расположение поквартирного учета", listValue, 0, "", listValue, 0, ""),
            new TextAndSpinnerList("Наличие автоматики на силовой проводке", listValue, 0, ""),
            new TextAndSpinnerList("Наличие системы дымоудаления", listValue, 0, ""),
            new TextAndSpinnerList("Электроснабжение насоса/приводов задвижек пожарного водопровода",
            listValue, 0, "", listValue, 0, ""),
            new TextAndSpinnerList("Наличие/способ электроснабжения электрических плит", listValue, 0, "", listValue, 0, "")
        });
        addProsentWithText(sequence, "");
        enter(sequence);

        addCompositeObjectList(sequence, new CompositeObject[]{
            new TextAndSpinnerList("и)Наличие коммуникационных сетей и сигнализаций", listValue, 0, ""),
            new TextAndSpinnerList("ОДС", listValue, 0, ""),
            new TextAndSpinnerList("Пожарная сигнализация", listValue, 0, ""),
            new TextAndSpinnerList("радио", listValue, 0, ""),
            new TextAndSpinnerList("телефон", listValue, 0, ""),
            new TextAndSpinnerList("ТВ_антенна", listValue, 0, ""),
            new TextAndSpinnerList("Кабельное ТВ", listValue, 0, "", listValue, 0, "", listValue, 0, ""),
            new TextAndSpinnerList("Домофон", listValue, 0, ""),
            new TextAndSpinnerList("КИП", listValue, 0, ""),
            new TextAndSpinnerList("Интернет провайдеров", listValue, 0, "", listValue, 0, "", listValue, 0, "", listValue, 0, "")
        });
        SDStructureFactory.enter(sequence);

        addCompositeObjectList(sequence, new CompositeObject[]{
            new TextAndSpinnerList("к)Элементы блгоустройства", listValue, 0, ""),
            new TextAndSpinnerList("Наличие сформированного земельного участка", listValue, 0, ""),
            new TextAndSpinnerList("Кадастровый номер", listValue, 0, ""),
            new TextAndSpinnerList("Площать привомового жилищного участка", listValue, 0, ""),
            new TextAndSpinnerList("Наличие надворных построек: туалеты", listValue, 0, ""),
            new TextAndSpinnerList("помойницы", listValue, 0, ""),
            new TextAndSpinnerList("сараи", listValue, 0, ""),
            new TextAndSpinnerList("площадки для ТБО", listValue, 0, ""),
            new TextAndSpinnerList("Строения: для инженерного оборудования", listValue, 0, ""),
            new TextAndSpinnerList("для хранения топлива", listValue, 0, ""),
            new TextAndSpinnerList("Наличие привомового проезда/парковки", listValue, 0, "", listValue, 0, ""),
            new TextAndSpinnerList("пешеходных дорожек", listValue, 0, ""),
            new TextAndSpinnerList("Уборочная часть", listValue, 0, ""),
            new TextAndSpinnerList("Наличие площадок:хозяйственной", listValue, 0, ""),
            new TextAndSpinnerList("спортивной", listValue, 0, ""),
            new TextAndSpinnerList("детской", listValue, 0, ""),
            new TextAndSpinnerList("для отдыха", listValue, 0, ""),
            new TextAndSpinnerList("Число малых форм", listValue, 0, "")
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
        document.setSequence(sequence);
        //log 
        SDLogPart log = new SDLogPart();
        document.setLogPart(log);
        FileModuleWriteThreadInterface fileModule = new FileModuleWriteThread(pathToFileCreate);
        document.saveDocument(fileModule);
        fileModule.close();
    }

    private static void addProsentWithText(SDSequenceObject sequenceObject, String text) {
        sequenceObject.AddXMLObject(SDObjectFactory.getText("% износа " + text + " по тех паспарту"));
        sequenceObject.AddXMLObject(SDObjectFactory.getEditText(3));
        sequenceObject.AddXMLObject(SDObjectFactory.getText("изменения внесены"));
        //sequenceObject.AddXMLObject(SDObjectFactory.getDate());
        sequenceObject.AddXMLObject(SDObjectFactory.getText("**Здесь могла быть ваша дата**"));
    }
}
