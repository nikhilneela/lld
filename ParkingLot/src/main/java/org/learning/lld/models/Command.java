package org.learning.lld.models;

import lombok.Getter;
import lombok.NonNull;
import org.learning.lld.exceptions.InvalidCommandException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Command {
    @Getter
    private final String commandName;
    @Getter
    private final List<String> arguments;
    private final String SEPARATOR = " ";

    public Command(@NonNull final String inputLine) {
        Stream<String> streamOfTokens = Arrays.stream(inputLine.split(SEPARATOR));
        List<String> tokens = streamOfTokens.map(String::trim).filter(t -> !t.isEmpty()).collect(Collectors.toList());

        if (tokens.isEmpty()) {
            throw new InvalidCommandException();
        }

        commandName = tokens.get(0).toLowerCase();
        tokens.remove(0);
        arguments = tokens;
    }
}
