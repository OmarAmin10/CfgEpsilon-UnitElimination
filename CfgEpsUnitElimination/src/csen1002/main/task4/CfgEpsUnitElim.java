package csen1002.main.task4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Write your info here
 * 
 * @name Omar Amin
 * @id 46-1014
 * @labNumber 23
 */

public class CfgEpsUnitElim {
	 String cfg;
	  ArrayList<ContextFree> cf=new ArrayList<ContextFree>();
	   ArrayList<ContextFree> cf1=new ArrayList<ContextFree>();
	   List<String> symbols = new ArrayList<String>();
	   List<String> states = new ArrayList<String>();
	/**
	 * Constructs a Context Free Grammar
	 * 
	 * @param cfg A formatted string representation of the CFG. The string
	 *            representation follows the one in the task description
	 */
	public CfgEpsUnitElim(String cfg) {
		
		this.cfg=cfg;
		
       List<String> list = new ArrayList<String>(Arrays.asList(this.cfg.split("#")));
		
		states = Arrays.asList(list.get(0).split(";"));
		
		 symbols =Arrays.asList(list.get(1).split(";"));
      
		List<String> transitions = new ArrayList<String>(Arrays.asList(list.get(2).split(";")));
				 
		for(int i =0;i<states.size();i++) {
			ArrayList<String> c =new ArrayList<String>(Arrays.asList(transitions.get(i).split("/")));
			ArrayList<String> c1 =new ArrayList<String>(Arrays.asList(c.get(1).split(",")));
			this.cf.add(new ContextFree(c1,states.get(i),false ,false));
		}
		
		for(int i=0;i<cf.size();i++) {
			if(cf.get(i).states.contains("e")) {	
				this.cf.get(i).setEpsilon(true);
			}
	     }
		
		
	}

	/**
	 * @return Returns a formatted string representation of the CFG. The string
	 *         representation follows the one in the task description
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
       String s="";
	 	
		for(int i=0;i<cf.size();i++) {
			s+=cf.get(i).id+";";
		}
		 s = s.substring(0, s.length() - 1);
		 s=s+"#";
		 for(int i=0;i<symbols.size();i++) {
				s+=symbols.get(i)+";";
			}
		 s = s.substring(0, s.length() - 1);
		 s=s+"#";
		 
		 for(int i=0;i<cf.size();i++) {
				s+=cf.get(i).id+"/";
				ArrayList<String> f=new ArrayList<String>();
				for(int j=0;j<cf.get(i).states.size();j++) {
					f.add(cf.get(i).states.get(j));
				}
				Collections.sort(f);
				for(int k=0;k<f.size();k++) {
					s+=f.get(k)+",";
				}
				 s = s.substring(0, s.length() - 1);
				 s+=";";
			}
		 s = s.substring(0, s.length() - 1);
		return s;
	}

	/**
	 * Eliminates Epsilon Rules from the grammar
	 */
	public  void eliminateEpsilonRules() {
		
		
	     while(!helper()) {
		for(int j=0;j<cf.size();j++) {
			String s="";
			if(cf.get(j).epsilon && !cf.get(j).epsilonRemoved) {
				int EpsilonIndex=cf.get(j).states.indexOf("e");
				cf.get(j).states.remove(EpsilonIndex);
				cf.get(j).setEpsilonRemoved(true);
				s= cf.get(j).getId();
				for(int k=0;k<cf.size();k++) {
					
					
					for(int i=0;i<cf.get(k).states.size();i++) {
						if( cf.get(k).states.get(i).length()==1 && cf.get(k).states.get(i).equals(s) ) {
							
							if(cf.get(k).epsilon== false) {
							cf.get(k).addState("e");
							cf.get(k).setEpsilon(true);
							}
						}
						if(cf.get(k).states.get(i).contains(s) && !(cf.get(k).states.get(i).length()==1) ) {
							String test = cf.get(k).states.get(i);
							int count2=0;
						
							String s1="";
							String s2="";
							String s3=test.replaceAll(s, "");
							 cf.get(k).addState(s3);
							
								for(int z=0;z<test.length();z++) {
									if(test.charAt(z)==s.charAt(0) && count2==0) {
							             s1= test.replaceFirst(s, "");
							             cf.get(k).addState(s1);
							            
										count2++;
									}
									else {
										
									if(test.charAt(z)==s.charAt(0) && count2>0 ) {										
										  s2 = test.substring(0, z) + ""
									              + test.substring(z + 1);
										  cf.get(k).addState(s2);
										 
										 break;
									}
								}
							
								}
						}
					}
				}
					
					
				}
			}
			
	} 		
		}
	

