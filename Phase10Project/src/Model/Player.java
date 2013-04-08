package Model;

import java.util.*;

import Strategy.Strategy;

public class Player {

  public String name;

  public Strategy strategy;

  public  Collection<Card> hand;

  public int phaseNumber;

  public int score;

  public  PhaseCollections<Card> currPhase;

    public Vector  myStrategy;
    public Vector  myHand;

  public void drawCard() {
  }

  public void discardCard() {
  }

}