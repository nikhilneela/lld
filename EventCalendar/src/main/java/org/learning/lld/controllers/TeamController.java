package org.learning.lld.controllers;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.learning.lld.models.User;
import org.learning.lld.services.TeamService;
import org.learning.lld.services.UserService;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class TeamController {
    private final TeamService teamService;
    private final UserService userService;

    public String createTeam(@NonNull final String teamName, List<String> userIds) {
        List<User> users = userIds.stream().map(this.userService::getUser).toList();
        return this.teamService.createTeam(teamName, users).getId();
    }
}
