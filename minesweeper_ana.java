//Anna Kravtsova CSCD 350
//Team Triploblastic

import java.io.*;
import java.util.*;


public class minesweeper_ana{
	private static char[][] currentMap;
	private static String[][] printedMap;

	public static void main(String[] args)throws Exception{
		Scanner fin = new Scanner(System.in);
		//Scanner fin = new Scanner(new File("maps.txt"));
		parseInput(fin);
	}
	
	private static void parseInput(Scanner fin){

		int whichFeild = 0;

		do{
			whichFeild++;

			int r = fin.nextInt();
			int c = fin.nextInt();

			if(r < 1 || c < 1)
				break;

			fin.nextLine();

			currentMap = new char[r][c];
			printedMap = new String[r][c];

			for (int x = 0; x < r; x++){//make current map

				char[] line = fin.nextLine().toCharArray();
				int cur = 0;
				for (char a : line){
					currentMap[x][cur] = a;
					cur++;
				}
			}
			findMines(r, c);
			printOutput(whichFeild);

		}while(true);//(r != 0 || c != 0);
	}

	private static void findMines(int r, int c){
		for(int x = 0; x < r; x++){
			for(int y = 0; y < c; y++){

				if(currentMap[x][y] == '*')
					printedMap[x][y] = Character.toString(currentMap[x][y]);
				else
					printedMap[x][y] = countSurrounding(x, y, r, c);
			}
		}
	}

	private static String countSurrounding(int x, int y, int r, int c){
		int total = 0;
		//1 2 3 y - 1
		//4   5
		//6 7 8 y + 1
		//x-1 x+1

		//1
		if((x - 1 >= 0 && y - 1 >= 0) && currentMap[x - 1][y - 1] == '*')
			total++;
		//2
		if((y - 1 >= 0) && currentMap[x][y - 1] == '*')
			total++;
		//3
		if((x + 1 < r && y - 1 >= 0) && currentMap[x + 1][y - 1] == '*')
			total++;
		//4
		if((x - 1 >= 0) && currentMap[x - 1][y] == '*')
			total++;
		//5
		if((x + 1 < r) && currentMap[x + 1][y] == '*')
			total++;
		//6
		if((x - 1 >= 0 && y + 1 < c) && currentMap[x - 1][y + 1] == '*')
			total++;
		//7
		if((y + 1 < c) && currentMap[x][y + 1] == '*')
			total++;
		//8
		if((x + 1 < r && y + 1 < c) && currentMap[x + 1][y + 1] == '*')
			total++;
			
		return Integer.toString(total);
	}
	
	private static void printOutput(int whichFeild){
		PrintStream pout = new PrintStream(System.out);

		pout.println("Field #" + whichFeild + ":");

		for (String[] array : printedMap){
		
			for (String element : array){
			
				pout.print(element);
			}
			pout.println();
		}	
		pout.println();
	}
}