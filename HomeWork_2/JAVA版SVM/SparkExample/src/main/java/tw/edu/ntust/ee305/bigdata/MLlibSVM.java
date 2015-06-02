/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tw.edu.ntust.ee305.bigdata;

import java.util.function.Consumer;
import org.apache.spark.api.java.*;
import org.apache.spark.mllib.linalg.Vectors;
import tw.edu.ntust.ee305.bigdata.parse.KDD;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.mllib.classification.*;
import org.apache.spark.mllib.evaluation.BinaryClassificationMetrics;
import org.apache.spark.mllib.regression.LabeledPoint;
import org.apache.spark.SparkConf;
import scala.Tuple2;

public class MLlibSVM {

    public static void main(String[] args) {

        if (args.length < 2) {
            System.err.println("Usage: <file> <clusters>");
            System.exit(1);
        }

        SparkConf conf = new SparkConf().setAppName("SVM Classifier Example");
//        conf = conf.set("spark.executor.memory", "4g");
        JavaSparkContext jsc = new JavaSparkContext(conf);

        // Load and parse data
        JavaRDD<String> gzip = jsc.textFile(args[0]);
        System.out.println("===== parse =====");
        
//        gzip = gzip.sample(false, 0.1, 11L);
        
        JavaRDD<LabeledPoint> data = gzip.map(
                new Function<String, LabeledPoint>() {
                    public LabeledPoint call(String s) {
                        KDD kdd = new KDD(s);
                        return new LabeledPoint(kdd.label(), Vectors.dense(kdd.vector()));
                    }
                }
        );
        
//        System.out.println("===== print =====");
//        data.collect().forEach(
//                new Consumer<LabeledPoint>() {
//                    @Override
//                    public void accept(LabeledPoint t) {
//                        System.out.println(t.toString());
//                    }
//                }
//        );

        // Split initial RDD into two... [60% training data, 40% testing data].
        JavaRDD<LabeledPoint> training = data.sample(false, 0.6, 11L);
        training.cache();
        JavaRDD<LabeledPoint> test = data.subtract(training);
        
        System.out.println(data.count());
        System.out.println(training.count());
        System.out.println(test.count());

        // Run training algorithm to build the model.
        int numIterations = 100;
        int classes = 42;
        LogisticRegressionWithLBFGS lr = new LogisticRegressionWithLBFGS();
        lr.optimizer().setNumIterations(numIterations);
        lr.setNumClasses(classes);
        final LogisticRegressionModel model = lr.run(training.rdd());

        // Clear the default threshold.
        model.clearThreshold();

        // Compute raw scores on the test set.
        JavaRDD<Tuple2<Object, Object>> scoreAndLabels = test.map(
                new Function<LabeledPoint, Tuple2<Object, Object>>() {
                    public Tuple2<Object, Object> call(LabeledPoint p) {
                        Double score = model.predict(p.features());
                        return new Tuple2<Object, Object>(score, p.label());
                    }
                }
        );

        // Get evaluation metrics.
        BinaryClassificationMetrics metrics
                = new BinaryClassificationMetrics(JavaRDD.toRDD(scoreAndLabels));
        
        metrics.pr().toJavaRDD().collect().forEach(
                new Consumer<Tuple2<Object, Object>>() {
                    @Override
                    public void accept(Tuple2<Object, Object> t) {
                        System.out.println("pr" + t.toString());
                    }
                }
        );

        double auROC = metrics.areaUnderROC();

        System.out.println("Area under ROC = " + auROC);

        jsc.stop();
    }
}
