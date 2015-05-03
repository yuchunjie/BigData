package tool;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regular
{
    /**
     * �^�Ǧr��(�涵���e�ɨϥ�)
     * @param regular ���W��ܪk
     * @param inputStr ��J���e
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
     * �^�ǦC��(�h�����e�ɨϥ�)
     * @param regular ���W��ܪk
     * @param inputStr ��J���e
     * @param getNum �n���ĴX��
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
     * �^�ǦC��(�h�����e�ɨϥ�)
     * @param regular ���W��ܪk
     * @param inputStr ��J���e
     * @param times ���e��(�j��h�֬�html�榡)
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
