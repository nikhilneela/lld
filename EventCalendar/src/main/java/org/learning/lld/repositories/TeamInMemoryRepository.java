package org.learning.lld.repositories;

import lombok.NonNull;
import org.learning.lld.exceptions.NoSuchUserException;
import org.learning.lld.models.Team;
import org.learning.lld.models.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TeamInMemoryRepository implements ITeamRepository {
    private final Map<String, Team> teams;

    public TeamInMemoryRepository() {
        this.teams = new HashMap<>();
    }

    @Override
    public void createTeam(@NonNull Team team) {
        if (!teams.containsKey(team.getId())) {
            teams.put(team.getId(), team);
        }
    }

    @Override
    public Team getTeam(@NonNull String id) {
        if (!teams.containsKey(id)) {
            throw new NoSuchUserException();
        }
        return teams.get(id);
    }


}
