package ANNdroid.src;

import ANNdroid.src.objects.*;
import java.util.*;

class StudentDataInitializer{
	
	public static void main(String args[]){

		Simulator sim = new Simulator();
		StudentData sd = new StudentData();

		SDataSL.save(sd);


	}

}