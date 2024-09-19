package org.learning.lld.services;

import lombok.NonNull;
import org.learning.lld.models.Team;
import org.learning.lld.models.User;
import org.learning.lld.repositories.ITeamRepository;

import java.util.List;
import java.util.UUID;

public class TeamService {
    private final ITeamRepository teamRepository;
    public TeamService(@NonNull final ITeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public Team createTeam(@NonNull final String name, @NonNull final List<User> users) {
        //validate how many users can be in a team
        Team team = new Team(UUID.randomUUID().toString(), name, users);
        teamRepository.createTeam(team);
        return team;
    }

    public Team getTeam(@NonNull final String teamId) {
        return this.teamRepository.getTeam(teamId);
    }
}
