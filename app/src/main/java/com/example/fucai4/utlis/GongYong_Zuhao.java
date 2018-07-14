package com.example.fucai4.utlis;

import android.util.Log;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 42224 on 2018/4/19.
 */

public class GongYong_Zuhao {

    public static List<List<String>> CalcQima(String code) {
        List<List<String>> list0 = new ArrayList<>();
        //计算胆码用
        List<String> list = new ArrayList<>();
        //前台显示用
        List<String> list2 = new ArrayList<>();

        String firstNumber = code.substring(0, 1);
        String secondNumber = code.substring(1, 1);
        String thirdNumber = code.substring(2, 1);

        int i = Integer.parseInt(firstNumber) + Integer.parseInt(secondNumber) + Integer.parseInt(thirdNumber);

        switch (i) {
            case 0:
            case 10:
            case 20:
                list.add("567");
                list.add("089");
                list.add("234");

                list2.add("0123489");
                list2.add("1234567");
                list2.add("0156789");

                list2.add("179 / 179");
                break;
            case 1:
            case 11:
            case 21:
                list.add("274");
                list.add("068");
                list.add("139");

                list2.add("0135689");
                list2.add("1234579");
                list2.add("0245678");

                list2.add("369 / 369");
                break;
            case 2:
            case 12:
            case 22:
                list.add("587");
                list.add("039");
                list.add("124");

                list2.add("0123469");
                list2.add("1245678");
                list2.add("0356789");

                list2.add("459 / 459");
                break;
            case 3:
            case 13:
            case 23:
                list.add("536");
                list.add("078");
                list.add("912");

                list2.add("0124789");
                list2.add("1234569");
                list2.add("0345678");

                list2.add("369 / 369");
                break;
            case 4:
            case 14:
            case 24:
                list.add("245");
                list.add("038");
                list.add("169");

                list2.add("0136789");
                list2.add("1245679");
                list2.add("0234578");


                list2.add("259 / 259");
                break;
            case 5:
            case 15:
            case 25:
                list.add("248");
                list.add("069");
                list.add("157");

                list2.add("0135679");
                list2.add("1234578");
                list2.add("0234689");

                list2.add("459 / 459");
                break;
            case 6:
            case 16:
            case 26:
                list.add("435");
                list.add("028");
                list.add("197");

                list2.add("0126789");
                list2.add("1345679");
                list2.add("0234568");

                list2.add("368 / 368");
                break;
            case 7:
            case 17:
            case 27:
                list.add("369");
                list.add("714");
                list.add("285");

                list2.add("0124578");
                list2.add("0235689");
                list2.add("0134679");

                list2.add("258 / 258");
                break;
            case 8:
            case 18:
                list.add("298");
                list.add("456");
                list.add("701");

                list2.add("0134567");
                list2.add("0123789");
                list2.add("2345689");

                list2.add("458 / 458");
                break;
            case 9:
            case 19:
                list.add("897");
                list.add("012");
                list.add("356");

                list2.add("0123456");
                list2.add("3456789");
                list2.add("0124789");


                list2.add("457 / 457");
                break;
        }
        list0.add(list);
        list0.add(list2);
        return list0;
    }

    /**
     * code 是输入的上期号码
     * t 为true就为 福彩，false 就为 体彩
     */

