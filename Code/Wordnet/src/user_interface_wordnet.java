import java.util.ArrayList;
import java.util.Scanner;


public class user_interface_wordnet {
	public static void main(String arg[])
	{
		Scanner input = new Scanner(System.in);
		
		
		WordNet wordnet_helper = new WordNet("/synsets.txt","/hypernyms.txt");
		SAP sap_helper = new SAP(wordnet_helper.getAlVerticesList(), wordnet_helper.getHmSynsets());
		
		
		
		System.out.println("Enter a choice 1. find common ancestor & distance between nouns 2. Find common ancestor between 2 synset IDs 3. Print first 100 nouns -1. Quit");
		System.out.println("4. Find if a word is a noun defined in wordnet 5. Find the noun of common ancestor between 2 nouns");
		System.out.println("6. List all the edges 7. Find semantic relatedness between 2 nouns");
		
		int input_choice = input.nextInt();
		if(	input_choice == 1 )
		{
			System.out.println("Enter noun A");
			String wordA = input.next();
			System.out.println("Enter noun B");
			String wordB = input.next();
			System.out.println("Distance = "+wordnet_helper.distance(wordA, wordB));
			
		}
		else if( input_choice == 2 )
		{
			System.out.println("Enter synset ID of noun A");
			int nounA = input.nextInt();
			System.out.println("Enter synset ID of noun B");
			int nounB = input.nextInt();
			System.out.println("Ancestor:"+sap_helper.ancestor(nounA, nounB));
			System.out.println("Length:"+sap_helper.length(nounA, nounB));			
			
		}
		else if( input_choice == 3 )
		{
			Iterable<String> itr_noun = wordnet_helper.nouns();
			int iLoop=0;
			for (String strNoun : itr_noun) {
		        System.out.println(strNoun);
		        iLoop++;
		        if(iLoop==100)break;
		        
		    }
		}
		else if( input_choice == 4) 
		{
			System.out.println("Enter the word to be checked for noun/not noun");
			String nounA = input.next();
			System.out.println("Is "+nounA+" a noun in synset: "+wordnet_helper.isNoun(nounA));
		}
		else if(	input_choice == 5 )
		{
			System.out.println("Enter noun A");
			String wordA = input.next();
			System.out.println("Enter noun B");
			String wordB = input.next();
			System.out.println("Noun in common ancestor = "+wordnet_helper.sap(wordA, wordB));
			
		}
		else if(input_choice == 6)
		{
			ArrayList<Edge> edges_list = wordnet_helper.getEdgeList();
			for(int iLoop=0;iLoop<edges_list.size();iLoop++)
			{
				System.out.println(edges_list.get(iLoop).getSource()+"-->"+edges_list.get(iLoop).getDestination());
			}
		}
		else if(input_choice == 7)
		{
			System.out.println("Enter noun A");
			String wordA = input.next();
			System.out.println("Enter noun B");
			String wordB = input.next();
			System.out.println("Ancestor = "+wordnet_helper.sap(wordA, wordB));
			System.out.println("Distance = "+wordnet_helper.distance(wordA, wordB));

		}
		
	}

}
