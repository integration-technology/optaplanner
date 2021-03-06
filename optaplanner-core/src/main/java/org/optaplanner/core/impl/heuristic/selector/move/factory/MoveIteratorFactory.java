/*
 * Copyright 2012 JBoss Inc
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

package org.optaplanner.core.impl.heuristic.selector.move.factory;

import java.util.Iterator;
import java.util.Random;

import org.optaplanner.core.config.heuristic.selector.common.SelectionCacheType;
import org.optaplanner.core.config.solver.EnvironmentMode;
import org.optaplanner.core.impl.heuristic.move.Move;
import org.optaplanner.core.impl.score.director.ScoreDirector;

/**
 * An interface to generate an {@link Iterator} of custom {@link Move}s.
 * <p/>
 * For a more simple version, see {@link MoveListFactory}.
 */
public interface MoveIteratorFactory {

    /**
     * @param scoreDirector never null, the {@link ScoreDirector}
     * which has the {@link ScoreDirector#getWorkingSolution()} of which the {@link Move}s need to be generated
     * @return the approximate number of elements generated by {@link #createOriginalMoveIterator(ScoreDirector)}
     * @throws UnsupportedOperationException if not supported
     */
    long getSize(ScoreDirector scoreDirector);

    /**
     * When it is called depends on the configured {@link SelectionCacheType}.
     * @param scoreDirector never null, the {@link ScoreDirector}
     * which has the {@link ScoreDirector#getWorkingSolution()} of which the {@link Move}s need to be generated
     * @return never null, an {@link Iterator} that will end sooner or later
     * @throws UnsupportedOperationException if only {@link #createRandomMoveIterator(ScoreDirector, Random)} is
     * supported
     */
    Iterator<Move> createOriginalMoveIterator(ScoreDirector scoreDirector);

    /**
     * When it is called depends on the configured {@link SelectionCacheType}.
     * @param scoreDirector never null, the {@link ScoreDirector}
     * which has the {@link ScoreDirector#getWorkingSolution()} of which the {@link Move}s need to be generated
     * @param workingRandom never null, the {@link Random} to use when any random number is needed,
     * so {@link EnvironmentMode#REPRODUCIBLE} works correctly
     * @return never null, an {@link Iterator} that is allowed (or even presumed) to be never ending
     * @throws UnsupportedOperationException if only {@link #createOriginalMoveIterator(ScoreDirector)} is supported
     */
    Iterator<Move> createRandomMoveIterator(ScoreDirector scoreDirector, Random workingRandom);

}
