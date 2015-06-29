package com.mlt.game.bowling;

import java.util.UUID;

import org.apache.log4j.Logger;

/**
 * Abstracts a ball's behaviour and holds pins hit and uses those to compute
 * score
 * 
 * @author mgoswami
 *
 */
public class Ball {
	private int totalPinHits;
	private String ballId;
	private boolean isExhausted = false;
	protected static Logger logger = Logger.getLogger(Ball.class);

	public Ball() {
		ballId = UUID.randomUUID().toString();
	}

	public void setTotalPinHits(int count) {
		logger.info(String.format(" total pins hit are: %d", count));
		totalPinHits = count;
		setExhausted(true);
	}

	public int getTotalPinHit() {
		return totalPinHits;
	}

	public int getScore() {
		int score = totalPinHits * 2;
		totalPinHits = 0;
		return score;
	}

	public String getBallId() {
		return ballId;
	}

	public boolean isExhausted() {
		return isExhausted;
	}

	public void setExhausted(boolean isExhausted) {
		this.isExhausted = isExhausted;
	}
}
// 42
