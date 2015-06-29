package com.mlt.game.bowling;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

/**
 * This class acts as the interface for the bowling game. It allows setting
 * players, configuring the game, by deciding on number of frames, balls per
 * frame etc. It finally allows simulating a game and allow querying a winner.
 * 
 * 
 * @author mgoswami
 *
 */
public class Bowling {
	private int totalNumberOfFrames = 5;
	private int numberOfBallsPerFrame = 2;
	private int numberOfPinsPerFrame = 10;
	private boolean isGameOver = false;
	private List<Player> allPlayers = new ArrayList<>();
	private boolean isPlayerSet = false;
	private Game currentGame;
	protected static Logger logger = Logger.getLogger(Bowling.class);

	public void configure(int numberOfFrames, int numberOfBallPerFrame,
			int numberOfPinPerFrame) {
		totalNumberOfFrames = numberOfFrames;
		numberOfBallsPerFrame = numberOfBallPerFrame;
		numberOfPinsPerFrame = numberOfPinPerFrame;
		logger.info("configured succesfully");
	}

	public void registerPlayer(List<String> players) {
		BasicConfigurator.configure();
		currentGame = new Game();
		for (String name : players) {
			logger.info("registering now : " + name);
			Player p = new Player();
			p.setName(name);
			p.configureFrame(totalNumberOfFrames, numberOfBallsPerFrame,
					numberOfPinsPerFrame);
			allPlayers.add(p);
		}
		currentGame.registerPlayers(allPlayers);
		isPlayerSet = true;
		logger.info("all players registered succesfully");
	}

	public Player getWinner() {
		if (!isGameOver) {
			throw new RuntimeException(
					"Game not over yet. Start the game first.");
		}
		Collections.sort(allPlayers);
		Player winner = allPlayers.get(allPlayers.size() - 1);
		resetGame();
		return winner;
	}

	public void startGame() {
		logger.info("starting the game...please hold tight...");
		if (!isPlayerSet) {
			throw new RuntimeException(
					"Players needs to be registered before starting the game");
		}
		currentGame.startTheGame();
		isGameOver = true;
	}

	public void resetGame() {
		allPlayers = new ArrayList<>();
	}
	
	public List<Player> getPlayers(){
		return currentGame.getAllPlayers();
	}
}
