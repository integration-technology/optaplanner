/*
 * Copyright 2013 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.optaplanner.examples.golftour.domain;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import org.optaplanner.core.api.domain.entity.PlanningEntity;
import org.optaplanner.core.api.domain.variable.PlanningVariable;
import org.optaplanner.examples.common.domain.AbstractPersistable;
import org.optaplanner.examples.tennis.domain.solver.MovableTeamAssignmentSelectionFilter;

import java.util.ArrayList;

@PlanningEntity(movableEntitySelectionFilter = MovableTeamAssignmentSelectionFilter.class)
@XStreamAlias("GolfTourMatchAssignment")
public class MatchAssignment extends AbstractPersistable {

    private Round round;
    private int indexInRound;
    private boolean locked;

    // planning variable
    private ArrayList<Pair> pairArrayList;

    public MatchAssignment() {
    }

    public MatchAssignment(long id, Round round, int indexInRound) {
        super(id);
        this.round = round;
        this.indexInRound = indexInRound;
    }

    public Round getRound() {
        return round;
    }

    public void setRound(Round round) {
        this.round = round;
    }

    public int getIndexInRound() {
        return indexInRound;
    }

    public void setIndexInRound(int indexInRound) {
        this.indexInRound = indexInRound;
    }

  /**
     * @return true if immovable planning entity
     * @see MovableTeamAssignmentSelectionFilter
     */

    public boolean isLocked() {
        return locked;
    }
    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    @PlanningVariable(valueRangeProviderRefs = {"pairRange"})
    public ArrayList<Pair> getPairArrayList() {
      return pairArrayList;
    }

    public void setPairArrayList(ArrayList<Pair> pairArrayList) {
      this.pairArrayList = pairArrayList;
    }

}
