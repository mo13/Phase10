import java.util.*;

public class Card {

	 final private int number; // invariant numbers between 1 and 12
	 final private cardColor color; // look for colors
	 final private type type; // look up enumerators
	
	static  enum cardColor {
		Red,Blue,Green,Yellow,Black
	}
	
	static enum type {
		Normal,Wild,Skip
	}

	public Card(int number, cardColor color, type type) {
		if (type == type.Normal){
			if (number <= 12 & number >= 1){
				this.number = number;
				this.color = color;
				this.type = type;
			} else
				throw new IllegalArgumentException("Number out of range.");
		} else if (type == type.Wild & number == 0){
			this.number = 0;
			this.color= color;
			this.type = type;
			
		} else {
			this.number = 0;
			this.color = color;
			this.type = type;
		}
	
}
	public int getNumber() {
		return this.number;
	}
	public cardColor getColor() {
		return this.color;
	}
	public type getType() {
		return this.type;
	}
	public String toString(){
		StringBuilder sb = new StringBuilder();
//		String output = "";
//		output+= this.number + " " + this.color + " " + this.type;
		sb.append(this.number);
		sb.append(" ");
		sb.append(this.color);
		sb.append(" ");
		sb.append(this.type);
		return sb.toString();
	}
}

