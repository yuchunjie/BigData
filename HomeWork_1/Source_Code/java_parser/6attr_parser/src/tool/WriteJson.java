package tool;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class WriteJson
{
    public boolean start = false;
    public static List<String> jsonList = Collections.synchronizedList(new LinkedList<String>());
    
    /**
     * �g�X�̫ᵲ�G
     */
    public void writeJson(String path)
    {
        try {
            File file = new File(path);// �إ��ɮסA�ǳƼg��
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, false), "UTF-8"));// �]�w��BIG5�榡
            try {
                
                // �Ѽ�append�N��n���n�~��\�J���ɮפ�
                int i = 0;
                do
                {
                    if(jsonList.size() >= 1)
                    {
                        start = true;
                        if(jsonList.get(0) != null)
                        {
                            String content = jsonList.get(0);
                            jsonList.remove(0);
                            writer.append(content);
                            writer.newLine();
                        }
                    }

                    i = (jsonList.size() == 0) ? i + 1 : 0;
                    if(i > 10 && start)
                    {
                        break;
                    }
                    Thread.sleep(50);
                }while(true);
                writer.close();
            } catch (Exception e)
            {
                writer.close();
                e.printStackTrace();
                System.out.println(path + "�g�ɿ��~!!");
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}
