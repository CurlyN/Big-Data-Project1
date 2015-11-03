import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class TeamGoMapper extends Mapper<Object, Text, Text, IntWritable> {
	private final IntWritable one = new IntWritable(1);
	private Text data = new Text();
	int i = 0;
	ArrayList<String[]> countres = new ArrayList<String[]>();

	public void map(Object key, Text value, Context context)
			throws IOException, InterruptedException {

		// Format per tweet is id;date;hashtags;tweet;
		String dump = value.toString();
		if (StringUtils.ordinalIndexOf(dump, ";", 4) > -1) {
			int startIndex = StringUtils.ordinalIndexOf(dump, ";", 2) + 1;
			int lstIndex = StringUtils.ordinalIndexOf(dump, ";", 3);
			String hastag = dump.substring(startIndex, lstIndex);

			hastag = hastag.toLowerCase();

			// split 3d part in hashtags
			String[] hashtags = hastag.split(" ");

			
			for (int i = 0; i < hashtags.length; i++) {
			
				if (hashtags[i].startsWith("team")) {
					hashtags[i] = hashtags[i]
							.substring(4, hashtags[i].length());

				} else {
					if (hashtags[i].startsWith("go")) {
						hashtags[i] = hashtags[i].substring(2,
								hashtags[i].length());

					} else { 
						if (hashtags[i].startsWith("goteam")) {
							hashtags[i] = hashtags[i].substring(7,
									hashtags[i].length());

						}
					else {
						hashtags[i] = "";
					}
					}
				}
					for (int j = 0; j < countres.size(); j++) {
						
						if ((hashtags[i].equals(countres.get(j)[0]))
								|| (hashtags[i].equals(countres.get(j)[1]))) {
							
							data.set(countres.get(j)[0]);
							context.write(data, one);

						}

					
				}
			}

		}
	}

	@Override
	protected void setup(Context context) throws IOException,
			InterruptedException {
		// We know there is only one cache file, so we only retrieve that URI
		URI fileUri = context.getCacheFiles()[0];
		FileSystem fs = FileSystem.get(context.getConfiguration());
		FSDataInputStream in = fs.open(new Path(fileUri));
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String line = null;
		try {
			while ((line = br.readLine()) != null) {
				line=line.toLowerCase();
				line=line.replaceAll(" ", "");
				countres.add(line.split(","));
			}
			br.close();
		} catch (IOException e1) {
		}
		super.setup(context);
	}
}
