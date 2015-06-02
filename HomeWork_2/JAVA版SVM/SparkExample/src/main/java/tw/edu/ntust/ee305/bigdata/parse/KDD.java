/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.edu.ntust.ee305.bigdata.parse;

import java.util.HashMap;
import java.util.StringTokenizer;

/**
 *
 * @author Leo
 */
public class KDD {

    public static Label protocolTypeMap;
    public static Label serviceMap;
    public static Label flagMap;
    public static Label attackTypesMap;

    static {
        protocolTypeMap = new Label();
        serviceMap = new Label();
        flagMap = new Label();
        attackTypesMap = new Label();
    }

    private String kdd;

    int duration = 0;
    int protocol_type = 0;
    int service = 0;
    int flag = 0;
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

    public KDD(String kdd) {
        this.kdd = kdd;
        this.parse();
    }

    private void parse() {
        StringTokenizer tokens = new StringTokenizer(this.kdd, ",");
        this.duration = Integer.parseInt(tokens.nextToken());
        this.protocol_type = protocolTypeMap.labeling(tokens.nextToken());
        this.service = serviceMap.labeling(tokens.nextToken());
        this.flag = flagMap.labeling(tokens.nextToken());
        this.src_bytes = Double.parseDouble(tokens.nextToken());
        this.dst_bytes = Double.parseDouble(tokens.nextToken());
        this.land = Double.parseDouble(tokens.nextToken());
        this.wrong_fragment = Double.parseDouble(tokens.nextToken());
        this.urgent = Double.parseDouble(tokens.nextToken());
        this.hot = Double.parseDouble(tokens.nextToken());
        this.num_failed_logins = Double.parseDouble(tokens.nextToken());
        this.logged_in = Double.parseDouble(tokens.nextToken());
        this.num_compromised = Double.parseDouble(tokens.nextToken());
        this.root_shell = Double.parseDouble(tokens.nextToken());
        this.su_attempted = Double.parseDouble(tokens.nextToken());
        this.num_root = Double.parseDouble(tokens.nextToken());
        this.num_file_creations = Double.parseDouble(tokens.nextToken());
        this.num_shells = Double.parseDouble(tokens.nextToken());
        this.num_access_files = Double.parseDouble(tokens.nextToken());
        this.num_outbound_cmds = Double.parseDouble(tokens.nextToken());
        this.is_host_login = Double.parseDouble(tokens.nextToken());
        this.is_guest_login = Double.parseDouble(tokens.nextToken());
        this.count = Double.parseDouble(tokens.nextToken());
        this.srv_count = Double.parseDouble(tokens.nextToken());
        this.serror_rate = Double.parseDouble(tokens.nextToken());
        this.srv_serror_rate = Double.parseDouble(tokens.nextToken());
        this.rerror_rate = Double.parseDouble(tokens.nextToken());
        this.srv_rerror_rate = Double.parseDouble(tokens.nextToken());
        this.same_srv_rate = Double.parseDouble(tokens.nextToken());
        this.diff_srv_rate = Double.parseDouble(tokens.nextToken());
        this.srv_diff_host_rate = Double.parseDouble(tokens.nextToken());
        this.dst_host_count = Double.parseDouble(tokens.nextToken());
        this.dst_host_srv_count = Double.parseDouble(tokens.nextToken());
        this.dst_host_same_srv_rate = Double.parseDouble(tokens.nextToken());
        this.dst_host_diff_srv_rate = Double.parseDouble(tokens.nextToken());
        this.dst_host_same_src_port_rate = Double.parseDouble(tokens.nextToken());
        this.dst_host_srv_diff_host_rate = Double.parseDouble(tokens.nextToken());
        this.dst_host_serror_rate = Double.parseDouble(tokens.nextToken());
        this.dst_host_srv_serror_rate = Double.parseDouble(tokens.nextToken());
        this.dst_host_rerror_rate = Double.parseDouble(tokens.nextToken());
        this.dst_host_srv_rerror_rate = Double.parseDouble(tokens.nextToken());
        this.attack_types = attackTypesMap.labeling(tokens.nextToken());
    }

