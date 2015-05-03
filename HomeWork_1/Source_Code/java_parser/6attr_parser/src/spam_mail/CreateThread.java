package spam_mail;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import tool.LoadFileFromFolder;
import tool.WriteJson;

/*
 * �Ыذ����
 */
public class CreateThread
{
    CreateThread create = new CreateThread();
    static MyThread T1 = new MyThread("�����1");
    static MyThread T2 = new MyThread("�����2");
    static MyThread T3 = new MyThread("�����3");
    
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

        System.out.println("����");
    }

    /*
     * �T�{����ǬO�_�٦b����
     */
    public static boolean check(MyThread T1, MyThread T2, MyThread T3)
    {
        return (!T1.t.isAlive() && !T2.t.isAlive() && !T3.t.isAlive());
    }

    /*
     * �N��ƥ�i����ǥh�B�z
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
     * �����ǻ���Ƥw�g�˧��F
     */
    public static void pushStop()
    {
        T1.stop = true;
        T2.stop = true;
        T3.stop = true;
    }
}
