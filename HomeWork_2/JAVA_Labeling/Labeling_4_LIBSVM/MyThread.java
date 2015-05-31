/*package kdd99;*/

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

class MyThread extends Thread
{
    public Thread t;
    public String threadName;
    public boolean stop = false;
    List<KDD99Type> kdd99List = Collections.synchronizedList(new LinkedList<KDD99Type>());

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
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(threadName), "UTF-8"));
            do{
                if(stop && kdd99List.size() == 0)
                {
                    break;
                }

                if(kdd99List.size() > 0)
                {
                    KDD99Type kdd99 = kdd99List.get(0);
                    out.write(kdd99.attack_types + " " +"1:"+kdd99.duration + " " +"2:"+kdd99.protocol_type + " " +"3:"+kdd99.service + " " +"4:"+kdd99.flag + " " +"5:"+kdd99.src_bytes + " " +"6:"+kdd99.dst_bytes + " " +"7:"+kdd99.land + " " +"8:"+kdd99.wrong_fragment + " " +"9:"+kdd99.urgent + " "
					                             +"10:"+kdd99.hot + " " +"11:"+kdd99.num_failed_logins + " " +"12:"+kdd99.logged_in + " " +"13:"+kdd99.num_compromised + " " +"14:"+kdd99.root_shell + " " +"15:"+kdd99.su_attempted + " " +"16:"+kdd99.num_root + " " +"17:"+kdd99.num_file_creations + " " +"18:"+kdd99.num_shells + " " +"19:"+kdd99.num_access_files + " "
												 +"20:"+kdd99.num_outbound_cmds + " " +"21:"+kdd99.is_host_login + " " +"22:"+kdd99.is_guest_login + " " +"23:"+kdd99.count + " " +"24:"+kdd99.srv_count + " " +"25:"+kdd99.serror_rate + " " +"26:"+kdd99.srv_serror_rate + " " +"27:"+kdd99.rerror_rate + " " +"28:"+kdd99.srv_rerror_rate + " " +"29:"+kdd99.same_srv_rate + " " 
												 +"30:"+kdd99.diff_srv_rate + " " +"31:"+kdd99.srv_diff_host_rate + " " +"32:"+kdd99.dst_host_count + " " +"33:"+kdd99.dst_host_srv_count + " " +"34:"+kdd99.dst_host_same_srv_rate + " " +"35:"+kdd99.dst_host_diff_srv_rate + " " +"36:"+kdd99.dst_host_same_src_port_rate + " " +"37:"+kdd99.dst_host_srv_diff_host_rate + " " +"38:"+kdd99.dst_host_serror_rate + " " +"39:"+kdd99.dst_host_srv_serror_rate + " "
												 +"40:"+kdd99.dst_host_rerror_rate + " " +"41:"+kdd99.dst_host_srv_rerror_rate);
                    out.newLine();
                    kdd99List.remove(0);
                }
            }while(true);
            out.close();
        }
        catch (Exception e)
        {
            System.out.println(e);
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
