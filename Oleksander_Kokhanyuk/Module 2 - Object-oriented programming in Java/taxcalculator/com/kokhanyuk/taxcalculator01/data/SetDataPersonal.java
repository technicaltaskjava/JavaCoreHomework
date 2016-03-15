/**
 * SetDataPersonal
 *
 * @author Olexandr Kokhanyuk
 * @version 1.0
 */
package com.kokhanyuk.taxcalculator01.data;

public class SetDataPersonal {

    public static void setExampleData(DataPersonal pers) {
        pers.setName("Ivan");//0
        pers.setSurname("Onopko");//1
        pers.setMainWork(1_200_000);//2
        pers.setAddJob(2500);//3
        pers.setCopyright(1000);//4
        pers.setSaleProperty(120_000);//5
        pers.setGift(150_250);//6
        pers.setTransAbroad(119_500);//7
        pers.setFinAssistanсe(1500);//8
        pers.setChildren(true);//9
    }

    public static boolean setData(DataPersonal pers, int idFieldName, String variable) {
        variable = variable.trim();
        try {
            switch (idFieldName) {
                case 0:
                    pers.setSurname(variable);//0
                    break;
                case 1:
                    pers.setName(variable);//1
                    break;
                case 2:
                    pers.setMainWork(Double.parseDouble(variable));//2
                    break;
                case 3:
                    pers.setAddJob(Double.parseDouble(variable));//3
                    break;
                case 4:
                    pers.setCopyright(Double.parseDouble(variable));//4
                    break;
                case 5:
                    pers.setSaleProperty(Double.parseDouble(variable));//5
                    break;
                case 6:
                    pers.setGift(Double.parseDouble(variable));
                case 7:
                    pers.setTransAbroad(Double.parseDouble(variable));//6
                    break;
                case 8:
                    pers.setFinAssistanсe(Double.parseDouble(variable));//7
                    break;
                case 9:
                    if (variable.equalsIgnoreCase("yes")) {
                        pers.setChildren(true);//8
                    }
                    break;
                default:
                    System.out.println("Error entering data");
                    return false;
            }
        } catch (NumberFormatException id1) {
            System.out.println("Invalid format entered data!");
            return false;
        }
        return true;
    }
}
