package ANNdroid.src;

import java.util.*;
import java.io.*;

class StudentData implements Serializable {
	
	public Map<Student,Map<? extends Enum, Object>> studentData;

	public StudentData(){

		studentData = new HashMap<Student,Map<? extends Enum,Object>>();

	}

	public Map<Object,Integer> count(Enum catagory){

		Map<Object,Integer> counter = new HashMap< Object,Integer>();

		for(Map<? extends Enum,Object> map: studentData.values()){
			Object obj = map.get(catagory);
			if(counter.containsKey(obj)){
				counter.put(obj, counter.get(obj) + 1);
			}else{
				counter.put(obj, 1);
			}
		}

		return counter;
	}

	public Map<Object, Integer> countWith(Enum category, Object obj, Enum[] categories){
			
		Map<Object,Integer> counter = new HashMap< Object,Integer>();

		for(Map<? extends Enum,Object> map: studentData.values()){
			Object obj2 = map.get(category);
			if(obj.equals(obj2)){ 
				for(Enum e: categories){
					if(e != category){
						Object o = map.get(e);

						if(counter.containsKey(o)){
							counter.put(o, counter.get(o) + 1);
						}else{
							counter.put(o, 1);
						}
					}
				}
			}
		}

		return counter;

	}

	public double bayes(Enum category, Object a, Object[] b, Enum[] categories){

		double a_occur = (double)((count(category).get(a) == null)?0:count(category).get(a));
		double total = (double)studentData.size();
		double[] b_occur = new double[b.length];

		Map<Object,Integer> b_count = countWith(category,a,categories);


		for(int i = 0; i < b.length; i++){
			b_occur[i] = (double)((b_count.get(b[i]) == null)?0:b_count.get(b[i]));

		}

		double ret = (a_occur/total);

		for(int i = 0; i < b.length; i++){
			ret *= (b_occur[i]/a_occur);

		}

		return ret;

	}

	public Object  classify(Enum category, Object[] b, Enum[] categories){

		Map<Object,Integer> counter = count(category);
		Object[] a = new Object[counter.keySet().size()];

		int i = 0;
		for(Object o: counter.keySet()){

			a[i] = o;
			i++;
		}		

		double[] probs = new double[a.length];

		for(int j = 0; j < a.length; j++){
			probs[j] = bayes(category, a[j], b, categories);

		}

		int index = 0;

		for(int j = 1; j < a.length; j++){
			if(probs[index] < probs[j])
				index = j;
		}

		return a[index];

	}

	public void put(Student key, Map<? extends Enum, Object> value){
		studentData.put(key,value);
	}

	public void update(Student key, Enum e, Object value){
		
		Map<Enum, Object> map = new HashMap<Enum, Object>(studentData.get(key));
		map.put(e,value);


	}

}