    public static List<List<String>> CalcBama(String code, int i, Boolean t) {
        List<List<String>> list0 = new ArrayList<>();
        //计算胆码用
        List<String> list = new ArrayList<>();

        //前台显示用
        List<String> list2 = new ArrayList<String>();
        List<String> list3 = new ArrayList<String>();
        //前台显示用

        String firstNumber = code.substring(0, 1);
        String secondNumber = code.substring(1, 2);
        String thirdNumber = code.substring(2, 3);
        Log.e("number-----", firstNumber + "---" + secondNumber + "---" + thirdNumber);

        i = Integer.parseInt(firstNumber) + Integer.parseInt(secondNumber) + Integer.parseInt(thirdNumber);

        String tempStr = i + "";
        i = Integer.parseInt(tempStr.substring(tempStr.length() - 1));

        switch (i) {
            case 0:
            case 10:
            case 20:
                list.add("56");
                list.add("01");

                if (t) {
                    list2.add("23456789");
                    list2.add("01234789");
                } else {
                    list2.add("0123489");
                    list2.add("1234567");
                    list2.add("0156789");
                }


//                list2.add("0145=12 / 2346=12");

                list3.add("(78-23）（285-369-147）胆278");
                break;
            case 1:
            case 11:
            case 21:
                list.add("57");
                list.add("02");

                if (t) {
                    list2.add("13456789");
                    list2.add("01234689");
                } else {

                    list2.add("0135689");
                    list2.add("1234579");
                    list2.add("0245678");
                }


//                list2.add("1256=12 / 3457=12");
                list3.add("(58-03)(587-039-124)胆078");
                break;
            case 2:
            case 12:
            case 22:
                list.add("58");
                list.add("03");

                if (t) {
                    list2.add("12456789");
                    list2.add("01234679");
                } else {
                    list2.add("1245678");
                    list2.add("0356789");
                    list2.add("0123469");
                }

//                list2.add("2367=12 / 4567=12");
                list3.add("(69-14)(197-345-028)胆489");
                break;
            case 3:
            case 13:
            case 23:
                list.add("59");
                list.add("04");

                if (t) {
                    list2.add("12356789");
                    list2.add("01234678");
                } else {
                    list2.add("0124789");
                    list2.add("0345678");
                    list2.add("1234569");
                }

//                list2.add("3478=12 / 1567=12");
                list3.add("（57-02）（274-068-139）胆027");
                break;
            case 4:
            case 14:
            case 24:
                list.add("67");
                list.add("12");
                if (t) {
                    list2.add("03456789");
                    list2.add("01234589");
                } else {

                    list2.add("0136789");
                    list2.add("1245679");
                    list2.add("0234578");
                }


//                list2.add("4589=12 / 1678=12");
                list3.add("（79-24）（298-456-017）胆469");
                break;
            case 5:
            case 15:
            case 25:
                list.add("68");
                list.add("13");
                if (t) {
                    list2.add("02456789");
                    list2.add("01234579");
                } else {
                    list2.add("0135679");
                    list2.add("0234689");
                    list2.add("1234578");
                }

//                list2.add("0569=12 / 1789=12");
                list3.add("（89-34）（897-356-012）胆389");
                break;
            case 6:
            case 16:
            case 26:
                list.add("69");
                list.add("14");
                if (t) {
                    list2.add("02356789");
                    list2.add("01234578");
                } else {
                    list2.add("0126789");
                    list2.add("0234568");
                    list2.add("1345679");
                }


//                list2.add("0167=12 / 0289=12");
                list3.add("（67-12）（169-245-038）胆267");
                break;
            case 7:
            case 17:
            case 27:
                list.add("78");
                list.add("23");
                if (t) {
                    list2.add("01456789");
                    list2.add("01234569");
                } else {

                    list2.add("0124578");
                    list2.add("0134679");
                    list2.add("0235689");
                }


//                list2.add("1278=12 / 0139=12");
                list3.add("（68-13）（609-824-175）胆 168");
                break;
            case 8:
            case 18:
                list.add("79");
                list.add("24");
                if (t) {
                    list2.add("01356789");
                    list2.add("01234568");
                } else {
                    list2.add("0123789");
                    list2.add("0134567");
                    list2.add("2345689");
                }


//                list2.add("2389=12 / 0124=12");
                list3.add("（56-01）（567-089-234）胆056");
                break;
            case 9:
            case 19:
                list.add("89");
                list.add("34");
                if (t) {
                    list2.add("01256789");
                    list2.add("01234567");
                } else {
                    list2.add("3456789");
                    list2.add("0124789");
                    list2.add("0123456");
                }

//                list2.add("0349=12 / 1235=12");
                list3.add("（59-04）（563-912-078）胆579");
                break;
        }
        list0.add(list);
        list0.add(list2);
        list0.add(list3);

        return list0;
    }

