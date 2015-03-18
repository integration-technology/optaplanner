package org.optaplanner.examples.golftour.domain;

/**
 * Created by owain on 17/03/15.
 *
 */

import com.thoughtworks.xstream.annotations.XStreamAlias;
import org.optaplanner.examples.common.domain.AbstractPersistable;
import org.optaplanner.examples.common.swingui.components.Labeled;

import java.util.ArrayList;


@XStreamAlias("GolfTourPair")
public class Pair extends AbstractPersistable implements Labeled {

  private String name;
  private ArrayList<Player> pair;

  public Pair() {
  }

  public Pair(long id, String name, ArrayList<Player> pair) {
    super(id);
    this.name = name;
    this.pair = pair;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getLabel() {
    return name;
  }

  public void setPair(ArrayList<Player> pair) {
    this.pair = pair;
  }

  public ArrayList<Player> getPair() {return pair; }

}
