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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty;
import org.optaplanner.core.api.domain.solution.PlanningSolution;
import org.optaplanner.core.api.domain.solution.Solution;
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider;
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.examples.common.domain.AbstractPersistable;

@PlanningSolution
@XStreamAlias("GolfTourSolution")
public class GolfTourSolution extends AbstractPersistable implements Solution<HardSoftScore> {

    private List<Pair> pairList;
    private List<Round> roundList;

    private List<MatchAssignment> matchAssignmentList;

    private HardSoftScore score;

    @ValueRangeProvider(id = "pairRange")
    public List<Pair> getPairList() {
        return pairList;
    }

    public void setPairList(List<Pair> pairList) {
        this.pairList = pairList;
    }

    public List<Round> getRoundList() {
        return roundList;
    }

    public void setRoundList(List<Round> roundList) {
        this.roundList = roundList;
    }



    @PlanningEntityCollectionProperty
    public List<MatchAssignment> getMatchAssignmentList() {
        return matchAssignmentList;
    }

    public void setMatchAssignmentList(List<MatchAssignment> matchAssignmentList) {
        this.matchAssignmentList = matchAssignmentList;
    }

    public HardSoftScore getScore() {
        return score;
    }

    public void setScore(HardSoftScore score) {
        this.score = score;
    }

    @Override
    public Collection<?> getProblemFacts() {
        List<Object> facts = new ArrayList<Object>();
        facts.addAll(pairList);
        facts.addAll(roundList);
        // Do not add the planning entity's (matchAssignmentList) because that will be done automatically
        return facts;
    }

}
