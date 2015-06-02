/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.edu.ntust.ee305.bigdata;

import java.util.function.Consumer;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.apache.spark.api.java.*;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.mllib.clustering.KMeans;
import org.apache.spark.mllib.clustering.KMeansModel;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.linalg.Vectors;
import org.apache.spark.SparkConf;
import org.apache.spark.rdd.RDD;

public class Ex2 {

    public static void main(String[] args) {

        if (args.length < 2) {
            System.err.println("Usage: <file> <clusters>");
            System.exit(1);
        }

        SparkConf conf = new SparkConf().setAppName("K-means Example");
        conf = conf.set("spark.executor.memory", "4g");
        JavaSparkContext sc = new JavaSparkContext(conf);

        // Load and parse data
//        String path = "data/mllib/kmeans_data.txt";
        JavaRDD<String> data = sc.textFile(args[0]);
        JavaRDD<String> filter = data.filter(new Function<String, Boolean>() {

            @Override
            public Boolean call(String s) throws Exception {
                try {
                    String[] sarray = s.split(" ");
                    for (int i = 0; i < sarray.length; i++) {
                        Double.parseDouble(sarray[i]);
                    }
                } catch (java.lang.NumberFormatException ex) {
                    return false;
                }
                return true;
            }
        });
        JavaRDD<Vector> parsedData = filter.map(
                new Function<String, Vector>() {
                    public Vector call(String s) {
                        System.out.println(s);
                        String[] sarray = s.split(" ");
                        double[] values = new double[sarray.length];
                        for (int i = 0; i < sarray.length; i++) {
                            values[i] = Double.parseDouble(sarray[i]);
                        }
                        return Vectors.dense(values);
                    }
                }
        );
//        parsedData.cache();

        // Cluster the data into two classes using KMeans
        int numClusters = Integer.parseInt(args[1]);
        int numIterations = 20;
        KMeansModel clusters = KMeans.train(parsedData.rdd(), numClusters, numIterations);

//        System.out.println(parsedData.);
        // Evaluate clustering by computing Within Set Sum of Squared Errors
        double WSSSE = clusters.computeCost(parsedData.rdd());
        System.out.println("Within Set Sum of Squared Errors = " + WSSSE);
        for (int i = 0; i < clusters.clusterCenters().length; i++) {
            System.out.println(clusters.clusterCenters()[i]);
        }

        JavaRDD<Integer> predict = clusters.predict(parsedData);
        predict.collect().forEach(
                new Consumer<Integer>() {
                    @Override
                    public void accept(Integer t) {
                        System.out.print(t + ", ");
                    }
                }
        );

        sc.stop();
    }
}
