/**
 * A class to count the number of occurrences of an element in a 2-D array
 * @author Paxton Kerber
 * CSC 385
 * Assignment 4 Searching 
 */
public class CountOccurrence {
	
	/**
	 * This method counts the number of occurrences of query in the array.
	 * You have to implement this method. You may use any helper method you want.
	 * @param query
	 * @return
	 */
	public static int count(int[][]array, int query)		//total: BigO( n^2 + nlog(n) )
	{
		int count=0;
		int startIndex;
		for (int column=0; column<=array[0].length-1; column++)		//this = BigO(n)
		{
				if (array[0][column]<query || query>array[array.length-1][column])	//skips all searches if query is not in range of column
				{
					startIndex=primativeSearch(array, column, query);	//BigO(log(n))
					if (startIndex!=-1)
					{
						count+=sequentialCountLeftandRight(array, column, query, startIndex);	
															//this method = BigO(n)
					}
				}
				else if (array[0][column]==query) //skips Binary Search if first term == query
				{
					startIndex=0;
					count+=sequentialCountLeftandRight(array, column, query, startIndex);	
															//this method = BigO(n)
				}
		}
		return count;
	}
	
	public static int primativeSearch(int arr[][], int column, int query)	
															//Binary Search so it will have BigO(log(n))
	{
		
		int high=arr.length-1;
		int low=0;
		int mid;
		while(low<=high)
		{
			mid=(high+low)/2;
			if (arr[mid][column] < query)
			{
				low=mid+1;
			}
			else if (arr[mid][column]> query)
			{
				high=mid-1;
			}
			else if (arr[mid][column]==query)
			{
				return mid;
			}
		}
		return -1;
	}
	
	public static int sequentialCountLeftandRight(int arr[][], int column, int query, int index)
															//total this method = BigO(2n) --> BigO(n)
	{
		int count=0;
		//goes to first instance of query value and minimum index value=0
		while (index>0)										// this loop will have a max of BigO(n)
		{
			if (arr[index-1][column]==query)
			{
				index--;
			}
			else
			{
				break;
			}
		}
		//counts sequentially
		while (index<arr.length)							//this loop max = BigO(n)
		{
			if (arr[index][column]==query)
			{
				count++;
				index++;
			}
			else
			{
				break;
			}
		}
		return count;
	}
}
