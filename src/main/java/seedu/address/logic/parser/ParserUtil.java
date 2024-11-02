package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_OVERFLOW_INDEX;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.group.Group;
import seedu.address.model.person.Email;
import seedu.address.model.person.Major;
import seedu.address.model.person.Name;
import seedu.address.model.person.StudentId;
import seedu.address.model.person.Year;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Error: Index is not a single non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();

        if (StringUtil.verifyNotNumber(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        if (!StringUtil.verifyNotIntOverflow(trimmedIndex)) {
            throw new ParseException(MESSAGE_OVERFLOW_INDEX);
        }
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String studentId} into a {@code StudentId}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code studentId} is invalid.
     */
    public static StudentId parseStudentId(String studentId) throws ParseException {
        requireNonNull(studentId);
        String trimmedStudentId = studentId.trim();
        if (!StudentId.isValidStudentId(trimmedStudentId)) {
            throw new ParseException(StudentId.MESSAGE_CONSTRAINTS);
        }
        return new StudentId(trimmedStudentId);
    }

    /**
     * Parses a {@code String major} into a {@code Major}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code major} is invalid.
     */
    public static Major parseMajor(String major) throws ParseException {
        requireNonNull(major);
        String trimmedMajor = major.trim();
        if (!Major.isValidMajor(trimmedMajor)) {
            throw new ParseException(Major.MESSAGE_CONSTRAINTS);
        }
        return Major.makeMajor(trimmedMajor);
    }

    /**
     * Parses a {@code String major} into a {@code Major} but allows empty major to be supplied.
     *
     * @throws ParseException if the given {@code major} is invalid or not empty.
     */
    public static Major parseOptionalMajor(String major) throws ParseException {
        requireNonNull(major);
        String trimmedMajor = major.trim();

        if (trimmedMajor.isEmpty()) {
            return Major.makeMajor(trimmedMajor);
        }

        return parseMajor(trimmedMajor);
    }

    /**
     * Parses a {@code String netId} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code netId} is invalid.
     */
    public static Email parseNetId(String netId) throws ParseException {
        requireNonNull(netId);
        String trimmedNetId = netId.trim();

        if (!Email.isValidNetId(trimmedNetId)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }

        return Email.makeEmail(trimmedNetId + Email.DOMAIN);
    }

    /**
     * Parses a {@code String netId} into an {@code Email} but allows empty netid to be supplied.
     *
     * @throws ParseException if the given {@code netId} is invalid or not empty.
     */
    public static Email parseOptionalNetId(String netId) throws ParseException {
        requireNonNull(netId);
        String trimmedNetId = netId.trim();

        if (trimmedNetId.isEmpty()) {
            return Email.makeEmail(trimmedNetId);
        }

        return parseNetId(trimmedNetId);
    }

    /**
     * Parses a {@code String year} into a {@code Year}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code year} is invalid.
     */
    public static Year parseYear(String year) throws ParseException {
        requireNonNull(year);
        String trimmedYear = year.trim();
        if (!Year.isValidYear(trimmedYear)) {
            throw new ParseException(Year.MESSAGE_CONSTRAINTS);
        }
        return Year.makeYear(trimmedYear);
    }

    /**
     * Parses a {@code String year} into a {@code Year} but allows empty year to be supplied.
     *
     * @throws ParseException if the given {@code year} is invalid or not empty.
     */
    public static Year parseOptionalYear(String year) throws ParseException {
        requireNonNull(year);
        String trimmedYear = year.trim();

        if (trimmedYear.isEmpty()) {
            return Year.makeYear(trimmedYear);
        }

        return parseYear(trimmedYear);
    }

    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Group parseOptionalGroup(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (trimmedTag.isEmpty()) {
            return new Group();
        }
        if (!Group.isValidGroupName(trimmedTag)) {
            throw new ParseException(Group.MESSAGE_CONSTRAINTS);
        }
        return new Group(trimmedTag);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Group> parseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<Group> groupSet = new HashSet<>();
        for (String tagName : tags) {
            groupSet.add(parseOptionalGroup(tagName));
        }
        return groupSet;
    }
}
