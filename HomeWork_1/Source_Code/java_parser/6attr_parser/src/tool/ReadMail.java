package tool;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class ReadMail
{
    /*
     * Ū�����
     */
    public String loadData(String path)
    {
        BufferedReader reader = null;
        String output = "";
        try {
            // ���wŪ����󪺽s�X�榡�A�H�K�X�{����ýX
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF-8"));//"UTF-8"
            String str = "";            
            while ((str = reader.readLine()) != null)//Ū��
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
