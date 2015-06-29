package com.mlt.game.bowling;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;

/**
 * This class abstracts a player behavior. It allows configuring total frames,
 * balls per frame and pins per frame etc. It computes total scores based on
 * number of frames configured. It also allows simulating a game for a player.
 * 
 * @author mgoswami
 *
 */
public class Player implements Comparable<Player> {

	private List<Frame> frames = new ArrayList<>();
	private String name;
	private String playerId;
	private int score;
	protected static Logger logger = Logger.getLogger(Player.class);

	public Player() {
		playerId = UUID.randomUUID().toString();
		score = 0;
	}

	public void configureFrame(int totalFrames, int ballsPerFrame,
			int pinsPerFrame) {
		int i = 0;
		while (i < totalFrames) {
			frames.add(new Frame(ballsPerFrame, pinsPerFrame));
			i++;
		}
	}

	public int getScore() {
		Iterator<Frame> it = frames.iterator();
		if(score != 0){
			return score;
		}
		while (it.hasNext()) {
			score += it.next().getScore();
		}
		logger.info(String.format("Total scored by %s is %d", this.name, score));
		return score;
	}

	public int compareTo(Player p) {
		if (this.playerId == p.getPlayerId()) {
			return 0;
		}
		int thisScore = this.getScore();
		int playerScore = p.getScore();

		if (thisScore > playerScore) {
			return 1;
		}
		if (thisScore == playerScore) {
			return 0;
		}

		return -1;
	}

	public String getPlayerId() {
		return playerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void play() {
		for (Frame frame : frames) {
			if (frame.isExhausted()) {
				continue;
			}
			frame.setPinsAndHit();
			frame.setExhausted(true);
		}
	}

}
