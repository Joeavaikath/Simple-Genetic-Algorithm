package main;

import java.util.*;


class Population{
	
	int pSize;
	Random rand = new Random(); 
	int Population[][];
	int fitness[];
	int selected[];
	int i,j;
	int rouletteWheel[];
	int rouletteSize = 0;
	List<int[]> children = new ArrayList<int[]>();
	
	public Population(int populationSize) {
		
		pSize = populationSize;
		Population  = new int[pSize][6];
		fitness = new int [pSize];
		selected = new int[pSize];
	}
	
	public void initPopulation() {
		
		for(i=0;i < getPopulation();i++) {
			for(int j=0;j<6;j++) {
				
			Population[i][j] = rand.nextInt(2);
			//System.out.println(Population[i][j]);
		
			}
		}
		
	}
	
	public void initFitness() {
		
		for(i=0;i<pSize;i++) {
			
			fitness[i] = 0;
		}
	}
	
	public void calcFitness(String solution) {
		
		for(i=0;i<pSize;i++) {
			for(j=0;j<6;j++) {
				
				//System.out.println(solution.charAt(j));
				String str = ""+ solution.charAt(j);
				if(getGene( getChromosome(i) ,j) == Integer.parseInt(str) ) {
					
					//System.out.println(getGene( getChromosome(i) ,j));
					fitness[i] += 1;
				}
			}
		System.out.println("Fitness of"+i+"th Chromosome is: "+fitness[i]);
		}
	}
	
	
	
	public void initRoulette() {
		
		int rIndex = 0;
		for(i=0;i<pSize;i++) {
			rouletteSize+=fitness[i];
		}
		rouletteWheel = new int[rouletteSize];
		
		for(i=0;i<pSize;i++) {
			for(j=0;j<fitness[i];j++) {
			
				rouletteWheel[rIndex] = i;
				rIndex++;
			}
		}
		
		/*System.out.println(rouletteSize);
		for(i=0;i<rouletteSize;i++) {
			
				System.out.print(rouletteWheel[i]+" ");
	
		}*/
	}	
	public int selection() {
	
		//System.out.println(rouletteWheel[rand.nextInt(rouletteSize)]);
		return rouletteWheel[rand.nextInt(rouletteSize)];
		
	}
	
	
	public List crossOverSingle(int chr1,int chr2) {
		
		int temp = 0;
		int []child1 = new int[6];
		int []child2 = new int[6];
		for(i=0;i<3;i++) {
			child1[i] = getGene(getChromosome(chr1), i);
			child2[i] = getGene(getChromosome(chr2), i);
		}
		
		for(i=3;i<6;i++) {
			child2[i] = getGene(getChromosome(chr1), i);
			child1[i] = getGene(getChromosome(chr2), i);
		}
		
		
		children.add(child1);
		children.add(child2);
		
		//Mutations at 10% chance
		
		mutation(child1);
	    mutation(child2);
		
		
	return children;
	}
	
	
	public List crossOverMulti(int chr1,int chr2) {
		
		
		
		
		
		return children;
	}
	
	private void mutation(int[] child) {
		
		
		if(rand.nextInt(10)<1) {
			
			i = rand.nextInt(6);	
			child[i] = (child[i] + 1) % 2;
		
		}
	}
	
	 
	public void addToPop(List<int[]> children, String solution) {
		
		for (int[] child : children) {
			
			int minIndex = 0;
			int minFitness = Integer.MAX_VALUE;
			for(i=0;i<pSize;i++) {
				
				if((minFitness > fitness[i]) && selected[i] != 1) {
					
					minFitness = fitness[i];
					minIndex = i;
				}
				
				Population[minIndex] = child;
				selected[minIndex] = 1;
				fitness[minIndex] = 0;
				for(j=0;j<6;j++) {
					
					String str = ""+ solution.charAt(j);
					if(getGene( getChromosome(minIndex) ,j) == Integer.parseInt(str) ) {
						
						//System.out.println(getGene( getChromosome(i) ,j));
						fitness[minIndex] += 1;
					}
				}
					
			}
				
		}	
	}		
			
			
	
	
	public int getSelected(int i) {
		
		return selected[i];
	}
	
	public void setSelected(int i,int val) {
	
		selected[i] = val;
	}
	
	public int getPopulation() {
		
		return pSize;
	}
	
	public int[] getChromosome(int Cno) {
		
		return Population[Cno];
		
	}
	
	public int getGene(int []Chromosome,int Gno) {
		
		return Chromosome[Gno];
		
	}
	public void setGene(int []chromosome, int index, int val) {
		
		chromosome[index] = val;
	
		
	}
}