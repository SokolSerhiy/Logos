package lv.lgs.hibernate.controller;

import lv.lgs.hibernate.daoImpl.BookDaoImpl;

public class Main {

	public static void main(String[] args) {
		BookDaoImpl b = new BookDaoImpl();
		b.closeSessionFactory();
	}
}
