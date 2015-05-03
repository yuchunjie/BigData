package tool;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class ReadMail
{
    /*
     * 讀取資料
     */
    public String loadData(String path)
    {
        BufferedReader reader = null;
        String output = "";
        try {
            // 指定讀取文件的編碼格式，以免出現中文亂碼
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF-8"));//"UTF-8"
            String str = "";            
            while ((str = reader.readLine()) != null)//讀行
            {
                output += str + "\n";
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return output;
    }

}