    public static List<List<String>> Zuhao3New1(String code) {
        //组3第二种情况 30注
        List<List<String>> list0 = new ArrayList<>();
        //组3第二种情况 30注
        List<String> list2 = new ArrayList<>();

        List<String> lists = new ArrayList<>();

        List<String> listzu3can = new ArrayList<>();
        switch (code) {
            case "[78, 23]":
                String data = "055 066 077 088 114 116 118 119 133 144 166 177 225 226 228 229 255 266 277 288 335 336 339 366 446 447 448 466 557 558 588 667 669 677 688 779";
                String[] strResult = data.split(" ");
                for (int s1 = 0; s1 < strResult.length; s1++) {
                    list2.add(strResult[s1]);
                }
                lists.add("0258=3  0369=3   147配369（258）=3");
                listzu3can.add("红色为本组胆 2378");

                break;
            case "[58, 03]":
                data = "033 066 077 088 114 117 118 119 133 144 155 177 225 228 229 255 288 334 335 336 337 338 339 366 445 446 447 448 466 477 488 557 558 559 566 577 588 668 669 779";
                strResult = data.split(" ");
                for (int s1 = 0; s1 < strResult.length; s1++) {
                    list2.add(strResult[s1]);
                }
                lists.add("1246=3  0369=3   578配039(124）=3");
                listzu3can.add("红色为本组胆 0358");

                break;
            case "[69, 14]":
                data = "009 066 114 116 117 118 119 133 144 166 177 188 199 224 226 229 244 255 299 334 335 336 338 339 366 377 445 446 447 448 449 466 477 488 558 559 577 588 668 669 677 779";
                strResult = data.split(" ");
                for (int s1 = 0; s1 < strResult.length; s1++) {
                    list2.add(strResult[s1]);
                }
                lists.add("1679=3  3456=3   028配345（179）=3");
                listzu3can.add("红色为本组胆 1469");

                break;
            case "[57, 02]":
                data = "005 006 008 009 033 055 066 088 099 133 144 177 223 224 225 227 228 229 244 255 266 299 336 366 377 388 445 446 447 448 449 455 466 558 559 566 577 588 668 669 779";
                strResult = data.split(" ");
                for (int s1 = 0; s1 < strResult.length; s1++) {
                    list2.add(strResult[s1]);
                }
                lists.add("0568=3  2457=3   139配247（068）=3");
                listzu3can.add("红色为本组胆 0257");

                break;
            case "[79, 24]":
                data = "009 133 166 114 117 118 119 144 177 199 225 224 255 227 228 229 233 244 277 288 334 355 336 366 377 388 339 445 446 447 448 449 455 466 477 499 557 577 667 669 779";
                strResult = data.split(" ");
                for (int s1 = 0; s1 < strResult.length; s1++) {
                    list2.add(strResult[s1]);
                }
                lists.add("2389=3  3456=3   017配456（289）=3");
                listzu3can.add("红色为本组胆 2479");

                break;
            case "[89, 34]":
                data = "008 009 114 133 144 155 177 188 199 228 229 244 255 266 277 288 299 334 335 336 337 339 355 366 377 388 445 446 447 448 455 466 477 488 558 559 588 668 669 778 779 889";
                strResult = data.split(" ");
                for (int s1 = 0; s1 < strResult.length; s1++) {
                    list2.add(strResult[s1]);
                }
                lists.add("3456=3  0124=3   789配012（356）=3");
                listzu3can.add("红色为本组胆 3489");

                break;
            case "[67, 12]":
                data = "066 099 114 115 116 117 118 119 133 144 166 188 199 225 226 229 244 255 266 277 288 335 336 337 338 339 366 377 445 446 447 448 449 466 477 556 557 558 577 668 669 778";
                strResult = data.split(" ");
                for (int s1 = 0; s1 < strResult.length; s1++) {
                    list2.add(strResult[s1]);
                }
                lists.add("0378=3  2457=3   169配245（038）");
                listzu3can.add("红色为本组胆 1267");

                break;
            case "[68, 13]":
                data = "006 008 088 114 116 117 118 119 133 155 166 177 199 226 228 244 288 334 335 337 338 339 355 388 446 448 466 488 556 557 558 566 667 668 669";
                strResult = data.split(" ");
                for (int s1 = 0; s1 < strResult.length; s1++) {
                    list2.add(strResult[s1]);
                }
                lists.add("1357=3  2348=3   069配248（157）=3");
                listzu3can.add("红色为本组胆 1368");

                break;
            case "[56, 01]":
                data = "005 006 008 009 115 116 117 118 119 133 144 155 166 177 188 225 226 227 228 229 255 266 335 336 337 338 366 445 446 447 448 449 556 557 558 559 566 577 588 667 668 669";
                strResult = data.split(" ");
                for (int s1 = 0; s1 < strResult.length; s1++) {
                    list2.add(strResult[s1]);
                }
                lists.add("0189=3  1567=3   234配567（089）=3");
                listzu3can.add("红色为本组胆 0156");

                break;
            case "[59, 04]":
                data = "007 008 009 114 115 117 119 133 144 155 166 177 225 228 229 244 255 266 288 335 336 337 338 339 355 366 388 445 446 447 448 449 466 477 556 557 558 559 566 577 588 668 779";
                strResult = data.split(" ");
                for (int s1 = 0; s1 < strResult.length; s1++) {
                    list2.add(strResult[s1]);
                }
                lists.add("1249=3  3456=3   078配356（129）=3");
                listzu3can.add("红色为本组胆 0459");

                break;
            default:
                break;
        }
        list0.add(list2);
        list0.add(lists);
        list0.add(listzu3can);

        return list0;
    }

