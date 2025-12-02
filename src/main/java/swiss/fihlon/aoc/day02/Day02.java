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

package swiss.fihlon.aoc.day02;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import swiss.fihlon.aoc.Tools;

import java.io.IOException;
import java.util.Arrays;

public class Day02 {

    /**
     * Logger for logging the solution.
     */
    private static final @NotNull Logger LOGGER = LoggerFactory.getLogger(Day02.class);

    /**
     * Solves the puzzle by validating the given ranges and returning the summary of invalid IDs.
     *
     * @param ranges the array of ranges to validate
     * @return the summary of invalid IDs across all ranges
     */
    public long solvePuzzle(final @NotNull String[] ranges) {
        final var validator = new Validator();
        return Arrays.stream(ranges)
                .parallel()
                .mapToLong(validator::checkRange)
                .sum();
    }

    /**
     * Main method to run the solution.
     *
     * @throws IOException if there is an error reading the input file
     */
    static void main() throws IOException {
        final var puzzleInput = Tools.readResourceLines("/day02.txt");
        final var day2 = new Day02();
        final var summary = day2.solvePuzzle(puzzleInput[0].split(","));
        LOGGER.info("The summary is: {}", summary);
    }
}
