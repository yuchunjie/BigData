//package kdd99;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Main
{
    Map<String, Double> protocolTypeMap = new HashMap<String, Double>();
    Map<String, Double> serviceMap = new HashMap<String, Double>();
    Map<String, Double> flagMap = new HashMap<String, Double>();
    Map<String, Double> attackTypesMap = new HashMap<String, Double>();
    
    public static void main(String[] args)
    {
        Main m = new Main();
        m.readFile("data/kddcup.data.corrected");
    }

    /**
     * Åª¨úcorrectedÀÉ
     */
    public void readFile(String fileName)
    {
        BufferedReader br = null;
        MyThread thread = new MyThread("data/kdd99_labeling.txt");
        thread.start();
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(fileName)), "UTF8"));
            String line = "";
            int loc = 1;
            while ((line = br.readLine()) != null) 
            {
                if(loc % 100000 == 0)
                {
                    System.out.println(loc);
                }
                loc++;
                String[] info = line.split(",");
                if(info.length == 42)
                {
                    KDD99Type kdd99 = new KDD99Type();
                    kdd99.duration = Double.parseDouble(info[0]);
                    kdd99.protocol_type = labeling(protocolTypeMap, info[1]);
                    kdd99.service = labeling(serviceMap, info[2]);
                    kdd99.flag = labeling(flagMap, info[3]);
                    kdd99.src_bytes = Double.parseDouble(info[4]);
                    kdd99.dst_bytes = Double.parseDouble(info[5]);
                    kdd99.land = Double.parseDouble(info[6]);
                    kdd99.wrong_fragment = Double.parseDouble(info[7]);
                    kdd99.urgent = Double.parseDouble(info[8]);
                    kdd99.hot = Double.parseDouble(info[9]);
                    kdd99.num_failed_logins = Double.parseDouble(info[10]);
                    kdd99.logged_in = Double.parseDouble(info[11]);
                    kdd99.num_compromised = Double.parseDouble(info[12]);
                    kdd99.root_shell = Double.parseDouble(info[13]);
                    kdd99.su_attempted = Double.parseDouble(info[14]);
                    kdd99.num_root = Double.parseDouble(info[15]);
                    kdd99.num_file_creations = Double.parseDouble(info[16]);
                    kdd99.num_shells = Double.parseDouble(info[17]);
                    kdd99.num_access_files = Double.parseDouble(info[18]);
                    kdd99.num_outbound_cmds = Double.parseDouble(info[19]);
                    kdd99.is_host_login = Double.parseDouble(info[20]);
                    kdd99.is_guest_login = Double.parseDouble(info[21]);
                    kdd99.count = Double.parseDouble(info[22]);
                    kdd99.srv_count = Double.parseDouble(info[23]);
                    kdd99.serror_rate = Double.parseDouble(info[24]);
                    kdd99.srv_serror_rate = Double.parseDouble(info[25]);
                    kdd99.rerror_rate = Double.parseDouble(info[26]);
                    kdd99.srv_rerror_rate = Double.parseDouble(info[27]);
                    kdd99.same_srv_rate = Double.parseDouble(info[28]);
                    kdd99.diff_srv_rate = Double.parseDouble(info[29]);
                    kdd99.srv_diff_host_rate = Double.parseDouble(info[30]);
                    kdd99.dst_host_count = Double.parseDouble(info[31]);
                    kdd99.dst_host_srv_count = Double.parseDouble(info[32]);
                    kdd99.dst_host_same_srv_rate = Double.parseDouble(info[33]);
                    kdd99.dst_host_diff_srv_rate = Double.parseDouble(info[34]);
                    kdd99.dst_host_same_src_port_rate = Double.parseDouble(info[35]);
                    kdd99.dst_host_srv_diff_host_rate = Double.parseDouble(info[36]);
                    kdd99.dst_host_serror_rate = Double.parseDouble(info[37]);
                    kdd99.dst_host_srv_serror_rate = Double.parseDouble(info[38]);
                    kdd99.dst_host_rerror_rate = Double.parseDouble(info[39]);
                    kdd99.dst_host_srv_rerror_rate = Double.parseDouble(info[40]);
                    kdd99.attack_types = (int)labeling(attackTypesMap, info[41]);
                    thread.kdd99List.add(kdd99);
                }
            }
            br.close();
            thread.stop = true;
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * ¼ÐÅÒ¤Æ
     */
    public double labeling(Map<String, Double> targetMap, String key)
    {
        if(targetMap.containsKey(key))
        {
            return targetMap.get(key);
        }else
        {
            int label = targetMap.size();
            targetMap.put(key, (double)label);
            return label;
        }
    }
}

class KDD99Type
{
    double duration = 0;
    double protocol_type = 0;
    double service = 0;
    double flag = 0;
    double src_bytes = 0;
    double dst_bytes = 0;
    double land = 0;
    double wrong_fragment = 0;
    double urgent = 0;
    double hot = 0;
    double num_failed_logins = 0;
    double logged_in = 0;
    double num_compromised = 0;
    double root_shell = 0;
    double su_attempted = 0;
    double num_root = 0;
    double num_file_creations = 0;
    double num_shells = 0;
    double num_access_files = 0;
    double num_outbound_cmds = 0;
    double is_host_login = 0;
    double is_guest_login = 0;
    double count = 0;
    double srv_count = 0;
    double serror_rate = 0;
    double srv_serror_rate = 0;
    double rerror_rate = 0;
    double srv_rerror_rate = 0;
    double same_srv_rate = 0;
    double diff_srv_rate = 0;
    double srv_diff_host_rate = 0;
    double dst_host_count = 0;
    double dst_host_srv_count = 0;
    double dst_host_same_srv_rate = 0;
    double dst_host_diff_srv_rate = 0;
    double dst_host_same_src_port_rate = 0;
    double dst_host_srv_diff_host_rate = 0;
    double dst_host_serror_rate = 0;
    double dst_host_srv_serror_rate = 0;
    double dst_host_rerror_rate = 0;
    double dst_host_srv_rerror_rate = 0;
    int attack_types = 0;
}
