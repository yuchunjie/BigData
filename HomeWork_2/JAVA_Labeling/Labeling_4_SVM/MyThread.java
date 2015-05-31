package kdd99;

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
                    out.write(kdd99.attack_types + " " +kdd99.duration + " " +kdd99.protocol_type + " " +kdd99.service + " " +kdd99.flag + " " +kdd99.src_bytes + " " +kdd99.dst_bytes + " " +kdd99.land + " " +kdd99.wrong_fragment + " " +kdd99.urgent + " " +kdd99.hot + " " +kdd99.num_failed_logins + " " +kdd99.logged_in + " " +kdd99.num_compromised + " " +kdd99.root_shell + " " +kdd99.su_attempted + " " +kdd99.num_root + " " +kdd99.num_file_creations + " " +kdd99.num_shells + " " +kdd99.num_access_files + " " +kdd99.num_outbound_cmds + " " +kdd99.is_host_login + " " +kdd99.is_guest_login + " " +kdd99.count + " " +kdd99.srv_count + " " +kdd99.serror_rate + " " +kdd99.srv_serror_rate + " " +kdd99.rerror_rate + " " +kdd99.srv_rerror_rate + " " +kdd99.same_srv_rate + " " +kdd99.diff_srv_rate + " " +kdd99.srv_diff_host_rate + " " +kdd99.dst_host_count + " " +kdd99.dst_host_srv_count + " " +kdd99.dst_host_same_srv_rate + " " +kdd99.dst_host_diff_srv_rate + " " +kdd99.dst_host_same_src_port_rate + " " +kdd99.dst_host_srv_diff_host_rate + " " +kdd99.dst_host_serror_rate + " " +kdd99.dst_host_srv_serror_rate + " " +kdd99.dst_host_rerror_rate + " " +kdd99.dst_host_srv_rerror_rate);
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
