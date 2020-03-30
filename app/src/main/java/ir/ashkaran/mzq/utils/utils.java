package ir.ashkaran.mzq.utils;

public class utils {




    public static String number2ClassName(int number) {
        switch (number) {
            case 1 : return  "اول";
            case 2 : return  "دوم";
            case 3 : return  "سوم";
            case 4 : return  "چهارم";
            case 5 : return  "پنجم";
            case 6 : return  "ششم";
            case 7 : return  "هفتم";
            case 8 : return  "هشتم";
            case 9 : return  "نهم";
            case 10 : return  "دهم";
            case 11 : return  "یازدهم";
            case 12 : return  "دوازدهم";
            case 13 : return  "سیزدهم";
        }

        return "" + number ;
    }



    public static String number2ClassName(String num) {

        switch (num) {
            case "1" : return  "اول";
            case "2" : return  "دوم";
            case "3" : return  "سوم";
            case "4" : return  "چهارم";
            case "5" : return  "پنجم";
            case "6" : return  "ششم";
            case "7" : return  "هفتم";
            case "8" : return  "هشتم";
            case "9" : return  "نهم";
            case "10" : return  "دهم";
            case "11" : return  "یازدهم";
            case "12" : return  "دوازدهم";
            case "13" : return  "سیزدهم";
        }

        return "" + num ;
    }



    public static String numberAsLevel(int number) {
        switch (number) {
            case 1 : return  "اول";
            case 2 : return  "دوم";
            case 3 : return  "سوم";
            case 4 : return  "چهارم";
            case 5 : return  "پنجم";
            case 6 : return  "ششم";
            case 7 : return  "هفتم";
            case 8 : return  "هشتم";
            case 9 : return  "نهم";
            case 10 : return "دهم";
            case 11: return  "یازدهم";
            case 12: return  "دوازدهم";
            case 13: return  "سیزدهم";
            case 14: return  "چهاردهم";
            case 15: return  "پانزدهم";
            case 16: return  "شانزدهم";
            case 17: return  "هفدهم";
            case 18: return  "هجدهم";
            case 19: return  "نوزدهم";
            case 20: return  "بیستم";
            case 21: return  "بیست و یکم";
            case 22: return  "بیست و دوم";
            case 23: return  "بیست و سوم";
            case 24: return  "بیست و چهارم";
            case 25: return  "بیست و پنجم";
            case 26: return  "بیست و ششم";
            case 27: return  "بیست و هفتم";
            case 28: return  "بیست و هشتم";
            case 29: return  "بیست و نهم";
            case 30: return  "سی ام";
            case 31: return  "سی و یکم";
            case 32: return  "سی و دوم";
            case 33: return  "سی و سوم";
            case 34: return  "سی و چهارم";
            case 35: return  "سی و پنجم";
            case 36: return  "سی و ششم";
            case 37: return  "سی و هفتم";
            case 38: return  "سی و هشتم";
            case 39: return  "سی و نهم";
            case 40: return  "چهلم";
            case 41: return  "چهل و یکم";
            case 42: return  "چهل و دوم";
            case 43: return  "چهل و سوم";
            case 44: return  "چهل و چهارم";
            case 45: return  "چهل و پنجم";
            case 46: return  "چهل و ششم";
            case 47: return  "چهل و هفتم";
            case 48: return  "چهل و هشتم";
            case 49: return  "چهل و نهم";
            case 50: return  "پنجاه";
        }

        return "بالاتر از پنجاه" ;
    }







    public static String getMahAsString(int mah) {
        switch (mah) {
            case 1 : return  "حَمَل";
            case 2 : return  "ثور";
            case 3 : return  "جَوزا";
            case 4 : return  "سَرَطان";
            case 5 : return  "اَسَد";
            case 6 : return  "سُنبُله";
            case 7 : return  "مِیزان";
            case 8 : return  "عَقرَب";
            case 9 : return  "قَوس";
            case 10 : return  "جَدْی";
            case 11 : return  "دَلو";
            case 12 : return  "حوت";
        }

        return "" ;
    }



    public static String getDayName(int day) {
        switch (day) {
            case 1 : return  "شنبه";
            case 2 : return  "یک شنبه";
            case 3 : return  "دوشنبه";
            case 4 : return  "سه شنبه";
            case 5 : return  "چهار شنبه";
            case 6 : return  "پنج شنبه";
        }

        return "جمعه" ;
    }


    public static String getPersonAsString(int person) {
        switch (person) {
            case 1 : return  "teacher";
            case 2 : return  "student";
            case 3 : return  "parent";
            case 4 : return  "teacher";
            case 5 : return  "teacher";
            case 6 : return  "teacher";
        }


        return "student";
    }





}
