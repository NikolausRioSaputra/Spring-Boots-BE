package com.example.learn_spring_framework.game;

public class PackmanGame implements GamingConsole {
	@Override
	public void up() {
		System.out.println("atas");
	}

	@Override
	public void down() {
		System.out.println("bawah");
	}

	@Override
	public void left() {
		System.out.println("kiri");
	}

	@Override
	public void right() {
		System.out.println("kanan");
	}

}