    public static List<List<String>> Zuhao3New2(String code) {
        //组3第二种情况 30注
        List<List<String>> list0 = new ArrayList<>();
        //组3第二种情况 30注
        List<String> list2 = new ArrayList<>();
        //说明
        List<String> lists = new ArrayList<>();
        //过滤参数
        List<String> listzu3can = new ArrayList<>();
        switch (code) {
            case "[78, 23]":
                String data = "055 066 077 088 114 116 118 119 133 144 166 177 225 226 228 229 255 266 277 288 335 336 339 366 446 447 448 466 557 558 588 667 669 677 688 779";
                String[] strResult = data.split(" ");
                for (int s1 = 0; s1 < strResult.length; s1++) {
                    list2.add(strResult[s1]);
                }
                lists.add("褔彩胆组2378 体彩胆组2378");
                listzu3can.add("01347-25689");

                break;
            case "[58, 03]":
                data = "033 066 077 088 114 117 118 119 133 144 155 177 225 228 229 255 288 334 335 336 337 338 339 366 445 446 447 448 466 477 488 557 558 559 566 577 588 668 669 779";
                strResult = data.split(" ");
                for (int s1 = 0; s1 < strResult.length; s1++) {
                    list2.add(strResult[s1]);
                }
                lists.add("褔彩胆组3578 体彩胆组0378");
                listzu3can.add("03458-12679");

                break;
            case "[69, 14]":
                data = "009 066 114 116 117 118 119 133 144 166 177 188 199 224 226 229 244 255 299 334 335 336 338 339 366 377 445 446 447 448 449 466 477 488 558 559 577 588 668 669 677 779";
                strResult = data.split(" ");
                for (int s1 = 0; s1 < strResult.length; s1++) {
                    list2.add(strResult[s1]);
                }
                lists.add("褔彩胆组1369 体彩胆组4569");
                listzu3can.add("03569-12478");

                break;
            case "[57, 02]":
                data = "005 006 008 009 033 055 066 088 099 133 144 177 223 224 225 227 228 229 244 255 266 299 336 366 377 388 445 446 447 448 449 455 466 558 559 566 577 588 668 669 779";
                strResult = data.split(" ");
                for (int s1 = 0; s1 < strResult.length; s1++) {
                    list2.add(strResult[s1]);
                }
                lists.add("褔彩胆组0267 体彩胆组2357");
                listzu3can.add("01367-24589");

                break;
            case "[79, 24]":
                data = "009 133 166 114 117 118 119 144 177 199 225 224 255 227 228 229 233 244 277 288 334 355 336 366 377 388 339 445 446 447 448 449 455 466 477 499 557 577 667 669 779";
                strResult = data.split(" ");
                for (int s1 = 0; s1 < strResult.length; s1++) {
                    list2.add(strResult[s1]);
                }
                lists.add("褔彩胆组4679 体彩胆组3479");
                listzu3can.add("01578-23469");

                break;
            case "[89, 34]":
                data = "008 009 114 133 144 155 177 188 199 228 229 244 255 266 277 288 299 334 335 336 337 339 355 366 377 388 445 446 447 448 455 466 477 488 558 559 588 668 669 778 779 889";
                strResult = data.split(" ");
                for (int s1 = 0; s1 < strResult.length; s1++) {
                    list2.add(strResult[s1]);
                }
                lists.add("褔彩胆组2389 体彩胆组3489");
                listzu3can.add("01467-23589");

                break;
            case "[67, 12]":
                data = "066 099 114 115 116 117 118 119 133 144 166 188 199 225 226 229 244 255 266 277 288 335 336 337 338 339 366 377 445 446 447 448 449 466 477 556 557 558 577 668 669 778";
                strResult = data.split(" ");
                for (int s1 = 0; s1 < strResult.length; s1++) {
                    list2.add(strResult[s1]);
                }
                lists.add("褔彩胆组1267 体彩胆组1278");
                listzu3can.add("02579-13468");

                break;
            case "[68, 13]":
                data = "006 008 088 114 116 117 119 133 144 155 166 177 188 199 226 228 229 244 255 266 288 334 335 336 338 339 355 377 388 446 447 448 466 556 557 558 566 577 588 668 669";
                strResult = data.split(" ");
                for (int s1 = 0; s1 < strResult.length; s1++) {
                    list2.add(strResult[s1]);
                }
                lists.add("褔彩胆组1368 体彩胆组3689");
                listzu3can.add("02567-13489");

                break;
            case "[56, 01]":
                data = "005 006 008 009 115 116 117 118 119 133 144 155 166 177 188 225 226 227 228 229 255 266 335 336 337 338 366 445 446 447 448 449 556 557 558 559 566 577 588 667 668 669";
                strResult = data.split(" ");
                for (int s1 = 0; s1 < strResult.length; s1++) {
                    list2.add(strResult[s1]);
                }
                lists.add("褔彩胆组0156 体彩胆组1456");
                listzu3can.add("01279-34568");

                break;
            case "[59, 04]":
                data = "007 008 009 114 115 117 119 133 144 155 166 177 225 228 229 244 255 266 288 335 336 337 338 339 355 366 388 445 446 447 448 449 466 477 556 557 558 559 566 577 588 668 779";
                strResult = data.split(" ");
                for (int s1 = 0; s1 < strResult.length; s1++) {
                    list2.add(strResult[s1]);
                }
                lists.add("褔彩胆组4579 体彩胆组0479");
                listzu3can.add("04567-12389");

                break;
            default:
                break;
        }
        list0.add(list2);
        list0.add(lists);
        list0.add(listzu3can);

        return list0;
    }

