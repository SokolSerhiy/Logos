package ua.classes;

import ua.interf.Ballada;
//Одна з реалізацій балади
public class HeroBallada implements Ballada{

	public void song() {
		System.out.println("I need a hero\n"
				+ "I'm holding out for a hero till the end of the night\n"
				+ "he's gotta be strong and he's gotta be fast\n"
				+ "and gotta be fresh from the fight\n"
				+ "I need a hero\n"
				+ "I'm holding out for a hero till the morning light\n"
				+ "He's gotta be sure and he's gotta be soon\n"
				+ "And he's gotta be larger than life\n"
				+ "Larger than life");
	}
}