#Big Data Processin. Project 1
The goal of this of this project is to process 18 millions tweets about Olympic games.
The project is made on the base of Apache Hadoop

##Analysis
The first part on the project is aimed to create a Histogram plot that depicts the distribution of sizes (measured in number of characters) among the Twitter dataset.

The project containes three files: the Mapper (TokenizerMapper.java), the Reducer (IntSumReducer.java) and the main class (TwitterAnalysisEx1.java).
The mapper is reading tweets from the file and parse it to take the last part of the tweet. The reduce file taking pairs of <key, value> from the mapper, sum up the values with the same key and sending the output pair <key, value> where the key is the length of the tweet and the value is the number of tweets with that length.

##Averages
The Second part Averages finds the average length of the Twitter dataset.
The task is solved with creation Mpper and Reduser with using extra class InitPair to process the middle results.

## Time analysis
The task is to plot a time series with the number of Tweets that were posted each day of the event.


##TeamGo
The goal of this part is to find most supported country among all tweets. 
The result of that program is the file with two columns: the country and the number of its supported
hashtags.
