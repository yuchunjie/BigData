import org.apache.spark.SparkContext
import org.apache.spark.mllib.classification.{SVMModel, SVMWithSGD}
import org.apache.spark.mllib.evaluation.BinaryClassificationMetrics
import org.apache.spark.mllib.regression.LabeledPoint
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.util.MLUtils

// Load training data in LIBSVM format.
<<<<<<< HEAD
//val data = MLUtils.loadLibSVMFile(sc, "kdd99_labeling.txt")
val data = MLUtils.loadLibSVMFile(sc, "sample_libsvm_data.txt")
=======
val data = MLUtils.loadLibSVMFile(sc, "kdd99_labeling.txt")
//val data = MLUtils.loadLibSVMFile(sc, "sample_libsvm_data.txt")
>>>>>>> 8c80c93e54d32f6f4058224e44fa07858b3646bf

// Split data into training (60%) and test (40%).
val splits = data.randomSplit(Array(0.6, 0.4), seed = 11L)
val training = splits(0).cache()
val test = splits(1)

// Run training algorithm to build the model
val numIterations = 100
val model = SVMWithSGD.train(training, numIterations)

// Clear the default threshold.
model.clearThreshold()

// Compute raw scores on the test set.
val scoreAndLabels = test.map { point =>
  val score = model.predict(point.features)
  (score, point.label)
}

// Get evaluation metrics.
val metrics = new BinaryClassificationMetrics(scoreAndLabels)
val auROC = metrics.areaUnderROC()

println("Area under ROC = " + auROC)

// Save and load model
model.save(sc, "./SVM_Model")
val sameModel = SVMModel.load(sc, ./SVM_Model")




