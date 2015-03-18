package org.optaplanner.examples.golftour.domain;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import org.optaplanner.examples.common.domain.AbstractPersistable;

@XStreamAlias("GolfRound")
public class Round extends AbstractPersistable {

  private int roundIndex;

  public Round() {
  }

  public Round(long id, int roundIndex) {
    super(id);
    this.roundIndex = roundIndex;
  }

  public int getRoundIndex() {
    return roundIndex;
  }

  public void setRoundIndex(int roundIndex) {
    this.roundIndex = roundIndex;
  }

  public String getLabel() {
    return "round " + roundIndex;
  }

}
