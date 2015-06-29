package com.mlt.game.bowling;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * Like a real world game, it holds together players, and allows computing
 * score.
 * 
 * @author mgoswami
 *
 */
public class Game {
	private List<Player> players;
	protected static Logger logger = Logger.getLogger(Game.class);

	public int getTotalScore(Player player) {
		Iterator<Player> it = players.iterator();
		while (it.hasNext()) {
			Player p = it.next();
			if (player.compareTo(it.next()) == 0) {
				return p.getScore();
			}
		}
		throw new RuntimeException("Player not found");
	}

	public void registerPlayers(List<Player> players) {
		this.players = players;
	}

	public void startTheGame() {
		;
		for (Player player : players) {
			logger.info(String.format("playing now: %s", player.getName()));
			player.play();
		}
	}
	
	public List<Player> getAllPlayers(){
		return players;
	}
}
