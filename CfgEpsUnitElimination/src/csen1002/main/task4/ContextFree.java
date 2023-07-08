//package csen1002.main.task4;
//
//import java.util.ArrayList;
//
//public class ContextFree {
//
//	ArrayList<String> states;
//	String id;
//	boolean epsilon;
//	boolean epsilonRemoved;
//	
//	
//  public ContextFree(ArrayList<String> states,String id , boolean epsilon,boolean epsilonRemoved ) {
//	   this.states=states;   
//	   this.id=id;
//	   this.epsilon=epsilon;
//	   this.epsilonRemoved=epsilonRemoved;
//   }
//
//
//public boolean isEpsilonRemoved() {
//	return epsilonRemoved;
//}
//
//
//public void setEpsilonRemoved(boolean epsilonRemoved) {
//	this.epsilonRemoved = epsilonRemoved;
//}
//
//
//public ArrayList<String> getStates() {
//	return states;
//}
//
//
//public void setStates(ArrayList<String> states) {
//	this.states = states;
//}
//
//public void addState(String e) {
//	if(!(this.states.contains(e))) {
//		this.states.add(e);
//	}
//	
//}
//
//
//public String getId() {
//	return id;
//}
//
//
//public void setId(String id) {
//	this.id = id;
//}
//
//
//public boolean isEpsilon() {
//	return epsilon;
//}
//
//
//public void setEpsilon(boolean epsilon) {
//	this.epsilon = epsilon;
//}
//	
//}
