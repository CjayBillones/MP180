package ANNdroid.src.ai.aimaker;
import java.io.*;
import java.util.*;
import ANNdroid.src.ai.aimaker.King.*;

/**
* Serializes or loads <code>Kings</code>  <br>
* <em>Long live the <code>King</code> </em>
*/

public class KingSL{



	/**
	*	Loads the <code>King</code> from a file
	*	@param name		File name of the <code>King</code>
	*	@return	The <code>King</code>
	*/
	public static King load(String name){
		King ret = null;
		try{
			FileInputStream file =  new FileInputStream("ANNdroid/bin/"+name+".king");
			ObjectInputStream in = new ObjectInputStream(file);

			ret = (King) in.readObject();
			in.close();
			file.close();

			System.out.println("King "+ ret.name + " has arrived");
		}catch(IOException e){
			e.printStackTrace();
		}catch(ClassNotFoundException c){
			c.printStackTrace();
		}
		return ret;
	}


	/**
	*	Saves the <code>King</code>
	*	@param king 	The <code>King</code> to be saved
	*/
	public static void save(King king){
		try{

			File kings_landing = new File("ANNdroid/bin");

			if(!kings_landing.exists()){
				try{
					kings_landing.mkdir();
				}catch(SecurityException e){
					e.printStackTrace();
				}
			}

			File king_file = new File("ANNdroid/bin/"+king.name+".bin");

			if(!king_file.exists())
				king_file.createNewFile();

			FileOutputStream file = new FileOutputStream(king_file);
			ObjectOutputStream out = new ObjectOutputStream(file);

			out.writeObject(king);
			out.close();
			file.close();

			System.out.println("Long Live King " + king.name +  "!");
		}catch(IOException e){
			e.printStackTrace();
		}
	}


	public static King generate(File file){
		String name = "Default Name";
		Map<Domain,Map<Difficulty, Integer>> ret = new HashMap<Domain,Map<Difficulty, Integer>>();

		for(Domain d: Domain.values()){
			Map<Difficulty,Integer> map = new HashMap<Difficulty,Integer>();
			ret.put(d,map);
		}

		try{
			Scanner s = new Scanner(file);
			while(s.hasNext()){
				String str = s.nextLine();
				String[] split =  str.split(":");
				switch(split[0]){

					case "NAME":{
						name = split[2];
					}break;

					case "BIOLOGY":{
						Map<Difficulty,Integer> map = ret.get(Domain.BIOLOGY); 
						switch(split[1]){
							case "EASY":{map.put(Difficulty.EASY,Integer.parseInt(split[2]));}break;
							case "INTERMEDIATE":{map.put(Difficulty.INTERMEDIATE,Integer.parseInt(split[2]));}break;
							case "HARD":{map.put(Difficulty.HARD,Integer.parseInt(split[2]));}break;
						}
					}
					case "CHEMISTRY":{
						Map<Difficulty,Integer> map = ret.get(Domain.CHEMISTRY); 
						switch(split[1]){
							case "EASY":{map.put(Difficulty.EASY,Integer.parseInt(split[2]));}break;
							case "INTERMEDIATE":{map.put(Difficulty.INTERMEDIATE,Integer.parseInt(split[2]));}break;
							case "HARD":{map.put(Difficulty.HARD,Integer.parseInt(split[2]));}break;
						}
					}
					case "PHYSICS":{
						Map<Difficulty,Integer> map = ret.get(Domain.PHYSICS); 
						switch(split[1]){
							case "EASY":{map.put(Difficulty.EASY,Integer.parseInt(split[2]));}break;
							case "INTERMEDIATE":{map.put(Difficulty.INTERMEDIATE,Integer.parseInt(split[2]));}break;
							case "HARD":{map.put(Difficulty.HARD,Integer.parseInt(split[2]));}break;
						}
					}

				}

			}
		}catch(FileNotFoundException f){
			System.out.println("File Not FOund");
		}
		King king = new King(name,ret);
		return king;
	}


}