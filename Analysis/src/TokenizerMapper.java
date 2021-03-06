import java.io.IOException;
import java.util.StringTokenizer;
import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;



public class TokenizerMapper extends Mapper<Object, Text, IntWritable, IntWritable> { 
	private final IntWritable one = new IntWritable(1);
	private IntWritable data = new IntWritable();
    int legthOftweett;
    
	public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
		// Format per tweet is id;date;hashtags;tweet;
		String dump = value.toString();
		if(StringUtils.ordinalIndexOf(dump,";",4)>-1){
			int startIndex = StringUtils.ordinalIndexOf(dump,";",3) + 1;
			String tweet = dump.substring(startIndex,dump.lastIndexOf(';'));
			tweet.replaceAll("\\W", " ");	
			
			legthOftweett=tweet.length();
			
			 if (legthOftweett<=140){
			data.set(legthOftweett);
			context.write(data, one);}
		}
	}





}
