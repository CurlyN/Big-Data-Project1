

	import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Mapper.Context;



	public class AverageMapper extends Mapper<Object, Text, IntWritable, IntIntPair> { 
		private final IntWritable one = new IntWritable(1);
	    private IntWritable data = new IntWritable();
	   
	     
	    public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
	    	
	    	String dump = value.toString();
	    	dump = dump.replaceAll("\t", " ");
	    	
	    	String[] spl = dump.split(" ");
	        
			
	    	IntIntPair pair = new IntIntPair(Integer.parseInt(spl[0]),Integer.parseInt(spl[1]));
	    	data.set(11111);
			
			context.write(data, pair);
	        }
	}
	



	