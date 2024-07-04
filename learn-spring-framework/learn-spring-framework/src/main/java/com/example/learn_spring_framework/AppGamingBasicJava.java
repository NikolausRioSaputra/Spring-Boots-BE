package com.example.learn_spring_framework;

import com.example.learn_spring_framework.game.GameRunner;
import com.example.learn_spring_framework.game.MarioGame;
import com.example.learn_spring_framework.game.PackmanGame;
import com.example.learn_spring_framework.game.SuperContraGame;

public class AppGamingBasicJava {

	public static void main(String[] args) {
//		var game = new MarioGame();
		var game = new PackmanGame();
//		var game = new SuperContraGame();
		var gameRunner = new GameRunner(game);
		gameRunner.run();
	}

}