    public static boolean test(String source, String rule, int method) {

        switch (method) {
            case 1:
                if (rule.equals("")) {
                    return false;
                } else {
                    return Pattern.matches(".*[" + rule + "].*", source);
                }

            case 7:
                if (rule.equals("")) {
                    return false;
                } else {
                    return !Pattern.matches(".*[" + rule + "].*", source);
                }

            case 2:
                for (String r : rule.split("-")) {
                    boolean result = true;
                    for (int i = 0; i < r.length(); i++) {
                        Pattern pattern = Pattern.compile(r.charAt(i) + "");
                        Matcher matcher = pattern.matcher(source);
                        if (!matcher.find()) {
                            result = false;
                        }
                    }
                    if (result) {
                        return true;
                    }
                }
                return false;
            case 3:
                int sum = 0;
                for (int i = 0; i < source.length(); i++) {
                    sum = sum + Integer.parseInt(source.charAt(i) + "");
                }
                return Pattern.matches("[" + rule + "]", Integer.toString(sum % 10));
            case 4:
                int max = 0;
                int min = 9;
                int t = 0;
                for (int i = 0; i < source.length(); i++) {
                    t = Integer.parseInt(source.charAt(i) + "");
                    max = max > t ? max : t;
                    min = min > t ? t : min;
                }
                return Pattern.matches("[" + rule + "]", Integer.toString(max - min));
            case 5:
                for (String r : rule.split("-")) {
                    Pattern pattern = Pattern.compile("[^" + r + "]");
                    Matcher matcher = pattern.matcher(source);
                    if (!matcher.find()) {
                        return true;
                    }
                }
                return false;
            case 6:
                for (String r : rule.split("-")) {
                    Pattern pattern = Pattern.compile("[" + r + "]");
                    Matcher matcher = pattern.matcher(source);
                    if (!matcher.find()) {
                        return false;
                    }
                }
                return true;
        }
        return false;
    }

    //    替换特殊方法
    public static String test1(String source, String rule) {
        return source.replaceAll(rule, "");
    }

    public static ArrayList<String> d(String a) {
        String[] t = a.split("-");
        return d(t[0], 3, t[1] + t[2]);
    }

