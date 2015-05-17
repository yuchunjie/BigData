package jsonparser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main
{
    Map<String, Integer> charsetMap = new HashMap<String, Integer>();
    Map<String, Integer> delivered_toMap = new HashMap<String, Integer>();
    Map<String, Integer> content_typeMap = new HashMap<String, Integer>();
    Map<String, Integer> toMap = new HashMap<String, Integer>();
    
    public static void main(String[] args)
    {
        Main m = new Main();
        String json = m.readFile("data/new.txt");
        m.Regular_All("\\{\"_index\": \"spam_mail\",(.*?])\\}\\}", json);
    }

    /**
     * 取回區塊
     * @param regular 正規表示法
     * @param inputStr 輸入內容
     */
    public void Regular_All(String regular, String inputStr)
    {
        String returnString = "";
        Pattern pattern = Pattern.compile(regular);
        Matcher matcher = pattern.matcher(inputStr);
        List<Attr> attrList = new LinkedList<Attr>();

        while (matcher.find())
        {
            Attr attr = new Attr();
            returnString = matcher.group(1);
            String charset = Regular_Data("\"charset\": \\[\"(.*?)\"\\]", returnString);
            attr.charsetLabel = labeling(charsetMap, charset);
            String delivered_to = Regular_Data("\"delivered_to\": \\[\"(.*?)\"\\]", returnString);
            attr.delivered_toLabel = labeling(delivered_toMap, delivered_to);
            String content_type = Regular_Data("\"content_type\": \\[\"(.*?)\"\\]", returnString);
            attr.content_typeLabel = labeling(content_typeMap, content_type);
            String to = Regular_Data("\"to\": \\[\"(.*?)\"\\]", returnString);
            attr.toLabel = labeling(toMap, to);
            String file_name = Regular_Data("\"file_name\": \\[\"(.*?)\"\\]", returnString);
            attr.file_nameLabel = file_name.equals("") ? 0 : 1;
            attrList.add(attr);
        }
        writeFile("Labeling.txt", attrList);
    }

    /**
     * 輸出Labeling
     */
    public void writeFile(String name, List<Attr> attrList)
    {
        try {
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(name), "UTF-8"));
            for(Attr attr : attrList)
            {
                out.append(attr.charsetLabel + " " + attr.content_typeLabel + " " + attr.delivered_toLabel + " " + attr.file_nameLabel + " " + attr.toLabel);
                out.newLine();
            }
            out.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 標籤化
     */
    public int labeling(Map<String, Integer> targetMap, String key)
    {
        if(targetMap.containsKey(key))
        {
            return targetMap.get(key);
        }else
        {
            int label = targetMap.size();
            targetMap.put(key, label);
            return label;
        }
    }

    /**
     * 回傳字串(單項內容時使用)
     * @param regular 正規表示法
     * @param inputStr 輸入內容
     */
    public String Regular_Data(String regular, String inputStr)
    {
        String returnString = "";
        Pattern pattern = Pattern.compile(regular);
        Matcher matcher = pattern.matcher(inputStr);
        while (matcher.find())
        {
            returnString = matcher.group(1);
            break;
        }
        return returnString;
    }

    /**
     * 讀取json檔
     */
    public String readFile(String fileName)
    {
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();
        try {
            br = new BufferedReader(new FileReader(fileName));
            String line = br.readLine();
            while (line != null)
            {
                sb.append(line.trim());
                line = br.readLine();
            }
            br.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return sb.toString();
    }

}

class Attr
{
    int charsetLabel = 0;
    int delivered_toLabel = 0;
    int content_typeLabel = 0;
    int toLabel = 0;
    int file_nameLabel = 0;
}
