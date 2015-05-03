package tool;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import spam_mail.CreateThread;

public class LoadFileFromFolder
{
    int id = 1;
    ReadMail readMail = new ReadMail();
    public List<String> fileList = new LinkedList<String>();

    /**
     * 讀取路徑與檔名
     */
    public void readFolder(String folderPath)
    {
        File a = new File(folderPath);
        String[] filenames;
        String fullpath = a.getAbsolutePath();
        if (a.isDirectory())
        {
            filenames = a.list();
            for (int i = 0; i < filenames.length; i++)
            {
                File tempFile = new File(fullpath + "\\" + filenames[i]);
                if (tempFile.isDirectory())
                {
                    readFolder(fullpath + "\\" + filenames[i]);
                }
                else
                {
                    if(filenames[i].contains(".lorien"))
                    {
                        String content = readMail.loadData(fullpath + "\\" + filenames[i]);
                        CreateThread.pushToThread(content, id++);
                    }
                }
            }
        }
        CreateThread.pushStop();
    }


}