    public double label() {
        return this.attack_types;
    }
    
    public double[] vector() {
        return new double[]{
            this.duration, this.protocol_type, this.service, this.flag,
            this.src_bytes, this.dst_bytes, this.land, this.wrong_fragment,
            this.urgent, this.hot, this.num_failed_logins, this.logged_in,
            this.num_compromised, this.root_shell, this.su_attempted, this.num_root,
            this.num_file_creations, this.num_shells, this.num_access_files, this.num_outbound_cmds,
            this.is_host_login, this.is_guest_login, this.count, this.srv_count,
            this.serror_rate, this.srv_serror_rate, this.rerror_rate, this.srv_rerror_rate,
            this.same_srv_rate, this.diff_srv_rate, this.srv_diff_host_rate, this.dst_host_count,
            this.dst_host_srv_count, this.dst_host_same_srv_rate, this.dst_host_diff_srv_rate, this.dst_host_same_src_port_rate,
            this.dst_host_srv_diff_host_rate, this.dst_host_serror_rate, this.dst_host_srv_serror_rate, this.dst_host_rerror_rate,
            this.dst_host_srv_rerror_rate
        };
    }

    @Override
    public String toString() {
        return this.attack_types + " " + "1:" + this.duration + " " + "2:" + this.protocol_type + " " + "3:" + this.service + " " + "4:" + this.flag + " " + "5:" + this.src_bytes + " " + "6:" + this.dst_bytes + " " + "7:" + this.land + " " + "8:" + this.wrong_fragment + " " + "9:" + this.urgent + " "
                + "10:" + this.hot + " " + "11:" + this.num_failed_logins + " " + "12:" + this.logged_in + " " + "13:" + this.num_compromised + " " + "14:" + this.root_shell + " " + "15:" + this.su_attempted + " " + "16:" + this.num_root + " " + "17:" + this.num_file_creations + " " + "18:" + this.num_shells + " " + "19:" + this.num_access_files + " "
                + "20:" + this.num_outbound_cmds + " " + "21:" + this.is_host_login + " " + "22:" + this.is_guest_login + " " + "23:" + this.count + " " + "24:" + this.srv_count + " " + "25:" + this.serror_rate + " " + "26:" + this.srv_serror_rate + " " + "27:" + this.rerror_rate + " " + "28:" + this.srv_rerror_rate + " " + "29:" + this.same_srv_rate + " "
                + "30:" + this.diff_srv_rate + " " + "31:" + this.srv_diff_host_rate + " " + "32:" + this.dst_host_count + " " + "33:" + this.dst_host_srv_count + " " + "34:" + this.dst_host_same_srv_rate + " " + "35:" + this.dst_host_diff_srv_rate + " " + "36:" + this.dst_host_same_src_port_rate + " " + "37:" + this.dst_host_srv_diff_host_rate + " " + "38:" + this.dst_host_serror_rate + " " + "39:" + this.dst_host_srv_serror_rate + " "
                + "40:" + this.dst_host_rerror_rate + " " + "41:" + this.dst_host_srv_rerror_rate;
    }

    public static class Label extends HashMap<String, Integer> {

        public int labeling(String key) {
            if (this.containsKey(key)) {
                return this.get(key);
            } else {
                int label = this.size();
                this.put(key, label);
                return label;
            }
        }
    }

    public static void main(String[] args) {
        String k = "0,tcp,http,SF,215,45076,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,1,1,0.00,0.00,0.00,0.00,1.00,0.00,0.00,0,0,0.00,0.00,0.00,0.00,0.00,0.00,0.00,0.00,normal.";
        KDD kdd = new KDD(k);
        System.out.println(kdd);

        System.out.println(kdd.vector().length);
    }
}
