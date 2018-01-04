
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class WeightedScheduleEB006374 {
	String content="";
	int count=0;

	ArrayList<Integer> weights = new ArrayList<>();
	ArrayList<Integer> starts = new ArrayList<>();
	ArrayList<Integer> finishes = new ArrayList<>();
	ArrayList<Integer> ps = new ArrayList<>();
	ArrayList<Integer> ms = new ArrayList<>();
	String theSolution="";

	public WeightedScheduleEB006374(){}


	public void readFile(String filename)
	{
		String content = null;
		File file = new File(filename); 
		FileReader reader = null;
		try {
			reader = new FileReader(file);
			char[] chars = new char[(int) file.length()];
			reader.read(chars);
			content = new String(chars);
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(reader !=null){try {
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}}
		}
		this.content=content;
		//    System.out.println(this.content);
		//    System.out.println(this.content.length());
	}


	public static boolean isNumeric(String str)  
	{  
		try  
		{  
			int d = Integer.parseInt(str);  
		}  
		catch(NumberFormatException nfe)  
		{  
			return false;  
		}  
		return true;  
	}


	public void parseString()
	{
		String delim1 = "\n";
		StringTokenizer tok1 = new StringTokenizer(this.content, delim1,true);

		while (tok1.hasMoreTokens()) {
			String token1 = tok1.nextToken();
			//System.out.println(token1+ " "+"vv");
			getNumbers(token1);

		}

	}

	public int ComputeOpt(int j)
	{
		if(j==-1)
			return 0;
		else
			if((weights.get(j)+ComputeOpt(ps.get(j)))>ComputeOpt(j-1))
			{


				return weights.get(j)+ComputeOpt(ps.get(j));

			} else
			{

				return ComputeOpt(j-1);
			}
	}

	public void getNumbers(String str)
	{
		if(!isNumeric(""+str.charAt(str.length()-1))) str=str.substring(0, str.length()-1);
		String delim = " ";
		StringTokenizer tok = new StringTokenizer(str, delim);

		int number=0;
		while (tok.hasMoreTokens()) {
			number++;
			String token = tok.nextToken();

			if(number==1) weights.add(Integer.parseInt(token));
			if(number==2) starts.add(Integer.parseInt(token));
			if(number==3) finishes.add(Integer.parseInt(token));
		}

		this.count=weights.size();

	}

	public void sortAll()
	{
		for(int i=0;i<finishes.size()-1;i++)
		{
			int min=i;

			for(int j=i+1;j<finishes.size();j++)
				if(finishes.get(j)<finishes.get(min))
					min=j;

			int temp=finishes.get(min);
			finishes.set(min, finishes.get(i));
			finishes.set(i, temp);

			temp=weights.get(min);
			weights.set(min, weights.get(i));
			weights.set(i, temp);

			temp=starts.get(min);
			starts.set(min, starts.get(i));
			starts.set(i, temp);

		}
	}

	public void setPs()
	{

		ps.add( -1);


		for(int i=1;i<count;i++)
		{
			int p=0;
			int index=-1;
			for (int j=0;j<i;j++)
			{
				if(finishes.get(j)<=starts.get(i) && p<finishes.get(j)) 
				{p=finishes.get(j);
				index=j;
				}
			}
			ps.add( index);
		}



	}


	public void fillMs()
	{
		for(int i=0;i<count;i++)
		{
			ms.add(ComputeOpt(i));
		}
	}

	public void findSolution(int j)
	{
		if(j==-1) return;
		else if(j==0)
		{
			theSolution=j+" "+theSolution;
			return;
		}
		else {
			int mGet=0;
			if(ps.get(j)!=-1) 
				mGet=ms.get(ps.get(j));

			if((weights.get(j)+mGet)>ms.get(j-1))
			{
				theSolution=j+" "+theSolution;
				findSolution(ps.get(j));
			} 


			else findSolution(j-1);
		}

	}


	public void solveIt()
	{
		this.parseString();
		this.sortAll();
		this.setPs();
		this.fillMs();
		System.out.println("The value : "+this.ComputeOpt(this.count-1));
		this.findSolution(this.count-1);
		System.out.println("The jobs are : "+this.theSolution);
	}


	public static void main(String[] args) {

		WeightedScheduleEB006374 schedule=new WeightedScheduleEB006374();
		schedule.readFile("WeightedIntervals.txt");
		schedule.solveIt();
		//System.out.println(schedule.finishes);
	}

}
