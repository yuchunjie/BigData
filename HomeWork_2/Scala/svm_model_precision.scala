import org.apache.spark.SparkContext
import org.apache.spark.mllib.classification.{LogisticRegressionWithLBFGS, LogisticRegressionModel}
import org.apache.spark.mllib.evaluation.MulticlassMetrics
import org.apache.spark.mllib.regression.LabeledPoint
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.util.MLUtils

// Load training data in LIBSVM format.
//val data = MLUtils.loadLibSVMFile(sc, "kdd99_labeling.txt")
//val data = MLUtils.loadLibSVMFile(sc, "kdd99_labeling_10_percent.txt")
val data = MLUtils.loadLibSVMFile(sc, "kdd99_labeling_1_percent.txt")
//val data = MLUtils.loadLibSVMFile(sc, "sample_libsvm_data.txt")

// Split data into training (60%) and test (40%).
val splits = data.randomSplit(Array(0.6, 0.4), seed = 11L)
val training = splits(0).cache()
val test = splits(1)

// Run training algorithm to build the model
val model = new LogisticRegressionWithLBFGS().setNumClasses(42).run(training)

// Compute raw scores on the test set.
val predictionAndLabels = test.map { case LabeledPoint(label, features) =>
  val prediction = model.predict(features)
  (prediction, label)
}

// Get evaluation metrics.
val metrics = new MulticlassMetrics(predictionAndLabels)
val precision = metrics.precision
println("Precision = " + precision)

val recall = metrics.recall
println("Recall = " + recall)

// Save and load model
model.save(sc, "./SVM_Precision")
val sameModel = LogisticRegressionModel.load(sc, "./SVM_Precision")
