package view;

import java.util.Iterator;
import java.util.Scanner;

import board.*;
import control.*;
import mapper.*;
import util.*;

public class Viewer {
	
	public static boolean isFreshStart=true;

	public static void main(String[] args) {
		startup();
		
		while (true) {
			Scanner inputScanner=new Scanner(System.in);
			String iString =inputScanner.next();
			
			Object rst=EventDeliver.processCommand(iString);
			
			if(rst.getClass()==WinCheck.class) {
				WinCheck winner=(WinCheck)rst;
				if(winner.isWin) {
					EventDeliver.processExchangeCommand(ExchangeCommands.AfterWin, (Event)rst);
				}
			}
		}
	}

	public static void startup() {
		Controller.init();
		System.out.println("Welcome!");
		
	}

	public static void outputBoard() {
		//BoardBuffer tmp=Controller.getMap();
		for (int i = 1; i <= Limits.width; i++) {
			for (int j = 1; j <= Limits.width; j++) {
				Player p = Controller.check(new Position(i, j));
				
				if (p != null) {
					if (p.p == Conditions.player_wight) {
						System.out.print("。");
					} else if (p.p == Conditions.player_black) {
						System.out.print(". ");
					}else {
						System.out.print("+ ");
					}
				}else {
					System.out.print("+ ");
				}
			}
			System.out.println();
		}
	}

	public static void afterWin(Event e) {
		
		WinCheck winner=(WinCheck)e;
		
		switch (winner.winner) {
		case player_black:
			System.out.println("Black Wins!");
			break;
		case player_wight:
			System.out.println("White Wins!");
			break;
		default:
			return;
		}
		
		isFreshStart=false;
		
		System.out.println("Do you Want Start Again!?[y/n]");
		
		Scanner inputScanner=new Scanner(System.in);
		String iString =inputScanner.next();
		
		switch (iString) {
		case "y":
			startup();
			break;
		case "n":
		default:
			EventDeliver.processCommand("Quit");
			break;
		}
	}
}