    public static ArrayList<String> d(String a, int c, String l) {
        ArrayList<String> r = new ArrayList<String>();
        if (c == 1) {
            for (int i = 0; i < a.length(); i++) {
                r.add(String.valueOf(a.charAt(i)));
            }
            for (int i = 0; i < l.length(); i++) {
                r.add(String.valueOf(l.charAt(i)));
            }
            return r;
        }
        String t;
        ArrayList<String> b;
        for (int i = 0; i < a.length(); i++) {
            t = String.valueOf(a.charAt(i));
            b = d(a.substring(i + 1), c - 1, l);
            for (String u : b) {
                r.add(t + u);
            }
        }
        Collections.sort(r, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Integer.parseInt(o1) - Integer.parseInt(o2);
            }
        });
//       // char[] buf = new char[];
//        for (int x = 0; x< r.size();x++) {
//        //    int[] as=new int[]{Integer.parseInt(r.get(x))};
//            int i, j;
//            for(i=0; i< r.size(); i++){//表示n次排序过程。
//                for(j=1; j< r.size()-i; j++){
//                    r.get(x)
//                    if(as[j-1] > as[j]){//前面的数字大于后面的数字就交换
//                        int temp;
//                        temp = as[j-1];
//                        as[j-1] = as[j];
//                        as[j]=temp;
//                    }
//                }
//            }
//        }


        return r;
    }


    public static ArrayList<String> zu66(String can1) {
        ArrayList<String> all = new ArrayList<String>();


        return all;
    }

    public static ArrayList<String> zu6(String can1, String can2, String can3) {
        ArrayList<String> all = new ArrayList<String>();
        String a = can1 + "-" + can2 + "-" + can3;
        all = test.d(a);
        String b = can2 + "-" + can1 + "-" + can3;
        all.addAll(test.d(b));
        String c = can3 + "-" + can1 + "-" + can2;
        all.addAll(test.d(c));


        return all;
    }


    public static boolean can(String can1, String can2, String can3) {
        int i;
        int j;
        String can_all = can1 + can2 + can3;
        boolean num = true;
        for (i = 0; i < can_all.length(); i++) {
            for (j = i + 1; j < can_all.length(); j++) {
                if (can_all.getBytes()[i] == can_all.getBytes()[j]) {
                    num = false;
                }
            }
        }
        return num;
    }


    public static ArrayList<String> zhongyaocanshu(String can1) {
        ArrayList<String> all = new ArrayList<>();
        List<String> list1 = new ArrayList<String>() {{
            add("0");
            add("1");
            add("2");
            add("3");
            add("4");
            add("5");
            add("6");
            add("7");
            add("8");
            add("9");
        }};
        List<String> list2 = new ArrayList<>();
        for (int i = 0; i < can1.length(); i++) {
            list2.add(can1.substring(i, i + 1));
        }
        //求出差集
        list1.removeAll(list2);

        String condition = list1.toString();
        condition = condition.replace("[", "");
        condition = condition.replace("]", "");
        condition = condition.replace(", ", "");
        Log.e("差集", condition.toString());
        all.add(can1);
        all.add(condition);
        return all;
    }

    public static ArrayList<String> xinzu6(List<String> can1, List<String> can2, List<String> mutableList) {


        ArrayList<String> all = new ArrayList<>();
        ArrayList<String> a = new ArrayList<>();
        String condition = can1.toString();
        condition = condition.replace("[", "");
        condition = condition.replace("]", "");
        condition = condition.replace(", ", "");
        for (int i = 0; i < condition.length(); i++) {
            String key = condition.substring(i, i + 1);
            for (int j = 0; j < can2.size(); j++) {
                String vlaue = can2.get(j);
                if (xiangtong(key, vlaue)) {
//                    Log.e("key的值", key);
//                    Log.e("vlaue的值", vlaue);
                    for (int t = 0; t < vlaue.length(); t++) {
                        String vlaue2 = vlaue.substring(t, t + 1);
                        if (!key.equals(vlaue2.toString())) {
                            if (Integer.parseInt(key.toString()) < Integer.parseInt(vlaue2.toString())) {
                                a.add(key + vlaue.substring(t, t + 1));
                            } else {
                                a.add(vlaue.substring(t, t + 1) + key);
                            }
                        }
                    }
                }
            }
        }
        for (int i = 0; i < a.size(); i++) {
            String key = a.get(i);
            for (int j = 0; j < mutableList.size(); j++) {
                String vlaue = mutableList.get(j);
                if (xiangtong1(key, vlaue)) {
//                    Log.e("key2的值", key);
//                    Log.e("vlaue2的值", vlaue);
                    for (int k = 0; k < vlaue.length(); k++) {
                        String vlaue1 = vlaue.substring(k, k + 1);
                        if (!xiangtong(vlaue1, key)) {
//                            Log.e("最后我想要的值key", key);
//                            Log.e("最后我想要的值vlaue", vlaue1);

                            all.add(bijiao(key, vlaue1));
                        }

                    }

                }
            }
        }
        all = unique(all);
        return all;
    }

    public static ArrayList<String> unique(List<String> arr) {
        //实例化一个set集合
        Set<String> set = new HashSet();
        //遍历数组并存入集合,如果元素已存在则不会重复存入
        for (int i = 0; i < arr.size(); i++) {
            set.add(arr.get(i));
        }
        ArrayList<String> list = new ArrayList<>();
        for (String string : set) {
            list.add(string);
        }


        //返回Set集合的数组形式
        return list;
    }

    public static String bijiao(String key, String vlaue1) {

        String data = "";

        List<String> set1 = new ArrayList<>();
        for (int i = 0; i < key.length(); i++) {
            set1.add(key.substring(i, i + 1));
        }
        int a = Integer.parseInt(set1.get(0));
        int b = Integer.parseInt(set1.get(1));
        int c = Integer.parseInt(vlaue1);

        if (a > b) {
            if (c > a) {
                data = (b + "" + a + "" + c);
            } else if (c < b) {
                data = (c + "" + b + "" + a);
            } else {
                data = (b + "" + c + "" + a);
            }

            // a<<b时
        } else {
            if (c < a) {
                data = (c + "" + a + "" + b);
            } else if (c > b) {
                data = (a + "" + b + "" + c);
            } else {
                data = (a + "" + c + "" + b);
            }
        }

        return data;
    }

    public static boolean xiangtong(String key, String vlaue) {
        Set<String> set1 = new HashSet<>();
        Set<String> set2 = new HashSet<>();
        for (int i = 0; i < key.length(); i++) {
            set1.add(key.substring(0, 1));
        }
        for (int i = 0; i < vlaue.length(); i++) {
            set2.add(vlaue.substring(i, i + 1));
        }
        //找出两个元素的交集
        set1.retainAll(set2);
        if (set1.size() == 0) {
            return false;
        } else {
            return true;
        }

    }

    public static boolean xiangtong1(String key, String vlaue) {
        Set<String> set1 = new HashSet<>();
        Set<String> set2 = new HashSet<>();
        for (int i = 0; i < key.length(); i++) {
            set1.add(key.substring(0, 1));
        }
        for (int i = 0; i < vlaue.length(); i++) {
            set2.add(vlaue.substring(i, i + 1));
        }
        //找出两个元素的交集
        set1.retainAll(set2);
        if (set1.size() == 1) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * 判断字符串是否包含重复字符
     *
     * @param str
     * @return
     */
    public static boolean containRepeatChar(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        char[] elements = str.toCharArray();
        for (char e : elements) {
            if (str.indexOf(e) != str.lastIndexOf(e)) {
                return true;
            }
        }
        return false;
    }

    /**
     * danma_text 输入的胆码
     */

    public static boolean zu3(@NotNull String item, @NotNull String danma_text, ArrayList<String> tiaojian) {
        boolean bool = false;
        for (int i = 0; i < danma_text.length(); i++) {
            String string = danma_text.substring(i, i + 1);
            if (xiangtong1(string, item)) {
                if (xiangtong1(string, tiaojian.get(0))) {
                    //有值
                    String t = tiaojian.get(1);
                    t = t + string;
                    if (xiangtong3(item, t)) {
                        bool = true;
                    }
                } else {
                    //无值
                    String t = tiaojian.get(0);
                    t = t + string;
                    if (xiangtong3(item, t)) {
                        bool = true;
                    }
                }
            }
        }
        return bool;
    }


    private static boolean xiangtong3(String string, String s) {
        Set<String> set1 = new HashSet<>();
        Set<String> set2 = new HashSet<>();
        for (int i = 0; i < string.length(); i++) {
            set1.add(string.substring(i, i + 1));
        }
        for (int i = 0; i < s.length(); i++) {
            set2.add(s.substring(i, i + 1));
        }
        //找出两个元素的交集
        set1.retainAll(set2);

        if (set1.size() == 2) {
            return true;
        } else {
            return false;
        }
    }
}
