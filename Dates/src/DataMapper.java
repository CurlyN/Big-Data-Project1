		import java.io.IOException;
		import java.util.StringTokenizer;
		import org.apache.commons.lang.StringUtils;
		import org.apache.hadoop.io.IntWritable;
		import org.apache.hadoop.io.Text;
		import org.apache.hadoop.mapreduce.Mapper;



		public class DataMapper extends Mapper<Object, Text, Text, IntWritable> { 
			private final IntWritable one = new IntWritable(1);
			private Text data = new Text();
		    int legthOftweett;
		    
			public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
				// Format per tweet is id;date;hashtags;tweet;
				String dump = value.toString();
				if(StringUtils.ordinalIndexOf(dump,";",2)>-1){
					int startIndex = StringUtils.ordinalIndexOf(dump,";",1) + 1;
					int lstIndex = StringUtils.ordinalIndexOf(dump,";",2) + 1;
					String tweet = dump.substring(startIndex,lstIndex);
					

					tweet = tweet.substring(0, 10);
					data.set(tweet);
					context.write(data, one);
				}
			}





		}

		
		
