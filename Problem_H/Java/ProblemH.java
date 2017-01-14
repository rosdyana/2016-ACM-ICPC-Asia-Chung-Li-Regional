import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

//
// Copyright 2017 Rosdyana Kusuma.
// Licensed under the Apache License, Version 2.0
// Name : Rosdyana Kusuma 
// Student ID : 1056035
//

public class ProblemH {

	// Function compute the permanent of matrix
	public static int compute(int[][] m) {
		int[] p = new int[1 << m.length];
		p[0] = 1;
	 
		for (int i = 1; i < p.length; i++) {
			//r = n - (the number of 1 bits in i)
			int r = m.length;
			for (int j = 0; j < m.length; j++)
				r -= (i >> j) & 1;
	 
			for (int j = 0; j < m.length; j++)
			{
				//if i has a 1 bit at location j
				if ((i & (1 << j)) != 0)
				{
					//i ^ (1 << j) = i with the bit at j turned off
					int ind = i ^ (1 << j);
					p[i] = (p[i] + m[r][j] * p[ind]) % 10000;
				}
			}
		}
	 
		return p[p.length - 1];
	}
	// function add an element into 1D Array
	public static int[]addOneIntToArray(int[] initialArray , int newValue) {

	    int[] newArray = new int[initialArray.length + 1];
	    for (int index = 0; index < initialArray.length; index++) {
	        newArray[index] = initialArray[index];
	    }

	    newArray[newArray.length - 1] = newValue;
	    return newArray;
	}
	// Function set all elements of 2 dimension Array = 0
	public static void zeros2DArr(int[][] arr)
	{
		for(int i = 0; i< arr[0].length; i++)
			for(int j = 0; j<arr.length;j++)
			{
				arr[i][j] = 0;
			}
		
	}
	public static void main(String[] arg)
	{
		double startTime = System.currentTimeMillis();
		int numberOfGraph =0;
		/* example to check permanent of matrix
		int [][]ArrayCheck1 =new int[][]{
				  { 1, 1, 0, 1, 1, 0, 0, 0, 0, 0 },
				  { 0, 1, 0, 0, 0, 0, 0, 1, 0, 0 },
				  { 1, 0, 1, 0, 1, 0, 0, 1, 1, 0 },
				  { 0, 0, 0, 1, 1, 0, 1, 0, 0, 0 },
				  { 1, 0, 0, 1, 1, 0, 1, 1, 0, 1 },
				  { 0, 0, 0, 1, 0, 1, 1, 0, 1, 0 },
				  { 1, 0, 0, 0, 0, 0, 1, 1, 1, 0 },
				  { 0, 0, 0, 1, 0, 1, 0, 1, 0, 0 },
				  { 0, 0, 0, 0, 1, 1, 0, 1, 1, 0 },
				  { 1, 1, 0, 0, 0, 0, 0, 1, 0, 1 }
				  };
		int [][]ArrayCheck2 =new int[][]{
				  { 1, 1, 0, 1, 0, 1, 0, 0, 1, 1 },
				  { 1, 1, 0, 0, 0, 0, 0, 0, 0, 0 },
				  { 0, 1, 1, 0, 1, 0, 0, 0, 1, 1 },
				  { 0, 0, 0, 1, 1, 0, 0, 0, 1, 0 },
				  { 0, 0, 1, 0, 1, 1, 0, 0, 0, 0 },
				  { 1, 1, 0, 0, 0, 1, 0, 0, 0, 1 },
				  { 1, 0, 0, 0, 1, 1, 1, 0, 0, 0 },
				  { 0, 0, 0, 0, 0, 0, 0, 1, 0, 0 },
				  { 0, 0, 1, 1, 1, 0, 0, 0, 1, 0 },
				  { 0, 1, 1, 1, 0, 1, 0, 0, 1, 1 }
				  };
		//System.out.println("Perm A1="+compute(ArrayCheck1));
		//System.out.println("Perm A2="+compute(ArrayCheck2));
		 */
		ArrayList<String> scripts = new ArrayList<String>();
		// read input file and add value of everyLine of input file into ArrayList
		 try {
	            String input = "input.txt";
	            FileInputStream fis = new FileInputStream(new File(input));
	            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
	            String line;
	            while ((line = br.readLine()) != null) 
	            {
	                line = line.trim();
	                if (line != null && !line.isEmpty()) 
	                {
	                	scripts.add(line);
	                	
	                }
	            }
	            // Number of bipartite graph
	            numberOfGraph = Integer.parseInt(scripts.get(0));
	            //Number of vertices and edges of first bipartite graph
	            String[] array =(scripts.get(1)).split(" ");
	            int nVertex = Integer.parseInt(array[0]);
	            int nEdge = Integer.parseInt(array[1]);
	            // variable contains Pernament of bipartite graph
	            int []Pernament= new int[numberOfGraph];
	            //adjacencyArr is adjacency matrix of bipartite graph
	        	int [][]adjacencyArr = new int[nVertex/2][nVertex/2];
            	// set all element of adjacency matrix = 0
            	zeros2DArr(adjacencyArr);
            	// check when finish read value line for every bipartite graph
            	int check = nEdge+2;
	            for(int i=2; i<=scripts.size();i++)
	            {
	            
	            	if (i < check)
	            	{
	            		String []k = (scripts.get(i)).split(" ");
	            		adjacencyArr[(Integer.parseInt(k[0]))-1][(Integer.parseInt(k[1]))-1-(nVertex/2)] = 1;
	            			
	            	}
	            	if(i == check)
	            	{
	            	
	            		//calculate and save Pernament value
	            		Pernament=addOneIntToArray(Pernament,compute(adjacencyArr));
		            	//System.out.println("compute="+compute(adjacencyArr));
		            	// output 0 if there is an odd number of perfect matchings 
		            	//on the input graph, and output 1 otherwise
		            	
		            	if((compute(adjacencyArr)%2)==1)
		            	{
		            		System.out.println("0");
		            	}
		            	else
		            	{
		            		System.out.println("1");
		            	}
		            	zeros2DArr(adjacencyArr);
		            	// stop get value if finish read input file
		            	if(i<scripts.size())
		            	{
			            	String []k = (scripts.get(i)).split(" ");
			            	nEdge = Integer.parseInt(k[1]);
			            	check = check + nEdge+1;
		            	}
	            	}          	
	            }            
	            //System.out.println("finish");
	            br.close();
				double endTime   = System.currentTimeMillis();
				double totalTime = endTime - startTime;
				System.out.println(totalTime/1000);	
	        } catch (IOException e) {
	            e.printStackTrace();
	        }	
	}

}
