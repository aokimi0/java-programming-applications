package music5;

import music.Note;


interface Playable{
	void play(Note n);
}

interface Instrument extends Playable{
	int VALUE=5;
	void adjust();
}
class Wind implements Instrument {
	  public void play(Note n) {
	    System.out.println(this + ".play() " + n);
	  }
	  public String toString() { return "Wind"; }
	  public void adjust() { 
		  System.out.println(this + ".adjust()"); 
	  }
}

class Percussion implements Instrument {
	  public void play(Note n) {
		  System.out.println(this + ".play() " + n);
	  }
	  public String toString() {
		  return "Percussion"; 
	  }
	  public void adjust() { 
		  System.out.println(this + ".adjust()"); 
	  }
}
	  

class Stringed implements Instrument {
	  public void play(Note n) {
		  System.out.println(this + ".play() " + n);
	  }
	  public String toString() {
		  return "Stringed"; 
	  }
	  public void adjust() { 
		  System.out.println(this + ".adjust()"); 
	  }
}

class Brass extends Wind {
	  public String toString() {
		  return "Brass"; 
	  }
}

class Woodwind extends Wind {
	  public String toString() {
		  return "Woodwind"; 
	  }
}
	
public class music5 {
	static void tune(Playable i){
		i.play(Note.MIDDLE_C);
	}
	static void tuneAll(Instrument[] e) {
	    for(Instrument i : e)
	      tune(i);
	  }	
	public static void main(String[] args) {

	    Instrument[] orchestra = {
	      new Wind(),
	      new Percussion(),
	      new Stringed(),
	      new Brass(),
	      new Woodwind()
	    };
	    tuneAll(orchestra);
	}
}
