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

import java.util.stream.LongStream;

/**
 * Validator for checking IDs within given ranges.
 */
public class Validator {

    /**
     * Logger for logging validation results.
     */
    private static final @NotNull Logger LOGGER = LoggerFactory.getLogger(Validator.class);

    /**
     * Constructs a Validator.
     */
    public Validator() {
        super();
    }

    /**
     * Checks the IDs within the given range string and returns the summary of invalid IDs.
     *
     * @param range the range string in the format "from-to"
     * @return the summary of invalid IDs within the range
     */
    public long checkRange(final @NotNull String range) {
        final var rangeSplit = range.trim().split("-");
        final var from = Long.parseLong(rangeSplit[0]);
        final var to = Long.parseLong(rangeSplit[1]);
        return checkRange(from, to);
    }

    /**
     * Checks the IDs within the given range and returns the summary of invalid IDs.
     *
     * @param from the starting ID of the range
     * @param to   the ending ID of the range
     * @return the summary of invalid IDs within the range
     */
    private long checkRange(final long from, final long to) {
        return LongStream.range(from, to + 1)
                .parallel()
                .filter(this::checkId)
                .sum();
    }

    /**
     * Checks if the given ID is invalid.
     *
     * @param id the ID to check
     * @return true if the ID is invalid, false otherwise
     */
    private boolean checkId(final long id) {
        if (checkId(Long.toString(id))) {
            LOGGER.info("The id {} is invalid", id);
            return true;
        }
        return false;
    }

    /**
     * Checks if the given ID string is invalid.
     *
     * @param id the ID string to check
     * @return true if the ID string is invalid, false otherwise
     */
    private boolean checkId(final @NotNull String id) {
        final var idLength = id.length();
        final var mid = idLength / 2;
        for (int i = 1; i <= mid; i++) {
            if (idLength % i != 0) {
                continue;
            }
            final var part = id.substring(0, i);
            final var value = part.repeat(idLength / part.length() + 1).substring(0, idLength);
            if (value.equals(id)) {
                return true;
            }
        }
        return false;
    }

}
