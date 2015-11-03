import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;


public class AverageReducer extends Reducer<IntWritable, IntIntPair, IntWritable, IntWritable>{
	private IntWritable result = new IntWritable();

	

	 public void reduce(IntWritable key, Iterable<IntIntPair> values, Context context)

              throws IOException, InterruptedException {

        int sum = 0;
        int size=0;
                                                                                                                                                                                                                                                                                                                              
     
        for (IntIntPair value : values) {
        	
            sum+= value.getFirst().get()*value.getSecond().get();
            size+=value.getSecond().get();

        }
        
      
               result.set(sum/size);
             

               context.write(key, result);

    }


}
