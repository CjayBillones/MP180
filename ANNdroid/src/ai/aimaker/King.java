package ANNdroid.src.ai.aimaker;

import ANNdroid.src.ai.ann.*;
import java.util.*;

/**
*	<h1> King AI Maker </h1>
*	<br>
*	<b>Note:</b><br> 
*	Bracket values and correspinding accuracies are as follows:
*	<ul>
*		<li>0 = [0,0.1)</li>
*		<li>1 = [0.1,0.2)</li>
*		<li>2 = [0.2,0.3)</li>
*		<li>3 = [0.3,0.4)</li>
*		<li>4 = [0.4,0.5)</li>
*		<li>5 = [0.5,0.6)</li>
*		<li>6 = [0.6,0.7)</li>
*		<li>7 = [0.7,0.8)</li>
*		<li>8 = [0.8,0.9)</li>
*		<li>9 = [0.9,1.0]</li>
*	</ul>
*	<br><br>
*	Attribute value is the bracket difference between the player and the King
*	<br>
*	For example the player accuracy bracket for a certain Domain and Difficulty is 6
*	and the King attribute for the same Domain and Difficulty is 3
*	the King's accuracy bracket will then be 9
*/

public class King implements java.io.Serializable{

	public enum Domain {
		BIOLOGY,PHYSICS,CHEMISTRY
	}

	public enum Difficulty {
		EASY,INTERMEDIATE,HARD
	}

	public String name;

	private Map<Domain,Map<Difficulty,Integer>> attributes;
	private Network brain;
	private static final int size = Difficulty.values().length * 10;

	public King(String name, Map<Domain,Map<Difficulty,Integer>> attr){

		int layers[] = {3,8,10};

		this.name = name;
		this.attributes = attr;
		this.brain = new Network(layers);

	}

	/**
	*	Adjusts the <code>King</code> attribute value on the given <code>Domain</code>
	*	and <code>Diffuculty</code>
	*	@param domain 	Domain of the attribute
	*	@param diff 	Difficulty of the attribute
	*	@param adjst 	How many steps up or down the new value will be relative to the
	*					current value
	*/

	public void adjust_attr(Domain domain, Difficulty diff, int adjst){

		int cur_val = this.attributes.get(domain).get(diff);
		int new_val = (cur_val + adjst < 10)?cur_val + adjst:9;
		new_val = (new_val >= -9)?new_val:-9;
		this.attributes.get(domain).put(diff,new_val);

	}

	/**
	*	Fetches the test inputs for the given domain
	*	@param domain 	<code>Domain</code> to where to base the inputs
	*	@return Test inputs for the given <code>Domain</code>
	*/
	private double[][] fetch_thy_inputs(Domain domain){
		double[][] ret = new double[size][3];

		for(Difficulty d: Difficulty.values())
			for(int i = 0; i < 10; i++){
				int index = d.ordinal() * 10 + i;
				ret[index][0] = domain.ordinal();
				ret[index][1] = d.ordinal();
				ret[index][2] = i;
			}

		return ret;
	}

	/**
	*	Fetches the target outputs for the given domain
	*	@param domain 	<code>Domain</code> to where to base the ouputs
	*	@return Target outputs for the given <code>Domain</code>
	*/
	private double[][] fetch_thy_outputs(Domain domain){
		double[][] ret = new double[size][10];
		
		for(Difficulty d: Difficulty.values())
			for(int i = 0; i < 10; i++){
				int index = d.ordinal() * 10 + i;
				int bracket = get_king_bracket(domain,d,i);
				ret[index][bracket] = 1;  
			}

		return ret;
	}

	
	/**
	*	Gives the attribute of the <code>King</code> given the player bracket as a Map
	*	@param domain 		<code>Domain</code> of the attribute to be fetched
	*	@param p_bracket	Player's bracket
	*	@return <code>King</code> attribute on the <code>Domain</code> in each <code>Difficulty</code>
	*			relative to player bracket 
	*/
	public Map<Difficulty, Integer> get_attributes_on(Domain domain, int p_bracket){
		Map<Difficulty, Integer> attr = new HashMap<Difficulty, Integer>();
		
		for(Difficulty d: Difficulty.values()){
			int bracket = get_king_bracket(domain,d,p_bracket);
			attr.put(d,bracket);
		}
				
		return attr;	
	}


	/**
	*	Computes the <code>King</code> bracket on the <code>Domain</code>
	*	and <code>Difficulty</code>
	*	@param domain 		<code>Domain</code> to which the <code>King</code> will be bracketed
	*	@param diff 		<code>Difficulty</code> to which the <code>King</code> will be bracketed
	*	@param p_bracket	Player's bracket
	*	@return <code>King</code> bracket on <code>Domain</code> and <code>Difficulty</code>
	*			relative to player bracket 
	*/

	public int get_king_bracket(Domain domain, Difficulty diff, int p_bracket){
		int difference = this.attributes.get(domain).get(diff);
		int bracket = (p_bracket + difference < 10)?p_bracket + difference: 9;
		bracket = (bracket >= 0)?bracket:0;

		return bracket;
	}


	/**
	*	Trains the King on the given <code>Domain</code>
	*	@param domain 	<code>Domain</code> to where the King shall be trained
	*/
	public void train(Domain domain){

		double[][] inputs = fetch_thy_inputs(domain);
		double[][] outputs = fetch_thy_outputs(domain);

		this.brain.train(inputs,outputs,2000,0.4,0.6);
	
	}

	public void train(){
		for(Domain domain: Domain.values()){
			train(domain);
		}

	}
}