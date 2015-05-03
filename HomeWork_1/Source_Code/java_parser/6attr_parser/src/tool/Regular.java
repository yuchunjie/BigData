package tool;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regular
{
    /**
     * 回傳字串(單項內容時使用)
     * @param regular 正規表示法
     * @param inputStr 輸入內容
     */
    public String Regular_String(String regular, String inputStr, int getNum)
    {
        String returnString = "";
        Pattern pattern = Pattern.compile(regular);
        Matcher matcher = pattern.matcher(inputStr);
        while (matcher.find())
        {
            if(getNum == 0)
            {
//                System.out.println(matcher.group());
                returnString = matcher.group();
            }else
            {
//                System.out.println(matcher.group(getNum));
                returnString = matcher.group(getNum);
            }
            break;
        }
        return returnString;
    }

    /**
     * 回傳列表(多項內容時使用)
     * @param regular 正規表示法
     * @param inputStr 輸入內容
     * @param getNum 要取第幾個
     */
    public List<String> Regular_List(String regular, String inputStr, int getNum)
    {
        List<String> returnList = new LinkedList<String>();
        Pattern pattern = Pattern.compile(regular);
        Matcher matcher = pattern.matcher(inputStr);
        while (matcher.find())
        {
            if(getNum == 0)
            {
//                System.out.println(matcher.group());
                returnList.add(matcher.group());
            }else
            {
//                System.out.println(matcher.group(getNum));
                returnList.add(matcher.group(getNum));
            }
        }
        return returnList;
    }

    /**
     * 回傳列表(多項內容時使用)
     * @param regular 正規表示法
     * @param inputStr 輸入內容
     * @param times 門檻值(大於多少為html格式)
     */
    public boolean Regular_Boolean(String regular, String inputStr, int times)
    {
        int count = 0;
        Pattern pattern = Pattern.compile(regular);
        Matcher matcher = pattern.matcher(inputStr);
        while (matcher.find())
        {
            count++;
        }
        return (count > times);
    }

}
