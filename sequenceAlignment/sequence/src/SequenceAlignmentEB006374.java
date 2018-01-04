import java.util.Scanner;

public class SequenceAlignmentEB006374 {

	public int costOfGap=0;
	public int costOfVV=0;
	public int costOfCC=0;
	public int costOfVC=0;

	public String string1="";
	public String string2="";
	public int[][] M;
	public int m=0;
	public int n=0;
	public static char[] vowels={'a','e','i','u','o','ü','ö','ý'};

	public char gapChar='_';
	public String string1Modified="";
	public String string2Modified="";

	public SequenceAlignmentEB006374(){


	}

	public static boolean isVowel(char theChar)
	{
		for(int i=0;i<vowels.length;i++)
			if(vowels[i]==theChar) return true;

		return false;
	}


	public int matchChars(char char1,char char2)
	{
		if (char1==char2) return 0;
		else
		{
			if(isVowel(char1)&& isVowel(char2)) return costOfVV;
			else	if(!isVowel(char1)&& isVowel(char2)) return costOfVC;
			else if(isVowel(char1)&& !isVowel(char2)) return costOfVC;
			else return costOfCC;
		}
	}

	public int min3(int a,int b, int c)
	{
		int min1=0;
		if(a<b) min1=a; else min1=b;

		if(min1<c) return min1; else return c;
	}

	public int max3(int a,int b, int c)
	{
		int max1=0;
		if(a>b) max1=a; else max1=b;

		if(max1>c) return max1; else return c;
	}
	
	public int align()
	{
		for (int i=0;i<m;i++)
			M[i][0]=i*costOfGap;
		for (int j=0;j<n;j++)
			M[0][j]=j*costOfGap;

		for (int i=1;i<m;i++)
			for (int j=1;j<n;j++)
			{
				M[i][j]=min3(matchChars(string1.charAt(i-1),string2.charAt(j-1))+M[i-1][j-1],costOfGap+M[i-1][j],costOfGap+M[i][j-1]);



			}

		return M[m-1][n-1];
	}

	public void modifyStrings()
	{
		int i=m-1;
		int j=n-1;

		while(i>0 || j>0)
		{
		//	System.out.println(i+" "+j);
			if(i>0 && j>0)
			{
				
				
				if(M[i][j]==matchChars(string1.charAt(i-1),string2.charAt(j-1))+M[i-1][j-1])
				{
					string1Modified=string1.charAt(i-1)+""+string1Modified;
					string2Modified=string2.charAt(j-1)+""+string2Modified;
					i--;j--;
				} else if(M[i][j]==costOfGap+M[i][j-1])
				{
					string2Modified=string2.charAt(j-1)+""+string2Modified;
					string1Modified=gapChar+string1Modified;
					j--;
				} else
				{
					string1Modified=string1.charAt(i-1)+""+string1Modified;
					string2Modified=gapChar+string2Modified;
					i--;
				}


			} else if(i ==0)
			{
				
				while(j>0)
				{
					string2Modified=gapChar+""+string2Modified;
					j--;
				}
				break;

			} else
			{
				while(i>0)
				{
					string1Modified=gapChar+""+string1Modified;
					i--;
				}
				break;
			}
		}



	}


	public void printFinalStrings()
	{
		System.out.println(string1Modified);
		System.out.println(string2Modified);
		

			
	}


	public void getInfo(){

		Scanner scanner = new Scanner(System.in);

		System.out.print("Enter the cost for gap: ");
		costOfGap=scanner.nextInt();
		System.out.println();
		System.out.print("Enter the costs for matching a vowel with a different vowel: ");
		costOfVV=scanner.nextInt();
		System.out.println();
		System.out.print("Enter the cost for matching a consonant with a different consonant: ");
		costOfCC=scanner.nextInt();
		System.out.println();
		System.out.print("Enter the cost for matching a vowel and consonant with each other: ");
		costOfVC=scanner.nextInt();
		System.out.println();
		System.out.print("Enter the first string: ");
		scanner.nextLine();
		System.out.println();
		string1=scanner.nextLine();

		System.out.print("Enter the second string: ");
		string2=scanner.nextLine();
		System.out.println();

		scanner.close();

		m=string1.length()+1;
		n=string2.length()+1;
		M=new int[m][n];
	}

	public static void main(String[] args) {

		SequenceAlignmentEB006374 sq=new SequenceAlignmentEB006374();
		sq.getInfo();
		System.out.println(sq.align());
		sq.modifyStrings();
		sq.printFinalStrings();
		
		

	}


}
