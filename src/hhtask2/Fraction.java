package hhtask2;

import java.util.Vector;
import static java.lang.Integer.parseInt;

/**
 * основной рабочий класс
 * содержит запись дроби и базу системы счисления
 */
public class Fraction {

    /**
     * числитель дроби
     */
    private String numerator;

    /**
     * знаменатель дроби
     */
    private String denominator;

    /**
     * база системы счисления дроби
     */
    private int base;

    /**
     * формирует обьект класса
     * проверяет входные данные на корректность
     * @param _num числитель
     * @param _denom знаменатель
     * @param _base база
     * @throws InstantiationError в случае если входные данные не позволяют сформировать обьект
     */
    public Fraction(String _num, String _denom, int _base) throws InstantiationError{
        if (isValid(_num, _denom, _base)) {
            numerator = _num;
            denominator = _denom;
            base = _base;
        }else{
            throw new IllegalArgumentException("Each argument must be a valid number");
        }
    }

    /**
     * проверяет на корректность данные, передаваемые для создания обьекта
     * максимальная база системы счисления - не более 36 (константа Character.MAX_RADIX)
     * не принимаются значения числителя или знаменателя, равные 0
     * @param _num числитель
     * @param _denom знаменатель
     * @param _base база
     * @return true/false
     */
    public static boolean isValid(String _num, String _denom, int _base){
        String reg = "^[1-9a-z][a-z0-9]*";
        if (!_num.matches(reg) || !_denom.matches(reg) || !(_base <= Character.MAX_RADIX))
            return false;
        return true;
    }

    /**
     * основной метод, реализующий школьный алгоритм деления в столбик
     * период дроби, если он возникает находится путем сравнения получаемого остатка на каждом шаге деления
     * с уже полученными ранее остатками (остатки находятся в векторе mods)
     * вызывает методы
     *  - divMod() - получение остатка в с.с с основанием base
     *  - divInt() - целочисленное деление в с.с с основанием base
     * @return строку результата
     */
    public String getPeriod(){
        String period = "";
        Vector<String> mods = new Vector<String>();
        int startPosition = -1;
        String mod = divMod(numerator, denominator);
        while (parseInt(mod, base) != 0) {
            if (mods.contains(mod)) {
                startPosition = mods.lastIndexOf(mod);
                break;
            }
            mods.add(mod);
            mod = mod.concat("0");
            period += divInt(mod, denominator);
            mod = divMod(mod, denominator);
        }
        String part = divInt(numerator, denominator);
        if (parseInt(mod, base) != 0){
            return part + "." + period.substring(0,startPosition) +
                    "(" + period.substring(startPosition) + ")";
        }
        if (period.isEmpty()) return part;
        return part + "." + period;
    }

    /**
     * вычисляет отстаток от деления a/b в с.с base
     * используется перевод в 1-чную с.с, поллучение остатка
     * и перевод результата обратно в с.с base
     * @param a делимое
     * @param b делитель
     * @return строка с остатком от деления a/b в с.с base
     */
    private String divMod(String a, String b){
        int a10 = parseInt(a, base);
        int b10 = parseInt(b, base);
        int result10 = a10 % b10;
        return Integer.toString(result10, base);
    }

    /**
     * выполняет целочисленное деление a/b в с.с base
     * используется перевод в 1-чную с.с, вычисление результата деления
     * и перевод обратно в с.с base
     * @param a делимое
     * @param b делитель
     * @return строка с частным от деления a/b в с.с base
     */
    private String divInt(String a, String b){
        int a10 = parseInt(a, base);
        int b10 = parseInt(b, base);
        int result10 = a10 / b10;
        return Integer.toString(result10, base);
    }
}
