/*
 * Advent of Code 2025
 * Copyright (C) Marcus Fihlon
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package swiss.fihlon.aoc.day01;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import swiss.fihlon.aoc.Tools;

import java.io.IOException;

/**
 * Solution for Day 1 of Advent of Code 2025.
 */
public final class Day01 {

    /**
     * Logger for logging the solution.
     */
    private static final @NotNull Logger LOGGER = LoggerFactory.getLogger(Day01.class);

    /**
     * The starting position of the dial.
     */
    private static final int START_POSITION = 50;

    /**
     * Solves the puzzle by processing the given rotations and returning the final password.
     *
     * @param rotations the array of rotation commands
     * @return the final password after applying all rotations
     */
    public int solvePuzzle(final @NotNull String[] rotations) {
        final var dial = new Dial(START_POSITION);
        for (final var rotation : rotations) {
            dial.turn(rotation);
        }
        return dial.getPassword();
    }

    /**
     * Main method to run the solution.
     *
     * @throws IOException if there is an error reading the input file
     */
    static void main() throws IOException {
        final var puzzleInput = Tools.readResourceLines("/day1.txt");
        final var day01 = new Day01();
        final var password = day01.solvePuzzle(puzzleInput);
        LOGGER.info("The password is: {}", password);
    }
}
