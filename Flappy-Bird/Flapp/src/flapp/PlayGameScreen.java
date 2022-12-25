/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package flapp;

import javax.swing.*;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Color;

public class PlayGameScreen extends JPanel {
	
	//global variables
        private final int screenWidth;
	private final int screenHeight;
	private boolean isStarted = false;
	private int successfulJumps = 0;
	private String message = "Flappy Bird";
	private final Font primaryFont = new Font("Goudy Stout", Font.BOLD, 56);
	private int messageWidth = 0, scoreWidth = 0;
	private BottomPipe bp1, bp2;
	private TopPipe tp1, tp2;
	private Bird bird;

	/**
	 * Default constructor for the PlayGameScreen class
         * @param screenWidth
         * @param screenHeight
         * @param isStarted
	 */
	public PlayGameScreen(int screenWidth, int screenHeight, boolean isStarted) {
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
		this.isStarted = isStarted;
	}
	
	/**
	 * Manually control what's drawn on this JPanel by calling the paintComponent method
	 * with a graphics object and painting using that object
	 */
        @Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(new Color(89, 81, 247)); //color for the blue sky
		g.fillRect(0, 0, screenWidth, screenHeight*7/8); //create the sky rectangle
		g.setColor(new Color(147, 136, 9)); //brown color for ground
		g.fillRect(0, screenHeight*7/8, screenWidth, screenHeight/8); //create the ground rectangle
		g.setColor(Color.BLACK); //dividing line color
		g.drawLine(0, screenHeight*7/8, screenWidth, screenHeight*7/8); //draw the dividing line
		
                g.setFont(primaryFont);
		FontMetrics metric = g.getFontMetrics(primaryFont);
		messageWidth = metric.stringWidth(message);
		scoreWidth = metric.stringWidth(successfulJumps+" ");
                

		//objects must be instantiated before they're drawn!
		if(bp1 != null && bp2 != null && tp1 != null && tp2 != null) {
			g.drawImage(bp1.getPipe(), bp1.getX(), bp1.getY(), null);
			g.drawImage(bp2.getPipe(), bp2.getX(), bp2.getY(), null);
			g.drawImage(tp1.getPipe(), tp1.getX(), tp1.getY(), null);
			g.drawImage(tp2.getPipe(), tp2.getX(), tp2.getY(), null);
		}
		if(isStarted && bird != null) {
			g.drawImage(bird.getBird(), bird.getX(), bird.getY(), null);
		}
		if(isStarted) {
			g.drawString(successfulJumps+" ", screenWidth/2-scoreWidth/2, 50);
		}	
                
                 g.drawString(message, screenWidth/2-messageWidth/2, screenHeight/4);
		
	}
	
	/**
	 * Parsing method for PlayGameScreen's global BottomPipe variables
	 * @param bp1 The first BottomPipe
	 * @param bp2 The second BottomPipe
	 */
	public void setBottomPipe(BottomPipe bp1, BottomPipe bp2) {
		this.bp1 = bp1;
		this.bp2 = bp2;
	}
	
	/**
	 * Parsing method for PlayGameScreen's global TopPipe variables
	 * @param tp1 The first TopPipe
	 * @param tp2 The second TopPipe
	 */
	public void setTopPipe(TopPipe tp1, TopPipe tp2) {
		this.tp1 = tp1;
		this.tp2 = tp2;
	}
	
	/**
	 * Parsing method for PlayGameScreen's global Bird variable
	 * @param bird The Bird object
	 */
	public void setBird(Bird bird) {
		this.bird = bird;
	}
	
	/**
	 * Method called to invoke an increase in the variable tracking the current
	 * jump score
	 */
	public void incrementJump() {
		successfulJumps++;
	}
	
	/**
	 * Method called to return the current jump score
	 * @return
	 */
	public int getScore() {
		return successfulJumps;
	}
	
	/**
	 * Method called to parse a message onto the screen
	 * @param message0 The message to parse
	 */
	public void sendText(String message) {
		this.message = message;
	}
}