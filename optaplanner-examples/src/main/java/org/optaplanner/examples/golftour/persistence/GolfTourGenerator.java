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

package org.optaplanner.examples.golftour.persistence;

import java.io.File;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.optaplanner.examples.common.app.LoggingMain;
import org.optaplanner.examples.common.persistence.AbstractSolutionImporter;
import org.optaplanner.examples.common.persistence.SolutionDao;
import org.optaplanner.examples.golftour.domain.GolfTourSolution;
import org.optaplanner.examples.golftour.domain.Round;
import org.optaplanner.examples.golftour.domain.Player;
import org.optaplanner.examples.golftour.domain.Pair;
import org.optaplanner.examples.golftour.domain.MatchAssignment;
import org.optaplanner.examples.golftour.persistence.GolfTourDao;

public class GolfTourGenerator extends LoggingMain {

    public static void main(String[] args) {
        new GolfTourGenerator().generate();
    }

    protected final SolutionDao solutionDao;
    protected final File outputDir;

    public GolfTourGenerator() {
        solutionDao = new GolfTourDao();
        outputDir = new File(solutionDao.getDataDir(), "unsolved");
    }

    public void generate() {
        String inputId = "golftour-four-rounds-eight-players";
        File outputFile = new File(outputDir, inputId + ".xml");
        GolfTourSolution golfTourSolution = createGolfTourSolution(inputId);
        solutionDao.writeSolution(golfTourSolution, outputFile);
    }

    private GolfTourSolution createGolfTourSolution(String inputId) {
        GolfTourSolution golfTourSolution = new GolfTourSolution();
        golfTourSolution.setId(0L);

        int ASCII_A = 97                    ;
        List<Player> playerList = new ArrayList<Player>();
        for (int i = 0; i < 8; i++) {
          playerList.add(new Player(i, Character.toString((char) (ASCII_A + i))));
        }

      long id = 0L;
      ArrayList<Pair> pairList = new ArrayList<Pair>();
      for (int leftIndex = 0; leftIndex < (playerList.size() - 2); leftIndex++) {
        Player leftPlayer = playerList.get(leftIndex);
        for (int rightIndex = leftIndex + 1; rightIndex < playerList.size() - 1; rightIndex++) {
          Player rightPlayer = playerList.get(rightIndex);
          ArrayList<Player> aPair = new ArrayList<Player>(2);
          aPair.add(leftPlayer);
          aPair.add(rightPlayer);
          String aPairName = new String(leftPlayer.getName() + " " + rightPlayer.getName());
          pairList.add(new Pair(id, aPairName, aPair));
          id++;
        }
      }

      golfTourSolution.setPairList(pairList);

      List<Round> roundList = new ArrayList<Round>();
      for (int i = 0; i < 4; i++) {
          roundList.add(new Round(i, i));
        }
      golfTourSolution.setRoundList(roundList);

      List<MatchAssignment> matchAssignmentList = new ArrayList<MatchAssignment>();
        for (Round round : roundList) {
            for (int i = 0; i < 2; i++) {
                matchAssignmentList.add(new MatchAssignment(id, round, i));
                id++;
            }
        }
        golfTourSolution.setMatchAssignmentList(matchAssignmentList);


        BigInteger possibleSolutionSize = BigInteger.valueOf(playerList.size()).pow(
                matchAssignmentList.size());
        logger.info("Golf Tour {} has {} players, {} rounds and {} matchAssignments"
                + " with a search space of {}.",
                inputId, playerList.size(), roundList.size(), matchAssignmentList.size(),
                AbstractSolutionImporter.getFlooredPossibleSolutionSize(possibleSolutionSize));
        return golfTourSolution;
    }

}
