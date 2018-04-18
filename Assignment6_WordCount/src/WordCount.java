import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

public class WordCount{

	static IntWritable intWritable = new IntWritable(1);

	public static class ClicksPerIpMap extends Mapper<LongWritable, Text , Text, IntWritable>{
		
		Text mapKey = new Text();
		
		public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException{
			String line = value.toString();
			StringTokenizer tokenizer = new StringTokenizer(line);
			while (tokenizer.hasMoreTokens()) {
				mapKey.set(tokenizer.nextToken());
				context.write(mapKey, intWritable);
			}
		}
	}
	
	public static class ClicksPerIpReduce extends Reducer<Text, IntWritable, Text, IntWritable>{
		
		public void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException{
			int sum = 0;
			for (IntWritable val : values) 
				sum += val.get();
			context.write(key, new IntWritable(sum));
		}
		
	}
	
	public static void main(String args[]) throws IOException, ClassNotFoundException, InterruptedException{
		
		Configuration configuration = new Configuration();
		Job job = Job.getInstance(configuration, "WordCount");
		
		job.setJarByClass(WordCount.class);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		
		job.setMapperClass(ClicksPerIpMap.class);
		job.setReducerClass(ClicksPerIpReduce.class);
		job.setCombinerClass(ClicksPerIpReduce.class);
		
		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		job.waitForCompletion(true);
		
	}
	
}
