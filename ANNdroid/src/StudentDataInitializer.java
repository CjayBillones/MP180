package ANNdroid.src;

import ANNdroid.src.objects.*;
import java.util.*;

class StudentDataInitializer{
	
	public static void main(String args[]){

		Simulator sim = new Simulator();
		StudentData sd = new StudentData();

		List<Student> studentList = new LinkedList<Student>();

		for(User user: sim.userList){

			if(user instanceof Student)
				studentList.add((Student)user);

		}


		for(Student student: studentList){

			Map<Categories, Object> studentInfo = new HashMap<Categories, Object>();
			sd.put(student,studentInfo);
			
		}

		SDataSL.save(sd);


	}

}