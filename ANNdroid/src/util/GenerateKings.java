package ANNdroid.src.util;

import ANNdroid.src.ai.aimaker.*;
import ANNdroid.src.*;

import java.util.LinkedList;
import java.io.File;

public class GenerateKings{

	public static void main(String args[]){

		LinkedList<King> kings = new LinkedList<King>();

		File bioFile = new File("ANNdroid/bin/Bio.txt");
		File phyFile = new File("ANNdroid/bin/Physics.txt");
		File cheFile = new File("ANNdroid/bin/Chemistry.txt");

		King bio = KingSL.generate(bioFile);
		King phy = KingSL.generate(phyFile);
		King chem = KingSL.generate(cheFile);

		bio.train();
		phy.train();
		chem.train();

		kings.add(bio);
		kings.add(chem);
		kings.add(phy);

		Saver s = new Saver();

		s.saveKings(kings,"ANNdroid/bin/kings.bin");

	}

}