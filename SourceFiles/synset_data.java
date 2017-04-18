
public class synset_data<T>  {
	
	int synset_id;
	String noun[];
	String synonym;
	
	//default constructor
	public synset_data()
	{
		noun=new String[20];
	}

	public int getSynset_id() {
		return synset_id;
	}

	public void setSynset_id(int synset_id) {
		this.synset_id = synset_id;
	}

	public String[] getNoun() {
		return noun;
	}

	public void setNoun(String[] noun) {
		this.noun = noun;
	}

	public String getSynonym() {
		return synonym;
	}

	public void setSynonym(String synonym) {
		this.synonym = synonym;
	}
	
	
	
}
