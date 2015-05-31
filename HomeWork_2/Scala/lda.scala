import org.apache.spark.mllib.clustering.LDA
import org.apache.spark.mllib.linalg.Vectors

// Load and parse the data
val data = sc.textFile("kdd99_labeling_1_percent_lda.txt")
//val data = sc.textFile("sample_lda_data.txt")
val parsedData = data.map(s => Vectors.dense(s.trim.split(' ').map(_.toDouble)))
// Index documents with unique IDs
val corpus = parsedData.zipWithIndex.map(_.swap).cache()

// Cluster the documents into 22 (kdd99 attack types) topics using LDA
val ldaModel = new LDA().setK(22).run(corpus)

// Output topics. Each is a distribution over words (matching word count vectors)
println("Learned topics (as distributions over vocab of " + ldaModel.vocabSize + " words):")
val topics = ldaModel.topicsMatrix
for (topic <- Range(0, 22)) {
  print("Topic " + topic + ":")
  for (word <- Range(0, ldaModel.vocabSize)) { print(" " + topics(word, topic)); }
  println()
}