	/**
	 * Eliminates Unit Rules from the grammar
	 */
	public  boolean helper() {
		boolean b = true;
		
		for(int i =0;i<cf.size();i++) {
			for(int j=0;j<cf.get(i).states.size();j++) {
				if(cf.get(i).states.get(j).equals("e")) {
					b=false;
					break;
				}
			}
		}
		
		return b;
		
	}
	
	public  boolean helper2() {
		boolean b = true;
		
		for(int i =0;i<cf.size();i++) {
			for(int j=0;j<cf.get(i).states.size();j++) {
				if(states.contains(cf.get(i).states.get(j))) {
					b=false;
					break;
				}
			}
		}
		
		return b;
		
	}
	
	public  void eliminateUnitRules() {
		
        
//         System.out.println(this.toString());
		for(int i=0;i<cf.size();i++) {
			for(int j=0;j<cf.get(i).states.size();j++) {
				if(cf.get(i).id.equals(cf.get(i).states.get(j))) {
					cf.get(i).states.remove(j);
					
				}
			}
			
		}
		
		while(!helper2()){
		  HashMap<String, ArrayList<String>> visited = new HashMap<>();
		for(int i =0;i<cf.size();i++) {
			
			ArrayList<String> c =new ArrayList<String>();
			ArrayList<String> visited1 =new ArrayList<String>();
		
			for(int j=0;j<cf.get(i).states.size();j++) {
				if(states.contains(cf.get(i).states.get(j))) {
					String s=cf.get(i).states.get(j);
					
					visited1.add(s);
					visited.put(cf.get(i).id, visited1);
					
				for(int k=0;k<cf.size();k++) {
				
					if(cf.get(k).id.equals(s)) {
						
					for(int z=0;z<cf.get(k).states.size();z++) {

						if(!(cf.get(k).states.get(z).equals(cf.get(i).id))){
						c.add(cf.get(k).states.get(z));
					}

					}
				//	break;

					}
				}
				cf.get(i).states.remove(s);
				j--;
				
				}
				else {
					visited.put(cf.get(i).id, visited1);
				}
			}
			for(int t=0;t<c.size();t++) {
				
			cf.get(i).addState(c.get(t));
				
		}
			
			for(int m=0;m<cf.get(i).states.size();m++ ) {
				if(visited.get(cf.get(i).id).contains(cf.get(i).states.get(m))) {
					cf.get(i).states.remove(m);
				}
				else if((cf.get(i).states.get(m)).equals(cf.get(i).id)) {
					cf.get(i).states.remove(m);
				}
			
			}
			
			
//			for(int m=0;m<cf.get(i).states.size();m++ ) {
//			
//			 if(states.contains(cf.get(i).states.get(m))){
//					i--;
//					break;
//				}
//			}
//			
			
//			if(cf.get(i).states.size()!= number) {
//				i--;
//			}
//			System.out.println(c);
		}

		
		
		}
	}

//	public static void main(String[] args) {
//		CfgEpsUnitElim c = new CfgEpsUnitElim("S;A;B#a;b#S/ASA,aB;A/B,S;B/b,e");
//		System.out.println(c.toString());
//		c.eliminateEpsilonRules();
//		System.out.println(c.toString());
//		c.eliminateUnitRules();
//		System.out.println(c.toString());
//	}
}

class ContextFree {

	ArrayList<String> states;
	String id;
	boolean epsilon;
	boolean epsilonRemoved;
	
	
  public ContextFree(ArrayList<String> states,String id , boolean epsilon,boolean epsilonRemoved ) {
	   this.states=states;   
	   this.id=id;
	   this.epsilon=epsilon;
	   this.epsilonRemoved=epsilonRemoved;
   }


public boolean isEpsilonRemoved() {
	return epsilonRemoved;
}


public void setEpsilonRemoved(boolean epsilonRemoved) {
	this.epsilonRemoved = epsilonRemoved;
}


public ArrayList<String> getStates() {
	return states;
}


public void setStates(ArrayList<String> states) {
	this.states = states;
}

public void addState(String e) {
	if(!(this.states.contains(e))) {
		this.states.add(e);
	}
	
}


public String getId() {
	return id;
}


public void setId(String id) {
	this.id = id;
}


public boolean isEpsilon() {
	return epsilon;
}


public void setEpsilon(boolean epsilon) {
	this.epsilon = epsilon;
}
	
}

