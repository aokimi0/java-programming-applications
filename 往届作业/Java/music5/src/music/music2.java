package music;
class Stringed extends Instrument{
	public void play(Note n){
		System.out.println("Stringed.play()"+n);
	}
}
class Brass extends Instrument{
	public void play(Note n){
		System.out.println("Brass.play()"+n);
	}
}
public class music2 {
	public static void tune(Wind i){
		i.play(Note.MIDDLE_C);
	}
	public static void tune(Stringed i){
		i.play(Note.MIDDLE_C);
	}
	public static void tune(Brass i){
		i.play(Note.MIDDLE_C);
	}
	public static void main(String[] args){
		Wind flute=new Wind();
		Stringed voilin=new Stringed();
		Brass frenchHorn=new Brass();
		tune(flute);
		tune(voilin);
		tune(frenchHorn);
	}
}/* Output:
Wind.play() MIDDLE_C
Stringed.play() MIDDLE_C
Brass.play() MIDDLE_C
*/
