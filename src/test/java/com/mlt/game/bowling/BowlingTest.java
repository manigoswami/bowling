package com.mlt.game.bowling;

import java.util.Arrays;
import java.util.List;

import junit.framework.Assert;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

public class BowlingTest {

	private String[] players = { "Mani", "John" };
	protected static Logger logger = Logger.getLogger(BowlingTest.class);

	public void configure() {
		throw new RuntimeException("Test not implemented");
	}

	@Test
	public void getWinner() {
		Bowling game = new Bowling();
		game.configure(5, 2, 10);
		game.registerPlayer(Arrays.asList(players));
		List<Player> players = game.getPlayers();
		game.startGame();
		Player p = game.getWinner();

		Player player1 = players.get(0);
		Player player2 = players.get(1);
		Player winner = player2;
		if (player1.getScore() > player2.getScore()) {
			winner = player1;
		}
		String nameExpected = winner.getName();

		logger.info(String.format("winner is : %s", p.getName()));
		Assert.assertEquals(nameExpected, p.getName());
	}

}
