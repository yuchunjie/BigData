package spam_mail;

import java.util.LinkedList;
import java.util.List;
import tool.Regular;
import tool.WriteJson;

class MyThread extends Thread
{
    public Thread t;
    public String threadName;
    public boolean stop = false;
    List<String> infoList = new LinkedList<String>();
    List<Integer> idList = new LinkedList<Integer>();
    Regular regular = new Regular();

    MyThread(String name)
    {
        threadName = name;
        System.out.println("創造 : " + threadName);
    }

    public void run()
    {
        System.out.println("執行 : " + threadName);
        try
        {
            do{
                if(stop && infoList.size() == 0)
                {
                    break;
                }
                if(infoList.size() > 0)
                {
                    String content = infoList.get(0);
                    List<String> ipList = regular.Regular_List("([\\d]{1,3}\\.[\\d]{1,3}\\.[\\d]{1,3}\\.[\\d]{1,3})", content, 1);
//                    2.IP→Geo
//
//                    3.Delivered to後面的網址
//                            Delivered-To:.*?\n
                    String delivered_to = regular.Regular_String("Delivered-To:(.*?)\n", content, 1).trim();
//                    4.html的格式或純文字
//                            <.*?>
                    boolean isHtml = !regular.Regular_String("Content-Type:\\s(.*?)\n", content, 1).contains("text/plain");

//                    5.http的網址
//                            ((http|https)://.*?)(\s|"|<|>|\n)
                    List<String> hyperlinkList = regular.Regular_List("((http|https)://.*?)(\\s|\"|<|>|\\n)", content, 1);

//                    6.是否有夾檔(檔案大小、名稱)
//                            Content-Type:[\s]{0,1}message.*?length=[\d]{1,}
                    String file_length = regular.Regular_String("Content-Type:[\\s]{0,1}message.*?length=([\\d]{1,})", content, 1).trim();
                    file_length = file_length.equals("") ? "0" : file_length;
//                            Content-Type: (image|application).*?name="(.*?)"
                    String file_name = regular.Regular_String("name=\"(.*?)\"", content, 1).trim();
                    file_name = file_name.equals("") ? "null" : file_name;

                    //charset="iso-8859-1"
                    String charset = regular.Regular_String("charset=(.*?)(\n|;|\\s)", content, 1).trim().replaceAll("\"", "").toLowerCase();
                    charset = charset.equals("") ? "null" : charset;
//                    System.out.println(charset);

                    
                    boolean haveData = false;
                    String outputIP = "[";
                    for(String ip : ipList)
                    {
                        outputIP += "\"" + ip + "\",";
                        haveData = true;
                    }
                    if(haveData)
                    {
                        outputIP = outputIP.substring(0,outputIP.length()-1);
                    }
                    outputIP += "]";

                    String outputhyperlink = "[";
                    haveData = false;
                    for(String hyperlink : hyperlinkList)
                    {
                        outputhyperlink += "\"" + hyperlink + "\",";
                        haveData = true;
                    }
                    if(haveData)
                    {
                        outputhyperlink = outputhyperlink.substring(0,outputhyperlink.length()-1);
                    }
                    outputhyperlink += "]";

                    String json = "{\"index\":{\"_index\":\"spammail\",\"_type\":\"6attr\",\"_id\":" + idList.get(0) + "}}"
                            + "\n{"
                            + "\"line_id\":" + (idList.get(0)) + ","
                            + "\"src_ip\":" + outputIP + ","
                            + "\"delivered_to\":\"" + delivered_to + "\","
//                            + "\"delivered_to.raw\" : {\"type\": \"string\", \"index\" : \"not_analyzed\", \"ignore_above\" : 256},"
                            + "\"is_html\":\"" + isHtml + "\","
                            + "\"hyperlink\":" + outputhyperlink + ","
                            + "\"file_length\":" + file_length + ","
                            + "\"file_name\":\"" + file_name + "\","
                            + "\"charset\":\"" + charset + "\"}";
                    WriteJson.jsonList.add(json);
                    infoList.remove(0);
                    idList.remove(0);
                }
                Thread.sleep(100);
            }while(true);
        }
        catch (Exception e)
        {
            System.out.println(threadName + " 出錯.");
        }
        t.interrupt();
        System.out.println(threadName + " 離開.");
    }

    public void start()
    {
        System.out.println("啟動 : " + threadName);
        if (t == null)
        {
            t = new Thread(this, threadName);
            t.start();
        }
    }
}
