package org.learning.lld.repositories;

import lombok.NonNull;
import org.learning.lld.models.Team;
import org.learning.lld.models.User;

import java.util.List;

public interface ITeamRepository {
    void createTeam(@NonNull final Team team);
    Team getTeam(@NonNull final String id);
}
