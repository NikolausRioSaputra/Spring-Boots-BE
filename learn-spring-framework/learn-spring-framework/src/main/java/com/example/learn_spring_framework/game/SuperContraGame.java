package com.example.learn_spring_framework.game;

public class SuperContraGame implements GamingConsole {
	@Override
	public void up() {
		System.out.println("jump");
	}

	@Override
	public void down() {
		System.out.println("sit down");
	}

	@Override
	public void left() {
		System.out.println("go back");
	}

	@Override
	public void right() {
		System.out.println("shot");
	}
}
