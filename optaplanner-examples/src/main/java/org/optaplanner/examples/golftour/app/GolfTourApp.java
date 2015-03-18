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

package org.optaplanner.examples.golftour.app;

import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;
import org.optaplanner.examples.common.app.CommonApp;
import org.optaplanner.examples.common.persistence.SolutionDao;
import org.optaplanner.examples.common.swingui.SolutionPanel;
import org.optaplanner.examples.golftour.swingui.GolfTourPanel;
import org.optaplanner.examples.golftour.persistence.GolfTourDao;

public class GolfTourApp extends CommonApp {

    public static final String SOLVER_CONFIG
            = "org/optaplanner/examples/golftour/solver/golfTourSolverConfig.xml";

    public static void main(String[] args) {
        prepareSwingEnvironment();
        new GolfTourApp().init();
    }

    public GolfTourApp() {
        super("Golf Tour Match Scheduling",
                "Assign players into pairs and into two matches per round.\n\n" +
                        "eight players.\n" +
                        "four rounds so eight matches.\n" +
                        "four ball better ball.\n" +
                        "players can't be paired together more than one.\n" +
                        "no two players can play in every match together.\n" +
                        "each player can only be paired with the same player once.\n" +
                        "oppose each player at least once.",
                GolfTourPanel.LOGO_PATH);
    }

    @Override
    protected Solver createSolver() {
        SolverFactory solverFactory = SolverFactory.createFromXmlResource(SOLVER_CONFIG);
        return solverFactory.buildSolver();
    }

    @Override
    protected SolutionPanel createSolutionPanel() {
        return new GolfTourPanel();
    }

    @Override
    protected SolutionDao createSolutionDao() {
        return new GolfTourDao();
    }

}
