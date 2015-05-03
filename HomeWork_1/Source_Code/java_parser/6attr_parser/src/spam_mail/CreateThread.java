package spam_mail;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import tool.LoadFileFromFolder;
import tool.WriteJson;

/*
 * 創建執行序
 */
public class CreateThread
{
    CreateThread create = new CreateThread();
    static MyThread T1 = new MyThread("執行序1");
    static MyThread T2 = new MyThread("執行序2");
    static MyThread T3 = new MyThread("執行序3");
    
    public static void main(String args[])
    {
        WriteJson write = new WriteJson();
        LoadFileFromFolder loadFolder = new LoadFileFromFolder();        
        String folderPath = "data/";
        String outputJsonFilePath = "Json.txt";
        T1.start();
        T2.start();
        T3.start();
        loadFolder.readFolder(folderPath);
        write.writeJson(outputJsonFilePath);
        do
        {
            try
            {
                if (check(T1, T2, T3))
                {
                    break;
                }
                Thread.sleep(5000);
            } catch (Exception ex)
            {
                ex.printStackTrace();
            }
        } while(true);

        System.out.println("結束");
    }

    /*
     * 確認執行序是否還在執行
     */
    public static boolean check(MyThread T1, MyThread T2, MyThread T3)
    {
        return (!T1.t.isAlive() && !T2.t.isAlive() && !T3.t.isAlive());
    }

    /*
     * 將資料丟進執行序去處理
     */
    public static void pushToThread(String content, int id)
    {
        if(T1.infoList.size() == 0)
        {
            T1.infoList.add(content);
            T1.idList.add(id);
        }else if(T2.infoList.size() == 0)
        {
            T2.infoList.add(content);
            T2.idList.add(id);
        }else if(T3.infoList.size() == 0)
        {
            T3.infoList.add(content);
            T3.idList.add(id);
        }else
        {
            Random ran = new Random();
            int rand = ran.nextInt(3)+1;
            switch(rand)
            {
                case 1:
                    T1.infoList.add(content);
                    T1.idList.add(id);
                    break;
                case 2:
                    T2.infoList.add(content);
                    T2.idList.add(id);
                    break;
                case 3:
                    T3.infoList.add(content);
                    T3.idList.add(id);
                    break;
            }
        }
    }

    /*
     * 跟執行序說資料已經倒完了
     */
    public static void pushStop()
    {
        T1.stop = true;
        T2.stop = true;
        T3.stop = true;
    }
}
