package main;

import java.util.*;
import main.Population;


class GeneticAlgo{
	
	public static void main(String args[]) {
	
		GeneticAlgo.runAlgo(20,"110110");
	}
	

	@SuppressWarnings("unchecked")
	protected static void runAlgo(int popSize, String solution) {
	
	Random rand = new Random();
	int flag = 0;	
	Population myPop = new Population(popSize);
	List<int[]> children = new ArrayList<int[]>();
	
	myPop.initPopulation();
	myPop.initFitness();
	myPop.calcFitness(solution);
	myPop.initRoulette();
	
	for(int i=0;i<popSize;i++) {
		System.out.println("\n\n"+i+"th chromosome is:");
		for(int j=0;j<6;j++) {
		System.out.print(myPop.getGene(myPop.getChromosome(i),j));
		}
		
	}
	
	while(true) {
		
		for(int i=0;i<myPop.pSize;i++)
			myPop.setSelected(i, 0);
		
		System.out.println("//////");
		
		for(int i=0;i<myPop.pSize;i++) {
			if(myPop.fitness[i] == 6 )
				System.out.println("!!!Chromosome "+ i +" with fitness "+myPop.fitness[i]);
		}	
		while(true) {
			
			
			//flag = 0;
			
		
			int parent1;
			int parent2;
			
			do {
				parent1 = myPop.selection();
				System.out.println("/");
			}while(myPop.getSelected(parent1) == 1);	
		
			do {
				parent2 = myPop.selection();
			}while((parent2 == parent1) || (myPop.getSelected(parent2) == 1) );
		
			myPop.setSelected(parent1, 1);
			myPop.setSelected(parent2, 1);
			
			System.out.println("\n\n\n"+parent1+"  "+parent2);
			
			
			//Crossover at 70% chance
			if (rand.nextInt(10)<7) {
				
				
				children = myPop.crossOverSingle(parent1, parent2);
			
				/*for (int[] child : children) {
					
					System.out.println("\n");
					
					for (int i = 0; i < 6; i++) {
						
						System.out.print(child[i]);
					}
				}*/	
			
			}
			else {
			
				myPop.setSelected(parent1, 0);
				myPop.setSelected(parent2, 0);
			}
			//System.exit(0);
			
			myPop.addToPop(children, solution);
			System.out.println("//");
			flag = 0;
			for(int i=0;i<myPop.pSize;i++) {
				System.out.println("///");
				if(myPop.getSelected(i) == 0) {
					flag = 1;
					System.out.println("///");
					break;
				}	
			}
			
			
			for(int i =0;i<myPop.pSize;i++)
				System.out.println("Fitness of"+i+"th Chromosome is: "+myPop.fitness[i]);
			
			
			
			
			if(flag == 0) {
				System.out.println("////");
				break;
			}
			
		}
		System.out.println("/////");
	
	  }
	}
}