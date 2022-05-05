import javax.naming.OperationNotSupportedException;
import java.util.Scanner;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws OperExeption
    {
        System.out.println("Добро пожаловать в программу \"Калькулятор\"");
        String anyText="";
//пока пользователь не введет что-то в строчку, будем просить его об это до посинения
        Scanner myScan = new Scanner(System.in);
        while (!anyText.equals("Выход"))
        {
            System.out.println("Введите математическую операцию с 2 аргументами и одним знаком операции(*,/,-,+) между ними. " +
                    "Либо слово Выход - программа закроется:");

            anyText=myScan.nextLine();
            if (anyText.equals("Выход"))
            {break;}
            System.out.println("Результат: "+calc(anyText.toUpperCase()));
        }
        System.out.println("Всего Вам доброго, до новых встреч!");
        myScan.close();
    }
    public static String calc(String txt) throws OperExeption {
       //убираем пробелы
        txt = txt.replace(" ","");
//        String[]latin={"I","II","III","IV","V","VI","VII","VIII","IX","X"};
//        String[]arabs={"1","2","3","4","5","6","7","8","9","10"};
        String a,b,a_ar,b_ar;
        int a_ind=-1,b_ind=-1,a_int=0,b_int=0;
        int mode=1; //режим - арабский =1, латинский =2
//        String[]rome={"I","V","X"};
        int calc_res = 0;
        String oper_result = "";
        String op;
        //определяем арифм. операцию из *,/,-,+, если нет- выбрасываем исключение
     String txt_i1 = txt.substring(1);//введенный текст, начиная со 2 элемента
      op=oper_def(txt_i1);
    if(op=="")
    {
      throw new OperExeption("Исключение, т.к. строка не является математической операцией");
    }
    String[]text={txt.substring(0,txt.indexOf(op)),txt.substring(txt.indexOf(op)+1)};
    if(text.length!=2)
    {
        throw new OperExeption("Исключение, т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
    }
    a=text[0];
    b=text[1];
    LNum[]nArr = {LNum.I,LNum.II,LNum.III,LNum.IV,LNum.V,LNum.VI,LNum.VII,LNum.VIII,LNum.IX,LNum.X};
    for (int i=0;i<nArr.length;i++)
    {
        if(a.equals(nArr[i].name()))
        {
            a_ind=i;
        }
        if(b.equals(nArr[i].name()))
        {
            b_ind = i;
        }
    }
    //оба числа из арабских цифр
    if(chk_els(a) &&chk_els(b))
    {
        mode = 1;
        a_int=Integer.parseInt(a);
        b_int = Integer.parseInt(b);
//        System.out.println("Все введено верно, продолжаю выполнение программы!(арабские цифры)");
    }
    //оба числа -из римских цифр
    if(a_ind!=-1&&b_ind!=-1)
    {
        mode = 2;
        a_int = nArr[a_ind].ordinal()+1;
        b_int=nArr[b_ind].ordinal()+1;
        if(a_int<=b_int &&op.equals("-"))
        {throw new OperExeption("Исключение, т.к. в римской системе нет отрицательных чисел");}
//        System.out.println("Все введено верно, продолжаю выполнение программы!(римские цифры)");

    }
    //a и b - числа от 1 до 10 (от I до X) включительно - иначе вываливаем исключение и завершаем работу
        if(a_int<1||a_int>10||b_int<1||b_int>10)
        {throw new OperExeption("Исключение, т.к.операнды не входят в допустимый промежуток (от 1 до 10 включительно)");}

    //один операнд состоит из арабских, а другой из римских чисел
    if((chk_els(a) && b_ind!=-1)|(a_ind!=-1 && chk_els(b)) )
    {
        throw new OperExeption("Исключение, т.к. используются одновременно разные системы счисления");
    }
    //(a - араб. и б- не араб) или(a -не араб. и б-  араб)или(a -не араб. и не рим и б-   не араб и не рим)
    if((chk_els(a)&&!chk_els(b))||(!chk_els(a)&&chk_els(b))||(!chk_els(a)&&(a_ind==-1)&&!chk_els(b)&&
            (b_ind==-1)))
        {
            throw new OperExeption("Исключение, т.к. формат математической операции не удовлетворяет заданию " +
                    "- два операнда и один оператор (+, -, /, *)");
        }

switch (op){
    case "/":calc_res=(a_int/b_int);
        break;
    case "*":calc_res=a_int*b_int;
        break;
    case "+":calc_res=a_int+b_int;
        break;
    case "-":calc_res=a_int-b_int;
        break;
};
    if(mode ==2)
    {LNum[] mass = {LNum.I,	LNum.II,	LNum.III,	LNum.IV,	LNum.V,	LNum.VI,	LNum.VII,	LNum.VIII,	LNum.IX,
            LNum.X,	LNum.XI,	LNum.XII,	LNum.XIII,	LNum.XIV,	LNum.XV,	LNum.XVI,	LNum.XVII,	LNum.XVIII,
            LNum.XIX,	LNum.XX,	LNum.XXI,	LNum.XXII,	LNum.XXIII,	LNum.XXIV,	LNum.XXV,	LNum.XXVI,	LNum.XXVII,
            LNum.XXVIII,	LNum.XXIX,	LNum.XXX,	LNum.XXXI,	LNum.XXXII,	LNum.XXXIII,	LNum.XXXIV,	LNum.XXXV,	LNum.XXXVI,
            LNum.XXXVII,	LNum.XXXVIII,	LNum.XXXIX,	LNum.XL,	LNum.XLI,	LNum.XLII,	LNum.XLIII,	LNum.XLIV,	LNum.XLV,
            LNum.XLVI,	LNum.XLVII,	LNum.XLVIII,	LNum.XLIX,	LNum.L,	LNum.LI,	LNum.LII,	LNum.LIII,	LNum.LIV,	LNum.LV,
            LNum.LVI,	LNum.LVII,	LNum.LVIII,	LNum.LIX,	LNum.LX,	LNum.LXI,	LNum.LXII,	LNum.LXIII,	LNum.LXIV,	LNum.LXV,
            LNum.LXVI,	LNum.LXVII,	LNum.LXVIII,	LNum.LXIX,	LNum.LXX,	LNum.LXXI,	LNum.LXXII,	LNum.LXXIII,	LNum.LXXIV,	LNum.LXXV,
            LNum.LXXVI,	LNum.LXXVII,	LNum.LXXVIII,	LNum.LXXIX,	LNum.LXXX,	LNum.LXXXI,	LNum.LXXXII,	LNum.LXXXIII,	LNum.LXXXIV,
            LNum.LXXXV,	LNum.LXXXVI,	LNum.LXXXVII,	LNum.LXXXVIII,	LNum.LXXXIX,	LNum.XC,	LNum.XCI,	LNum.XCII,	LNum.XCIII,	LNum.XCIV,
            LNum.XCV,	LNum.XCVI,	LNum.XCVII,	LNum.XCVIII,	LNum.XCIX,	LNum.C};
    for(LNum el:mass)
    {
        if((el.ordinal()+1)==(calc_res))
        {oper_result= el.name();}

    }
    }
    else
    {
        oper_result = (String.valueOf(calc_res));
    }
    return oper_result;
    }
//ВСПОМОГАТЕЛЬНЫЕ ФУНКЦИИ
    //функция определяет - все ли символы строки - числа арабские
static boolean chk_els(String smtxt)
{
    boolean res_chk = true;
    String[]mas_txt=smtxt.split("");
    try {
        for (String el : mas_txt)
            {
                int i = Integer.parseInt(el);
            }
        }
        catch(Exception ex)
        {
           res_chk= false;
        }
    return res_chk;
}
    //функция, определяющая кол-во опр.элементов(2 аргумент) в заданной строке
    static int numOfOps(String sm_txt, String f)
    {
      return sm_txt.length()-sm_txt.replace(f,"").length();

    }
    //функция, определяющая арифм. операцию
    static String oper_def(String some_txt)
    {
       int min =some_txt.indexOf("-");
       int pl = some_txt.indexOf("+");
       int div = some_txt.indexOf("/");
       int mult = some_txt.indexOf("*");
        int[]arr={min,pl,div,mult};
        Arrays.sort(arr);
        int plc=-2;
        for (int i=0;i<arr.length;i++)
        {
            if(arr[i]!=-1)
            {
            plc=arr[i];
            break;
            }
        }
        if(plc!=-2)
        {return some_txt.substring(plc,plc+1);}
        else {return "";}
    }
//    //функция проверки наличия элементов текстового массива в строке
//  static boolean el_in_mas(String[]ms,String textus)
//  {
//      boolean res = false;
//      for(String st:ms)
//      {
//          if(textus.indexOf(st)>=0)
//          {res=true;
//          break;}
//      }
//      return res;
//  }
//КОНВЕРТЕР РИМСКИХ ЧИСЕЛ В АРАБСКИЕ
//    static String arToLatCnv(String txt)
//    {
//        String res="";
//       switch (txt)
//       {case "I":res="1";
//       break;
//        case "II":res="2";
//            break;
//           case "III":res="3";
//               break;
//           case "IV":res="4";
//               break;
//           case "V":res="5";
//               break;
//           case "VI":res="6";
//               break;
//           case "VII":res="7";
//               break;
//           case "VIII":res="8";
//               break;
//           case "IX":res="9";
//               break;
//           case "X":res="10";
//               break;
//       }
//
//    return res;
//    }
//КОНВЕРТЕР АРАБСКИХ ЧИСЕЛ В РИМСКИЕ и наоборот (найденное число из первого массива конвертируется
// в соответствующее число из второго массива)
//    static String numConv(String[] ms1,String[] ms2,String txt)
//    {
//        String res="";
//        int i;
//
//
//      for(i=0;i<ms1.length-1;i++)
//      {
//          if(ms1[i].equals(txt))
//          {
//              res=ms2[i];
//              break;
//          }
//      }
//      return res;
//    }
}
//ИСКЛЮЧЕНИЯ
class OperExeption extends Exception
{
    OperExeption(String description)
    {super (description);}
}
enum LNum
{
    I,	II,	III,	IV,	V,	VI,	VII,	VIII,	IX,	X,	XI,	XII,	XIII,	XIV,	XV,	XVI,	XVII,	XVIII,	XIX,
    XX,	XXI,	XXII,	XXIII,	XXIV,	XXV,	XXVI,	XXVII,	XXVIII,	XXIX,	XXX,	XXXI,	XXXII,	XXXIII,	XXXIV,
    XXXV,	XXXVI,	XXXVII,	XXXVIII,	XXXIX,	XL,	XLI,	XLII,	XLIII,	XLIV,	XLV,	XLVI,	XLVII,	XLVIII,	XLIX,
    L,	LI,	LII,	LIII,	LIV,	LV,	LVI,	LVII,	LVIII,	LIX,	LX,	LXI,	LXII,	LXIII,	LXIV,	LXV,	LXVI,
    LXVII,	LXVIII,	LXIX,	LXX,	LXXI,	LXXII,	LXXIII,	LXXIV,	LXXV,	LXXVI,	LXXVII,	LXXVIII,	LXXIX,	LXXX,	LXXXI,
    LXXXII,	LXXXIII,	LXXXIV,	LXXXV,	LXXXVI,	LXXXVII,	LXXXVIII,	LXXXIX,	XC,	XCI,	XCII,	XCIII,	XCIV,	XCV,	XCVI,
    XCVII,	XCVIII,	XCIX,	C;
//    int numb;
//    LNum(int num)
//    {numb=num;}
//    int getNum()
//    {return numb;}
}



