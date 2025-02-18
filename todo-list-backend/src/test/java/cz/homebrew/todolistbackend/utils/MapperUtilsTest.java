package cz.homebrew.todolistbackend.utils;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MapperUtilsTest {
    @Test
    void testMapList() {
        List<Person> srcList;
        List<String> resultList;

        // null, empty srcList or srcList with null elements or null mapper
        record InvalidInputCase(List<Person> srcList,
                                Function<Person, String> mapper,
                                String caseDescription) {
        }

        final InvalidInputCase[] invalidInputCaseArray = {
                new InvalidInputCase(null, null, "both params null"),
                new InvalidInputCase(null, Person::getLastName, "srcList null"),
                new InvalidInputCase(List.of(), null, "srcList empty and mapper null"),
                new InvalidInputCase(List.of(), Person::getLastName, "srcList empty"),
                new InvalidInputCase(
                        List.of(
                                new Person()
                                        .setFirstName("Jon")
                                        .setLastName("Snow"),
                                new Person()
                                        .setFirstName("Daenerys")
                                        .setLastName("Targaryen")

                        ),
                        null,
                        "mapper null"
                ),
                new InvalidInputCase(
                        Arrays.asList(null, null),
                        Person::getLastName,
                        "srcList with null elements"
                )
        };
        for (InvalidInputCase invalidInputCase : invalidInputCaseArray) {
            // when
            resultList = MapperUtils.mapList(invalidInputCase.srcList, invalidInputCase.mapper);
            // then
            assertNotNull(resultList);
            assertEquals(
                    0,
                    resultList.size(),
                    String.format("Should return empty list when %s", invalidInputCase.caseDescription)
            );
        }

        // valid input
        srcList = Arrays.asList(
                new Person()
                        .setFirstName("Jon")
                        .setLastName("Snow"),
                null,
                new Person()
                        .setFirstName("Daenerys")
                        .setLastName("Targaryen")

        );
        // when
        resultList = MapperUtils.mapList(srcList, Person::getLastName);
        // then
        assertNotNull(resultList);
        assertEquals(2, resultList.size());
        assertEquals("Snow", resultList.getFirst());
        assertEquals("Targaryen", resultList.get(1));
    }


    // Inner class
    @Getter
    @Setter
    @Accessors(chain = true)
    static class Person {
        private String number;
        private String firstName;
        private String lastName;
    }
}
