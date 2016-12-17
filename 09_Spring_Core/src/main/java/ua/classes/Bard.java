package ua.classes;

import ua.interf.Ballada;
//Звичайний клас
public class Bard {
	//звичайне стрічкове поле
	private String name;
	//звичайне поле яке являється інтерфейсом
	//зверніть увагу, жодно натяку на те хто
	//і як імплементує цей інтефейс
	private Ballada ballada;
	//метод який використовує метод балади
	//звичайна річ для будь-яких додатків
	public void singing(){
		System.out.print(name+": ");
		ballada.song();
	}
	//звичайний метод
	public void born(){
		System.out.print(name+": ");
		System.out.println("I was born");
	}
	//ще один звичайний метод
	public void death(){
		System.out.print(name+": ");
		System.out.println("I died");
	}
	//геттери і сеттери
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Ballada getBallada() {
		return ballada;
	}

	public void setBallada(Ballada ballada) {
		this.ballada = ballada;
	}
}
