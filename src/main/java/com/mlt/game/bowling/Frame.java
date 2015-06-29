package com.mlt.game.bowling;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;

/**
 * Just like real world balling game, Frame here holds the pins to be hit by the
 * balls thrown and controls the number of turns. It decides the iteration based
 * on number of pins hit and balls allowed. It also tracks the score based on
 * pins hit.
 * 
 * @author mgoswami
 *
 */
public class Frame {

	private List<Ball> balls = new ArrayList<>();
	private boolean isExhausted = false;
	private int totalPins;
	protected static Logger logger = Logger.getLogger(Frame.class);

	public Frame(int totalBalls, int totalPins) {
		int i = 0;
		while (i < totalBalls) {
			balls.add(new Ball());
			i++;
		}
		this.totalPins = totalPins;
	}

	public int getScore() {
		Iterator<Ball> it = balls.iterator();
		int score = 0;
		while (it.hasNext()) {
			score += it.next().getScore();
		}
		return score;
	}

	public void initiateFrame() {

	}

	public boolean isExhausted() {
		return isExhausted;
	}

	public void setExhausted(boolean isExhausted) {
		this.isExhausted = isExhausted;
	}

	/**
	 * this simulates the setting up of pins and player's throwing the ball
	 */
	public void setPinsAndHit() {
		logger.info("Frame set...");
		for (Ball ball : balls) {
			if (ball.isExhausted()) {
				continue;
			}
			logger.info("throwing ball now....");

			int totalhit = new Random().nextInt(totalPins + 1);
			ball.setTotalPinHits(totalhit);

			/*
			 * if total pins hit is equal to total pins in the frame then no
			 * more throws are required
			 */
			if (totalhit == totalPins) {
				logger.info("Hit all the pins..need not throw next ball");

				Iterator<Ball> it = balls.iterator();
				while (it.hasNext()) {
					it.next().setExhausted(true);
				}
				return;
			}
			totalPins -= totalhit; // those hit is being taken off
			ball.setExhausted(true);
		}
	}
}
